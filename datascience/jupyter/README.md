## Jupyter Notebook Deployment

Since we are using one EMR cluster to run across different environments, we have set up the folders in the following way.  The following folder stucture on the Jupyter Notebook is what we have agreed upon.

Root
  - Prod
    - datapipeline
    - models
  - Test
    - datapipeline
    - models
  - Dev
    - datapipeline
    - models


### Setup

envSetup.ipynb should be configured for each env 
using the os.environ["PLATFORM"] = "dev"
the script will download a copy of the celebrity data and models locally to the /home/jovyan directory.

For each environment there should be a folder 
    /dev/data
    /test/data
    /prod/data

When promoting the python notebook up, make sure the heading in each notebook is confgured to point to the right env.  Python will look at the current working directory for the file.

    # Configure Environment 
    from  <b>dev</b>.data.settings import * 




### EMR bootstrap scripts
Install the libraries to support the notebooks.
- setup
    - setup.sh
    - requirements.txt


### TODO :

build the script to run excute the notebook from the cmd line.
https://nbconvert.readthedocs.io/en/latest/execute_api.html

##### Datapiline: 
- Refresh the data IMDB, theMovieDb, Wikipedia.
- The input is the 100TopActors.txt file in the data folder.  
- We have already downloaded the data, so only run if necessary or if there's a data issue.

Order should be :
- themoviedbAPI.ipynb
- themoviedbMovieAPI.ipynb
- imdbPersonBio.ipynb
- wikipedia.ipynb

##### DS Models:
- actorBios.ipynb
- actorClassification.ipynb
- celeberitMovieClassification.ipynb
- facedetection.ipynb


#### Elasticsearch 
- Could not connect to ES
- The ES for each env should be in the AWS SSM and needs to be updated in the settings.py file.


#### Recommendation engine 
- Verify the recommendation engine is pointing to the right S3 bucket for each env and contains all the *.pkl files.
