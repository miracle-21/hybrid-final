resource "aws_eks_cluster" "hbinT_clu" {
  name     = "hbinT-clu"
  role_arn = aws_iam_role.cluster_role.arn
  version  = "1.27"
  vpc_config {
    subnet_ids              = concat(aws_subnet.hbinT_pri_[*].id, aws_subnet.hbinT_pub_[*].id, aws_subnet.hbinT_db_[*].id)
    endpoint_private_access = true
    endpoint_public_access  = true
    security_group_ids      = [aws_security_group.hbinT_sg.id]
  }
  depends_on = [
    aws_iam_role_policy_attachment.eks-AmazonEKSClusterPolicy,
    aws_iam_role_policy_attachment.eks-AmazonEKSVPCResourceController,
  ]
}

data "aws_iam_policy_document" "assume_role" {
  statement {
    effect = "Allow"

    principals {
      type        = "Service"
      identifiers = ["eks.amazonaws.com"]
    }

    actions = ["sts:AssumeRole"]
  }
}

resource "aws_iam_role" "cluster_role" {
  name               = "eks-cluster-role"
  assume_role_policy = data.aws_iam_policy_document.assume_role.json
}

resource "aws_iam_role_policy_attachment" "eks-AmazonEKSClusterPolicy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSClusterPolicy"
  role       = aws_iam_role.cluster_role.name
}

resource "aws_iam_role_policy_attachment" "eks-AmazonEKSVPCResourceController" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSVPCResourceController"
  role       = aws_iam_role.cluster_role.name
}

# resource "aws_eks_addon" "eks_coredns" {
#   cluster_name                = aws_eks_cluster.hbinT_clu.name
#   addon_name                  = "coredns"
#   addon_version               = "v1.10.1-eksbuild.2"
#   resolve_conflicts_on_create = "OVERWRITE"
# }

resource "aws_eks_addon" "eks_cni" {
  cluster_name                = aws_eks_cluster.hbinT_clu.name
  addon_name                  = "vpc-cni"
  addon_version               = "v1.13.2-eksbuild.1"
  resolve_conflicts_on_create = "OVERWRITE"
}

# resource "aws_eks_addon" "eks_proxy" {
#   cluster_name                = aws_eks_cluster.hbinT_clu.name
#   addon_name                  = "kube-proxy"
#   addon_version               = "v1.27.3-eksbuild.1"
#   resolve_conflicts_on_create = "OVERWRITE"
# }
