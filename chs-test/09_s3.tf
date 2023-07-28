resource "aws_s3_bucket" "hbinT_s3_bucket" {
  bucket = "hbint-bucket"
  acl    = "private"

  force_destroy = true

  versioning {
    enabled = false
  }


  server_side_encryption_configuration {
    rule {
      apply_server_side_encryption_by_default {
        sse_algorithm = "AES256"
      }
    }
  }


  lifecycle_rule {
    id      = "DisableBucketKeys"
    enabled = true
    noncurrent_version_transition {
      days          = 30
      storage_class = "STANDARD_IA"
    }
  }


  tags = {
    Name = "${var.tag}-s3-bucket"
  }
}
