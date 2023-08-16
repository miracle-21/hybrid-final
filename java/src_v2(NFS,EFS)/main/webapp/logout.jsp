<%@ page import="project04.util.inputCheck" %>
<%@ page import="project04.DAO" %><%--
  Created by IntelliJ IDEA.
  User: skawn
  Date: 2022-06-30
  Time: 오후 3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String path = request.getContextPath();
    %>
    <script type="text/javascript" src="<%=path%>/util/check.js"></script>
    <script type="text/javascript" src="<%=path%>/util/cookie.js"></script>
    <%
        String curId = null;
        if(session.getAttribute("curId") != null) {
            session.invalidate();
        }
    %>
    <script type="text/javascript">
        location.href  = document.referrer;
    </script>
    <title>Title</title>
</head>
<body>

</body>
</html>
