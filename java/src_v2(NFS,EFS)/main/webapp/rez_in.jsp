<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
import="project04.DAO"
import="project04.vo.*"  
%>
<!DOCTYPE html>
<html>
<head>
<%
request.setCharacterEncoding("utf-8");
String path = request.getContextPath();
%>
<link href="<%=path%>\css\rez.css" rel="stylesheet">
<meta charset="UTF-8">
<title>예약하기</title>
<jsp:include page="topNav.jsp"></jsp:include>
</head>
<body>
<%
String pname = request.getParameter("pname");
session.setAttribute(pname,"pname");
String span1 = request.getParameter("span1");
session.setAttribute(pname,"span1");
String span2 = request.getParameter("span2");
session.setAttribute(pname,"span2");
String totSpan = span1+"~"+span2;
String ptime = request.getParameter("ptime");
session.setAttribute(ptime,"ptime");
%>
<h2>예약하기</h2>
<%
String date = request.getParameter("date");
if(date==null) date="";
//String pname = request.getParameter("pname");
//if(pname==null) pname="";
String name = request.getParameter("name");
if(name==null) name="";
session.setAttribute(name,"name");
String email = request.getParameter("email");
if(email==null) email="";
String phone = request.getParameter("phone");
if(phone==null) phone="";
String pay = request.getParameter("pay");
if(pay==null) pay="";
String isIns = "N";
String rezdate=date+" "+ptime;

// 등록처리를 위한 조건
if(name!=null && !name.trim().equals("")){
	DAO dao = new DAO();
	dao.insertRez_2(new Rez(rezdate,pname,name,email,phone,pay));
	//String id, String date, String pname, String name, String email, int phone, String pay
	isIns="Y";
}
%>
<script type="text/javascript">
	var isIns = "<%=isIns%>";
	var name = "<%=name%>";
	if(isIns=="Y"){
		if(confirm("등록 완료\n조회화면으로 이동하시겠습니까?")){
			location.href="rez_info.jsp?name="+name;
		}else{
			location.href="main.jsp";
		}
	}
</script>

<div>
  <form>
    <div class="row">
    <div class="col-25">
      <label for="date">날짜 / 예약 가능한 기간: <%=totSpan%></label>
    </div>
    <div class="col-75">
    	<input type="text" name="date" placeholder="날짜 입력(YYYY-MM-DD)">
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <input type="hidden" name="pname" value="<%=pname%>">프로그램명: <%=pname %>
    </div>
    <div class="col-25">
      <input type="hidden" name="ptime" value="<%=ptime%>">시간: <%=ptime %>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label for="name">이름</label>
    </div>
    <div class="col-75">
      <input type="text" name="name" placeholder="이름 입력..">
    </div>
  </div>
   <div class="row">
    <div class="col-25">
      <label for="email">이메일</label>
    </div>
    <div class="col-75">
      <input type="text" name="email" placeholder="이메일 입력..">
    </div>
  </div>
   <div class="row">
    <div class="col-25">
      <label for="phone">연락처</label>
    </div>
    <div class="col-75">
      <input type="text" name="phone" placeholder="010-xxxx-xxxx">
    </div>
  </div>
   <div class="row">
    <div class="col-25">
      <label for="pay">결제방법</label>
    </div>
    <div class="col-75">
    	<select name="pay">
    		<option value="">선택</option>
    		<option value="현장결제">현장결제</option>
    	</select>
    </div>
  </div>
  <div class="row">
    <input type="submit" value="등록">
  </div>
  </form>
</div>

</body>
</html>