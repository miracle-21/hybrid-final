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
<title>게시글 수정(포토갤러리)</title>
<link href="<%=path %>\css\pg_postingCss.css" rel="stylesheet">
<style>
</style>
<script>
	function insPost(){
		if(confirm("수정하시겠습니까?")){
			var titleObj = document.querySelector("[name=title]");
			var contentObj = document.querySelector("[name=content]");
			var imgurlObj = document.querySelector("[name=imgurl]");
			if(titleObj.value.trim()==""){
				alert("제목을 입력하세요");
				titleObj.focus();
				return;
			}
			if(contentObj.value.trim()==""){
				alert("내용을 입력하세요");
				contentObj.focus();
				return;
			}
			if(imgurlObj.value.trim()==""){
				alert("이미지 경로를 입력하세요");
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
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String imgurl = request.getParameter("imgurl");
	String postidS = request.getParameter("postid");
	int postid = 10000000;
	Photog pt = new Photog();
	if(postidS!=null && !postidS.trim().equals("")){
		postid = Integer.parseInt(postidS);
		pt = dao.getPgList_Postid(postid);
	}
	String curId = null;
	if(session.getAttribute("curId") != null) curId = (String)session.getAttribute("curId");
	Account ac = new Account();
	int accno = 0;
	ac = dao.getAccountId(curId);
	accno = ac.getAccno();
	String isReg = "N";
	if(title!=null && !title.trim().equals("")){
		Photog pg = new Photog(postid, accno, title, content, imgurl);
		dao.updatePgList(pg);
		isReg = "Y";
	}
%>
<script type="text/javascript">
	var isReg = "<%=isReg%>";
	if(isReg=="Y"){
		if(confirm("수정이 완료되었습니다.\n목록으로 이동하시겠습니까?")){
			location.href="photoGal.jsp";
		}else{
			location.href="";
		}
	}
</script>
<jsp:include page="topNav.jsp"/>
<h1 class="page_title">게시글 수정</h1>
<div class="input_area">
	<form class="input_form">
		<table class="input_table" > 
			<tr><th>제목</th><td><input type="text" id=title name=title value="<%=pt.getTitle() %>" size="20"></td></tr>
			<tr><th>내용</th><td><textarea id=content name=content><%=pt.getContent()%></textarea></td></tr>
			<tr><th>이미지 첨부</th><td><input type="text" id=imgurl name=imgurl value="<%=pt.getImgurl()%>"></td></tr>
		</table>
		<br>
		<div class="inputBtn_area">
			<input type="hidden" name="postid" value="<%=pt.getPostid() %>"/>
			<input type="button" value="수정" onclick="insPost()">
		</div>
	</form>
</div>
</body>
</html>