variable "region" {
  type = "string"
  default = "us-east-1"
}

provider "aws" {
  region = "${var.region}"
}

variable "project" {
  type    = "string"
  default = "Instastructure"
}

module "keypair" {
  source = "../dynkeys"
  path   = "${path.root}/keys"
  name   = "key"
}

output "key_name" {
  value = "${module.keypair.key_name}"
}

resource "aws_vpc" "vpc" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_support   = true
  enable_dns_hostnames = true

  tags = {
    Name = "${var.project}-vpc"
  }
}

output "vpc" {
  value = "${aws_vpc.vpc.id}"
}

resource "aws_subnet" "public_subnet_a" {
  vpc_id                  = "${aws_vpc.vpc.id}"
  cidr_block              = "10.0.1.0/24"
  map_public_ip_on_launch = true
  availability_zone       = "${var.region}a"

  tags = {
    Name = "Subnet ${var.region}a"
  }
}

output "public_subnet_a" {
  value = "${aws_subnet.public_subnet_a.id}"
}

resource "aws_subnet" "public_subnet_b" {
  vpc_id                  = "${aws_vpc.vpc.id}"
  cidr_block              = "10.0.2.0/24"
  map_public_ip_on_launch = true
  availability_zone       = "${var.region}b"

  tags = {
    Name = "Subnet ${var.region}b"
  }
}

output "public_subnet_b" {
  value = "${aws_subnet.public_subnet_b.id}"
}

resource "aws_internet_gateway" "gw" {
  vpc_id = "${aws_vpc.vpc.id}"

  tags = {
    Name = "InternetGateway"
  }
}

resource "aws_route" "internet_access" {
  route_table_id         = "${aws_vpc.vpc.main_route_table_id}"
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = "${aws_internet_gateway.gw.id}"
}

resource "aws_route_table_association" "public_subnet_a_association" {
  subnet_id      = "${aws_subnet.public_subnet_a.id}"
  route_table_id = "${aws_vpc.vpc.main_route_table_id}"
}
