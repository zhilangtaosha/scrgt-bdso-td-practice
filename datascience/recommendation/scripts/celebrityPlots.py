import os
import requests
from pprint import pprint
from datetime import datetime
from elasticsearch import Elasticsearch
import json
from flask import jsonify
from flask import Blueprint

import pickle


genresplot_api = Blueprint('genresplot_api', __name__)


ESPORT= os.environ.get('ES_73_PORT')
ESHOST= os.environ.get('ES_73_HOST')


class GenrePlotsModel():

    def __init__(self):

      self._index='genresplot'
      self._doc= 'genreplot'
      self.esIndex = 'http://'+ESHOST+':'+ESPORT+'/'+self._index  
      self.es=Elasticsearch([{'host':ESHOST,'port':ESPORT}])
      self.headers={"Content-Type": "application/json"}
      self.vector_size = 1024


    def rebuildIndex(self):
        '''
        Create Elasticsearch index, drop if it already exists.
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
            "genre" : {
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
        Load the vecotr data if a new index was created.
        '''

        try:
          actorGenreJson = pickle.load( open( './models/actorGenreDict.pkl', 'rb' ) )
          xin = pickle.load( open( './models/actorGenreVect.pkl', 'rb' ) )

          count=0
          for i in range(len(actorGenreJson)):
              actorGenre =actorGenreJson[i]
              _id = actorGenre['_actorID']+'-'+actorGenre['genre']

              doc = {
                  "_actorID" : actorGenre['_actorID'],
                  "celebrity": actorGenre['actor'],
                  "genre": actorGenre['genre'],
                  "genre_vector": [float(x) for x in xin[i]]
                  }
              r = requests.put(self.esIndex+'/_doc/'+_id, headers= self.headers, data = json.dumps(doc))
              count +=1
          return 'No of records loaded  Genre Plots Model : {}'.format( str(count) )
        except Exception as e:

            return 'Failed to load model Genre Plots :'+ str(e)
        
    def listCelbGenres(self, celebrity): 
        '''
        For a given celebrity, find all the documents where based on their genre.
        The list of genre is used to iterate through the list of celebrity recommendation in each genre.
        '''
        q={
          "_source": {
            "includes": [ "genre"]
            },
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
            results=[]
            matches = res['hits']['hits']
            for match in matches:
                results.append(match['_source']['genre'])
            return results
        else:
            return None

    def getCelbGenreVec(self, celebrity, genre):
        '''
        For a given celebrity and genre, return their vector space based on the movies plots they have performed in.
        '''

        q={
          "query": { 
            "bool": { 
              "must": [
                { "match": { "celebrity":celebrity}}
              ],
              "filter": [ 
                { "term":  { "genre": genre }}
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
            

    def getSimilarGenreVec(self, genre_vec, genre): 
        '''
        Based on the celebrity genre vector for a given genre, find similar celebrity in the same genre that performed in similary plots.
        '''
        q={
              "query": {
                "script_score": {
                "query": {
                        "terms" : { "genre" : [genre]}
                    },
                  "script": {
                    "source": "cosineSimilarity(params.query_vector, doc['genre_vector']) ", 
                    "params": {
                      "query_vector": genre_vec
                    }
                  }
                }
              }
            }

        res= self.es.search(index=self._index, body=q)

        res= self.es.search(index=self._index, body=json.dumps(q))
        if res['hits']['hits']:
            matches = res['hits']['hits']
            results=[]
            for match in matches:
                results.append( { 'genre': genre,  '_actorID': match['_source']['_actorID'], 'celebrity': match['_source']['celebrity'], 'score': match['_score']})
            return (results) 
        else:
            return None


    def recommendCelebrities(self, celebritySearch):
        '''
        Main search entrypoint, input a celebrity name and return the list of genre they performed and recommend alternative celebrities.
        '''
        results=[]
        for genreSearch in self.listCelbGenres(celebritySearch):
            genreVec = self.getCelbGenreVec(celebritySearch, genreSearch)
            genreRecs = self.getSimilarGenreVec( genreVec, genreSearch)

            # Remove the search celebrirty from the recomendations
            results = results+ [i  for i in genreRecs if i['celebrity']!= celebritySearch ] 
            
        return results




    