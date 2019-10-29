resource "aws_security_group" "sonar" {
  name        = "${local.project}-sonar"
  description = "sonar necessary ports"
  vpc_id      = "${module.network.vpc}"

  ingress {
    from_port   = 9000
    to_port     = 9000
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
    description = "sonarqube web frontend"
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
    Name = "${local.project}-sonar"
  }

}

resource "aws_instance" "sonar" {
  ami                    = "${local.base_ami}"
  instance_type          = "t2.medium"
  subnet_id              = "${module.network.public_subnet_a}"
  key_name               = "${module.network.key_name}"
  vpc_security_group_ids = ["${aws_security_group.sonar.id}"]

  ebs_block_device {
    device_name           = "/dev/xvda"
    volume_size           = 20
    volume_type           = "gp2"
    delete_on_termination = true
  }

  tags = {
    Name = "${local.project}-sonar"
  }

  provisioner "local-exec" {
    command = "echo '${var.pass}' > vault.pass; sleep 120; ANSIBLE_HOST_KEY_CHECKING=False ansible-playbook --vault-password-file=vault.pass -u ec2-user --extra-vars 'master_password=${var.pass} sonar_ip=${aws_instance.sonar.public_ip}' --private-key ./keys/key.pem -i '${aws_instance.sonar.public_ip},' ../ansible/sonarqube.yml; rm vault.pass"
  }
}

output "sonar_ip" {
  value = aws_instance.sonar.public_ip
}
