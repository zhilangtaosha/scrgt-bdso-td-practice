//  Notes: We could make the internal domain a variable, but not sure it is
//  really necessary.

//  Create the internal DNS.
resource "aws_route53_zone" "internal" {
  name = "openshift.local"
  comment = "OpenShift Cluster Internal DNS"
  vpc {
    vpc_id = "${aws_vpc.openshift.id}"
  }
  tags = {
    Name    = "OpenShift Internal DNS"
    Project = "openshift"
  }
}

//  Routes for 'master', 'node1' and 'node2'.
resource "aws_route53_record" "master-a-record" {
    zone_id = "${aws_route53_zone.internal.zone_id}"
    name = "master.openshift.local"
    type = "A"
    ttl  = 300
    records = [
        "${aws_instance.master.private_ip}"
    ]
}
resource "aws_route53_record" "node1-a-record" {
    zone_id = "${aws_route53_zone.internal.zone_id}"
    name = "node1.openshift.local"
    type = "A"
    ttl  = 300
    records = [
        "${aws_instance.node1.private_ip}"
    ]
}
resource "aws_route53_record" "node2-a-record" {
    zone_id = "${aws_route53_zone.internal.zone_id}"
    name = "node2.openshift.local"
    type = "A"
    ttl  = 300
    records = [
        "${aws_instance.node2.private_ip}"
    ]
}

provider "cloudflare" {
  email = "vijay.deshmukh@ctr.salientcrgt.com"
  api_token = "A8WSfcOAjHf0cseP8G5avT_RVZjLcXLX_HgNON4t"
}

data "cloudflare_zones" "scrgt" {
  filter {
    name   = "${var.domain}"
    status = "active"
    paused = false
  }
}

resource "cloudflare_record" "ocp" {
  zone_id = "${lookup(data.cloudflare_zones.scrgt.zones[0], "id")}"
  name   = "ocp.${var.subdomain}"
  value  = "${aws_eip.master_eip.public_ip}"
  type   = "A"
  ttl    = 300
}

resource "cloudflare_record" "wildcard" {
  zone_id = "${lookup(data.cloudflare_zones.scrgt.zones[0], "id")}"
  name   = "*.ocp.${var.subdomain}"
  value  = "${aws_eip.master_eip.public_ip}"
  type   = "A"
  ttl    = 300
}
