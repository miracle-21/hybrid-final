#! /bin/bash
sudo -i
yum update -y
yum install -y mariadb-server.x86_64
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
wget https://downloads.mariadb.com/Connectors/java/connector-java-2.7.4/mariadb-java-client-2.7.4.jar

rm /home/tomcat/apache-tomcat-9.0.76/webapps/ROOT/index.jsp
#"mariadb.cvyqzm7yoa5l.ap-northeast-2.rds.amazonaws.com:3306/hbinTdb"
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
    String driver = "org.mariadb.jdbc.Driver";
    String url = "jdbc:mariadb://mariadb.chaydnayiqdi.ap-northeast-1.rds.amazonaws.com:3306/hbinTdb";
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
    String sql = "SELECT * FROM keyboard";
    rs = stmt.executeQuery(sql);
%>

<h1>Keyboard Data</h1>

<table>
    <tr>
    <th>Category</th>
    <th>Yellow</th>
    <th>Brown</th>
    <th>Red</th>
    <th>Blue</th>
    </tr>
    <% while (rs.next()) { %>
    <tr>
        <td><%= rs.getString("category") %></td>
        <td><%= rs.getInt("yellow") %></td>
        <td><%= rs.getInt("brown") %></td>
        <td><%= rs.getInt("red") %></td>
        <td><%= rs.getInt("blue") %></td>
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
