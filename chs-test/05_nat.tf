resource "aws_eip" "hbinT_eip_" {
  count  = 2
  domain = "vpc"
}

resource "aws_nat_gateway" "hbinT_nat_" {
  count         = 2
  allocation_id = aws_eip.hbinT_eip_[count.index].id
  subnet_id     = aws_subnet.hbinT_pub_[count.index].id

  tags = {
    Name = "${var.tag}-nat-${count.index == 0 ? "0" : "1"}"
  }
}
