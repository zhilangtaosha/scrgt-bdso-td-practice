import os
import requests
from pprint import pprint
from datetime import datetime
from elasticsearch import Elasticsearch
import json
from flask import jsonify
from flask import Blueprint
import pickle

celebrityGenreStats_api = Blueprint('celebrityGenreStats_api', __name__)

ESPORT= os.environ.get('ES_73_PORT')
ESHOST= os.environ.get('ES_73_HOST')


class GenreStatsModel():

    def __init__(self):

      self._index='genrestats'
      self._doc= 'genrestats'
      self.esIndex = 'http://'+ESHOST+':'+ESPORT+'/'+self._index  
      self.es=Elasticsearch([{'host':ESHOST,'port':ESPORT}])
      self.headers={"Content-Type": "application/json"}
      self.vector_size = 15

    def rebuildIndex(self):
        '''
        Build elasticsearch index to store the genre vectors
        '''

        request_body = {
          "mappings": {
            "properties": {
              "_actorID" : {
                "type" : "keyword"
              },     
              "genre_vector": {
                "type": "dense_vector",
                "dims": self.vector_size
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
            
            return 'Failed to create index : '+self._index+' :: '+ str(e)
        
    def loadModel(self):
        '''
        Load model into index
        '''

        try:
          genre_counts = pickle.load( open( './models/genreStats.pkl', 'rb' ) )

          count=0
          for i, d in genre_counts.items():
              _id = str(i)
              doc = {
                  "_actorID" : _id,
                  "genre_vector": d["vec"], 
                  "celebrity" : d["name"]
                  }
              r = requests.put(self.esIndex+'/_doc/'+_id, headers= self.headers, data = json.dumps(doc))

              count +=1
          return 'No of records loaded for Genre Stats Model : {}'.format( str(count) )

        except Exception as e:
            return 'Failed to load model Genre Stats '+ str(e)

    def getGenreVec(self, celebrity):
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

            return match['_source']['genre_vector']
        else:
            return None
            

    def getSimilarGenreVec(self, vec): 
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
                "source": "cosineSimilarity(params.query_vector, doc['genre_vector']) ", 
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

    def recGenreStatsCelebrities(self, celebritySearch):
        '''
        Main search entrypoint, input a celebrity name and return the list of genre they performed and recommend alternative celebrities.
        '''
        results=[]

        genre_vec = self.getGenreVec(celebritySearch)
        recomends = self.getSimilarGenreVec( genre_vec)
        # Remove the search celebrirty from the recomendations
        results = [i  for i in recomends if i['celebrity']!= celebritySearch ] 

        return results