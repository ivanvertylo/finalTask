<%@ page contentType="text/html;charset=UTF-8" %>
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
<div>
    <label>
        Поиск пользователя:
        <input type="text" oninput="onChange(this)">
    </label>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="blockUser">
        <label for="findLogin"> Login:</label><input style="border: none;" type="text" readonly id="findLogin" name="login">
        <label for="findUsername">Username:</label><input type="text" style="border: none" readonly id="findUsername" name="username">
        <button disabled id="blockUser" type="submit">---</button>
    </form>
</div>
<ul>
    <jsp:useBean id="tests" scope="request" type="java.util.List<com.epam.finaltack.ivanvertylo.db.entity.Test>"/>
    <c:forEach var="item" items="${tests}">
        <li>
            <a <c:if test='${item.isPublic != true}'>style="color: red"</c:if> href="/editor?id=${item.id}">${item.name}</a>
        </li>
    </c:forEach>
</ul>
<script>
    function onChange(e) {
        $.ajax({
            url: "/controller?command=findLogin&login="+e.value,
            type: "GET",
            dataType: "html",
            success: function (response) {
                const json = $.parseJSON(response)
                if (json.id != null){
                    console.log(json)
                    $("#findLogin").val(json.login);
                    $("#findUsername").val(json.username);
                    $("#blockUser").prop('disabled', false);
                    $("#blockUser").html(json.isBlocked === true? "Разблокировать":"Заблокировать")
                }
                else {
                    $("#blockUser").prop('disabled', true);
                    $("#findLogin").val("");
                    $("#findUsername").val("");
                    $("#blockUser").html("---")
                }
            }
        });
    }
</script>
</body>
</html>