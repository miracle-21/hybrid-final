<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="project04.DAO"
	import="project04.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
String path = request.getContextPath();
String id = request.getParameter("id");
%>
<jsp:include page="../topNav.jsp"></jsp:include>
<script type="text/javascript" src="<%=path%>/util/check.js"></script>
<script type="text/javascript" src="<%=path%>/util/cookie.js"></script>
<style type="text/css">
.sub-txtz{
text-align : center;
display : flex;
justify-content : center;
font-size:18px;
}
</style>
<link href="<%=path%>/css/layout.css" rel="stylesheet"/>
<link href="<%=path%>/css/layout2.css" rel="stylesheet"/>
<link href="<%=path%>/css/common.css" rel="stylesheet"/>
</head>
<body>
	<form name="terms" action="register.jsp">
		<input type="hidden" name="agree" id="agree" />
	</form>
	<header>
		<h2 class="sub-page__title">회원가입</h2>
	</header>
	<div>
		<ul class="join-process">
			<li>
				<p>01. 약관동의</p>
			</li>
			<li>
				<p>02. 정보입력</p>
			</li>
			<li>
				<p class="active">03. 가입완료</p>
			</li>
		</ul>
	</div>
	<div class="join-wrap mt50">
		<p class="sub-title mb30">가입완료</p>

		<div class="join-content">
			<div class="sub-txtz">
				<img alt="가입완료" src="https://www.ultari.go.kr/resource/2018/images/sub/member_icon2.png"><br>
			</div>
			<div class="sub-txtz">
				<p>국립생태원 회원가입이 완료되었습니다.</p><br>
			</div>
			<div class="sub-txtz">
				<p>아이디는 <%=id %> 입니다.</p><br>
			</div>
		</div>
		<div class="board-btn">
			<a href="#" class="board-btn__item write"
				onclick="location.href='../main.jsp';return false;" title="메인홈">국립생태원 홈</a>
			<a href="#" class="board-btn__item cancel"
				onclick="location.href='../main.jsp';return false;" title="예약">예약시스템 홈</a>
		</div>
	</div>
</body>

</body>
</html>