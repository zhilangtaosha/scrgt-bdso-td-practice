

module "iam_policy" {
  source = "terraform-aws-modules/iam/aws//modules/iam-policy"

  name        = "bucketpolicy"
  path        = "/"
  description = "Full S3 Access"

  policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": [
        "s3:*"
      ],
      "Effect": "Allow",
      "Resource": "*"
    }
  ]
}
EOF
}

module "iam_user" {
  source  = "terraform-aws-modules/iam/aws//modules/iam-user"
  version = "~> 2.0"

  name          = "bucketuser"
  force_destroy = true

  create_iam_user_login_profile = false
  create_iam_access_key         = true
}

resource "aws_iam_user_policy_attachment" "bucketuser-bucketpolicy" {
  user       = "bucketuser"
  policy_arn = "${module.iam_policy.arn}"
}

# secrets in AWS parameter store
resource "aws_ssm_parameter" "emr-aws-accesskey" {
  name        = "S3_AWS_ACCESS_KEY_ID"
  description = "emr aws access key"
  type        = "SecureString"
  value       = "${module.iam_user.this_iam_access_key_id}"
  overwrite   = true
  tags = {
    environment = "all"
  }
}
resource "aws_ssm_parameter" "emr-aws-secretkey" {
  name        = "S3_AWS_SECRET_ACCESS_KEY"
  description = "emr aws access key"
  type        = "SecureString"
  value       = "${module.iam_user.this_iam_access_key_secret}"
  overwrite   = true
  tags = {
    environment = "all"
  }
}
resource "aws_ssm_parameter" "emr-aws-region" {
  name        = "S3_AWS_REGION"
  description = "emr region"
  type        = "SecureString"
  value       = "us-east-2"
  overwrite   = true
  tags = {
    environment = "all"
  }
}



# secrets for OCP
resource "local_file" "dev_secrets" {
    filename = "${path.root}/../../ocp-setup/setup/dev_secrets.yml"
    content     = <<EOF
apiVersion: v1
kind: Secret
metadata:
  name: bdso-secret
type: Opaque
data:
  POSTGRES_DB_HOST: "${base64encode(aws_db_instance.dev.address)}"
  POSTGRES_DB_USERNAME: "${base64encode(aws_db_instance.dev.username)}"
  POSTGRES_DB_PASSWORD: "${base64encode(var.pass)}"
  POSTGRES_DB_NAME: "${base64encode(aws_db_instance.dev.name)}"
  POSTGRES_DB_PORT: "${base64encode("5432")}"
  EMR-ENDPOINT-URL: "${base64encode(aws_cloudformation_stack.cfn-emr-dev.outputs["EMRMasterNotebookURL"])}"
  AWS_S3_BUCKET: "${base64encode(aws_s3_bucket.s3bucket-ds-dev.bucket)}"
  AWS_REGION: "${base64encode("us-east-2")}"
  ES_68_HOST: "${base64encode("elasticsearchdb-master-headless.dev.svc.cluster.local")}"
  ES_68_PORT: "${base64encode("9300")}"
  ES_68_CLUSTER: "${base64encode("elasticsearchdb")}"
  ES_68_HC_PORT: "${base64encode("9200")}"
  ES_73_HOST: "${base64encode("elasticsearch-master.dev.svc.cluster.local")}"
  ES_73_PORT: "${base64encode("9200")}"
  ES_73_CLUSTER: "${base64encode("elasticsearch")}"
  SECURITY_ENABLED: "${base64encode("false")}"
  KECLOAK_URL: "${base64encode("http://keycloak-http/auth/realms/bdso")}"
  AWS_ACCESS_KEY_ID: ${base64encode(module.iam_user.this_iam_access_key_id)}
  AWS_SECRET_ACCESS_KEY: ${base64encode(module.iam_user.this_iam_access_key_secret)}

EOF
}

