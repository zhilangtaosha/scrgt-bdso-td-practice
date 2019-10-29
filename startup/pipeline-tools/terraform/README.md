#SETUP INSTRUCTIONS

From the directory terraform/pipeline-infrastructure:
0. for developers: edit the main.tf file and set the locals.project variable on line 3 to something unique to yourself so as to not overlap with other developers. 
1. terraform init && terraform apply -var pass=[enter password]
2. enter 'yes' when prompted
3. once setup is complete, the frontend URLs will be printed at the end while the terraform/pipeline-infrastructure/keys/BDSO.pem file can be used to ssh to the systems themselves.
For example:

 ``` ssh -i keys/BDSO.pem centos@jenkins.salientcrgt-bdso.com ```
