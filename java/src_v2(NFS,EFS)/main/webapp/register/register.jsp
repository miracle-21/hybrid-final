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
request.setCharacterEncoding("utf-8");

String name = request.getParameter("name"); name=inputCheck.ifEmptyReplace(name);
String id = request.getParameter("id"); id=inputCheck.ifEmptyReplace(id);
String pw[] = request.getParameterValues("pw");
if(pw!=null)
	pw[0]=inputCheck.ifEmptyReplace(pw[0]);
String birth = request.getParameter("birth"); birth=inputCheck.ifEmptyReplace(birth);
String cp = request.getParameter("cp"); cp=inputCheck.ifEmptyReplace(cp);
String tp = request.getParameter("tp"); tp=inputCheck.ifEmptyReplace(tp);
String email = request.getParameter("email"); email=inputCheck.ifEmptyReplace(email);
String address = request.getParameter("address"); address=inputCheck.ifEmptyReplace(address);
String idChkYn = request.getParameter("idChkYn"); idChkYn=inputCheck.ifEmptyReplace(idChkYn);
String agree = request.getParameter("agree"); agree=inputCheck.ifEmptyReplace(agree);
if(inputCheck.isEmpty(agree) || !agree.equals("Y"))
	response.sendRedirect("terms.jsp");

