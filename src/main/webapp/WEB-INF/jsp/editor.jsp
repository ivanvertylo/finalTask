<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources"/>
<html>
<c:set var="title" value="Админ" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<form method="post" action="controller" style="display: flex; align-items: center; justify-content: center; margin-top: 15px">
    <input type="hidden" name="command" value="updateTestInfo">
    <input type="hidden" name="testId" value="${testId}">
    <label style="margin: 15px; display: flex; flex-direction: column">
        <fmt:message key="editor_test_name"/>
        <input style="outline: none;" oninput="onFindTestName(this)" id="createTest" type="text" required="required" name="testName" value="${testName}">
    </label>
    <label style="margin: 15px; display: flex; flex-direction: column">
        <fmt:message key="editor_test_subject"/>
        <div style="position: relative">
            <input id="subjectInput" oninput="onChange(this)" type="text" required="required" name="testSubject" value="${testSubject}">
            <div id="sortBar" style="z-index: 1; position: absolute; top: 100%; left: 0; width: 100%; display: flex; flex-direction: column; background-color: lightgoldenrodyellow; text-align: center"></div>
        </div>
    </label>
    <label style="margin: 15px; display:flex; flex-direction: column">
        <fmt:message key="editor_test_time"/>
        <input type="number" name="testTime" min="1" step="1" required pattern="[0-9]+" value="${testTime}">
    </label>
    <label style="margin: 15px">
        <fmt:message key="editor_test_is_public"/>
        <input type="checkbox" name="testPublic" <c:if test="${testPublic == true}">checked</c:if>>
    </label>
    <button id="createTestButton" class="btn btn-success" type="submit"><fmt:message key="editor_test_save"/></button>
</form>
<div style="display: flex; align-items: center; justify-content: center; border-bottom: lightgray 1px solid">
    <ul style="list-style-type: none; padding: 0; margin: 1rem">

        <jsp:useBean id="questions" scope="request"
                     type="java.util.List<com.epam.finaltack.ivanvertylo.db.entity.Question>"/>
        <c:forEach var="item" items="${questions}">
            <li>
                <form action="controller" method="post" style="display: flex; flex-direction: column">
                    <input type="hidden" name="command" value="updateQuestion">
                    <input type="hidden" name="questionId" value="${item.id}">
                    <input type="text" name="questionName" value="${item.name}">
                    <ul style="list-style-type: none">
                        <c:forEach var="item2" items="${item.answers}">
                            <li style="margin-top: 5px">
                                <label>
                                    <input type="text" name="${item2.id}" value="${item2.name}">
                                    <input type="checkbox" name="${item2.id}" <c:if test="${item2.right == true}">checked</c:if>>
                                </label>
                            </li>
                        </c:forEach>
                    </ul>
                    <button class="btn btn-success" type="submit"><fmt:message key="editor_question_save"/></button>
                </form>
                <form action="controller" method="post" style="display: flex;flex-direction: column">
                    <input type="hidden" name="command" value="deleteQuestion">
                    <input type="hidden" name="questionId" value="${item.id}">
                    <input type="hidden" name="testId" value="${testId}">
                    <button class="btn btn-outline-danger" type="submit"><fmt:message key="editor_question_delete"/></button>
                </form>
            </li>
        </c:forEach>
    </ul>
</div>
    <div style="display: flex; align-items: center; justify-content: center; margin-top: 2rem">
        <form action="controller" method="post" style="display: flex; align-items: initial; justify-content: center; flex-direction: column">
            <input type="hidden" name="command" value="saveQuestion">
            <input type="hidden" name="testId" value="${testId}">
            <label>
                <fmt:message key="editor_answer_points"/>
                <input type="number" min="1" step="1" required pattern="[0-9]+" name="questionPoint">
            </label>
            <label>
                <fmt:message key="editor_question_name"/>
                <input type="text" required="required" name="questionName">
            </label>
            <div id="answerBlock">
                <input type="text" required="required" style="margin: 5px" name="answer"><div style="display:inline-block"><input type="hidden" name="answerRight" value="false"><input type="checkbox" onclick="checkBox(this)"></div><br>
            </div>
            <div style="margin-top: 10px" class="btn btn-primary" onclick="onClick()"><fmt:message key="editor_question_add"/></div>
            <button style="margin-top: 10px" class="btn btn-success" type="submit"><fmt:message key="editor_question_save"/></button>
        </form>
    </div>
<script>
    function onFindTestName(e) {
        $.ajax({
            url: "/controller?command=findTestByName&testName="+e.value+"&testId=${testId}",
            type: "GET",
            dataType: "html",
            success: function (response) {
                debugger
                const json = $.parseJSON(response)
                if (json || e.value.trim() === ""){
                    $("#createTest").css("border","solid 3px red");
                    $("#createTestButton").prop( "disabled", true );

                }
                else {
                    $("#createTest").css("border","solid 3px green");
                    $("#createTestButton").prop( "disabled", false );
                }

            }
        });
    }
    function onClick() {
        $("#answerBlock").append('<input type="text" required="required" style="margin: 5px" name="answer"><div style="display:inline-block"><input type="hidden" name="answerRight" value="false"><input type="checkbox" onclick="checkBox(this)"></div><br>')
    }

    function checkBox(e) {
        e.parentNode.firstElementChild.value = e.checked;
    }
    function onChange(e) {
        $.ajax({
            url: "/controller?command=findSubject&subject="+e.value,
            type: "GET",
            dataType: "html",
            contentType: "application/json;charset=utf-8",
            success: function (response) {
                const json = $.parseJSON(response)
                const myNode = document.getElementById("sortBar");
                while (myNode.firstChild) {
                    myNode.removeChild(myNode.firstChild);
                }
                $("#sortBar").show()
                json.map((item)=>{
                    $("#sortBar").append("<div style='padding: 15px; border-top: 1px solid gray'><span style='font-style: italic'>#</span><span onclick='selectSubject(this)' style='font-weight: bold; cursor: pointer;'>"+item.name+"</span></div>");
                })
            }
        });
    }
    function selectSubject(e) {
        $("#subjectInput").val(e.innerText);
        $("#sortBar").hide();
    }
</script>
</body>
</html>