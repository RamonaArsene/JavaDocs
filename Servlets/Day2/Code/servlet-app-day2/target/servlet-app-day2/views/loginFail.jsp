<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Http Session</title>
    <%
        HttpSession httpSession = (HttpSession) session.getAttribute("session");
        String username = (String) session.getAttribute("username");
        Cookie [] cookies = (Cookie[])session.getAttribute("cookies");
    %>
</head>
<body>
    Username <b><%=username%></b> or password is invalid! Please try again.
    <br>
    Your session id is <%=httpSession.getId()%>
    <br>
    Cookies are:
    <br>
    <%
        for (Cookie cookie : cookies) {
            %> Cookie name <%=cookie.getName()%>
           Cookie value <%=cookie.getValue()%>
    <br>
        <%}
    %>
</body>
</html>
