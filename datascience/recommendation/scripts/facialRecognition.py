from flask import Flask, jsonify, request, redirect

import face_recognition
from PIL import Image, ImageDraw
from elasticsearch import Elasticsearch
import os
import numpy as np
import string, random
import requests, json
import pickle
import logging
logger = logging.getLogger('facialRecognitionAPI')
logger.setLevel(logging.INFO)
# You can change this to any folder on your system
ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg', 'gif'}

from flask import Blueprint

ESPORT= os.environ.get('ES_73_PORT')
ESHOST= os.environ.get('ES_73_HOST')

face_recognition_api = Blueprint('face_recognition_api', __name__)



class FacialRecognitionModel():

    def __init__(self):

      self._index='facialimages'
      self._doc= 'facialRecognition'
      self.esIndex = 'http://'+ESHOST+':'+ESPORT+'/'+self._index  
      self.es=Elasticsearch([{'host':ESHOST,'port':ESPORT}])
      self.headers={"Content-Type": "application/json"}
      self.vector_size = 128


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
            "facial_vector": {
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
            
            return 'Failed to upload to : '+ self._index+':::'+ str(e)
        

    def loadModel(self):
        '''
        Load index files if new index was built
        '''
        try:
            faceLabels   = pickle.load( open( './models/faceLabels.pkl', 'rb' ) )
            faceFeatures = pickle.load( open( './models/faceFeatures.pkl', 'rb' ) )
            actorNameRef = pickle.load( open( './models/actorNameRef.pkl', 'rb' ) )

            celebrityImages =[]
            for images in faceLabels['images']:
                image = images.split('/')
                celebrityImages.append(image[-1])

            celebrityFacial=[]
            for feature in faceFeatures:
                celebrityFacial.append(feature.tolist())

            count=0
            for i in range(len(actorNameRef)):
                _id = celebrityImages[i].split('.')[0]
                doc = {

                    "_actorID" : actorNameRef[i][0],
                    "celebrity": actorNameRef[i][1],
                    "age": actorNameRef[i][2],
                    "birthdate": actorNameRef[i][3],
                    "gender": actorNameRef[i][4],
                    "height": actorNameRef[i][5],
                    "image" : celebrityImages[i],
                    "facial_vector": celebrityFacial[i]  
                    }
                r = requests.put(self.esIndex+'/_doc/'+_id, headers= self.headers, data = json.dumps(doc))

                count +=1
            return 'No of records loaded for Facial Recognition model : {}'.format( str(count) )

        except Exception as e:
            return 'Failed to load model Facial recognition '+ str(e)



    def getSimilarVector(self, facialVector):
        '''
        Perform a facial ID match against elasticsearch using the cosine similary measurement.
        Return the first result set
        '''

        false=''
        q={
        "query": {
            "script_score": {
            "query" : {
                "match_all" : {}
            },
            "script": {
                "source": "cosineSimilarity(params.query_vector, doc['facial_vector']) + 1.0", 
                "params": {
                "query_vector": facialVector
                }
            }
            }
        }
        }
        match = None
        results= self.es.search(index=self._index, body=json.dumps(q))

        # Select the top match result
        if results:
            topHit = results['hits']['hits'][0]
            match =  { 'name' : topHit['_source']['celebrity']+' : '+ str( topHit['_score']), 
            'facial_vector': topHit['_source']['facial_vector'] ,
            'gender': topHit['_source']['gender'],
            'age': topHit['_source']['age'],
            'birthdate': topHit['_source']['birthdate'],
            'height': topHit['_source']['height'],       
            }
        logger.info(match['name'])
        return match


    def getFacialVec(self, celebrity):
        '''
        For a given celebrity the first facial record in the facial database.
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

            return match['_source']['facial_vector']
        else:
            return None
            

    def getSimilarFacialVec(self, vec): 
        '''
        Based on the celebrity facial vector, find similar celebrity with similar facial features.
        '''
        q={
          "size" : 40,
          "_source": {
                "includes": [ "_actorID", "celebrity"]
                },
          "query": {
            "script_score": {

              "query" : {
                "match_all" : {}
              },
              "script": {
                "source": "cosineSimilarity(params.query_vector, doc['facial_vector']) ", 
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
            results={}
            for match in matches:
                celebrirty= match['_source']['celebrity']
                if celebrirty not in  results:
                    results[celebrirty] = {  '_actorID': match['_source']['_actorID'], 'score': match['_score'] }
            return results
        else:
            return None

    def recFacialCelebrities(self, celebritySearch):
        '''
        Main search entrypoint, input a celebrity name and return a list of celebrities that have similary facial features.
        '''
        results=[]

        facial_vec = self.getFacialVec(celebritySearch)
        recomends  = self.getSimilarFacialVec( facial_vec)
        # Remove the search celebrirty from the recomendations
        results = [{'celebrity': celebrity, '_actorID':recomends[celebrity]['_actorID'],  'score':recomends[celebrity]['score'] }  for celebrity in recomends if celebrity != celebritySearch ] 

        return results



        

def id_generator(size=6, chars=string.ascii_uppercase + string.digits):
    ''' generate a unique filename for every file uploaded.
    '''
    return ''.join(random.choice(chars) for _ in range(size))



def allowed_file(filename):
    '''
    Only allow the jpeg, jpg, png files
    '''
    return '.' in filename and \
        filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


@face_recognition_api.route('/facedemo', methods=['GET', 'POST'])
def upload_image():
    # Check if a valid image file was uploaded
    if request.method == 'POST':
        if 'file' not in request.files:
            return redirect(request.url)

        file = request.files['file']

        if file.filename == '':
            return redirect(request.url)

        if file and allowed_file(file.filename):
            logger.error('\n\n'+ file.filename)
            # The image file seems valid! Detect faces and return the result.
            return detect_faces_in_image(file)

    # If no valid image file was uploaded, show the file upload form:
    return '''
    <!doctype html>
    <title>Celebrity Faical Recognition Demo</title>
    <h1>Upload a new image to compare against the top 100 Celebrity</h1>
    <form method="POST" enctype="multipart/form-data">
      <input type="file" name="file">
      <input type="submit" value="Upload">
    </form>
    '''


def detect_faces_in_image(file_stream):

    facialVector=''
    known_face_encodings=[]
    known_face_names=[]
    unknown_image = face_recognition.load_image_file(file_stream)
    # Get face encodings for any faces in the uploaded image

    # Find all the faces and face encodings in the unknown image
    face_locations = face_recognition.face_locations(unknown_image)
    face_encodings = face_recognition.face_encodings(unknown_image, face_locations)

    # Convert the image to a PIL-format image so that we can draw on top of it with the Pillow library
    # See http://pillow.readthedocs.io/ for more about PIL/Pillow
    pil_image = Image.fromarray(unknown_image)
    # Create a Pillow ImageDraw Draw instance to draw with
    draw = ImageDraw.Draw(pil_image)

    # Loop through each face found in the unknown image
    facialModel = FacialRecognitionModel()
    for (top, right, bottom, left), face_encoding in zip(face_locations, face_encodings):

        # Use Elastic Search Image search database to find similar facces and its confidence socre
        
        match = facialModel.getSimilarVector(face_encoding.tolist())
        if match:
            
            searchname = match['name']
            known_face_names= [[searchname]]
            logger.error(searchname)
            known_face_encodings = [match['facial_vector'] ]
            facialVector = str(match['facial_vector'])


        # See if the face is a match for the known face(s)
        matches = face_recognition.compare_faces(known_face_encodings, face_encoding)
        
        name = "Unknown"

        # Or instead, use the known face with the smallest distance to the new face
        face_distances = face_recognition.face_distance(known_face_encodings, face_encoding)
        best_match_index = np.argmin(face_distances)
        if matches[best_match_index]:
            name = known_face_names[best_match_index]
            logger.error(name)

        # Draw a box around the face using the Pillow module
        # draw.rectangle(((left, top), (right, bottom)), outline=(128, 255, 215), width=9)

        # # Draw a label with a name below the face
        # text_width, text_height = draw.textsize(name)
        # draw.rectangle(((left, bottom - text_height - 10), (right, bottom)), fill=(128, 255, 215), outline=(128, 255, 215))
        # draw.text((left + 10, bottom - text_height - 10), name, fill=(0, 0, 0, 0))

        # Find all facial features in all the faces in the image
        face_landmarks_list = face_recognition.face_landmarks(unknown_image)

        pil_image = Image.fromarray(unknown_image)
        for face_landmarks in face_landmarks_list:
            d = ImageDraw.Draw(pil_image, 'RGBA')

        # Let's trace out each facial feature in the image with a line!
        for facial_feature in face_landmarks.keys():
            d.line(face_landmarks[facial_feature], width=5)


  

    # Display the resulting image
    pil_image.show()
    image_name= id_generator()+'.jpg'
    basewidth = 400
    wpercent = (basewidth / float(pil_image.size[0]))
    hsize = int((float(pil_image.size[1]) * float(wpercent)))
    pil_image = pil_image.resize((basewidth, hsize), Image.ANTIALIAS)


    # save a copy of the new image to disk 
    pil_image.save("./static/"+image_name)
    # Remove the drawing library from memory as per the Pillow docs
    #
    del draw

    # Return the result as json
    return '''
    <!doctype html>
    <title>Celebrity Facial Features Matching Demo</title>
    <h1>Celebrity Facial Matching</h1>
    <img src="/static/'''+image_name+'''">
    <h3>'''+searchname+'''</h3>
    <br>
     <h3> Gender : '''+match['gender']+'''</h3>        
    <h3> Age : '''+match['age']+'''</h3>
    <h3> Birth Date : '''+match['birthdate']+'''</h3>
    <h3> Height : '''+match['height']+'''</h3>
        
    <b> Facial Features Vector</b>
    '''+facialVector
    

