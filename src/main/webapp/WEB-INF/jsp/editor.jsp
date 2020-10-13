<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Админ" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<form method="post" action="controller">
    <input type="hidden" name="command" value="updateTestInfo">
    <input type="hidden" name="testId" value="${testId}">
    <label>
        Название
        <input type="text" name="testName" value="${testName}">
    </label>
    <label>
        Предметная область
        <input type="text" name="testSubject" value="${testSubject}">
    </label>
    <label>
        isPublic
        <input type="checkbox" name="testPublic" <c:if test="${testPublic == true}">checked</c:if>>
    </label>
    <label>
        Время тестирования
        <input type="number" name="testTime" min="1" step="1" required pattern="[0-9]+" value="${testTime}">
    </label>
    <button type="submit">Сохранить</button>
</form>
<ul>

    <jsp:useBean id="questions" scope="request"
                 type="java.util.List<com.epam.finaltack.ivanvertylo.db.entity.Question>"/>
    <c:forEach var="item" items="${questions}">
        <li>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="updateQuestion">
                <input type="hidden" name="questionId" value="${item.id}">
                <input type="text" name="questionName" value="${item.name}">
                <ul>
                    <c:forEach var="item2" items="${item.answers}">
                        <li>
                            <label>
                                <input type="text" name="${item2.id}" value="${item2.name}">
                                <input type="checkbox" name="${item2.id}" <c:if test="${item2.right == true}">checked</c:if>>
                            </label>
                        </li>
                    </c:forEach>
                </ul>
                <button type="submit">Сохранить</button>
            </form>
        </li>
    </c:forEach>
</ul>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="saveQuestion">
        <input type="hidden" name="testId" value="${testId}">
        <label>
            Кол. баллов
            <input type="number" min="1" step="1" required pattern="[0-9]+" name="questionPoint">
        </label>
        <label>
            Вопрос:
            <input type="text" name="questionName">
        </label>
        <div id="answerBlock">
            <input type="text" style="margin: 5px" name="answer"><div style="display:inline-block"><input type="hidden" name="answerRight" value="false"><input type="checkbox" onclick="checkBox(this)"></div><br>
        </div>
        <div onclick="onClick()">Добавить вопрос</div>
        <button type="submit">Сохранить</button>
    </form>
<script>
    function onClick() {
        $("#answerBlock").append('<input type="text" style="margin: 5px" name="answer"><div style="display:inline-block"><input type="hidden" name="answerRight" value="false"><input type="checkbox" onclick="checkBox(this)"></div><br>')
    }

    function checkBox(e) {
        e.parentNode.firstElementChild.value = e.checked;
    }
</script>
</body>
</html>