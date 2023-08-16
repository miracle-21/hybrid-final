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
<link href="<%=path%>/css/rez_info.css" rel="stylesheet">
<script type="text/javascript">
</script>
</head>
<body>
<h2>예약 정보 조회</h2>
<h3>더블클릭시, 예약 상세 정보를 확인할 수 있습니다.</h3>
<%
DAO dao = new DAO();
String name = request.getParameter("name");
if(name==null) name="";
session.setAttribute(name,"name");
//if(phone==null) phone=""; // 처음에는 전체 값이 출력되게 하고, 값이 없으면 공백으로 처리함
//if(name==null) name="";
%>
<div class="container">
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
  <%for(Rez r:dao.getRezList3(name)){ %>
  <tr ondblclick="goDetail(<%=r.getRezidS()%>)"><td><%=r.getRezidS()%><td><%=r.getDate()%></td><td><%=r.getPname() %></td><td><%=r.getName()%></td><td><%=r.getEmail() %></td>
  <td><%=r.getPhone() %></td><td><%=r.getPay() %></td></tr>
  <%} %>
</table>
<input type="button" value="홈화면으로 이동" onclick="location.href='main.jsp'">
<script type="text/javascript">
	function goDetail(rezidS){
		//alert(empno+" 상세화면 이동");
		location.href="rez_detail.jsp?rezidS="+rezidS;
	}
</script>
</body>
</html>