<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
import="project04.DAO"
import="project04.vo.*"  
%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="topNav.jsp"></jsp:include>
<link href="<%=path %>/css/rez_info.css" rel="stylesheet">
<script type="text/javascript">
</script>
</head>
<body>
<h2>예약하기</h2>
<br>
<table>
  <tr><th><input type="button" value="프로그램목록" onclick="location.href='programs.jsp'"><br></th>
  	  <th><input type="button" value="예약조회" onclick="location.href='rez_info.jsp'"></th></tr>
</table>

</body>
</html>