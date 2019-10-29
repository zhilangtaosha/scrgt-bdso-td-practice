### start an elastic search cloudformation job. Runs when changed dir to cloudformation folder in startup.sh

###### S3 bucket to store yaml files for elasticsearch and emr and Jupyter logs and data
resource "aws_s3_bucket" "s3bucket" {
  bucket = "${local.project}-cc-${module.network.vpc}"
  acl    = "private"
  force_destroy = true
  tags = {
    Name        = "Bucket for BDSO CC"
    Environment = "All"
  }
}


##### EMR - DEV
resource "aws_s3_bucket_object" "emr" {
  bucket = "${aws_s3_bucket.s3bucket.id}"
  key    = "EMR-cluster-create.yml"
  source = "../../cloudformation/EMR-cluster-create.yml"
  etag = "${filemd5("../../cloudformation/EMR-cluster-create.yml")}"
}

resource "aws_s3_bucket_object" "emr_bootstrap" {
  bucket = "${aws_s3_bucket.s3bucket.id}"
  key    = "bootstrap_jupyter.sh"
  source = "../../jupyter/bootstrap_jupyter.sh"
  etag = "${filemd5("../../jupyter/bootstrap_jupyter.sh")}"
}

resource "aws_cloudformation_stack" "cfn-emr-dev" {
  name = "${local.project}-EMR-dev"
  parameters = {
    ApplicationName = "${local.project}-emr-dev"
	VPC 	= "${module.network.vpc}"
	SubnetID	= "${module.network.public_subnet_a}"
	S3BucketName="${aws_s3_bucket.s3bucket.bucket}"
  }
  capabilities = ["CAPABILITY_NAMED_IAM"]
  template_url = "https://${aws_s3_bucket.s3bucket.bucket}.s3.${local.region}.amazonaws.com/${aws_s3_bucket_object.emr.key}"
}

resource "aws_ssm_parameter" "emr-endpoint-secret-dev" {
  name        = "/dev/emr/endpoint"
  description = "emr endpoint encrypted"
  type        = "SecureString"
  value       = "${aws_cloudformation_stack.cfn-emr-dev.outputs["EMRMasterNotebookURL"]}"
  overwrite   = true
  tags = {
    environment = "dev"
  }
}
# ssm parameter for notebooks
resource "aws_ssm_parameter" "s3-emr-bucketname" {
  name        = "jupyterBucketName-emr"
  description = "Bucket name for Jupyter notebooks"
  type        = "SecureString"
  value       = "${aws_s3_bucket.s3bucket.bucket}"
  overwrite   = true
  tags = {
    environment = "dev"
  }
}

output "emr-endpoint-dev" {
        value = "${aws_cloudformation_stack.cfn-emr-dev.outputs["EMRMasterNotebookURL"]}"
}

##### S3 buckets for Data Science team

resource "aws_s3_bucket" "s3bucket-ds-dev" {
  bucket = "${local.project}-celebrity-content-dev-${module.network.vpc}"
  acl    = "private"
  force_destroy = true
  tags = {
    Name        = "Bucket for BDSO CC"
    Environment = "Dev"
  }
}

# ssm parameter for s3 bucket name DEV
resource "aws_ssm_parameter" "s3-ds-bucketname-dev" {
  name        = "jupyterBucketName-dev"
  description = "Bucket name for Jupyter notebooks"
  type        = "SecureString"
  value       = "${aws_s3_bucket.s3bucket-ds-dev.bucket}"
  overwrite   = true
  tags = {
    environment = "dev"
  }
}

resource "aws_s3_bucket" "s3bucket-ds-test" {
  bucket = "${local.project}-celebrity-content-test-${module.network.vpc}"
  acl    = "private"
  force_destroy = true
  tags = {
    Name        = "Bucket for BDSO CC"
    Environment = "Test"
  }
}

# ssm parameter for s3 bucket name TEST
resource "aws_ssm_parameter" "s3-ds-bucketname-test" {
  name        = "jupyterBucketName-test"
  description = "Bucket name for Jupyter notebooks"
  type        = "SecureString"
  value       = "${aws_s3_bucket.s3bucket-ds-test.bucket}"
  overwrite   = true
  tags = {
    environment = "test"
  }
}

resource "aws_s3_bucket" "s3bucket-ds-prod" {
  bucket = "${local.project}-celebrity-content-prod-${module.network.vpc}"
  acl    = "private"
  force_destroy = true
  tags = {
    Name        = "Bucket for BDSO CC"
    Environment = "Prod"
  }
}

# ssm parameter for s3 bucket name PROD
resource "aws_ssm_parameter" "s3-ds-bucketname-prod" {
  name        = "jupyterBucketName-prod"
  description = "Bucket name for Jupyter notebooks"
  type        = "SecureString"
  value       = "${aws_s3_bucket.s3bucket-ds-prod.bucket}"
  overwrite   = true
  tags = {
    environment = "prod"
  }
}
