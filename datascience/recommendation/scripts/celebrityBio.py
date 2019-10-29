import os
import requests
from pprint import pprint
from datetime import datetime
from elasticsearch import Elasticsearch
import json
from flask import jsonify
from flask import Blueprint
import pickle

celebritybio_api = Blueprint('celebritybio_api', __name__)


ESPORT= os.environ.get('ES_73_PORT')
ESHOST= os.environ.get('ES_73_HOST')



class BioModel():
    def __init__(self):

      self._index='bios'
      self.esIndex = 'http://'+ESHOST+':'+ESPORT+'/'+self._index  
      self.es=Elasticsearch([{'host':ESHOST,'port':ESPORT}])
      self._doc= 'bio'
      self.headers={"Content-Type": "application/json"}


    def rebuildIndex(self):
        '''
        Create new index, and rebuild if one already exists
        '''

        request_body = {
          "mappings": {
            "properties": {
              "_actorID" : {
                "type" : "keyword"
              },  
              "celebrity" : {
                "type" : "keyword"
              },         
              "bio_vector": {
                "type": "dense_vector",
                "dims": 1024
              }

            }
          }
        }

        try:
            self.es.indices.delete(index = self._index)
        except:
            pass
        try:

            self.es.indices.create(index = self._index, body = request_body)
            return 'Index Rebuilt for : '+ self._index
        except Exception as e:
            
            return 'Failed to upload to ftp: '+ str(e)
        

    def loadModel(self):
        '''
        Load index files if new index was built
        '''

        try:
          bio_dict = pickle.load( open( './models/bio_dict.pkl', 'rb' ) )
          xin = pickle.load( open( './models/elmoBioweights.pkl', 'rb' ) )
          celebrityLookup = pickle.load( open( './models/celebrityLookup.pkl', 'rb' ) )

          count=0
          ids = list(bio_dict.keys())
          for i, v in zip(ids, xin):
              _id = str(i)
              doc = {
                  #_actorID	actor	age	birthdate	gender	height
                  "_actorID" : i,
                  "celebrity" : celebrityLookup[_id],
                  "bio_vector": [float(x) for x in v], 
                  }
              r = requests.put(self.esIndex+'/_doc/'+_id, headers= self.headers, data = json.dumps(doc))

              count +=1
          return 'No of records loaded for Biographic Model : {}'.format( str(count) )

        except Exception as e:
            return 'Failed to load model Biographic '+ str(e)


    def getBiosVec( self, celebrity):
        '''
        For a given celebrity and genre, return their vector space based on the movies plots they have performed in.
        '''

        q={
          "query": { 
            "bool": { 
              "must": [
                { "match": { "celebrity":celebrity}}
              ]
            }
          }
        }
        res= self.es.search(index=self._index, body=json.dumps(q))
        if res['hits']['hits']:
            match = res['hits']['hits'][0]

            return match['_source']['bio_vector']
        else:
            return None
            

    def getSimilarBiosVec(self,  vec): 
        '''
        Based on the celebrity genre vector for a given genre, find similar celebrity in the same genre that performed in similary plots.
        '''
        q={
           "_source": {
                "includes": [ "_actorID", "celebrity"]
                },
          "query": {
            "script_score": {
              "query" : {
                "match_all" : {}
              },
              "script": {
                "source": "cosineSimilarity(params.query_vector, doc['bio_vector']) ", 
                "params": {
                  "query_vector": vec
                }
              }
            }
          }
        }


        res= self.es.search(index=self._index, body=json.dumps(q))

        if res['hits']['hits']:
            matches = res['hits']['hits']

            results=[]
            for match in matches:
                results.append( {   '_actorID': match['_source']['_actorID'], 'celebrity': match['_source']['celebrity'], 'score': match['_score']})
            return (results) 
        else:
            return None

    def recBioCelebrities(self, celebritySearch):
        '''
        Main search entrypoint, input a celebrity name and return the list of genre they performed and recommend alternative celebrities.
        '''
        results=[]

        bioVec = self.getBiosVec(celebritySearch)
        biosRecs = self.getSimilarBiosVec( bioVec)
        # Remove the search celebrirty from the recomendations
        results = results+ [i  for i in biosRecs if i['celebrity']!= celebritySearch ] 

        return results