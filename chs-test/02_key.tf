resource "aws_key_pair" "hbinT_key" {
  key_name   = "chs-key"
  public_key = ""
}

resource "aws_key_pair" "hbinT_key1" {
  key_name   = "chs-key1"
  public_key = file("./chs-key.pub")
}
