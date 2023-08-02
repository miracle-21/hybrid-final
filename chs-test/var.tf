variable "region" {
  type        = string
  description = "region name"
  default     = "ap-northeast-2"
}

variable "cidr" {
  type    = string
  default = "10.100.0.0/16"
}

variable "tag" {
  type    = string
  default = "hbinT"
}

variable "rocidr" {
  type    = string
  default = "0.0.0.0/0"
}

variable "akey" {
  type      = string
  default   = ""
  sensitive = true
}

variable "skey" {
  type      = string
  default   = ""
  sensitive = true
}
