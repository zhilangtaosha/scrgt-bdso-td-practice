

import os
import numpy as np
import string, random
import requests, json
import logging
from flask_cors import CORS
logger = logging.getLogger('facialRecognitionAPI')
logger.setLevel(logging.INFO)
import boto3

from flask import Flask, jsonify, request, redirect, jsonify
from flask_restplus import Api, Resource, fields

import face_recognition
from PIL import Image, ImageDraw

# You can change this to any folder on your system
ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg', 'gif'}

# Import Models
from celebrityBio import BioModel
from celebrityPlots import GenrePlotsModel
from celebrityGenreStats import GenreStatsModel
from facialRecognition import face_recognition_api, FacialRecognitionModel



s3 = boto3.client('s3',
    aws_access_key_id=os.environ['AWS_ACCESS_KEY_ID'],
    aws_secret_access_key=os.environ['AWS_SECRET_ACCESS_KEY'])

AWS_S3_BUCKET= os.environ.get('AWS_S3_BUCKET')

ESPORT= os.environ.get('ES_73_PORT')
ESHOST= os.environ.get('ES_73_HOST')


app = Flask(__name__)
CORS(app,resources={r"/*": {"origins": "*", "allow_headers": "*", "methods":["GET","OPTIONS"],"expose_headers":["content-length", "Allow", "Authorization"]}})


from werkzeug.contrib.fixers import ProxyFix
app.wsgi_app = ProxyFix(app.wsgi_app, x_proto=1, x_host=1)

app.register_blueprint(face_recognition_api)


api_app = Api(app = app, 
		  version = "1.0", 
		  title = "BDSO Code Challenge", 
		  description = "Demo of various NLP/ML/AI models")


ns = api_app.namespace('names', description='Manage names')

model = api_app.model('Name Model', 
				  {'name': fields.String(required = True, 
    					  				 description="Name of the person", 
    					  				 help="Name cannot be blank.")})






_doc= 'facialRecognition'
_index='facialimages'
headers={"Content-Type": "application/json"}

es = 'http://'+ESHOST+':'+ESPORT+'/'+_index+'/_search'

