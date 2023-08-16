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
<title>간행물 상세보기</title>
<link href="<%=path %>\css\postInfo.css" rel="stylesheet">
<style>
</style>
<script>
	function backList(){
		location.href="postGal.jsp";
	}
	function download(){
		alert("다운로드를 시작합니다.")
	}
</script>
</head>
<body>
<%
	String postIdS = "";
	postIdS = request.getParameter("postid");
	int postId = 0;
	Posting p = new Posting();
	DAO dao = new DAO();
	if(postIdS!=null && !postIdS.trim().equals("")){
		postId = Integer.parseInt(postIdS);
		p = dao.getPList_Postid(postId);
	}
	String proc = request.getParameter("proc");
	if(proc==null) proc="";
	if(proc!=null && !proc.trim().equals("")){
		if(proc.equals("del")){
			dao.deletePList(postId);
		}
	}
	String curId = null;
	int accno = 0;
	if(session.getAttribute("curId") != null) {
		curId = (String)session.getAttribute("curId");
		Account ac = new Account();
		ac = dao.getAccountId(curId);
		accno = ac.getAccno();
	}
%>
<script type="text/javascript">
	var proc = "<%=proc%>";
	if(proc!=""){
		if(proc="del"){
			alert("삭제되었습니다.\n목록 화면으로 이동합니다");
			location.href="postGal.jsp";
		}
	}
</script>
<jsp:include page="topNav.jsp"/>
<div class="title_area">
	<h1 class="title">간행물</h1>
</div>
<div class="main_area">
	<div class="name_area">
		<span id="type"><%=p.getSubtype() %></span><span id="name"><%=p.getTitle() %></span>
	</div>
	<div class="info_area">
		<ul class="info_list">
			<li class="info_head">번호</li>
			<li class="info_data"><%=p.getPostid() %></li>
			<li class="info_head">작성일</li>
			<li class="info_data"><%=p.getUploaddate() %></li>
			<li class="info_head">작성자</li>
			<li class="info_data"><%=dao.getAccountNo(p.getAccno()).getName() %></li>
			<li class="info_head">첨부파일</li>
			<li class="info_data" style="cursor:pointer;" onclick="download()"><%=p.getPfile() %></li>
		</ul>
	</div>
	<br>
	<div class="content_area">
		<p><%=p.getContent() %></p>
	</div>
</div>
<input class="list_button" type="button" value="목록" onclick="backList()">
<input class="upt_button" type="button" value="수정" onclick="uptPg()">
<input class="del_button" type="button" value="삭제" onclick="delPg()">
<form><input type="hidden" name="proc"/><input type="hidden" name="postid" value="<%=p.getPostid()%>"/></form>
<script>
	function uptPg(){
		var id = "<%=curId%>";
		var accno = "<%=accno%>";
		if(id.trim()==""||id=="null"){
			alert("작성자만 수정이 가능합니다.");
		}else if(accno==<%=p.getAccno()%>){
			if(confirm("수정하시겠습니까?")){
				location.href="p_mod.jsp?postid="+<%=postId%>;
			}else{
				alert("작성자만 수정이 가능합니다.");
			}
		}	
	}
	function delPg(){
		var id = "<%=curId%>";
		var accno = "<%=accno%>";
		if(id.trim()==""||id=="null"){
			alert("작성자만 삭제가 가능합니다.");
		}else if(accno==<%=p.getAccno()%>){
			if(confirm("삭제하시겠습니까?")){
				document.querySelector("[name=proc]").value="del";
				document.querySelector("form").submit();
			}else{
				alert("작성자만 삭제가 가능합니다.");
			}
		}	
	}
</script>
</body>
</html>