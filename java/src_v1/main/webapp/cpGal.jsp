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
	<title>공모전·캠페인</title>
	<link href="<%=path %>\css\cpGal.css" rel="stylesheet">
	<style type="text/css">
	</style>
</head>
<body>
<%

	String term = request.getParameter("term");
	if(term==null||term.trim().equals("")) term="all";
	DAO dao = new DAO();
	String input = request.getParameter("search_input");
	if(input==null) input="";
	String range = request.getParameter("range");
	if(range==null) range="all";
	String input_title = "";
	String input_content= "";
	if(!range.equals("all")){
		if(range.equals("title")){
			input_title=input;
		}
		if(range.equals("content")){
			input_content=input;
		}
	}
	int data_cnt=0;
	if(term.equals("all")){
		data_cnt=dao.getCpList_Count();
	}else if(term.equals("now")){
		data_cnt=dao.getCpList_Count_Now();
	}else if(term.equals("future")){
		data_cnt=dao.getCpList_Count_Future();
	}else if(term.equals("past")){
		data_cnt=dao.getCpList_Count_Past();
	}
	String curId = null;
	if(session.getAttribute("curId") != null) curId = (String)session.getAttribute("curId");
	Account ac = new Account();
	int accno = 0;
	if(curId != null){
		ac = dao.getAccountId(curId);
		accno = ac.getAccno();
	}
