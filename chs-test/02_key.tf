resource "aws_key_pair" "hbinT_key" {
  key_name   = "chs-key"
  public_key = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQDDMOBFTJYEZtJfOiZXVmOsUM32e9+VJM56MShpFmHHNpGxeYDg3wxZOt8yRKF0rJYlalABqeNKlJBAAbwPPllkZ2W5TNyQS/ED1P5tl9nrl+caO3Gbt5LhqVBtbjEwHS4rlm7j9UQkAXjVnypSkZIpsTW6tBy3+A1zk71jpeGQ90dOT/r9d068rJJx72dpa6uhFDGxN+OlWZPa7jSGCXdWHAqOEoAG9IrwptpZQAOQFmfOpSVRefaYfL+1+hJAlmOQyqvYZdRHoDTubaa5TJF7as0XVkkxl9sU3KvWVOattfL9f9LIwzBQhKZB1fy9i28DhMw2gjgBzoZV5ssF6eVVvr0woJh31eXbUFnyh6XUfvAkB3vZdp+YY5v+P0Lhyo2se6J63sfc5R1glCjXctiFdgNuDYHK32yrgSvERGeSmOj23iBS5WicexOQSGP4qEaC5jjOngrIKgiqOCoKuRTGg+mkB/3qUmGj/Ot6x20hH7toK6njIJm423gaCQz0Kk8="
}

resource "aws_key_pair" "hbinT_key1" {
  key_name   = "chs-key1"
  public_key = file("./chs-key.pub")
}
