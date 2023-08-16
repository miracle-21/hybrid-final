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
	<title>포토갤러리</title>
	<link href="<%=path %>\css\photoGalCss.css" rel="stylesheet">
	<style type="text/css">
	</style>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
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
		function goPhotoInfo(postid){
			location.href="photoInfo.jsp?postid="+postid;
		}
		function goPostingPage(){
			var id = "<%=curId%>";
			if(id.trim()==""||id=="null"){
				alert("회원만 등록 가능합니다.")
			}else{
				location.href="pg_posting.jsp"	
			}		
		}
		
	</script>
	<jsp:include page="topNav.jsp"/>
	<div class="main">
		<h1 class="title">포토갤러리</h1>
	</div>
	<div class="search_bar">
		<span class="data_page_cnt">총 <span class="data_cnt"><%=dao.getPgList_Count() %></span>건</span>
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
		<div class="list_container_sub">
			<%
			if(range.equals("title")||range.equals("content")){
				for(Photog p:dao.getPgListTitle_Content(input_title, input_content)){
				%>
				<div class="list_area" onclick="goPhotoInfo(<%=p.getPostid()%>)">
					<div class="img_area">
						<img  class="list_img"src=<%=p.getImgurl() %>>
					</div>
					<div class="text_area">
						<div class="name_area">
							<br>
							<p><%=p.getTitle() %></p>
						</div>
						<div class="content_type_area">
							<span class="content_type">
							분류
							</span>
							<span class="upload_date">
							<%=p.getUploaddate() %>
							</span>
						</div>
					</div>
				</div>
				<%
				}
			}else{
				for(Photog p:dao.getPgList_All(input)){
			%>
				<div class="list_area" onclick="goPhotoInfo(<%=p.getPostid()%>)">
					<div class="img_area">
						<img  class="list_img"src=<%=p.getImgurl() %>>
					</div>
					<div class="text_area">
						<div class="name_area">
							<br>
							<p><%=p.getTitle() %></p>
						</div>
						<div class="content_type_area">
							<span class="content_type">
							분류
							</span>
							<span class="upload_date">
							<%=p.getUploaddate() %>
							</span>
						</div>
					</div>
				</div>
			<%
				}
			} %>			
		</div>
	</div>
</body>
</html>