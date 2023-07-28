#!/bin/bash
sudo -i
yum update -y
amazon-linux-extras install -y epel
yum install -y s3fs-fuse

mkdir /home/ec2-user/mnt
mkdir /home/ec2-user/mnt/hbint-bucket
echo "'${var.access_key}':'${var.secret_key}'" > /home/ec2-user/.s3_credentials
chmod 600 /home/ec2-user/.s3_credentials
s3fs hbint-bucket /home/ec2-user/mnt/hbint-bucket -o passwd_file=/home/ec2-user/.s3_credentials

curl -O https://s3.us-west-2.amazonaws.com/amazon-eks/1.23.17/2023-05-11/bin/linux/amd64/kubectl
chmod +x ./kubectl
mkdir -p $$HOME/bin && cp ./kubectl $$HOME/bin/kubectl && export PATH=$$PATH:$$HOME/bin
echo 'export PATH=$PATH:$HOME/bin' >> ~/.bashrc

