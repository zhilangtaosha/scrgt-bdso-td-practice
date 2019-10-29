#!/bin/bash

cat << 'EOF' > ./run_notebooks.sh
#!/bin/bash

jupyterbucketnameemr=`aws ssm get-parameter --name jupyterBucketName-emr --with-decryption --region us-east-2 | jq -r .Parameter.Value`
jupyterbucketnametest=`aws ssm get-parameter --name jupyterBucketName-test --with-decryption --region us-east-2 | jq -r .Parameter.Value`

mkdir /home/jovyan/tst
mkdir /home/jovyan/tst/data


aws s3 cp s3://$jupyterbucketnameemr/jupyter/jovyan/dev/envSetup.ipynb /home/jovyan/dev/ --region us-east-2
aws s3 cp s3://$jupyterbucketnameemr/jupyter/jovyan/test/envSetup.ipynb /home/jovyan/test/ --region us-east-2
aws s3 cp s3://$jupyterbucketnameemr/jupyter/jovyan/prod/envSetup.ipynb /home/jovyan/prod/ --region us-east-2
aws s3 cp s3://$jupyterbucketnametest/settings.py /home/jovyan/tst/data/ --region us-east-2

jupyter nbconvert --execute --ExecutePreprocessor.timeout=600 /home/jovyan/dev/envSetup.ipynb
jupyter nbconvert --execute --ExecutePreprocessor.timeout=600 /home/jovyan/test/envSetup.ipynb
jupyter nbconvert --execute --ExecutePreprocessor.timeout=600 /home/jovyan/prod/envSetup.ipynb
chown -R jovyan:users /home/jovyan/dev
chown -R jovyan:users /home/jovyan/test
chown -R jovyan:users /home/jovyan/prod
chown -R jovyan:users /home/jovyan/tst
EOF


sudo docker cp ./run_notebooks.sh jupyterhub:/etc/jupyter/run_notebooks.sh
sudo docker exec jupyterhub bash /etc/jupyter/run_notebooks.sh
