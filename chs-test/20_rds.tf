# resource "aws_db_parameter_group" "hbinT_pg" {
#   name   = "mariadb-pg"
#   family = "mariadb10.6"

#   parameter {
#     name = "character_set_server"
#     value = "utf8"
#   }

#   parameter {
#     name = "character_set_client"
#     value = "utf8"
#   }

#   parameter {
#     name = "character_set_connection"
#     value = "utf8"
#   }

#   parameter {
#     name = "character_set_database"
#     value = "utf8"
#   }

#   parameter {
#     name = "character_set_filesystem"
#     value = "utf8"
#   }

#   parameter {
#     name = "character_set_results"
#     value = "utf8"
#   }

#   parameter {
#     name = "collation_server"
#     value = "utf8_general_ci"
#   }

#   parameter {
#     name = "collation_connection"
#     value = "utf8_general_ci"
#   }

#   parameter {
#     name = "time_zone"
#     value = "Asia/Seoul"
#   }
# }

# resource "aws_db_option_group" "hbinT_og" {
#   name                     = "mariadb-og"
#   engine_name              = "mariadb"
#   major_engine_version     = "10.6"
# }

# resource "aws_db_subnet_group" "hbinT_dbsg" {
#   name       = "mariadb-dbsg"
#   subnet_ids = concat(aws_subnet.hbinT_pri_[*].id)
# }

# resource "aws_db_instance" "hbinT_db" {
#   engine                 = "mariadb"
#   engine_version         = "10.6"
#   auto_minor_version_upgrade  = false
#   identifier             = "mariadb"
#   username               = "root"
#   password               = "VMware1!"

#   instance_class         = "db.t3.micro"
#   allocated_storage      = 20
#   max_allocated_storage  = 100
#   storage_type           = "gp2"

#   multi_az = true
#   publicly_accessible      = false 
#   network_type = "IPV4"

#   vpc_security_group_ids = [aws_security_group.hbinT_sg.id]
#   db_subnet_group_name   = aws_db_subnet_group.hbinT_dbsg.name
#   port = 3306

#   db_name                = "hbinTdb"
#   parameter_group_name   = aws_db_parameter_group.hbinT_pg.name
#   option_group_name = aws_db_option_group.hbinT_og.name

#   skip_final_snapshot         = true
#   backup_retention_period = 7

#   replica_mode = "enabled"

#   tags = {
#     Name = "${var.tag}-db"
#   }
# }

# resource "aws_db_instance" "hbinT_db_replica" {
#   replicate_source_db         = aws_db_instance.hbinT_db.identifier
#   identifier                  = "mariadb-replica"
#   instance_class              = aws_db_instance.hbinT_db.instance_class
#   auto_minor_version_upgrade  = false
#   publicly_accessible         = false
#   skip_final_snapshot         = true
#   backup_retention_period = 7

#   tags = {
#     Name = "${var.tag}-db-replica"
#   }
# }