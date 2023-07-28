resource "aws_route_table" "hbinT_rtb_pub" {
  vpc_id = aws_vpc.hbinT_vpc.id

  route {
    cidr_block = var.rocidr
    gateway_id = aws_internet_gateway.hbinT_ig.id
  }

  tags = {
    Name = "${var.tag}-rtb-pub"
  }
}

resource "aws_route_table" "hbinT_rtb_pri" {
  vpc_id = aws_vpc.hbinT_vpc.id

  route {
    cidr_block = var.rocidr
    gateway_id = aws_nat_gateway.hbinT_nat_[0].id
  }

  tags = {
    Name = "${var.tag}-rtb-pri}"
  }
}

resource "aws_route_table" "hbinT_rtb_db" {
  vpc_id = aws_vpc.hbinT_vpc.id

  route {
    cidr_block = var.rocidr
    gateway_id = aws_nat_gateway.hbinT_nat_[1].id
  }

  tags = {
    Name = "${var.tag}-rtb-db}"
  }
}