int pass = 0;
if(!(inputCheck.isEmpty(name)||inputCheck.isEmpty(id)||pw==null||inputCheck.isEmpty(pw[0])
		||!pw[0].equals(pw[1])||inputCheck.isEmpty(birth)
		||inputCheck.isEmpty(cp)||inputCheck.isEmpty(email)
		||inputCheck.isEmpty(address)||!idChkYn.equals("Y"))){
	while(true){
		if(!inputCheck.isKorean(name) || !inputCheck.inLength(name, 2, 10))
			break;
		if(!pw[0].matches("^[a-zA-Z][a-zA-Z!@#$%^&*()_+=<>?0-9]{9,20}$"))
			break;
		if(!id.matches("^[a-zA-Z0-9]{7,15}$"))
			break;
		if(!birth.chars().allMatch(Character::isDigit)||birth.length()!=8){
			break;
		}
		if(!cp.matches("^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$"))
			break;
		if(!email.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"))
			break;
		if(!inputCheck.inLength(address, 6, 40))
			break;
			
		pass = 1;
		break;
	}
	if(pass == 1){
		DAO dao = new DAO();
		if(dao.getAccountId(id)==null){
			Account temp = new Account(name,id,pw[0],birth,cp,tp,email,address,0);
			dao.insertAccount(temp);
			String nextUrl = "complete.jsp?id="+id;
			response.sendRedirect(nextUrl);
		}
	}
}

String path = request.getContextPath();
%>
<jsp:include page="../topNav.jsp"></jsp:include>
<script type="text/javascript" src="<%=path%>/util/check.js"></script>
<script type="text/javascript" src="<%=path%>/util/cookie.js"></script>
<link href="<%=path%>/css/layout.css" rel="stylesheet"/>
<link href="<%=path%>/css/layout2.css" rel="stylesheet"/>
<link href="<%=path%>/css/common.css" rel="stylesheet"/>

</head>
<body>
	<header>
		<h2 class="sub-page__title">회원가입</h2>
	</header>
	<div>
		<ul class="join-process">
			<li>
				<p>01. 약관동의</p>
			</li>
			<li>
				<p class="active">02. 정보입력</p>
			</li>
			<li>
				<p>03. 가입완료</p>
			</li>
		</ul>
	</div>
	
	<div class="join-wrap mt50">
	<div class="board-write__title">
		<p class="sub-title mb15">정보입력</p>
		<p class="required-txt">표시된 항목은 필수입력사항입니다.</p>
	</div>
	<form name="regForm" id="regForm">
		<input type="hidden" name="agree" id="agree" />
		<input type="hidden" id="email" name="email" value="" />
		<input type="hidden" id="cp" name="cp" value="" />
		<input type="hidden" id="tp" name="tp" value="" />
	
		<ul class="board-write__item">
			<li>
				<p class="title required">이름</p>
				<div class="write-item">
					<input type="text" name="name" id="name" value="" title="이름">
				</div>
			</li>
			<li>
				<p class="title required">아이디</p>
				<div class="write-item">
					<input type="text" name="id" id="id" onchange="recheckId(this)" value="" title="아아디"/>
					<input type="hidden" name="idChkYn" id="idChkYn" value="N"/>
					<button type="button" name="chkUserId" onclick="checkId(this)" class="btn-write chkUserId ml5" title="ID 중복확인">ID 중복확인</button>
					<span name="chkMessage"></span>
					<p class="txt">※ ID 영문과 숫자 조합ID는 영문과 숫자 조합 7~15 자리</p>
				</div>

			</li>
			<li>
				<p class="title required">비밀번호 입력</p>
				<div class="write-item">
					<input type="password" id="pw" name="pw" title="비밀번호"/>
					<p class="txt">※PW 첫 문자는 영문자를 사용하고, 9자 이상 영문 대,소문자, 숫자, 특수문자 사용</p>
				</div>

			</li>
			<li>
				<p class="title required">비밀번호 확인</p>
				<div class="write-item">
					<input type="password" name="pw" title="비밀번호확인"/>
				</div>
			</li>
			<li>
				<p class="title required">생년월일</p>
				<div class="write-item">
					<input type="text" name="birth" id="birth" value="" title="생년월일"/>
					<p class="txt">※오직 숫자로만 입력하여 주세요. ex) 19950101</p>
				</div>
			</li>
			<li>
				<p class="title required">휴대 전화</p>
				<div class="write-item phone">
					<input type="text" class="cp1" value="" title="휴대전화 앞자리"/>
					<span>-</span>
					<input type="text" class="cp1" value="" title="휴대전화 가운데자리"/>
					<span>-</span>
					<input type="text" class="cp1" value="" title="휴대전화 끝자리"/>
				</div>
			</li>
			<li>
				<p class="title">일반 전화</p>
				<div class="write-item phone">
					<input type="text" class="tp1" value="" title="전화 앞자리"/>
					<span>-</span>
					<input type="text" class="tp1" value="" title="전화 가운데자리"/>
					<span>-</span>
					<input type="text" class="tp1" value="" title="전화 끝자리"/>
				</div>
			</li>
			<li>
				<p class="title required">이메일</p>
				<div class="write-item email">
					<input type="text" id="email1" value="" title="이메일 계정 아이디" />
					<span>@</span>
					<input type="text" id="email2" value="" class="emailDomainForm" title="이메일 도메인 입력"/>
					<select class="email-select" title="이메일 선택" onchange="emailSelect(this)">
						<option value="">직접 입력</option>
						<option value="empas.com">empas.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="hanmail.net">hanmail.net</option>
						<option value="hotmail.com">hotmail.com</option>
						<option value="korea.com">korea.com</option>
						<option value="nate.com">nate.com</option>
						<option value="naver.com">naver.com</option>
						<option value="param.com">param.com</option>
						<option value="yahoo.com">yahoo.com</option>
					</select>
				</div>
			</li>
			<li>
				<p class="title required">주소</p>
				<div class="write-item address">
					<input type="text" name="address" id="address" value="" class="postcodify_address" title="주소"/>
				</div>
			</li>

		</ul>
	</form>
	<div class="board-btn">
		<a href="#" class="board-btn__item write" onclick="javascript:regCheck();return false;" title="가입">가입</a>
		<a href="#" class="board-btn__item cancel" onclick="location.href='/main.jsp';return false;" title="취소">취소</a>
	</div>
