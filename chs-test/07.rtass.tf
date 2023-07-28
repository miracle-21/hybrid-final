resource "aws_route_table_association" "hbinT_rtass_pub" {
  count          = 2
  subnet_id      = aws_subnet.hbinT_pub_[count.index].id
  route_table_id = aws_route_table.hbinT_rtb_pub.id
}

resource "aws_route_table_association" "hbinT_rtass_pri" {
  count          = 2
  subnet_id      = aws_subnet.hbinT_pri_[count.index].id
  route_table_id = aws_route_table.hbinT_rtb_pri.id
}

resource "aws_route_table_association" "hbinT_rtass_db" {
  count          = 2
  subnet_id      = aws_subnet.hbinT_db_[count.index].id
  route_table_id = aws_route_table.hbinT_rtb_db.id
}
