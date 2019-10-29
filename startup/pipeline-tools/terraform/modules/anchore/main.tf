variable "project" {
  type    = "string"
  default = "Instastructure"
}

variable "base_ami" {
  type    = "string"
  default = "ami-4bf3d731"
}

variable "key_name" {
  type = "string"
}

variable "vpc" {
  type = "string"
}

variable "subnet" {
  type = "string"
}

variable "master_password" {
  type = "string"
}

data "aws_subnet" "s" {
  id = "${var.subnet}"
}

variable "domain" {
  type = "string"
}

resource "aws_security_group" "anchore" {
  name        = "${var.project}-anchore"
  description = "anchore necessary ports"
  vpc_id      = "${var.vpc}"

  ingress {
    from_port   = 8081
    to_port     = 8081
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
    description = "anchore web frontend"
  }

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
    description = "ssh access"
  }

  egress {
    from_port       = 0
    to_port         = 0
    protocol        = "-1"
    cidr_blocks     = ["0.0.0.0/0"]
    description     = "allow all egress"
    }

  tags = {
    Name = "${var.project}-anchore"
  }

}

resource "aws_instance" "anchore" {
  ami                    = "${var.base_ami}"
  instance_type          = "t2.medium"
  subnet_id              = "${data.aws_subnet.s.id}"
  key_name               = "${var.key_name}"
  vpc_security_group_ids = ["${aws_security_group.anchore.id}"]

  ebs_block_device {
    device_name           = "/dev/xvda"
    volume_size           = 20
    volume_type           = "gp2"
    delete_on_termination = true
  }

  tags = {
    Name = "${var.project}-anchore"
  }

  provisioner "local-exec" {
    command = "sleep 120; ANSIBLE_HOST_KEY_CHECKING=False ansible-playbook -u ec2-user --extra-vars 'master_password=${var.master_password} anchore_url=http://${aws_instance.anchore.public_ip}' --private-key ./keys/key.pem -i '${aws_instance.anchore.public_ip},' ../ansible/anchore.yml"
  }
}

output "ip" {
  value = aws_instance.anchore.public_ip
}
