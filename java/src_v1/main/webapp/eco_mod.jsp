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
<head>
<meta charset="UTF-8">
<title>게시글 수정(공모전캠페인)</title>
<link href="<%=path %>\css\pg_postingCss.css" rel="stylesheet">
<style>
</style>
<script>
	function uptPost(){
		if(confirm("수정하시겠습니까?")){
			var mmObj = document.querySelector("[name=mm]");
			var exspaceObj = document.querySelector("[name=exspace]");
			var lcategoryObj = document.querySelector("[name=lcategory]");
			var mcategoryObj = document.querySelector("[name=mcategory]");
			var scategoryObj = document.querySelector("[name=scategory]");
			var snameObj = document.querySelector("[name=sname]");
			var knameObj = document.querySelector("[name=kname]");
			var distributionObj = document.querySelector("[name=distribution]");
			var contentObj = document.querySelector("[name=content]");
			var imgurlObj = document.querySelector("[name=imgurl]");
			if(mmObj.value.trim()==""){
				alert("월 정보를 입력하세요");
				mmObj.focus();
				return;
			}
			if(exspaceObj.value.trim()==""){
				alert("전시관 정보를 입력하세요");
				exspaceObj.focus();
				return;
			}
			if(lcategoryObj.value.trim()==""){
				alert("대분류를 입력하세요");
				lcategoryObj.focus();
				return;
			}
			if(mcategoryObj.value.trim()==""){
				alert("중분류를 입력하세요");
				mcategoryObj.focus();
				return;
			}
			if(scategoryObj.value.trim()==""){
				alert("소분류를 입력하세요");
				scategoryObj.focus();
				return;
			}
			if(snameObj.value.trim()==""){
				alert("소분류를 입력하세요");
				snameObj.focus();
				return;
			}
			if(snameObj.value.trim()==""){
				alert("학명을 입력하세요");
				snameObj.focus();
				return;
			}
			if(knameObj.value.trim()==""){
				alert("국명을 입력하세요");
				knameObj.focus();
				return;
			}
			if(distributionObj.value.trim()==""){
				alert("분포를 입력하세요");
				distributionObj.focus();
				return;
			}
			if(contentObj.value.trim()==""){
				alert("내용을 입력하세요");
				contentObj.focus();
				return;
			}
			if(imgurlObj.value.trim()==""){
				alert("사진 파일을 첨부하세요");
				imgurlObj.focus();
				return;
			}
			document.querySelector("form").submit();
		}
	}
</script>
</head>
<body>
<%
	DAO dao = new DAO();
	String mm = request.getParameter("mm");
	if(mm==null || mm.equals("")) mm="";
	String exspace = request.getParameter("exspace");
	if(exspace==null || exspace.equals("")) exspace="";
	String lcategory = request.getParameter("lcategory");
	if(lcategory==null || lcategory.equals("")) lcategory="";
	String mcategory = request.getParameter("mcategory");
	if(mcategory==null || mcategory.equals("")) mcategory="";
	String scategory = request.getParameter("scategory");
	if(scategory==null || scategory.equals("")) scategory="";
	String sname = request.getParameter("sname");
	if(sname==null || sname.equals("")) sname="";
	String kname = request.getParameter("kname");
	if(kname==null || kname.equals("")) kname="";
	String distribution = request.getParameter("distribution");
	if(distribution==null || distribution.equals("")) distribution="";
	String content = request.getParameter("content");
	if(content==null || content.equals("")) content="";
	String imgurl = request.getParameter("imgurl");
	if(imgurl==null || imgurl.equals("")) imgurl="";
	String postidS = request.getParameter("postid");
	int postid = 10000000;
	Ecog e = new Ecog();
	if(postidS!=null && !postidS.trim().equals("")){
		postid = Integer.parseInt(postidS);
		e = dao.getEgList_Postid(postid);
	}
	String curId = null;
	if(session.getAttribute("curId") != null) curId = (String)session.getAttribute("curId");
	Account ac = new Account();
	int accno = 0;
	ac = dao.getAccountId(curId);
	accno = ac.getAccno();
	String isReg = "N";
	if(mm!=null && !mm.trim().equals("")){
		Ecog e2 = new Ecog(postid, accno, mm, exspace, lcategory, mcategory, scategory, sname, kname, distribution, content, imgurl);
		dao.updateEgList(e2);
		isReg = "Y";
	}
%>
<script type="text/javascript">
	var isReg = "<%=isReg%>";
	if(isReg=="Y"){
		if(confirm("수정이 완료되었습니다.\n목록으로 이동하시겠습니까?")){
			location.href="ecoGal.jsp";
		}else{
			location.href="";
		}
	}
</script>
<jsp:include page="topNav.jsp"/>
<h1 class="page_title">게시글 작성</h1>
<div class="input_area">
	<form class="input_form">
		<table class="input_table" > 
			<tr><th>월 정보</th><td><input type="text" id=mm name=mm value="<%=e.getMm() %>" placeholder="숫자만 입력하세요..(1, 2, 3 .. 11, 12)" size="20"></td></tr>
			<tr><th>전시관 정보</th><td><input type="text" id=exspace name=exspace value="<%=e.getExspace()%>"></td></tr>
			<tr><th>대분류</th><td><input type="text" id=lcategory name=lcategory value="<%=e.getLcategory()%>"></td></tr>
			<tr><th>중분류</th><td><input type="text" id=mcategory name=mcategory value="<%=e.getMcategory()%>"></td></tr>
			<tr><th>소분류</th><td><input type="text" id=scategory name=scategory value="<%=e.getScategory()%>"></td></tr>
			<tr><th>학명</th><td><input type="text" id=sname name=sname value="<%=e.getSname()%>"></td></tr>
			<tr><th>국명</th><td><input type="text" id=kname name=kname value="<%=e.getKname()%>"></td></tr>
			<tr><th>분포</th><td><input type="text" id=distribution name=distribution value="<%=e.getDistribution()%>"></td></tr>
			<tr><th>이미지 첨부</th><td><input type="text" id=imgurl name=imgurl value="<%=e.getImgurl() %>"></td></tr>
			<tr><th>내용</th><td><textarea id=content name=content><%=e.getContent() %></textarea></td></tr>
		</table>
		<br>
		<div class="inputBtn_area">
			<input type="hidden" name="postid" value="<%=e.getPostid() %>"/>
			<input type="button" value="수정" onclick="uptPost()">
		</div>
	</form>
</div>
</body>
</html>