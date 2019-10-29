provider "cloudflare" {
  email = "amit@techsur.solutions"
  api_token = "aY2oUFUh1aS4dwGVHa1aVlP63SnTvlZssHr5SARF"
}

resource "cloudflare_record" "jenkins" {
  domain = "${local.domain}"
  name   = "jenkins.${local.project}"
  value  = "${aws_instance.jenkins.public_ip}"
  type   = "A"
  ttl    = 300
}

resource "cloudflare_record" "sonar" {
  domain = "${local.domain}"
  name   = "sonar.${local.project}"
  value  = "${aws_instance.sonar.public_ip}"
  type   = "A"
  ttl    = 300
}

# resource "cloudflare_record" "anchore" {
#   domain = "${local.domain}"
#   name   = "anchore.${local.project}"
#   value  = "${aws_instance.anchore.public_ip}"
#   type   = "A"
#   ttl    = 300
# }

output "jenkins" {
  value = "jenkins.${local.project}.${local.domain}"
}
output "sonar" {
  value = "sonar.${local.project}.${local.domain}"
}
# output "anchore" {
#   value = "anchore.${local.project}.${local.domain}"
# }
