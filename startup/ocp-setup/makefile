infrastructure:
	# Get the modules, create the infrastructure.
	terraform init && terraform get && terraform apply -auto-approve

# Installs OpenShift on the cluster.
openshift:
	# Add our identity for ssh, add the host key to avoid having to accept the
	# the host key manually. Also add the identity of each node to the bastion.
	ssh-add ../pipeline-tools/terraform/keys/key.pem
	ssh-keyscan -t rsa -H $$(terraform output bastion-public_ip) >> ~/.ssh/known_hosts
	ssh -A ec2-user@$$(terraform output bastion-public_ip) "ssh-keyscan -t rsa -H master.openshift.local >> ~/.ssh/known_hosts"
	ssh -A ec2-user@$$(terraform output bastion-public_ip) "ssh-keyscan -t rsa -H node1.openshift.local >> ~/.ssh/known_hosts"
	ssh -A ec2-user@$$(terraform output bastion-public_ip) "ssh-keyscan -t rsa -H node2.openshift.local >> ~/.ssh/known_hosts"
	ssh -A ec2-user@$$(terraform output bastion-public_ip) "sudo pip install -U pyopenssl"
	scp -r certs ec2-user@$$(terraform output bastion-public_ip):~
	# Copy our inventory to the master and run the install script.
	scp ./inventory.cfg ec2-user@$$(terraform output bastion-public_ip):~
	cat install-from-bastion.sh | ssh -o StrictHostKeyChecking=no -A ec2-user@$$(terraform output bastion-public_ip)

	#install helm on the master node so we can run setup later
	cat ./scripts/install-helm.sh | ssh -A ec2-user@$$(terraform output bastion-public_ip) ssh master.openshift.local


	# Now the installer is done, run the postinstall steps on each host.
	# Note: these scripts cause a restart, so we use a hyphen to ignore the ssh
	# connection termination.
	- cat ./scripts/postinstall-master.sh | ssh -A ec2-user@$$(terraform output bastion-public_ip) ssh master.openshift.local
	- cat ./scripts/postinstall-node.sh | ssh -A ec2-user@$$(terraform output bastion-public_ip) ssh node1.openshift.local
	- cat ./scripts/postinstall-node.sh | ssh -A ec2-user@$$(terraform output bastion-public_ip) ssh node2.openshift.local
	echo "Complete! Wait a minute for hosts to restart, then run 'make browse-openshift' to login with user 'admin' and password '123'."

# Destroy the infrastructure.
destroy:
	terraform init && terraform destroy -auto-approve

# Open the console.
browse-openshift:
	open $$(terraform output master-url)

# SSH onto the master.
ssh-bastion:
	ssh -t -A ec2-user@$$(terraform output bastion-public_ip)
ssh-master:
	ssh -t -A ec2-user@$$(terraform output bastion-public_ip) ssh master.openshift.local
ssh-node1:
	ssh -t -A ec2-user@$$(terraform output bastion-public_ip) ssh node1.openshift.local
ssh-node2:
	ssh -t -A ec2-user@$$(terraform output bastion-public_ip) ssh node2.openshift.local

# Create sample services.
setup:
	# Copy additional setup
	scp -r -o ProxyCommand="ssh -W %h:%p ec2-user@$$(terraform output bastion-public_ip)" -o StrictHostKeyChecking=no ./setup ec2-user@master.openshift.local:~
	scp -r -o ProxyCommand="ssh -W %h:%p ec2-user@$$(terraform output bastion-public_ip)" -o StrictHostKeyChecking=no ../ocp-apps ec2-user@master.openshift.local:~

	cat ./scripts/finalize.sh | ssh -A ec2-user@$$(terraform output bastion-public_ip) ssh master.openshift.local


# Lint the terraform files. Don't forget to provide the 'region' var, as it is
# not provided by default. Error on issues, suitable for CI.
lint:
	terraform get
	TF_VAR_region="us-east-1" tflint --error-with-issues

# Run the tests.
test:
	echo "Simulating tests..."

# Run the CircleCI build locally.
circleci:
	circleci config validate -c .circleci/config.yml
	circleci build --job lint

.PHONY: setup
