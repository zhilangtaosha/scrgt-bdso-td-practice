#!/bin/bash



JENKINSIP=$1
PASS=$2
PROJECT=$3

jupyterbucketnamedev=`aws ssm get-parameter --name jupyterBucketName-dev --with-decryption | jq -r .Parameter.Value`
jupyterbucketnametest=`aws ssm get-parameter --name jupyterBucketName-test --with-decryption | jq -r .Parameter.Value`
jupyterbucketnameprod=`aws ssm get-parameter --name jupyterBucketName-prod --with-decryption | jq -r .Parameter.Value`
jupyterbucketnameemr=`aws ssm get-parameter --name jupyterBucketName-emr --with-decryption | jq -r .Parameter.Value`

# copy files
echo "copying jupyter notebook files to S3."

cpdev1=`aws s3 cp jupyter s3://$jupyterbucketnameemr/jupyter/jovyan/ --recursive`

echo "copied files to S3 buckets"

#2. copy data files
echo "Copying data files..."
cpdatafilesdev=`aws s3 cp datapipeline/newdata s3://$jupyterbucketnamedev/ --recursive`
echo "copy to dev folder complete"
cpdatafilestest=`aws s3 cp datapipeline/newdata s3://$jupyterbucketnametest/ --recursive`
echo "copy to test folder complete"
cpdatafilesprod=`aws s3 cp datapipeline/newdata s3://$jupyterbucketnameprod/ --recursive`
aws s3 cp dev-settings.py s3://$jupyterbucketnamedev/settings.py
aws s3 cp test-settings.py s3://$jupyterbucketnametest/settings.py
aws s3 cp prod-settings.py s3://$jupyterbucketnameprod/settings.py
echo "copy to prod folder complete"
echo "data files copy complete."

aws s3 cp ./emr_job.sh s3://$jupyterbucketnameemr/
emr_cluster_id=$(aws emr list-clusters --active | jq -r .Clusters[].Id)
aws emr add-steps --cluster-id $emr_cluster_id --steps Name="Script Runner",Jar=s3://us-east-2.elasticmapreduce/libs/script-runner/script-runner.jar,Args=["s3://$jupyterbucketnameemr/emr_job.sh"]

# 3. execute the data pipeline load [TBD after CSRF in Jenkins is disabled]
# get the jenkins cli
echo "Working on Jenkins and project is $PROJECT"

URL="http://$JENKINSIP:8080/jnlpJars/jenkins-cli.jar"
wget $URL
#execute builds
echo "executing Jenkins pipeline jobs"

# wait until the celebrity job is done. Others can be run in asynch mode (just trigger them no need to wait)
echo "executing celebrity_ms jenkins job. Could take up to 5 mins."
java -jar jenkins-cli.jar -s http://$JENKINSIP:8080/ -auth admin:$PASS build celebrity_ms -s -v


echo "executing frontend_ms jenkins job. Could take up to 5 mins."
java -jar jenkins-cli.jar -s http://$JENKINSIP:8080/ -auth admin:$PASS build frontend_ms -s -v

echo "executing recommendation_ms jenkins job. Could take up to 5 mins."
java -jar jenkins-cli.jar -s http://$JENKINSIP:8080/ -auth admin:$PASS build recommendation_ms -s -v

echo "executing datapipeline jenkins job. Could take up to 10 mins."
java -jar jenkins-cli.jar -s http://$JENKINSIP:8080/ -auth admin:$PASS build datapipeline -p OCP_DOMAIN=$PROJECT -s -v

echo "Jenkins pipeline jobs executed."