nsBios = api_app.namespace('similarBios', description='Find celebrity based on genre')
@nsBios.route('/<string:celebritySearch>/')
class celebrityBios(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' }, 
        params={ 'celebritySearch': 'Specify the celebrity name (First and Last Name)' })

    def get(self, celebritySearch):
        """
        Compare the biographic information for a celebrity and find similar celebrities.
        """
        try:
            celebrityBio = BioModel()
            results = celebrityBio.recBioCelebrities(celebritySearch)
            return {
                "status": "Celebrity Biographic Recommendations",
                "celebrity" : celebritySearch, 
                "results" : results
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            print(str(e))
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")

@nsBios.route('/createIndex')
class celebrityBiosCreate(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' })

    def post(self):
        """
        Create/Rebuild Celebrity Biographic Recommendation Index
        """
        try:
            celebrityBio = BioModel()
            results = celebrityBio.rebuildIndex()
            return {
                "status": "Success Rebuilt Elasticsearch Index",
                "results" : results
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")

@nsBios.route('/loadModel/')
class celebrityBiosLoad(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' })

    def post(self):
        """
        Load Biographic Model into Elasticsearch
        """
        try:
            celebrityBio = BioModel()
            results = celebrityBio.loadModel()
            return {
                "status": "Success Loading Model",
                "results" : results
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")


nsGenrePlots = api_app.namespace('similarGenrePlots', description='Find celebrity based on genre')
@nsGenrePlots.route('/<string:celebritySearch>/')
class celebrityGenre(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' }, 
        params={ 'celebritySearch': 'Specify the celebrity name (First and Last Name)' })

    def get(self, celebritySearch):
        """
            Input a cebritity and return a list of similar celebrities by genre
        """
        try:
            celebrityPlots = GenrePlotsModel()
            results = celebrityPlots.recommendCelebrities(celebritySearch)
            return {
                "status": "Celebrity Genre Movie Plots Recommendations",
                "celebrity" : celebritySearch, 
                "results" : results
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")


@nsGenrePlots.route('/createIndex')
class genrePlotIndex(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' })

    def post(self):
        """
        Create/Rebuild Celebrity Genre Plot Recommendation Index
        """
        try:
            celebrityPlots = GenrePlotsModel()
            results = celebrityPlots.rebuildIndex()
            return {
                "status": "Success Rebuilt Elasticsearch Index",
                "results" : results
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            print(str(e))
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")

@nsGenrePlots.route('/loadModel/')
class genrePlotLoad(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' })

    def post(self):
        """
        Load model for Celebrity Genre Plot Recommendation Index
        """
        try:
            celebrityPlots = GenrePlotsModel()
            results = celebrityPlots.loadModel()
            return {
                "status": "Success Rebuilt Elasticsearch Index",
                "results" : results
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")


nsGenreStats = api_app.namespace('similarGenreStats', description='Find celebrity based on genre stats')
@nsGenreStats.route('/<string:celebritySearch>/')
class celebrityGenreStat(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' }, 
        params={ 'celebritySearch': 'Specify the celebrity name (First and Last Name)' })

    def get(self, celebritySearch):
        """
        Recommmend celebrity based on the similar filmographic genre stats.
        """
        try:
            celebrityGenreStats = GenreStatsModel()
            results = celebrityGenreStats.recGenreStatsCelebrities(celebritySearch)
            return {
                "status": "Celebrity Genre History Recommendations",
                "celebrity" : celebritySearch, 
                "results" : results
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")


@nsGenreStats.route('/createIndex')
class genreStatsIndex(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' })

    def post(self):
        """
        Create/Rebuild Celebrity Biographic Recommendation Index
        """
        try:
            celebrityGenreStats = GenreStatsModel()
            results = celebrityGenreStats.rebuildIndex()
            return {
                "status": "Success Rebuilt Elasticsearch Index",
                "results" : results
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")

@nsGenreStats.route('/loadModel/')
class genreStatsLoad(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' })

    def post(self):
        """
        Create/Rebuild Celebrity Biographic Recommendation Index
        """
        try:
            celebrityGenreStats = GenreStatsModel()
            results = celebrityGenreStats.loadModel()
            return {
                "status": "Success Rebuilt Elasticsearch Index",
                "results" : results
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")


nsFacial = api_app.namespace('facialRecognition', description='Find celebrity based on genre')
@nsFacial.route('/<string:celebritySearch>/')
class facialReco(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' }, 
        params={ 'celebritySearch': 'Specify the celebrity name (First and Last Name)' })

    def get(self, celebritySearch):
        """
        Compare the facial feature for a celebrity and find similar celebrities.
        """
        try:
            facialRecognition = FacialRecognitionModel()
            results = facialRecognition.recFacialCelebrities(celebritySearch)
            return {
                "status": "Celebrity Biographic Recommendations",
                "celebrity" : celebritySearch, 
                "results" : results
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")

@nsFacial.route('/createIndex')
class facialIndex(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' })

    def post(self):
        """
        Create/Rebuild Celebrity Biographic Recommendation Index
        """
        try:
            facialRecognition = FacialRecognitionModel()
            results = facialRecognition.rebuildIndex()
            return {
                "status": "Success Rebuilt Elasticsearch Index",
                "results" : results
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            print(str(e))
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")

@nsFacial.route('/loadModel/')
class facialLoad(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' })

    def post(self):
        """
        Load Biographic Model into Elasticsearch
        """
        try:
            facialRecognition = FacialRecognitionModel()
            results = facialRecognition.loadModel()
            return {
                "status": "Success Loading Model",
                "results" : results
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")



nsAdmin = api_app.namespace('admin', description='Find celebrity based on genre')
@nsAdmin.route('/importModels/')
class adminImport(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' }) 
  
    def post(self):
        """
        Import  latest models from S3
        """
        try:
            results=[]
            s3files = s3.list_objects(Bucket=AWS_S3_BUCKET, Prefix='models')
            files = s3files['Contents']
            for f in files:
                s3_object = f['Key']
                if s3_object.endswith(".gz") or s3_object.endswith(".pkl") :
                    try:
                        s3.download_file(AWS_S3_BUCKET, s3_object, './'+s3_object )
                        results.append(s3_object )
                    except Exception as e:
                        print(e)
                        continue


            return {
                "status": "New Models downloaded from "+ AWS_S3_BUCKET,
                "models" : results
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")

@nsAdmin.route('/loadModels')
class adminLoad(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' })

    def post(self):
        """
        Load recommendaiton models
        """
        try:
            results =[]
            celebrityBio = BioModel()
            results.append( celebrityBio.loadModel()) 

            celebrityGenreStats = GenreStatsModel()
            results.append( celebrityGenreStats.loadModel())

            celebrityPlots = GenrePlotsModel()
            results.append( celebrityPlots.loadModel()) 

            facialRecognition = FacialRecognitionModel()
            results.append( facialRecognition.loadModel() )


            return {
                "status": "Success Reloading Recomendaiton Models",
                "results" :  results 
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")

@nsAdmin.route('/createIndexes/')
class adminCreateIndexes(Resource):
    @api_app.doc(responses={ 200: 'OK', 400: 'Invalid Argument', 500: 'Mapping Key Error' })

    def post(self):
        """
        Rebuild Elasticsearch Recommendation Indexes
        """
        try:
            results=[]

            celebrityBio = BioModel()
            results.append( celebrityBio.rebuildIndex()) 

            celebrityGenreStats = GenreStatsModel()
            results.append( celebrityGenreStats.rebuildIndex())

            celebrityPlots = GenrePlotsModel()
            results.append( celebrityPlots.rebuildIndex()) 

            facialRecognition = FacialRecognitionModel()
            results.append( facialRecognition.rebuildIndex() )


            return {
                "status": "Success Rebuilding Indexes ",
                "results" :  results 
            }
        except KeyError as e:
            ns.abort(500, e.__doc__, status = "Could not retrieve information", statusCode = "500")
        except Exception as e:
            ns.abort(400, e.__doc__, status = "Could not retrieve information", statusCode = "400")



if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5001, debug=True)
