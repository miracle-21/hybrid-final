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
	<title>포토갤러리 상세보기</title>
	<link href="<%=path %>\css\photoInfoCss.css" rel="stylesheet">
	<style>
	</style>
</head>
<%
	DAO dao = new DAO();
	String postidS = request.getParameter("postid");
	int postid = 10000000;
	Photog pt = new Photog();
	if(postidS!=null && !postidS.trim().equals("")){
		postid = Integer.parseInt(postidS);
		pt = dao.getPgList_Postid(postid);
	}
	String proc = request.getParameter("proc");
	if(proc==null) proc="";
	if(proc!=null && !proc.trim().equals("")){
		if(proc.equals("del")){
			dao.deletePgList(postid);
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
			location.href="photoGal.jsp";
		}
	}
</script>
<body>
	<jsp:include page="topNav.jsp"/>
	<div class="title_area">
		<h1 class="title">포토갤러리</h1>
	</div>
	<div class="main_area">
		<div class="name_area">
			<span id="type">생태연구</span><span id="name"><%=pt.getTitle() %></span>
		</div>
		<div class="info_area">
			<ul class="info_list">
				<li class="info_head">작성일</li>
				<li class="info_data"><%=pt.getUploaddate() %></li>
				<li class="info_head">작성자</li>
				<li class="info_data"><%=dao.getPgList_Name(postid) %></li>
			</ul>
		</div>
		<br>
		<div class="img_area"> <%-- 0620 17:55 --%>
			<div class="img_box">
				<img class="img" src=<%=pt.getImgurl() %>>
			</div>
		</div>
		<br>
		<div class="content_title_area">
			<span class="content_title">내용</span>
		</div>
	</div>
	<div class="sub_area">
		<div class="content_area">
			<p class="content"><%=pt.getContent() %></p>
		</div>
		
		<div class="source_area">
			<span class="source">출처표시</span>
		</div>
		<br>
		<input class="list_button" type="button" value="목록" onclick="backList()">
		<input class="upt_button" type="button" value="수정" onclick="uptPg()">
		<input class="del_button" type="button" value="삭제" onclick="delPg()">
		<form><input type="hidden" name="proc"/><input type="hidden" name="postid" value="<%=pt.getPostid()%>"/></form>
	</div>
<script>
	function backList(){
		location.href="photoGal.jsp";
	}
	function uptPg(){
		var id = "<%=curId%>";
		var accno = "<%=accno%>";
		if(id.trim()==""||id=="null"){
			alert("작성자만 수정이 가능합니다.");
		}else if(accno==<%=pt.getAccno()%>){
			if(confirm("수정하시겠습니까?")){
				location.href="pg_mod.jsp?postid="+<%=postid%>;
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
		}else if(accno==<%=pt.getAccno()%>){
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