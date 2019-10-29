resource "aws_security_group" "jenkins" {
  name        = "${local.project}-jenkins"
  description = "jenkins necessary ports"
  vpc_id      = "${module.network.vpc}"

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
    description = "jenkins web frontend"
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
    Name = "${local.project}-jenkins"
  }

}


resource "aws_instance" "jenkins" {
  ami                    = "${local.base_ami}"
  instance_type          = "t2.medium"
  subnet_id              = "${module.network.public_subnet_a}"
  key_name               = "${module.network.key_name}"
  vpc_security_group_ids = ["${aws_security_group.jenkins.id}"]

  ebs_block_device {
    device_name           = "/dev/xvda"
    volume_size           = 100
    volume_type           = "gp2"
    delete_on_termination = true
  }

  tags = {
    Name = "${local.project}-jenkins"
  }

  depends_on = [aws_instance.sonar]

  provisioner "local-exec" {
    command = "echo '${var.pass}' > vault.pass; sleep 120; ANSIBLE_HOST_KEY_CHECKING=False ansible-playbook --vault-password-file=vault.pass -u ec2-user --extra-vars 'jenkins_url=http://${aws_instance.jenkins.public_ip} domain=${local.project}.${local.domain} sonar=${aws_instance.sonar.public_ip}' --private-key ./keys/key.pem -i '${aws_instance.jenkins.public_ip},' ../ansible/jenkins.yml; rm vault.pass"
  }
}

output "jenkins_ip" {
  value = aws_instance.jenkins.public_ip
}