%>
	<script type="text/javascript">
		function goPostingPage(){
			var id = "<%=curId%>";
			if(id.trim()==""||id=="null"){
				alert("회원만 등록 가능합니다.")
			}else{
				location.href="cp_posting.jsp"	
			}		
		}
		function goInfo(postid){
			location.href="cpInfo.jsp?postid="+postid;
		}
	</script>
	<jsp:include page="topNav.jsp"/>
	<div class="main">
		<h1 class="title">공모전·캠페인</h1>
	</div>
	<div class="term_bar">
		<form>
			<input type="button" id="all_btn" value="전체" onclick="term_all()"/>
			<input type="button" id="now_btn" value="현재" onclick="term_now()"/>
			<input type="button" id="future_btn" value="예정" onclick="term_future()"/>
			<input type="button" id="past_btn" value="종료" onclick="term_past()"/>
			<input type="hidden" name="term" value=""/>
		</form>
	</div>
	<br>
	<div class="search_bar">
		<span class="data_page_cnt">총 <span class="data_cnt"><%=data_cnt %></span>건</span>
		<div class="search">
			<form>
				<select id="range" name="range">
					<option value="all">전체</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select>
				<input id="search_input" name="search_input" type="text" placeholder="검색어를 입력해주세요." value=<%=input %>>
				 <input type="image" id="search_img" alt="search_img" src="img\search.png">
				 <input type="button" id="posting_btn" value="글쓰기" onclick="goPostingPage()"/>
			</form>
			
		</div>	
	</div><br><br>
	<div class="list_container">
		<%if(term.equals("all")){ %>
			<%if(range.equals("all")){ 
				for(Campaign c:dao.getCpListAll(input)){
				%>
				<div class="list_area" onclick="goInfo(<%=c.getPostid()%>)">
					<img class="poster" src="<%=c.getPoster()%>">
					<div class="list_title_area">
						<span class="list_title"><%=c.getTitle() %></span>
					</div>
					<div class="list_content_area">
						<span class="list_content"><%=c.getContent() %></span>
						<input type="button" id="link_btn" value="바로가기" onclick="location.href='http://<%=c.getLink()%>'"/>
					</div>
				</div>
			<% }
			} else{
				for(Campaign c:dao.getCpListTitle_Content(input_title, input_content)){%>
				<div class="list_area" onclick="goInfo(<%=c.getPostid()%>)">
					<img class="poster" src="<%=c.getPoster()%>">
					<div class="list_title_area">
						<span class="list_title"><%=c.getTitle() %></span>
					</div>
					<div class="list_content_area">
						<span class="list_content"><%=c.getContent() %></span>
						<input type="button" id="link_btn" value="바로가기" onclick="location.href='http://<%=c.getLink()%>'"/>
					</div>
				</div>
				<%}
			} %>
		<%} %>
		<%if(term.equals("now")){ %>
			<%if(range.equals("all")){ 
				for(Campaign c:dao.getCpListAll_Now(input)){
				%>
				<div class="list_area" onclick="goInfo(<%=c.getPostid()%>)">
					<img class="poster" src="<%=c.getPoster()%>">
					<div class="list_title_area">
						<span class="list_title"><%=c.getTitle() %></span>
					</div>
					<div class="list_content_area">
						<span class="list_content"><%=c.getContent() %></span>
						<input type="button" id="link_btn" value="바로가기" onclick="location.href='http://<%=c.getLink()%>'"/>
					</div>
				</div>
			<% }
			} else{
				for(Campaign c:dao.getCpListTitle_Content_Now(input_title, input_content)){%>
				<div class="list_area" onclick="goInfo(<%=c.getPostid()%>)">
					<img class="poster" src="<%=c.getPoster()%>">
					<div class="list_title_area">
						<span class="list_title"><%=c.getTitle() %></span>
					</div>
					<div class="list_content_area">
						<span class="list_content"><%=c.getContent() %></span>
						<input type="button" id="link_btn" value="바로가기" onclick="location.href='http://<%=c.getLink()%>'"/>
					</div>
				</div>
				<%}
			} %>
		<%} %>
		<%if(term.equals("future")){ %>
			<%if(range.equals("all")){ 
				for(Campaign c:dao.getCpListAll_Future(input)){
				%>
				<div class="list_area" onclick="goInfo(<%=c.getPostid()%>)">
					<img class="poster" src="<%=c.getPoster()%>">
					<div class="list_title_area">
						<span class="list_title"><%=c.getTitle() %></span>
					</div>
					<div class="list_content_area">
						<span class="list_content"><%=c.getContent() %></span>
						<input type="button" id="link_btn" value="바로가기" onclick="location.href='http://<%=c.getLink()%>'"/>
					</div>
				</div>
			<% }
			} else{
				for(Campaign c:dao.getCpListTitle_Content_Future(input_title, input_content)){%>
				<div class="list_area" onclick="goInfo(<%=c.getPostid()%>)">
					<img class="poster" src="<%=c.getPoster()%>">
					<div class="list_title_area">
						<span class="list_title"><%=c.getTitle() %></span>
					</div>
					<div class="list_content_area">
						<span class="list_content"><%=c.getContent() %></span>
						<input type="button" id="link_btn" value="바로가기" onclick="location.href='http://<%=c.getLink()%>'"/>
					</div>
				</div>
				<%}
			} %>
		<%} %>
		<%if(term.equals("past")){ %>
			<%if(range.equals("all")){ 
				for(Campaign c:dao.getCpListAll_Past(input)){
				%>
				<div class="list_area" onclick="goInfo(<%=c.getPostid()%>)">
					<img class="poster" src="<%=c.getPoster()%>">
					<div class="list_title_area">
						<span class="list_title"><%=c.getTitle() %></span>
					</div>
					<div class="list_content_area">
						<span class="list_content"><%=c.getContent() %></span>
						<input type="button" id="link_btn" value="바로가기" onclick="location.href='http://<%=c.getLink()%>'"/>
					</div>
				</div>
			<% }
			} else{
				for(Campaign c:dao.getCpListTitle_Content_Past(input_title, input_content)){%>
				<div class="list_area" onclick="goInfo(<%=c.getPostid()%>)">
					<img class="poster" src="<%=c.getPoster()%>">
					<div class="list_title_area">
						<span class="list_title"><%=c.getTitle() %></span>
					</div>
					<div class="list_content_area">
						<span class="list_content"><%=c.getContent() %></span>
						<input type="button" id="link_btn" value="바로가기" onclick="location.href='http://<%=c.getLink()%>'"/>
					</div>
				</div>
				<%}
			} %>
		<%} %>
		
	</div>
	
	<script type="text/javascript">
		function term_all(){
			document.querySelector("[name=term]").value="all";
			document.querySelector("form").submit();
		}
		function term_now(){
			document.querySelector("[name=term]").value="now";
			document.querySelector("form").submit();
		}
		function term_future(){
			document.querySelector("[name=term]").value="future";
			document.querySelector("form").submit();
		}
		function term_past(){
			document.querySelector("[name=term]").value="past";
			document.querySelector("form").submit();
		}
	</script>
</body>
</html>