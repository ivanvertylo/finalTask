<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Пользователи" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<ol style="font-size: 25px; padding: 0; margin-top: 25px">
    <jsp:useBean id="usersTestCounts" scope="request"
                 type="java.util.List<com.epam.finaltack.ivanvertylo.db.entity.UsersTestCount>"/>
    <c:forEach items="${usersTestCounts}" var="item">
        <li style="display: flex; flex-direction: row; <c:if test="${item.user.blocked}">background-color: lightcoral;</c:if> justify-content: center;">
            <div style="font-weight: bold">Login:${item.user.login}</div>
            &numsp;
            <div style="font-style: italic">Username:${item.user.username}</div>
            &numsp;
            <div style="font-weight: bold">Test count:${item.count}</div>
            &numsp;
            <div style="color:red"><c:if test="${item.user.blocked}">Блокировка</c:if></div>
        </li>
        <hr>
    </c:forEach>
</ol>
</body>
</html>