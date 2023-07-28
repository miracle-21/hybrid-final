resource "aws_internet_gateway" "hbinT_ig" {
  vpc_id = aws_vpc.hbinT_vpc.id

  tags = {
    Name = "${var.tag}-ig"
  }
}
