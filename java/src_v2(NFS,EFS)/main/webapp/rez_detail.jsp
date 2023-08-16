<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"  
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
<title>예약 상세정보</title>
<jsp:include page="topNav.jsp"></jsp:include>
<link href="<%=path%>/css/rez_info.css" rel="stylesheet">
</head>
<body>
<%
String rezidS = request.getParameter("rezidS");
int rezid = Integer.parseInt(rezidS);
DAO dao = new DAO();
%>
</body>
<h2>예약 상세정보</h2>
<table id="customers">
  <tr>
    <th>예약번호</th>
    <th>예약날짜</th>
    <th>프로그램명</th>
    <th>이름</th>
    <th>이메일</th>
    <th>연락처</th>
    <th>결제방법</th>
  </tr>
  <%for(Rez r:dao.getRezList(rezid)){ %>
  <tr><td><%=r.getRezidS()%><td><%=r.getDate()%></td><td><%=r.getPname() %></td><td><%=r.getName()%></td><td><%=r.getEmail() %></td>
  <td><%=r.getPhone() %></td><td><%=r.getPay() %></td></tr>
  <%} %>
</table>
<div class="row">
    <input type="button" value="목록" onclick="location.href='rez_info.jsp'">
    <input type="button" value="예약취소" onclick="location.href='rez_delete.jsp?rezid=<%=rezid%>'"/>
</div>
</html>
