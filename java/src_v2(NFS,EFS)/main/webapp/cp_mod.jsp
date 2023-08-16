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
			var titleObj = document.querySelector("[name=title]");
			var poster_urlObj = document.querySelector("[name=poster_url]");
			var linkObj = document.querySelector("[name=link]");
			var contentObj = document.querySelector("[name=content]");
			var sdateObj = document.querySelector("[name=sdate]");
			var edateObj = document.querySelector("[name=edate]");
			if(titleObj.value.trim()==""){
				alert("제목을 입력하세요");
				titleObj.focus();
				return;
			}
			if(poster_urlObj.value.trim()==""){
				alert("포스터 url을 입력하세요");
				poster_urlObj.focus();
				return;
			}
			if(linkObj.value.trim()==""){
				alert("주최 url을 입력하세요");
				linkObj.focus();
				return;
			}
			if(contentObj.value.trim()==""){
				alert("내용을 입력하세요");
				contentObj.focus();
				return;
			}
			if(sdateObj.value.trim()==""){
				alert("시작 날짜를 입력하세요");
				sdateObj.focus();
				return;
			}
			if(edateObj.value.trim()==""){
				alert("마감 날짜를 입력하세요");
				edateObj.focus();
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
	if(title==null) title="";
	String poster_url = request.getParameter("poster_url");
	if(poster_url==null) poster_url="";
	String content = request.getParameter("content");
	if(content==null) content="";
	String link = request.getParameter("link");
	if(link==null) link="";
	String sdate = request.getParameter("sdate");
	if(sdate==null) sdate="";
	String edate = request.getParameter("edate");
	if(edate==null) edate="";
	String postidS = request.getParameter("postid");
	int postid = 10000000;
	Campaign c = new Campaign();
	if(postidS!=null && !postidS.trim().equals("")){
		postid = Integer.parseInt(postidS);
		c = dao.getCpList_Postid(postid);
	}
	String curId = null;
	if(session.getAttribute("curId") != null) curId = (String)session.getAttribute("curId");
	Account ac = new Account();
	int accno = 0;
	ac = dao.getAccountId(curId);
	accno = ac.getAccno();
	String isReg = "N";
	if(title!=null && !title.trim().equals("")){
		Campaign c2 = new Campaign(postid, accno, title, poster_url, link, content, sdate, edate);
		dao.updateCpList(c2);
		isReg = "Y";
	}
%>
<script type="text/javascript">
	var isReg = "<%=isReg%>";
	if(isReg=="Y"){
		if(confirm("수정이 완료되었습니다.\n목록으로 이동하시겠습니까?")){
			location.href="cpGal.jsp";
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
			<tr><th>제목</th><td><input type="text" id=title name=title value="<%=c.getTitle() %>" size="20"></td></tr>
			<tr><th>포스터 url</th><td><input type="text" id=poster_url name=poster_url value="<%=c.getPoster() %>"></td></tr>
			<tr><th>주최 Link</th><td><input type="text" id=link name=link value="<%=c.getLink() %>"></td></tr>
			<tr><th>내용</th><td><textarea id=content name=content><%=c.getContent() %></textarea></td></tr>
			<tr><th>시작 날짜</th><td><input type="text" id=sdate name=sdate placeholder="YYYY-MM-DD" value="<%=c.getSdate() %>"></td></tr>
			<tr><th>마감 날짜</th><td><input type="text" id=edate name=edate placeholder="YYYY-MM-DD" value="<%=c.getEdate() %>"></td></tr>
		</table>
		<br>
		<div class="inputBtn_area">
			<input type="hidden" name="postid" value="<%=c.getPostid() %>"/>
			<input type="button" value="수정" onclick="uptPost()">
		</div>
	</form>
</div>
</body>
</html>