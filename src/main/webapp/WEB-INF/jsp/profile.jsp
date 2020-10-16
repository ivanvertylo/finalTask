<jsp:useBean id="user" scope="request" type="com.epam.finaltack.ivanvertylo.db.entity.User"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Профиль" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<c:choose>
    <c:when test="${login == user.login || role == 'admin'}">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="changeUsername">
            <input type="hidden" name="user" value="${user.login}">
            <label>
                Username:
                <input type="text" name="username" value="${user.username}">
            </label>
            <button type="submit" class="btn btn-outline-success">Сменить имя пользователя</button>
        </form>
    </c:when>
    <c:otherwise>
        <h1 style="width: 100%; text-align: center">${user.username}</h1>
    </c:otherwise>
</c:choose>
<c:if test="${login == user.login}">
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
</c:if>
<ul>
    <jsp:useBean id="testPoints" scope="request" type="java.util.List<com.epam.finaltack.ivanvertylo.db.entity.TestPoints>"/>
    <c:forEach var="item" items="${testPoints}">
        <li>
            <a on ondblclick="deletePoints(this)" <c:if test="${role != 'admin'}"><c:if test="${item.test.isPublic}">href="${pageContext.request.contextPath}/test?id=${item.test.id}"</c:if></c:if> style="display: flex;">
                <c:if test="${role == 'admin'}">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="deletePoints">
                        <input type="hidden" name="userLogin" value="${user.login}">
                        <input type="hidden" name="testId" value="${item.test.id}">
                        <input type="hidden" name="userId" value="${user.id}">
                    </form>
                </c:if>
                <span>${item.test.name}</span>
                &numsp;
                <span><c:if test="${item.points == -1}">Пересдача</c:if><c:if test="${item.points != -1}">${item.points}%</c:if></span>
                <c:if test="${!item.test.isPublic}"><span style="color: red">&numsp;-Тест был удален или скрыт создателем.</span></c:if>
            </a>
        </li>
    </c:forEach>
</ul>
<c:if test="${role == 'admin'}">
    <script>
        function deletePoints(e) {
            if(confirm("Онулить результаты теста "+$(e).children('span').eq(0).text())){
                e.firstElementChild.submit()
            }
        }
    </script>
</c:if>
</body>
</html>