<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Админ" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<form method="post" action="controller">
    <input type="hidden" name="command" value="updateTestInfo">
    <label>
        Название
        <input type="text" name="testName" value="${testName}">
    </label>
    <label>
        Предметная область
        <input type="text" name="testSubject">
    </label>
    <label>
        isPublic
        <input type="checkbox" name="testPublic">
    </label>
    <label>
        Время тестирования
        <input type="number" name="testTime">
    </label>
    <button type="submit">Сохранить</button>
</form>
</body>
</html>