resource "aws_elasticache_subnet_group" "team4_redis_subg" {
  name       = "redis-subg"
  subnet_ids = concat(aws_subnet.team4_was_[*].id)
}

# resource "aws_elasticache_cluster" "hbinT_redis" {
#   cluster_id           = "hbint-redis"
#   engine               = "redis"
#   node_type            = "cache.t3.medium"
#   num_cache_nodes      = 1
#   parameter_group_name = "default.redis6.x"
#   engine_version       = "6.2"
#   port                 = 6379

#   subnet_group_name          = aws_elasticache_subnet_group.hbinT_redis_subg.name
#   auto_minor_version_upgrade = false
#   ip_discovery               = "ipv4"
#   network_type               = "ipv4"
#   #   notification_topic_arn = "arn"

#   tags = {
#     Name = "${var.tag}-redis"
#   }
# }


resource "aws_elasticache_replication_group" "team4_redis" {
  replication_group_id    = "team4-redis"
  description             = "My Redis Replication Group"
  node_type               = "cache.t3.medium"
  port                    = 6379
  parameter_group_name    = "default.redis6.x.cluster.on"
  engine_version          = "6.2"
  num_node_groups         = 2
  replicas_per_node_group = 2

  multi_az_enabled           = true
  automatic_failover_enabled = true

  subnet_group_name          = aws_elasticache_subnet_group.team4_redis_subg.name
  apply_immediately          = true
  auto_minor_version_upgrade = false
  security_group_ids         = [aws_security_group.team4_sg.id]

  tags = {
    Name = "${var.tag}-redis"
  }
}