resource "local_file" "test_secrets" {
    filename = "${path.root}/../../ocp-setup/setup/test_secrets.yml"
    content     = <<EOF
apiVersion: v1
kind: Secret
metadata:
  name: bdso-secret
type: Opaque
data:
  POSTGRES_DB_HOST: "${base64encode(aws_db_instance.test.address)}"
  POSTGRES_DB_USERNAME: "${base64encode(aws_db_instance.test.username)}"
  POSTGRES_DB_PASSWORD: "${base64encode(var.pass)}"
  POSTGRES_DB_NAME: "${base64encode(aws_db_instance.test.name)}"
  POSTGRES_DB_PORT: "${base64encode("5432")}"
  EMR-ENDPOINT-URL: "${base64encode(aws_cloudformation_stack.cfn-emr-dev.outputs["EMRMasterNotebookURL"])}"
  AWS_S3_BUCKET: "${base64encode(aws_s3_bucket.s3bucket-ds-test.bucket)}"
  AWS_REGION: "${base64encode("us-east-2")}"
  ES_68_HOST: "${base64encode("elasticsearchdb-master-headless.test.svc.cluster.local")}"
  ES_68_PORT: "${base64encode("9300")}"
  ES_68_CLUSTER: "${base64encode("elasticsearchdb")}"
  ES_68_HC_PORT: "${base64encode("9200")}"
  ES_73_HOST: "${base64encode("elasticsearch-master.test.svc.cluster.local")}"
  ES_73_PORT: "${base64encode("9200")}"
  ES_73_CLUSTER: "${base64encode("elasticsearch")}"
  SECURITY_ENABLED: "${base64encode("true")}"
  KECLOAK_URL: "${base64encode("http://keycloak-http/auth/realms/bdso")}"
  AWS_ACCESS_KEY_ID: ${base64encode(module.iam_user.this_iam_access_key_id)}
  AWS_SECRET_ACCESS_KEY: ${base64encode(module.iam_user.this_iam_access_key_secret)}

EOF
}

resource "local_file" "prod_secrets" {
    filename = "${path.root}/../../ocp-setup/setup/prod_secrets.yml"
    content     = <<EOF
apiVersion: v1
kind: Secret
metadata:
  name: bdso-secret
type: Opaque
data:
  POSTGRES_DB_HOST: "${base64encode(aws_db_instance.prod.address)}"
  POSTGRES_DB_USERNAME: "${base64encode(aws_db_instance.prod.username)}"
  POSTGRES_DB_PASSWORD: "${base64encode(var.pass)}"
  POSTGRES_DB_NAME: "${base64encode(aws_db_instance.prod.name)}"
  POSTGRES_DB_PORT: "${base64encode("5432")}"
  EMR-ENDPOINT-URL: "${base64encode(aws_cloudformation_stack.cfn-emr-dev.outputs["EMRMasterNotebookURL"])}"
  AWS_S3_BUCKET: "${base64encode(aws_s3_bucket.s3bucket-ds-prod.bucket)}"
  AWS_REGION: "${base64encode("us-east-2")}"
  ES_68_HOST: "${base64encode("elasticsearchdb-master-headless.prod.svc.cluster.local")}"
  ES_68_PORT: "${base64encode("9300")}"
  ES_68_CLUSTER: "${base64encode("elasticsearchdb")}"
  ES_68_HC_PORT: "${base64encode("9200")}"
  ES_73_HOST: "${base64encode("elasticsearch-master.prod.svc.cluster.local")}"
  ES_73_PORT: "${base64encode("9200")}"
  ES_73_CLUSTER: "${base64encode("elasticsearch")}"
  SECURITY_ENABLED: "${base64encode(true)}"
  KECLOAK_URL: "${base64encode("http://keycloak-http/auth/realms/bdso")}"
  AWS_ACCESS_KEY_ID: ${base64encode(module.iam_user.this_iam_access_key_id)}
  AWS_SECRET_ACCESS_KEY: ${base64encode(module.iam_user.this_iam_access_key_secret)}

EOF
}
