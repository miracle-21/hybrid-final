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
<title>간행물</title>
<link href="<%=path %>\css\postGal.css" rel="stylesheet">
<style>
</style>
</head>
<body>
<%
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
	data_cnt=dao.getPList_Count();
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
			location.href="p_posting.jsp"	
		}		
	}
	function goInfo(postid){
		location.href="postInfo.jsp?postid="+postid;
	}
</script>
<jsp:include page="topNav.jsp"/>
<h1 class="page_title">간행물</h1>
<div class="search_bar">
	<span class="data_page_cnt">총 <span class="data_cnt"><%=data_cnt %></span>건</span>
	<div class="search">
		<form>
			<select id="range" name="range">
				<option value="all">전체</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input id="search_input" name="search_input" type="text" placeholder="검색어를 입력해주세요." value="">
			 <input type="image" id="search_img" alt="search_img" src="img\search.png">
			 <input type="button" id="posting_btn" value="글쓰기" onclick="goPostingPage()"/>
		</form>
	</div>	
</div>
<br><br>
<div class="list_area">
	<table class="list_table">
		<tr><th>번호</th><th class="th_title">제목</th><th>첨부파일</th><th>작성자</th><th>작성일</th></tr>
		<%
			if(range.equals("title")||range.equals("content")){
				for(Posting p:dao.getPListTitle_Content(input_title, input_content)){
					int cnt = 0;
					cnt++;
				%>
				<tr class="data_row" onclick="goInfo(<%=p.getPostid()%>)">
					<td><%=cnt%></td>
					<td class="td_title"><%=p.getTitle() %></td>
					<td>
						<%if(p.getPfile()!=null && !p.getPfile().trim().equals("")){ %>
							<img src="<%=path%>\z01.prj4\img\pfile.png">
						<%}else{ %>
							&nbsp;
						<%} %>
					</td>
					<td><%=dao.getPList_Name(p.getPostid()) %></td>
					<td><%=p.getUploaddate() %></td>
				</tr>
		<%
				}
			}else{
				for(Posting p:dao.getPList_All(input)){
					int cnt = 0;
					cnt++;
				%>
				<tr class="data_row" onclick="goInfo(<%=p.getPostid()%>)">
					<td><%=cnt%></td>
					<td class="td_title"><%=p.getTitle() %></td>
					<td>
						<%if(p.getPfile()!=null && !p.getPfile().trim().equals("")){ %>
							<img src="<%=path%>\z01.prj4\img\pfile.png">
						<%}else{ %>
							&nbsp;
						<%} %>
					</td>
					<td><%=dao.getPList_Name(p.getPostid()) %></td>
					<td><%=p.getUploaddate() %></td>
				</tr>
				<%
				}
			} %>
	</table>
</div>
</body>
</html>