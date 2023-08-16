<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"  
    import="project04.DAO"
	import="project04.vo.*"   
    %> 
<%
String path = request.getContextPath();
String rezidS = request.getParameter("rezid");

if(rezidS!=null && !rezidS.trim().equals("")){
	// 등록할 Emp() 객체 생성
	int rezid = Integer.parseInt(rezidS);
	DAO dao = new DAO();
	System.out.println(rezid);
	dao.deleteRez(rezid);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 상세정보</title>
<jsp:include page="topNav.jsp"></jsp:include>
<link href="<%=path %>/css/rez_info.css" rel="stylesheet">
</head>
<body>
<h2>예약 취소되었습니다.</h2>
<input type="button" value="홈화면으로 이동" onclick="location.href='main.jsp'">
</body>

</html>
