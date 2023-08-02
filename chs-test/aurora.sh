#! /bin/bash
sudo -i
yum update -y
yum install -y mariadb-server
systemctl start mariadb
amazon-linux-extras install -y redis6

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
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Keyboard Data, session-test</title>
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
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

<%!
    public Connection getConnection() throws Exception {
    String driver = "software.aws.rds.jdbc.mysql.Driver";
    String url = "jdbc:mysql:aws://aurora-db.cluster-cvyqzm7yoa5l.ap-northeast-2.rds.amazonaws.com:3306/hbinTdb";
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

<table border=1 bordercolor="gray" cellspacing=1 cellpadding=0 width="100%">
    <tr bgcolor="gray">
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
</table>
<br>
<table border=1 bordercolor="gray" cellspacing=1 cellpadding=0 width="100%">
      <tr bgcolor="gray">
       <td colspan=2 align="center"><font color="white"><b>Session Info</b></font></td>
      </tr>
     <tr>
       <td>Server HostName</td>
       <td><%=java.net.InetAddress.getLocalHost().getHostName()%></td>
     </tr>
     <tr>
       <td>Server IP</td>
       <td><%=java.net.InetAddress.getLocalHost().getHostAddress()%></td>
     </tr>
     <tr>
       <td>Request SessionID</td>
       <td><%=request.getRequestedSessionId()%></td>
     </tr>
     <tr>
       <td>SessionID</td>
       <td><%=session.getId()%></td>
     </tr>
</table>
</br>
<%
 int count = 0;
 if(session.getAttribute("count") != null)
 count = (Integer) session.getAttribute("count");
 count += 1;
 session.setAttribute("count", count);
 out.println(session.getId() + " : " + count);
%>
</body>
</html>
EOF

wget https://github.com/ran-jit/tomcat-cluster-redis-session-manager/releases/download/3.0.3/tomcat-cluster-redis-session-manager.zip
unzip tomcat-cluster-redis-session-manager.zip
rm -rf tomcat-cluster-redis-session-manager.zip
cp tomcat-cluster-redis-session-manager/lib/* /home/tomcat/apache-tomcat-9.0.76/lib/
cp tomcat-cluster-redis-session-manager/conf/* /home/tomcat/apache-tomcat-9.0.76/conf/

sed -i 's/redis\.hosts=127.0.0.1/redis\.hosts=hbint-redis.tcmsce.clustercfg.apn2.cache.amazonaws.com/g' /home/tomcat/apache-tomcat-9.0.76/conf/redis-data-cache.properties
sed -i 's/redis\.cluster\.enabled=false/redis\.cluster\.enabled=true/g' /home/tomcat/apache-tomcat-9.0.76/conf/redis-data-cache.properties
sed -i '/<Context>/a \ \ \ \ <Manager className="tomcat.request.session.redis.SessionManager" />' /home/tomcat/apache-tomcat-9.0.76/conf/context.xml
sed -i '/<Context>/a \ \ \ \ <Valve className="tomcat.request.session.redis.SessionHandlerValve" />' /home/tomcat/apache-tomcat-9.0.76/conf/context.xml
sed -i 's/<session-timeout>30<\/session-timeout>/<session-timeout>60<\/session-timeout>/g' /home/tomcat/apache-tomcat-9.0.76/conf/web.xml

echo "export JAVA_HOME=/home/tomcat" | sudo tee -a /etc/profile
echo "export JRE_HOME=/home/tomcat" | sudo tee -a /etc/profile
source /etc/profile
sudo /home/tomcat/apache-tomcat-9.0.76/bin/shutdown.sh
sudo /home/tomcat/apache-tomcat-9.0.76/bin/startup.sh
