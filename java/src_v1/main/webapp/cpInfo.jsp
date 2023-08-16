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
<title>공모전·캠페인 상세보기</title>
<link href="<%=path %>\css\cpInfo.css" rel="stylesheet">
<style>
</style>
<script>
	function backList(){
		location.href="cpGal.jsp";
	}
</script>
</head>
<body>
<%
	String postIdS = "";
	postIdS = request.getParameter("postid");
	int postid = 0;
	Campaign c = new Campaign();
	DAO dao = new DAO();
	if(postIdS!=null && !postIdS.trim().equals("")){
		postid = Integer.parseInt(postIdS);
		c = dao.getCpList_Postid(postid);
	}
	String proc = request.getParameter("proc");
	if(proc==null) proc="";
	if(proc!=null && !proc.trim().equals("")){
		if(proc.equals("del")){
			dao.deleteCpList(postid);
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
			location.href="cpGal.jsp";
		}
	}
</script>
<jsp:include page="topNav.jsp"/>
<div class="title_area">
	<h1 class="title">공모전·캠페인</h1>
</div>
<div class="main_area">
	<div class="img_area">
		<img src="<%=c.getPoster()%>"/>
	</div>
	<div class="title_box">
		<span><%=c.getTitle() %></span>
	</div>
	<div class="content_box">
		<pre>
	- 접수처 : <%=c.getLink() %>
	
	- 기간 : <%=c.getSdate() %> ~ <%=c.getEdate() %>
	
	- 내 용 : <%=c.getContent() %> 
		</pre>
	</div>
	<input type="button" id="link_btn" value="바로가기" onclick="location.href='http://<%=c.getLink()%>'"/>
</div>
<input class="list_button" type="button" value="목록" onclick="backList()">
<input class="upt_button" type="button" value="수정" onclick="uptPg()">
<input class="del_button" type="button" value="삭제" onclick="delPg()">
<form><input type="hidden" name="proc"/><input type="hidden" name="postid" value="<%=c.getPostid()%>"/></form>
<script>
	function uptPg(){
		var id = "<%=curId%>";
		var accno = "<%=accno%>";
		if(id.trim()==""||id=="null"){
			alert("작성자만 수정이 가능합니다.");
		}else if(accno==<%=c.getAccno()%>){
			if(confirm("수정하시겠습니까?")){
				location.href="cp_mod.jsp?postid="+<%=postid%>;
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
		}else if(accno==<%=c.getAccno()%>){
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