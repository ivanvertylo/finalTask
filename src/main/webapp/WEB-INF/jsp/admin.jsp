<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Админ" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<form method="post" action="controller">
    <input type="hidden" name="command" value="createTest">
    <input type="text" name="testName">
    <button type="submit">Создать</button>
</form>
</body>
</html>