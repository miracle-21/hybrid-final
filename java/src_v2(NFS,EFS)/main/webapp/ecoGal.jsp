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
<title>생태볼거리</title>
<link href="<%=path %>\css\ecoGal.css" rel="stylesheet">
<style>
</style>
<script>
</script>
</head>
<body>
<%
	String mon = request.getParameter("mon");
	if(mon==null || mon.equals("")) mon="1";
	DAO dao = new DAO();
	String curId = null;
	if(session.getAttribute("curId") != null) curId = (String)session.getAttribute("curId");
	Account ac = new Account();
	int accno = 0;
	if(curId != null){
		ac = dao.getAccountId(curId);
		accno = ac.getAccno();
	}
%>
<jsp:include page="topNav.jsp"/>
<h1 class="page_title">이달의 생태볼거리</h1>
<div class="search_area">
	<%for(int idx=1;idx<=12;idx++){ %>
	<div class="month_area" onclick="mon_search(<%=idx%>)">
		<%=idx %>월
	</div>
	<%} %>
	<form>
		<input type="hidden" name="mon"/>
	</form>
</div>
<div class="main_area">
	<div class="month_info">
		<%=mon %>월
		<input class="post_btn" type="button" value="글쓰기" onclick="goPost()"/>
	</div>
	<%for(Ecog e:dao.getEgListM_Space(mon, "")){ %>
		<div class="list_area" onclick="goInfo(<%=e.getPostid()%>)">
			<div class="exspace_area">
				<%=e.getExspace() %>
			</div>
			<img src="<%=e.getImgurl()%>"/>
			<div class="kname_area">
				<%=e.getKname() %>
			</div>
		</div>
	<%} %>
</div>
<script type="text/javascript">
	function mon_search(idx){
		document.querySelector("[name=mon]").value=idx;
		document.querySelector("form").submit();
	}
	function goInfo(postid){
		location.href="ecoInfo.jsp?postid="+postid;
	}
	function goPost(){
		var id = "<%=curId%>";
		if(id.trim()==""||id=="null"){
			alert("회원만 등록 가능합니다.")
		}else{
			location.href="eco_posting.jsp"	
		}		
	}
</script>
</body>
</html>