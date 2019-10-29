#/bin/bash
eval $(ssh-agent)

# setup_db () {
#   echo "======== SETUP_DB START";
#   pushd database;
#     terraform init;
#     terraform apply -var "pass=$1" -auto-approve;
#   popd;
#   echo "======== SETUP_DB END";
# }

setup_ocp () {
  echo "======== SETUP_OCP START";
  pushd ocp-setup;
    # sleep 15 # wait for key to exist
	date | tee infrastructure.log;
    make infrastructure | tee -a infrastructure.log;
	  sleep 60; # wait for instances to boot
	date | tee ocp-cluster.log;
    make openshift | tee -a ocp-cluster.log;
    sleep 120; # wait for new cluster to come up
	date | tee setup.log;
    make setup | tee -a setup.log;
	
	
  popd;
  echo "======== SETUP_OCP END";
}

setup_tools () {
  echo "======== SETUP_TOOLS START. TOOLS BUILT ARE: JENKINS, SONARQUBE, ANCHORE, ELASTICSEARCH";
  pushd pipeline-tools/terraform;
	date | tee pipeline-tools.log
    terraform init;
    terraform apply -var "pass=$1" -auto-approve | tee -a pipeline-tools.log;
	
  popd;
  echo "======== SETUP_TOOLS END";
}


# run one after another. When run together with an & it does not exit when complete. Testing with calling them separately

# enable terraform logging
export TF_LOG=TRACE
export TF_LOG_PATH="/home/ec2-user/tf_process.log"

 

STARTTIMETOOLS=`date +"%D %T"`
setup_tools $1
ENDTIMETOOLS=`date +"%D %T"`

# # verify the secret files are created. If not exit
file="ocp-setup/setup/dev_secrets.yml"

if [ -f "$file" ]
then
    # check file size. if ==0 then exit
	filesize=`wc -c $file | awk '{print $1}'`
	if [ $filesize -gt 0 ]
    then
		echo "$file exists and has a size of $filesize. The process will continue."
		STARTTIMEOCP=`date +"%D %T"`
		setup_ocp $1
		ENDTIMEOCP=`date +"%D %T"`
    else
		echo "$file exists but is empty. Exiting the process."
		exit 1
	fi
else
	echo "$file not found. Exiting."
	exit 1
fi




echo "Complete"


echo "Tools Start Time: $STARTTIMETOOLS"
echo "Tools End  Time: $ENDTIMETOOLS"

echo "OCP Start Time: $STARTTIMEOCP"
echo "OCP End  Time: $ENDTIMEOCP"




pushd pipeline-tools/terraform;
JENKINS=$(terraform output jenkins):8080
PROJECT=$(terraform output project_name)
	 
FEURL="https://scrgt-bdso-cc-frontend-prod.ocp.$PROJECT.salientcrgt-bdso.com"
EMRENDPOINTDEV=`terraform output emr-endpoint-dev`


SONAR=$(terraform output sonar):9000

popd;

pushd ocp-setup;
OCP=`terraform output master-url`
popd;

echo "Processing complete. Below are the URLs:"
echo "---------------------------------"
echo "Jenkins URL: http://$JENKINS"
echo "Credentials: User ID: admin; Password: $1"
echo ""
echo "Jenkins pipeline jobs are:"
echo "     Celebrity MS: http://$JENKINS/job/celebrity_ms/" 
echo "      Frontend MS: http://$JENKINS/job/frontend_ms/" 
echo "Recommendation MS: http://$JENKINS/job/recommendation_ms/"  
echo "     Datapipeline: http://$JENKINS/job/datapipeline/" 
echo "--------------------------------------------" 
echo "SonarQube URL: http://$SONAR"
echo "Credentials: User ID: admin; Password: admin"
echo "--------------------------------------------"
echo "OpenShift URL: $OCP"
echo "Credentials: User ID: admin; Password: 123"
echo "--------------------------------------------"
echo "EMR EndPoint URL : $EMRENDPOINTDEV"
echo "Credentials: User ID: jovyan; Password: jupyter"
echo "--------------------------------------------"
 

# add to file
OUTPUTFILE="/home/ec2-user/bdso-infrastructure-assets.out"

echo "Processing complete. Below are the URLs:" > $OUTPUTFILE
echo "---------------------------------"	>> $OUTPUTFILE
echo "Jenkins URL: http://$JENKINS"	>> $OUTPUTFILE
echo "Credentials: User ID: admin; Password: $1"	>> $OUTPUTFILE
echo ""	>> $OUTPUTFILE
echo "Jenkins pipeline jobs are:"	>> $OUTPUTFILE
echo "     Celebrity MS: http://$JENKINS/job/celebrity_ms/"	>> $OUTPUTFILE
echo "      Frontend MS: http://$JENKINS/job/frontend_ms/"	>> $OUTPUTFILE 
echo "Recommendation MS: http://$JENKINS/job/recommendation_ms/"	>> $OUTPUTFILE  
echo "     Datapipeline: http://$JENKINS/job/datapipeline/"	>> $OUTPUTFILE 
echo "--------------------------------------------"	>> $OUTPUTFILE 
echo "SonarQube URL: http://$SONAR"	>> $OUTPUTFILE
echo "Credentials: User ID: admin; Password: admin"	>> $OUTPUTFILE
echo "--------------------------------------------"	>> $OUTPUTFILE
echo "OpenShift URL: $OCP"	>> $OUTPUTFILE
echo "Credentials: User ID: admin; Password: 123"	>> $OUTPUTFILE
echo "--------------------------------------------"	>> $OUTPUTFILE
echo "EMR EndPoint URL : $EMRENDPOINTDEV"	>> $OUTPUTFILE
echo "Credentials: User ID: jovyan; Password: jupyter"	>> $OUTPUTFILE
echo "--------------------------------------------"	>> $OUTPUTFILE
echo "--------------------------------------------"	>> $OUTPUTFILE
echo "Web Application URL: $FEURL"	>> $OUTPUTFILE
echo "Credentials: BusinessUser: test-user; Password: test123"	>> $OUTPUTFILE
echo "Credentials: Supervisor: test-supervisor; Password: test123"	>> $OUTPUTFILE
echo "Credentials: Data Scientist: test-ds; Password: test123"	>> $OUTPUTFILE
echo "--------------------------------------------"	>> $OUTPUTFILE

echo "The above URLs are also available in the bdso-infrastructure-assets.out file in the ec2-user home location (/home/ec2-user/)."

