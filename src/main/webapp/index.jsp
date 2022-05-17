<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Message</title>
</head>
<body>
<h3>Input 10 messages</h3>
<form action="<c:url value="/mdb"/>">
    <c:forEach begin="1" end="10" varStatus="status">
        Message ${status.count}: <input type="text" name="msg"/><br/>
    </c:forEach>
    <button>Submit</button>
</form>
</body>
</html>
