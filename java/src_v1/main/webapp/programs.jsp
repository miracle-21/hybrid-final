<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="project04.DAO"
    import="project04.vo.*"%>
<%
request.setCharacterEncoding("utf-8");
String path = request.getContextPath();
DAO dao = new DAO();
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로그램 목록</title>
<jsp:include page="topNav.jsp"></jsp:include>
<link href="<%=path%>\css\programs.css" rel="stylesheet">
</head>
<body>
<br><h1>프로그램 목록</h1><br>
<form>
<div>
<table id="pList">
<tr><th>프로그램명</th><th>시간</th><th>프로그램 정보</th><th>예약상태</th></tr>
  <%for(Programs p:dao.getPrgList()){%>
  <tr><td><%=p.getPname()%></td>
  <td><%=p.getPtime()%></td>
  <td>접수기간: <%=p.getSpan1()%>~<%=p.getSpan2()%><br>교육요일: <%=p.getDays()%><br>모집정원: <%=p.getCapacity()%></td>
  <td><input type="button" value="예약하기" 
  onclick="location.href='program_Info.jsp?pname=<%=p.getPname()%>&span1=<%=p.getSpan1()%>&span2=<%=p.getSpan2()%>&ptime=<%=p.getPtime()%>'">
  </td></tr>
  <%} %>
</table>
</div>
</form>

<script>
</script>
</body>
</html>