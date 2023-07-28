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
  default = "chs"
}

variable "rocidr" {
  type    = string
  default = "0.0.0.0/0"
}

variable "key" {
  type      = string
  default   = ""
  sensitive = true
}
