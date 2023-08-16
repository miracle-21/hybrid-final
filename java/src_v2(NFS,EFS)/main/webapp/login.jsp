<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="project04.vo.Account"
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
%>
<jsp:include page="topNav.jsp"></jsp:include>
<script type="text/javascript" src="<%=path%>/util/check.js"></script>
<script type="text/javascript" src="<%=path%>/util/cookie.js"></script>
<link href="<%=path%>/css/login.css" rel="stylesheet">
</head>
<body>
<%
String id = request.getParameter("id");
String pw = request.getParameter("pw");
if(!(inputCheck.isEmpty(id) || inputCheck.isEmpty(pw))){
	DAO dao = new DAO();
	if(dao.login(id, pw) == 1){
		session.setAttribute("curId", id);
		session.setMaxInactiveInterval(60*10); // 로그인 세션 10분간 유지.
		%>
		<script type="text/javascript">
		alert("<%=id%>님 "+"로그인 성공");
		location.href = "main.jsp";
		</script>
<%		
	}
}
%>

	<div class="login-wrap">
		<header>
			<h2>Login</h2>
		</header>
		<div>
		<h3>국립생태원을 찾아주셔서 감사합니다.</h3>
		로그인을 하시면 더욱 다양한 서비스를 이용하실 수 있습니다.
		</div>

		<form onsubmit="return actionLogin(this);">
			<div class="input-box">
				<input id="id" type="text" name="id" placeholder="아이디">
				<label for="id">아이디</label>
			</div>
			<div class="input-box">
				<input id="pw" type="password" name="pw" placeholder="비밀번호">
				<label for="pw">비밀번호</label>
			</div>
			<div>
			<input type="checkbox" name="saveId">아아디 저장
			</div>
			<input type="submit" value="로그인">
		</form>
	</div>
</body>

<script>
	var cookie_id = getCookie('saveId');
	if(!isEmpty(cookie_id)){
		var id = document.querySelector("[name=id]");
		var saveId = document.querySelector("[name=saveId]");
		id.value = cookie_id;
		saveId.checked = true;
	}

	function actionLogin(form) {
		//alert("check");
		var id = document.querySelector("[name=id]");
		var pw = document.querySelector("[name=pw]");

		if (isEmpty(id.value) || haveBlank(id.value)) {
			alert("아이디 미입력 혹은 공백문자가 있습니다.");
			id.focus();
			return false;
		}

		if (isEmpty(pw.value) || haveBlank(pw.value)) {
			alert("비밀번호 미입력 혹은 공백문자가 있습니다.");
			pw.focus();
			return false;
		}
		
		var saveId = document.querySelector("[name=saveId]");
		if(saveId.checked){
			setCookie('saveId', id.value, 3);
		} else {
			setCookie('saveId', 0, -1);
		}
		return true;
	}
</script>
</html>