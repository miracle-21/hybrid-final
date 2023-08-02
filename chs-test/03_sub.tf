resource "aws_subnet" "hbinT_pub_" {
  count             = 2
  vpc_id            = aws_vpc.hbinT_vpc.id
  cidr_block        = "10.100.${count.index == 0 ? "0" : "1"}.0/24"
  availability_zone = "${var.region}${count.index == 0 ? "a" : "c"}"

  tags = {
    Name                              = "${var.tag}-pub-${count.index == 0 ? "a" : "c"}"
    "kubernetes.io/cluster/4team-clu" = "shared"
    "kubernetes.io/role/internal-elb" = 1
    "kubernetes.io/role/elb"          = 1
  }
}

resource "aws_subnet" "hbinT_pri_" {
  count             = 2
  vpc_id            = aws_vpc.hbinT_vpc.id
  cidr_block        = "10.100.${count.index == 0 ? "2" : "3"}.0/24"
  availability_zone = "${var.region}${count.index == 0 ? "a" : "c"}"
  tags = {
    Name                              = "${var.tag}-pri-${count.index == 0 ? "a" : "c"}"
    "kubernetes.io/cluster/4team-clu" = "shared"
    "kubernetes.io/role/internal-elb" = 1
    "kubernetes.io/role/elb"          = 1
  }
}

resource "aws_subnet" "hbinT_db_" {
  count             = 2
  vpc_id            = aws_vpc.hbinT_vpc.id
  cidr_block        = "10.100.${count.index == 0 ? "4" : "5"}.0/24"
  availability_zone = "${var.region}${count.index == 0 ? "a" : "c"}"
  tags = {
    Name                              = "${var.tag}-db-${count.index == 0 ? "a" : "c"}"
    "kubernetes.io/cluster/4team-clu" = "shared"
    "kubernetes.io/role/internal-elb" = 1
    "kubernetes.io/role/elb"          = 1
  }
}
