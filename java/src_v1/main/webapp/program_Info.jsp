<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="project04.DAO"
    import="project04.vo.*"
    %>
<%
request.setCharacterEncoding("utf-8");
String path = request.getContextPath();
%> 
<!DOCTYPE html>
<html>
	<link href="<%=path%>\css\progInfo.css" rel="stylesheet">
	<style type="text/css">
	</style>
<head>
	<meta charset="UTF-8">
	<title>프로그램 상세정보</title>
	<style>

	</style>
	
</head>
<%
DAO dao = new DAO();
String pname = request.getParameter("pname");
String span1 = request.getParameter("span1");
String span2 = request.getParameter("span2");
String ptime = request.getParameter("ptime");
String totSpan = span1+"~"+span2;

session.setAttribute("pname",pname);
session.setAttribute("span1",span1);
session.setAttribute("span2",span2);
session.setAttribute("ptime",ptime);
%>
<script type="text/javascript">

</script>

<body>
<h1>프로그램 상세 정보</h1>
<div>
  <%for(Programs p:dao.getPrgList(pname)){ %>
  <h2><%=pname%></h2>
  <h3>접수기간: <%=totSpan%></h3>
  <table>
  		<tr>
  		<th>구분</th>
  		<td><%=p.getCategory()%></td>
  		</tr>
  		<tr>
  		<th>교육대상</th>
  		<td><%=p.getTarget()%></td>
  		</tr>
  		<tr>
  		<th>교육요일</th>
  		<td><%=p.getDays()%></td>
  		</tr><tr>
  		<th>모집정원</th>
  		<td><%=p.getCapacity()%></td>
  		</tr><tr>
  		<th>시간</th>
  		<td><%=p.getPtime()%></td>
  		</tr><tr>
  		<th>모이는 장소</th>
  		<td><%=p.getLoc()%></td>
  		</tr><tr>
  		<th>학습내용</th>
  		<td><%=p.getContents()%></td>
  		</tr>
  </table>
  <%} %>
  <input type="button" class="button" value="목록" onclick="location.href='program_info.jsp'">
  <input type="button" class="button" value="예약하기" onclick="location.href='rez_in.jsp?pname=<%=pname%>&span1=<%=span1%>&span2=<%=span2%>&ptime=<%=ptime%>'" style="float: right;">
</div>

</body>
</html>