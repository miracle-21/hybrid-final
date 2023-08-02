# resource "aws_instance" "hbinT_ec2_pub" {
#   ami                         = "ami-0ea4d4b8dc1e46212" #"ami-0ea4d4b8dc1e46212"
#   instance_type               = "t2.micro"
#   key_name                    = "chs-key"
#   vpc_security_group_ids      = [aws_security_group.hbinT_sg.id]
#   availability_zone           = "${var.region}a"
#   private_ip                  = "10.100.0.10"
#   subnet_id                   = aws_subnet.hbinT_pub_[0].id
#   associate_public_ip_address = true
#   user_data                   = filebase64("nginx.sh")

#   tags = {
#     Name = "${var.tag}-ec2-pub"
#   }
# }

output "bastion_ip" {
  value = aws_instance.hbinT_ec2_bastion.public_ip
}

# resource "aws_instance" "hbinT_ec2_pri" {
#   ami                    = "ami-0ea4d4b8dc1e46212"
#   instance_type          = "t2.micro"
#   key_name               = "chs-key"
#   vpc_security_group_ids = [aws_security_group.hbinT_sg.id]
#   availability_zone      = "${var.region}c"
#   private_ip             = "10.100.3.10"
#   subnet_id              = aws_subnet.hbinT_pri_[1].id
#   user_data              = filebase64("aurora.sh")

#   tags = {
#     Name = "${var.tag}-ec2-pri"
#   }
# }

resource "aws_instance" "hbinT_ec2_bastion" {
  ami                         = "ami-0ea4d4b8dc1e46212"
  instance_type               = "t2.micro"
  key_name                    = "chs-key"
  vpc_security_group_ids      = [aws_security_group.hbinT_sg.id]
  availability_zone           = "${var.region}c"
  private_ip                  = "10.100.1.10"
  subnet_id                   = aws_subnet.hbinT_pub_[1].id
  associate_public_ip_address = true
  iam_instance_profile        = aws_iam_instance_profile.instance_profile.name
  # user_data                   = filebase64("bastion.sh")
  user_data = <<-EOF
  #!/bin/bash
  sudo -i
  yum update -y
  amazon-linux-extras install -y epel
  yum install -y s3fs-fuse
  
  yum install -y docker
  systemctl start docker

  curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /
  mv /eksctl /bin

  curl -O https://s3.us-west-2.amazonaws.com/amazon-eks/1.23.17/2023-05-11/bin/linux/amd64/kubectl
  chmod +x ./kubectl
  mkdir -p $HOME/bin && cp ./kubectl $HOME/bin/kubectl && export PATH=$PATH:$HOME/bin
  echo 'export PATH=$PATH:$HOME/bin' >> ~/.bashrc
  mv kubectl /bin/

  source <(kubectl completion bash)
  echo "source <(kubectl completion bash)" >> ~/.bashrc

  mkdir /home/ec2-user/mnt
  mkdir /home/ec2-user/mnt/hbint-bucket
  echo "${var.akey}:${var.skey}" > /home/ec2-user/.s3_credentials
  chmod 600 /home/ec2-user/.s3_credentials
  s3fs hbint-bucket /home/ec2-user/mnt/hbint-bucket -o passwd_file=/home/ec2-user/.s3_credentials
  EOF

  tags = {
    Name = "${var.tag}-bastion"
  }
}

