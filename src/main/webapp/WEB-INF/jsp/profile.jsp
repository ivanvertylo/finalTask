<jsp:useBean id="user" scope="request" type="com.epam.finaltack.ivanvertylo.db.entity.User"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Профиль" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<form action="controller" method="post">
    <input type="hidden" name="command" value="changeUsername">
    <label>
        Username:
        <input type="text" name="username" value="${user.username}">
    </label>
    <button type="submit" class="btn btn-outline-success">Сменить имя пользователя</button>
</form>
<form id="changePassword" action="controller" method="post">
    <input type="hidden" name="command" value="changePassword">
    <label>
        Password:
        <input id="changeInputPassword" type="text" name="password">
    </label>
    <label>
        Again password:
        <input id="dblChangeInputPassword" type="text">
    </label>
    <div class="btn btn-outline-success" onclick="onClick()">Сменить пароль</div>
</form>
<ul>
    <jsp:useBean id="testPoints" scope="request" type="java.util.List<com.epam.finaltack.ivanvertylo.db.entity.TestPoints>"/>
    <c:forEach var="item" items="${testPoints}">
        <li>
            <a <c:if test="${item.test.isPublic}">href="${pageContext.request.contextPath}/test?id=${item.test.id}"</c:if> style="display: flex;">
                <span>${item.test.name}</span>
                &numsp;
                <span>${item.points}</span>
                <c:if test="${!item.test.isPublic}"><span style="color: red">&numsp;-Тест был удален или скрыт создателем.</span></c:if>
            </a>
        </li>
    </c:forEach>
</ul>
<script>
    function onClick() {
        debugger
        if ($("#changeInputPassword").val() === $("#dblChangeInputPassword").val()){
            $("#changePassword").submit();
        }
        else {
            alert("Ошибка: Вы ввели не одиноковые пароли!")
        }
    }
</script>
</body>
</html>