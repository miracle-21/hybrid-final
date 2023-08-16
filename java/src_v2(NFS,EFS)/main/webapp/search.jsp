<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="project04.vo.*"
	import="project04.DAO"
	import="project04.util.*"
	%>
<%
request.setCharacterEncoding("utf-8");
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	DAO dao = new DAO();
	String searchAll = request.getParameter("searchStr");
	if(searchAll==null) searchAll="";
	String range = request.getParameter("range");
	if(range==null) range="all";
	String searchTitle = "";
	String searchContent= "";
	if(!range.equals("all")){
		if(range.equals("title")){
			searchTitle=searchAll;
		}
		if(range.equals("content")){
			searchContent=searchAll;
		}
	}
	String postidS = request.getParameter("postid");
	int postid = 10000000;
	Photog pt = new Photog();
	if(postidS!=null && !postidS.trim().equals("")){
		postid = Integer.parseInt(postidS);
		pt = dao.getPgList_Postid(postid);
	}
%>

<meta charset="UTF-8">
<title>통합검색 | 통합검색 | 국립생태원</title>
<link href="<%=path%>/css/common.css" rel="stylesheet">
<link href="<%=path%>/css/layout.css" rel="stylesheet">
<link href="<%=path%>/css/searchResult.css" rel="stylesheet">
</head>
<script>
	var dateChange = () => {
	var date_input = document.getElementById("date");
	var text_input = document.getElementById("text");
	text_input.value = date_input.value;
	};

	function showhide(){
		var x = document.getElementById("proSearch");
		if(x.style.display === "none"){
			x.style.display = "block";
		}else{
			x.style.display = "none";
		}
	}
	function go1(postid){
		location.href="photoInfo.jsp?postid="+postid;
	}
	function go2(postid){
		location.href="ecoInfo.jsp?postid="+postid;
	}
	function go3(postid){
		location.href="cpInfo.jsp?postid="+postid;
	}
	function go4(postid){
		location.href="postInfo.jsp?postid="+postid;
	}
	
</script>
<style>

#ultbl01 ul.ultable li ul li:nth-child(1) { width:6%  }

#ultbl01 ul.ultable li ul li:nth-child(2) { width:20% }

#ultbl01 ul.ultable li ul li:nth-child(3) { width:50% }

#ultbl01 ul.ultable li ul li:nth-child(4) { width:13% }

#ultbl01 ul.ultable li ul li:nth-child(5) { width:5%  }

</style>
<body>
<jsp:include page="topNav.jsp"></jsp:include>
	<div class="sub-page layout">
		<h2 class="sub-page__title">통합검색</h2>
		<div class="board-search2">
			<form>
				<div class="search-wrap2">
				<select id="range" name="range" class="search-select">
					<option value="all" selected="selected">전체</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select>
					<input id="searchStr" name="searchStr" class="search" type="text" placeholder="검색어를 입력해주세요">
					<button class="search-btn" type="submit">검색</button>
				</div>
				<button onclick="showhide()" class="board-btn active">상세검색</button>
		<ul class="search-division" style="display: block;">
            <li>
                <span class="title">기간</span>
                <div class="cont">
                    <div class="board-datepicker">
                        <input type="text" name="startDate" class="datepicker" placeholder="YYYY-MM-DD" value="1970-01-22" title="시작일 입력">
                        <span>~</span>
                        <input type="text" name="endDate" class="datepicker" placeholder="YYYY-MM-DD" value="2030-12-31" title="종료일 입력">
                    </div>
                </div>
            </li>
            <li>
                <span class="title">정렬</span>
                <div class="cont">
                    <input type="radio" name="sort" id="chk1" class="" value="RANK" title="정확도순">
                    <label for="chk1">정확도순</label>
                    <input type="radio" name="sort" id="chk2" class="" value="DATE" title="최신순" checked="checked">
                    <label for="chk2">최신순</label>
                </div>
            </li>
        </ul>
			</form>	
		</div>
		<div class="search-tab mt40">
			<a onclick="">전체</a><a onclick="">게시판</a>
			<a onclick="">첨부파일</a><a onclick="">멸종위기종</a><a onclick="">주요동식물</a>
		</div>
	</div>
<form>
    <div id="ultbl01" >
        <ul>
            <li>
                <ul alt="table" class="ultable ultable-striped ultable-bordered ultable-hover">
                    <li alt="thead">
                        <ul alt="tr">
                            <li>카테고리</li>
                            <li>제목</li>
                            <li>내용</li>
                            <li>작성일</li>
                        </ul>
                    </li>
            		
	<%
	if(range.equals("title")){
		for(Search s:dao.searchTitle(searchTitle)){	
	%>              
					 <%if(s.getPtype().equals("포토갤러리")) {%>
                    <li onclick="go1(<%=s.getPostid()%>)">
                    <%}else if(s.getPtype().equals("생태볼거리")) {%>
                    <li onclick="go2(<%=s.getPostid()%>)">
                    <%}else if(s.getPtype().equals("공모전캠페인")) {%>
                    <li onclick="go3(<%=s.getPostid()%>)">
                    <%}else if(s.getPtype().equals("간행물")) {%>
                    <li onclick="go4(<%=s.getPostid()%>)">
                    <%}%>
                        <ul alt="tr" class="row">
                            <li><%=s.getPtype() %></li>
                            <li><%=s.getTitle() %></li>
                            <li><%=s.getContent() %></li>
                            <li><%=s.getUploaddate() %></li>
                        </ul>
                    </li>
	<%
		}
	}else if(range.equals("content")){
		for(Search s:dao.searchContent(searchContent)){
	%>
                     <%if(s.getPtype().equals("포토갤러리")) {%>
                    <li onclick="go1(<%=s.getPostid()%>)">
                    <%}else if(s.getPtype().equals("생태볼거리")) {%>
                    <li onclick="go2(<%=s.getPostid()%>)">
                    <%}else if(s.getPtype().equals("공모전캠페인")) {%>
                    <li onclick="go3(<%=s.getPostid()%>)">
                    <%}else if(s.getPtype().equals("간행물")) {%>
                    <li onclick="go4(<%=s.getPostid()%>)">
                    <%}%>
                        <ul alt="tr" class="row">
                            <li><%=s.getPtype() %></li>
                            <li><%=s.getTitle() %></li>
                            <li><%=s.getContent() %></li>
                            <li><%=s.getUploaddate() %></li>
                        </ul>
                    </li>
	<%
		}
	}else{
		for(Search s:dao.searchAll(searchAll)){
	%>
                    <%if(s.getPtype().equals("포토갤러리")) {%>
                    <li onclick="go1(<%=s.getPostid()%>)">
                    <%}else if(s.getPtype().equals("생태볼거리")) {%>
                    <li onclick="go2(<%=s.getPostid()%>)">
                    <%}else if(s.getPtype().equals("공모전캠페인")) {%>
                    <li onclick="go3(<%=s.getPostid()%>)">
                    <%}else if(s.getPtype().equals("간행물")) {%>
                    <li onclick="go4(<%=s.getPostid()%>)">
                    <%}%>
                        <ul alt="tr" class="row">
                            <li><%=s.getPtype() %></li>
                            <li><%=s.getTitle() %></li>
                            <li><%=s.getContent() %></li>
                            <li><%=s.getUploaddate() %></li>
                        </ul>
                    </li>
	<%
		}
	}
	%> 
			
			</ul>                        	
        </ul>
    </div>
</form>

</body>
</html>