</div>
</body>
<script type="text/javascript">
	function emailSelect(obj){
		//alert(obj.value);
		document.querySelector("#email2").value = obj.value;
	}

	function recheckId(obj){
		var idChkYn = document.querySelector("[name=idChkYn]");
		idChkYn.value = "N";
	}
	function canUseId(){
		var idChkYn = document.querySelector("[name=idChkYn]");
		var msg = document.querySelector("[name=chkMessage]");
		idChkYn.value = "Y";
		msg.innerText = "사용 가능한 ID입니다.";
		alert(msg.innerText);
		return;
	}
	function cantUseId(){
		var idChkYn = document.querySelector("[name=idChkYn]");
		var msg = document.querySelector("[name=chkMessage]");
		idChkYn.value = "N";
		msg.innerText = "사용 불가능한 ID입니다.";
		alert(msg.innerText);
		return;
	}
	
	function checkId(){
		var id = document.querySelector("#id")
		if(isEmpty(id.value)) {
			alert("[아이디] 반드시 입력하셔야 하는 사항입니다.");
			id.focus();
			return;
		}

		if(!id.value.match('^[a-zA-Z0-9]{7,15}$')) {
			alert('아이디는 영문과 숫자조합 7~15자입니다.');
			id.focus();
			return;
		}
		
		window.open("idcheck.jsp?id="+id.value,"check","toolbar=no,scrollbars=no,resizable=yes,status=no,menubar=no,width=1, height=1");
		return false;
	}
	
	function regCheck(){
		var name = document.querySelector("#name");
		if(!name.value.match("^[가-힣]{2,10}")){
			alert("한글 이름을 입력하세요.")
			name.focus();
			return;
		}
		var idChk = document.querySelector("#idChkYn");
		if(idChk.value != 'Y') {
			alert("아이디 중복체크를 해주세요.");
			document.querySelector("#id").focus();
			return;
		}
		var pw = document.querySelectorAll("[name=pw]");
		if(!pw[0].value.match("^[a-zA-Z][a-zA-Z!@#$%^&*()_+=<>?0-9]{9,20}$")){
			alert("첫 문자는 영문자를 사용하고, 9자 이상 영문 대,소문자, 숫자, 특수문자를 사용해주세요.");
			pw[0].focus();
			return;
		}
		if(pw[0].value != pw[1].value){
			alert("비밀번호가 일치하지 않습니다.")
			pw[0].focus();
			return;
		}
		var birth = document.querySelector("#birth");
		var today = new Date().toISOString().substring(0,10).replace(/-/g,'');
		if(!today.length==8 ||isNaN(birth.value) || Number(birth.value) < today-1000000 || Number(birth.value) > today){
			alert("생년월일 형식이 올바르지 않습니다.")
			birth.focus();
			return;
		}
		
		var cp1 = document.querySelectorAll(".cp1");
		var cp = cp1[0].value+"-"+cp1[1].value+"-"+cp1[2].value;
		if(!cp.match("^[0-9]{3}-[0-9]{3,4}-[0-9]{4}")){
			//alert(cp);
			alert("휴대전화 형식이 올바르지 않습니다.")
			cp1[0].focus();
			return;
		}
		document.querySelector("#cp").value=cp;
		
		var tp1 = document.querySelectorAll(".tp1");
		var tp = "";
		if(tp1[0].value!=""){
			tp = tp1[0].value+"-"+tp1[1].value+"-"+tp1[2].value;
		}
		document.querySelector("#tp").value=tp;
		
		var email1 = document.querySelector("#email1");
		var email2 = document.querySelector("#email2");
		var email = email1.value+"@"+email2.value;
		var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		if(!email.match(emailPattern)){
			//alert(email);
			alert("이메일 형식이 올바르지 않습니다.")
			email1.focus();
			return;
		}
		document.querySelector("#email").value=email;
		
		var address = document.querySelector("#address");
		if(!inLength(address.value, 6, 40)){
			//alert(address);
			alert("정상적인 주소를 입력하세요.")
			address.focus();
			return;
		}
		
		document.querySelector("#agree").value="<%=agree%>";
		document.querySelector("#regForm").submit();
	}
</script>
</html>