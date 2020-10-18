<jsp:useBean id="user" scope="request" type="com.epam.finaltack.ivanvertylo.db.entity.User"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<html>
<c:set var="title" value="Профиль" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div style="display: flex; flex-direction: column; align-items: center; justify-content: center">
    <c:choose>
        <c:when test="${login == user.login || role == 'admin'}">
            <form action="controller" method="post" style="display: flex; align-items: center; margin-top: 30px">
                <input type="hidden" name="command" value="changeUsername">
                <input type="hidden" name="user" value="${user.login}">
                <label style="margin:10px; display: flex; align-items: center;">
                    <span style="width: 280px; text-align: right"><fmt:message key="profile_username"/>:</span>
                    <input class="form-control" style="margin-left: 10px" type="text" name="username" value="${user.username}">
                </label>
                <button type="submit" class="btn btn-outline-success"><fmt:message key="profile_change_username"/></button>
            </form>
        </c:when>
        <c:otherwise>
            <h1 style="width: 100%; text-align: center">${user.username}</h1>
        </c:otherwise>
    </c:choose>
    <c:if test="${login == user.login}">
        <form id="changePassword" action="controller" method="post" style="display: flex; flex-direction: column; align-items: flex-end; margin-top: 20px">
            <input type="hidden" name="command" value="changePassword">
            <label>
                <fmt:message key="profile_password"/>:
                <input class="form-control" id="changeInputPassword" type="password" name="password">
            </label>
            <label>
                <fmt:message key="profile_again_password"/>:
                <input class="form-control" id="dblChangeInputPassword" type="password">
            </label>
            <div class="btn btn-outline-success" onclick="onClick()"><fmt:message key="profile_change_password"/></div>
        </form>
        <script>
            function onClick() {
                debugger
                if ($("#changeInputPassword").val().trim() !== "" && $("#changeInputPassword").val() === $("#dblChangeInputPassword").val()){
                    $("#changePassword").submit();
                }
                else {
                    alert('<fmt:message key="error_profile_password"/>')
                }
            }
        </script>
    </c:if>
    <ul style="padding: 0; list-style-type: none; width: 100%; margin: 20px 0 0;">
        <jsp:useBean id="testPoints" scope="request" type="java.util.List<com.epam.finaltack.ivanvertylo.db.entity.TestPoints>"/>
        <c:forEach var="item" items="${testPoints}">
            <li style="padding: 10px 25px 25px;border-top: 1px solid lightgray">
                <a ondblclick="deletePoints(this)" <c:if test="${role != 'admin'}"><c:if test="${item.test.isPublic}">href="${pageContext.request.contextPath}/test?id=${item.test.id}"</c:if></c:if> style="display: flex; align-items: center; justify-content: center; flex-direction: column; text-decoration: none">
                    <c:if test="${role == 'admin'}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="deletePoints">
                            <input type="hidden" name="userLogin" value="${user.login}">
                            <input type="hidden" name="testId" value="${item.test.id}">
                            <input type="hidden" name="userId" value="${user.id}">
                        </form>
                    </c:if>
                    <span style="font-size: 25px"><c:if test="${item.points == -1}"><fmt:message key="profile_repass"/></c:if><c:if test="${item.points != -1}">&numsp;${item.points}%</c:if></span>
                    &numsp;
                    <span style="font-size: 20px">${item.test.name}</span>
                    <c:if test="${!item.test.isPublic}"><span style="color: red">&numsp;<fmt:message key="profile_test_hide"/>.</span></c:if>
                </a>
            </li>
        </c:forEach>
    </ul>
</div>
<c:if test="${role == 'admin'}">
    <script>
        function deletePoints(e) {
            if(confirm($(e).children('span').eq(1).text())){
                e.firstElementChild.submit()
            }
        }
    </script>
</c:if>
</body>
</html>