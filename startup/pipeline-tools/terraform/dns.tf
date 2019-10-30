provider "cloudflare" {
  email = "vijay.deshmukh@ctr.salientcrgt.com"
  api_token = "A8WSfcOAjHf0cseP8G5avT_RVZjLcXLX_HgNON4t"
}

data "cloudflare_zones" "scrgt" {
  filter {
    name   = "${local.domain}"
    status = "active"
    paused = false
  }
}

resource "cloudflare_record" "jenkins" {
zone_id = "${lookup(data.cloudflare_zones.scrgt.zones[0], "id")}"
  domain = "${local.domain}"
  name   = "jenkins.${local.project}"
  value  = "${aws_instance.jenkins.public_ip}"
  type   = "A"
  ttl    = 300
}

resource "cloudflare_record" "sonar" {
zone_id = "${lookup(data.cloudflare_zones.scrgt.zones[0], "id")}"
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
