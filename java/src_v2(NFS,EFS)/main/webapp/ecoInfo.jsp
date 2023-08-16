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
<title>생태볼거리 상세보기</title>
<link href="<%=path %>\css\ecoInfo.css" rel="stylesheet">
<style>
</style>
<script>
	function backList(){
		location.href="ecoGal.jsp";
	}
</script>
</head>
<body>
<%
	String postIdS = "";
	postIdS = request.getParameter("postid");
	int postid = 0;
	Ecog e = new Ecog();
	DAO dao = new DAO();
	if(postIdS!=null && !postIdS.trim().equals("")){
		postid = Integer.parseInt(postIdS);
		e = dao.getEgList_Postid(postid);
	}
	String proc = request.getParameter("proc");
	if(proc==null) proc="";
	if(proc!=null && !proc.trim().equals("")){
		if(proc.equals("del")){
			dao.deleteEgList(postid);
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
			location.href="ecoGal.jsp";
		}
	}
</script>
<jsp:include page="topNav.jsp"/>
<div class="title_area">
	<h1 class="title">생태볼거리</h1>
</div>
<div class="main_area">
	<div class="img_area">
		<img src="<%=e.getImgurl()%>"/>
	</div>
	<div class="title_box">
		<span id="category"><%=e.getLcategory() %> > <%=e.getMcategory()%> > <%=e.getScategory() %></span><span><%=e.getKname() %></span><span id="sname"><%=e.getSname() %></span>
	</div>
	<div class="content_box">
		<table class="info_table" cellspacing="10">
			<tr><th>· 학  명 :</th><td><%=e.getSname() %></td></tr>
			<tr><th>· 국  명 :</th><td><%=e.getKname() %></td></tr>
			<tr><th>· 분  포 :</th><td><%=e.getDistribution() %></td></tr>
		</table>
		<pre>
	<%=e.getContent() %>
		</pre>
	</div>
</div>
<input class="list_button" type="button" value="목록" onclick="backList()">
<input class="upt_button" type="button" value="수정" onclick="uptPg()">
<input class="del_button" type="button" value="삭제" onclick="delPg()">
<form><input type="hidden" name="proc"/><input type="hidden" name="postid" value="<%=e.getPostid()%>"/></form>
<script>
	function uptPg(){
		var id = "<%=curId%>";
		var accno = "<%=accno%>";
		if(id.trim()==""||id=="null"){
			alert("작성자만 수정이 가능합니다.");
		}else if(accno==<%=e.getAccno()%>){
			if(confirm("수정하시겠습니까?")){
				location.href="eco_mod.jsp?postid="+<%=postid%>;
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
		}else if(accno==<%=e.getAccno()%>){
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