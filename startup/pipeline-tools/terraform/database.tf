
resource "aws_security_group" "bdso-postgres" {
  name        = "bdso-postgres"
  description = "Security group that allows pgsql ingress"
  # vpc_id      = "${aws_vpc.openshift.id}"

  //postgres
  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_db_instance" "dev" {
  allocated_storage    = 20
  max_allocated_storage = 100
  storage_type         = "gp2"
  engine               = "postgres"
  engine_version       = "11.4"
  instance_class       = "db.t2.micro"
  name                 = "${local.project}dev"
  identifier           = "${local.project}dev"
  username             = "bdsodev"
  password             = "${var.pass}"
  skip_final_snapshot  = true
  publicly_accessible  = true
  vpc_security_group_ids = ["${aws_security_group.bdso-postgres.id}"]
}

resource "aws_db_instance" "test" {
  allocated_storage    = 20
  max_allocated_storage = 100
  storage_type         = "gp2"
  engine               = "postgres"
  engine_version       = "11.4"
  instance_class       = "db.t2.micro"
  name                 = "${local.project}test"
  identifier           = "${local.project}test"
  username             = "bdsotest"
  password             = "${var.pass}"
  skip_final_snapshot  = true
  publicly_accessible  = true
  vpc_security_group_ids = ["${aws_security_group.bdso-postgres.id}"]
}

resource "aws_db_instance" "prod" {
  allocated_storage    = 20
  max_allocated_storage = 100
  storage_type         = "gp2"
  engine               = "postgres"
  engine_version       = "11.4"
  instance_class       = "db.t2.micro"
  name                 = "${local.project}prod"
  identifier           = "${local.project}prod"
  username             = "bdsoprod"
  password             = "${var.pass}"
  skip_final_snapshot  = true
  publicly_accessible  = true
  vpc_security_group_ids = ["${aws_security_group.bdso-postgres.id}"]
}

output "dev_pg_host" {
  value = aws_db_instance.dev.address
}
output "dev_pg_username" {
  value = aws_db_instance.dev.username
}
output "dev_pg_password" {
  value = aws_db_instance.dev.password
}
output "test_pg_host" {
  value = aws_db_instance.test.address
}
output "test_pg_username" {
  value = aws_db_instance.test.username
}
output "test_pg_password" {
  value = aws_db_instance.test.password
}
output "prod_pg_host" {
  value = aws_db_instance.prod.address
}
output "prod_pg_username" {
  value = aws_db_instance.prod.username
}
output "prod_pg_password" {
  value = aws_db_instance.prod.password
}
