<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<%
	request.setCharacterEncoding("utf-8");
	String path = request.getContextPath();
%>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../topNav.jsp"></jsp:include>
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
				<p class="active">01. 약관동의</p>
			</li>
			<li>
				<p>02. 정보입력</p>
			</li>
			<li>
				<p>03. 가입완료</p>
			</li>
		</ul>
	</div>
	<div class="join-wrap mt50">
		<p class="sub-title mb30">사이트이용약관</p>

		<div class="sub-txt">
			<p class="sub-iconTitle">이용약관,개인정보 수집 및 이용에 모두 동의합니다.</p>
			<span class="agree-check"> 
			<input type="checkbox" class="join" name="allCheck" 
			id="allCheck" title="모두 동의합니다" onclick="allCheck(this)">
				<label for="allCheck">모두 동의합니다.</label>
			</span>
		</div>

		<div class="join-content">
			<div class="sub-txt">
				<p class="sub-iconTitle">국립생태원 사이트 이용약관 동의(필수)</p>
				<span class="agree-check"> <input type="checkbox"
					class="join" name="checkAgree1" id="checkAgree1" title="동의합니다">
					<label for="checkAgree1">동의합니다.</label>
				</span>
			</div>

			<div class="sub-textareaBox" tabindex="0">
				<p>약관 내용</p>
			</div>

			<div class="sub-txt mt40">
				<p class="sub-iconTitle">개인정보 수집 및 이용에 대한 안내</p>
				<span class="agree-check"> <input type="checkbox"
					class="join" name="checkAgree2" id="checkAgree2" title="동의합니다" /> <label
					for="checkAgree2">동의합니다.</label>
				</span>
			</div>

			<div class="sub-textareaBox" tabindex="0">
				<p>안내 내용</p>
			</div>
		</div>
		<div class="board-btn">
			<a href="#" class="board-btn__item write"
				onclick="nextPage();return false;" title="동의함">동의함</a>
		</div>
	</div>
</body>
<script type="text/javascript">
function allCheck(obj){
	var checkAgree1 = document.querySelector("[name=checkAgree1]");
	var checkAgree2 = document.querySelector("[name=checkAgree2]");
	if(obj.checked){
		checkAgree1.checked = true;
		checkAgree2.checked = true;
	} else {
		checkAgree1.checked = false;
		checkAgree2.checked = false;
	}
}

function nextPage(){
	var checkAgree1 = document.querySelector("[name=checkAgree1]");
	if(!checkAgree1.checked){
		alert("서비스 이용약관에 동의 하셔야 회원가입이 가능 합니다.");
		$('#checkAgree1').focus();
		return;
	}
	var checkAgree2 = document.querySelector("[name=checkAgree2]");
	if(!checkAgree2.checked){
		alert("개인정보 처리지침 약관에 동의 하셔야 회원가입이 가능 합니다.");
		$('#checkAgree2').focus();
		return;
	}
	var terms = document.querySelector("[name=terms]");
	terms.agree.value = "Y";
	terms.submit();
	return false;
}
</script>
</html>