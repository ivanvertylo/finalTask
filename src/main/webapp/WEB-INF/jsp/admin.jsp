<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources"/>
<html>
<c:set var="title" value="Админ" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<form method="post" action="controller" style="display: flex; align-items: center; justify-content: center; margin: 0">
    <input type="hidden" name="command" value="createTest">
    <input style="width: 300px; margin: 35px" class="form-control" type="text" name="testName">
    <button class="btn btn-success" type="submit"><fmt:message key="admin_create_test"/></button>
</form>
<div style="display: flex; justify-content: center; align-items: center;">
    <label style="margin: 0; display: flex;">
        <span style="width: 260px; display: flex; align-items: center; justify-content: flex-end"><fmt:message key="admin_find_user"/>:</span>
        <input style="width: 200px; margin: 15px" class="form-control" type="text" oninput="onChange(this)">
    </label>
    <form style="margin: 0" action="controller" method="post">
        <input type="hidden" name="command" value="blockUser">
        <label style="margin: 0" for="findLogin"><fmt:message key="admin_login"/>:</label><input style="border: none;" type="text" readonly id="findLogin" name="login">
        <label style="margin: 0" for="findUsername"><fmt:message key="admin_username"/>:</label><input type="text" style="border: none" readonly id="findUsername" name="username">
        <button class="btn btn-primary" disabled id="blockUser" type="submit">---</button>
    </form>
    <a style="display: none; margin-left: 10px;" id="profileUser"><fmt:message key="admin_profile"/></a>
</div>
<ul style="padding: 0; list-style-type: none; width: 100%; margin: 20px 0 0;">
    <jsp:useBean id="tests" scope="request" type="java.util.List<com.epam.finaltack.ivanvertylo.db.entity.Test>"/>
    <c:forEach var="item" items="${tests}">
        <li style="padding: 25px;border-top: 1px solid lightgray">
            <a style="display: flex; align-items: center; justify-content: center; flex-direction: column; text-decoration: none; <c:if test='${item.isPublic != true}'>color: red;</c:if>" href="/editor?id=${item.id}">${item.name}</a>
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
                    $("#blockUser").html(json.isBlocked === true? '<fmt:message key="admin_un_block"/>':'<fmt:message key="admin_block"/>')
                    $("#profileUser").show();
                    $("#profileUser").attr("href", "/profile?user="+json.login)
                }
                else {
                    $("#profileUser").hide();
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