import os
os.environ["ES_73_HOST"] = "elasticsearch-prod.ocp.bdso.salientcrgt-bdso.com"
os.environ["ES_73_PORT"] = "80"
os.environ["AWS_DEFAULT_REGION"] = "us-east-2"
ESPORT= os.environ.get('ES_73_PORT')
ESHOST= os.environ.get('ES_73_HOST')
PLATFORM='prod'
os.chdir('/home/jovyan/'+PLATFORM+'/data')
