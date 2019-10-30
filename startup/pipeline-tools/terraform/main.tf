locals {

  # "project is the subdomain used for the bdso code challenge"

  domain          = "salientcrgt-devsecops.com"
  project         = "bdsotd" 
  region          = "us-east-2"
  base_ami        = "ami-0d8f6eb4f641ef691"
}

variable "pass" {
  type = "string"
}

provider "aws" {
  region = "${local.region}"
}

module "network" {
  source = "./modules/network"
  region = "${local.region}"
  project    = "${local.project}"
}

output "tools_vpc" {
  value = "${module.network.vpc}"
}

output "master_password" {
  value = "${var.pass}"
}

output "project_name" {
  value = "${local.project}"
}
