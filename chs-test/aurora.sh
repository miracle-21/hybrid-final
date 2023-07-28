#! /bin/bash
sudo -i
yum update -y
yum install -y mariadb-server
systemctl start mariadb

yum install -y java-1.8.0-openjdk.x86_64
yum install -y java-1.8.0-openjdk-devel.x86_64

echo "export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.332.b09-1.amzn2.0.2.x86_64" | sudo tee -a /etc/profile
echo "export PATH=$PATH:$JAVA_HOME/bin" | sudo tee -a /etc/profile
echo "export CLASSPATH=$JAVA_HOME/jre/lib:$JAVA_HOME/lib/tools.jar" | sudo tee -a /etc/profile

mkdir /home/tomcat
cd /home/tomcat
wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.76/bin/apache-tomcat-9.0.76.tar.gz
tar -zxvf apache-tomcat-9.0.76.tar.gz
rm apache-tomcat-9.0.76.tar.gz

cd /home/tomcat/apache-tomcat-9.0.76/lib
wget https://github.com/awslabs/aws-mysql-jdbc/releases/download/1.1.8/aws-mysql-jdbc-1.1.8.jar

rm /home/tomcat/apache-tomcat-9.0.76/webapps/ROOT/index.jsp
cat > /home/tomcat/apache-tomcat-9.0.76/webapps/ROOT/index.jsp << EOF
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Keyboard Data</title>
<style>
    table {
    border-collapse: collapse;
    }
    th, td {
    border: 1px solid black;
    padding: 8px;
    text-align: left;
    }
</style>
</head>
<body>
<%-- MariaDB 연결 정보 --%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>

<%!
    public Connection getConnection() throws Exception {
    String driver = "software.aws.rds.jdbc.mysql.Driver";
    String url = "jdbc:mysql:aws://aurora-replica-2.cvyqzm7yoa5l.ap-northeast-2.rds.amazonaws.com:3306/hbinTdb";
    String username = "root";
    String password = "VMware1!";
    Class.forName(driver);
    Connection conn = DriverManager.getConnection(url, username, password);
    return conn;
    }
%>

<%
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
    conn = getConnection();
    stmt = conn.createStatement();
    String sql = "SELECT * FROM test";
    rs = stmt.executeQuery(sql);
%>

<h1>Keyboard Data</h1>

<table>
    <tr>
    <th>name</th>
    <th>age</th>
    </tr>
    <% while (rs.next()) { %>
    <tr>
        <td><%= rs.getString("name") %></td>
        <td><%= rs.getInt("age") %></td>
    </tr>
    <% }
    rs.close();
    stmt.close();
    conn.close();
    } catch (Exception e) {
    e.printStackTrace();
    }
%>
</body>
</html>
EOF

echo "export JAVA_HOME=/home/tomcat" | sudo tee -a /etc/profile
echo "export JRE_HOME=/home/tomcat" | sudo tee -a /etc/profile
source /etc/profile
sudo /home/tomcat/apache-tomcat-9.0.76/bin/startup.sh
