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
<title>게시글 수정(간행물)</title>
<link href="<%=path %>\css\pg_postingCss.css" rel="stylesheet">
<style>
</style>
<script>
	function insPost(){
		if(confirm("수정하시겠습니까?")){
			var titleObj = document.querySelector("[name=title]");
			var subtypeObj = document.querySelector("[name=subtype]");
			var contentObj = document.querySelector("[name=content]");
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
			if(subtypeObj.value.trim()==""){
				alert("분류를 입력하세요");
				subtypeObj.focus();
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
	String subtype = request.getParameter("subtype");
	String title = request.getParameter("title");
	String pfile = request.getParameter("pfile");
	if(pfile==null || pfile.trim().equals("")) pfile="";
	String content = request.getParameter("content");
	String postidS = request.getParameter("postid");
	int postid = 10000000;
	Posting p = new Posting();
	if(postidS!=null && !postidS.trim().equals("")){
		postid = Integer.parseInt(postidS);
		p = dao.getPList_Postid(postid);
	}
	String curId = null;
	if(session.getAttribute("curId") != null) curId = (String)session.getAttribute("curId");
	Account ac = new Account();
	int accno = 0;
	ac = dao.getAccountId(curId);
	accno = ac.getAccno();
	String isReg = "N";
	if(title!=null && !title.trim().equals("")){
		Posting p2 = new Posting(postid, accno, subtype, title, pfile, content);
		dao.updatePList(p2);
		isReg = "Y";
	}
%>
<script type="text/javascript">
var isReg = "<%=isReg%>";
if(isReg=="Y"){
	if(confirm("수정이 완료되었습니다.\n목록으로 이동하시겠습니까?")){
		location.href="postGal.jsp";
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
			<tr><th>제목</th><td><input type="text" id=title name=title value="<%=p.getTitle() %>" size="20"></td></tr>
			<tr><th>분류</th><td><input type="text" id=subtype name=subtype value="<%=p.getSubtype()%>"></td></tr>
			<tr><th>첨부파일</th><td><input type="text" id=pfile name=pfile value="<%=p.getPfile()%>"></td></tr>
			<tr><th>내용</th><td><textarea id=content name=content><%=p.getContent() %></textarea></td></tr>
		</table>
		<br>
		<div class="inputBtn_area">
			<input type="hidden" name="postid" value="<%=p.getPostid() %>"/>
			<input type="button" value="수정" onclick="insPost()">
		</div>
	</form>
</div>
</body>
</html>