<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources"/>
<html>
<c:set var="title" value="Тестирование" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>
<c:choose>
    <c:when test="${total == null || total == -1}">
        <div style="position: absolute; top: 0; left: 0">
            <h1 id="timer">

            </h1>
        </div>
        <div id="onUnload" style="margin-top: 50px" class="w3-container">
            <div class="w3-row scroll-block" style="display: flex; flex-direction: row; align-items: center; justify-content: center">
                <jsp:useBean id="questions" scope="request" type="java.util.List<com.epam.finaltack.ivanvertylo.db.entity.Question>"/>
                <c:forEach var="item" items="${questions}">
                    <c:set var="k" value="${k+1}"/>
                    <a href="javascript:void(0)" onclick="openCity(this, ${item.id});">
                        <div id="red_${item.id}" class="w3-third tablink w3-bottombar w3-hover-light-grey w3-padding <c:if test="${k <= 1}">w3-border-blue</c:if>" style="width: max-content">${k}</div>
                    </a>
                </c:forEach>
            </div>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="checkTest">
                <input type="hidden" name="testId" value="${testId}">
                <c:forEach var="item" items="${questions}">
                    <c:set var="i" value="${i+1}"/>
                    <div id="${item.id}" class="w3-container question" style="<c:if test="${i > 1}">display:none;</c:if>">
                        <h2>${item.name}</h2>
                        <ul class="list-group list-group-flush">
                            <c:forEach var="item2" items="${item.answers}">
                                <li class="list-group-item">
                                    <label style="width: 100%; margin: 0; position: relative; display: flex; flex-direction: row; justify-content: space-between; align-items: center">
                                        <span style="position: relative; font-size: 20px">${item2.name}</span>
                                        <input onclick="selectItem(this)" style="position: relative; margin: 0; transform: scale(2)" class="form-check-input" type="checkbox" name="answers" value="${item2.id}">
                                    </label>
                                </li>
                            </c:forEach>
                        </ul>
                        <a style="float: right" href="javascript:void(0)" onclick="openCity(this, ${i < questions.size()-1?questions.get(i).id:questions.get(questions.size()-1).id});setColor('red_${i < questions.size()-1?questions.get(i).id:questions.get(questions.size()-1).id}');">
                            <img style="height: 45px; margin: 17px" src="${pageContext.request.contextPath}/img/next.png" alt=">>Next>>">
                        </a>
                    </div>
                </c:forEach>
                <button id="submit" style="position: absolute; margin: 10px; <c:if test="${questions.size() == 0}">display:none;</c:if> top: 0; right: 0" type="submit" class="btn btn-success"><fmt:message key="test_end"/></button>
            </form>

        </div>

        <script>
            $('form').submit(function () {
                window.onbeforeunload = null;
            });
            window.onbeforeunload = function () {
                $.ajax({
                    url: "/controller?command=leaveTest&testId="+${testId},
                    type: "GET",
                    dataType: "html",
                    contentType: "application/json;charset=utf-8"
                });
            };
            let time = ${testTime*60};
            let timerId = setInterval(() => {
                $("#timer").text(Math.floor(time / 60) + ':' + time % 60);
                time--
            }, 1000);


            setTimeout(() => {$("#submit").trigger('click')}, ${testTime*60*1000});
            function openCity(evt, id) {
                let i, x, tablinks;
                x = document.getElementsByClassName("question");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablink");
                for (i = 0; i < x.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" w3-border-blue", "");
                }
                document.getElementById(id).style.display = "block";
                evt.firstElementChild.className += " w3-border-blue";
            }
            function setColor(id) {
                document.getElementById(id).className.replace(" w3-border-blue", "");
                document.getElementById(id).className += " w3-border-blue";
            }
            function selectItem(e) {
                if (e.checked)
                    $(e.parentNode.parentElement).css("background-color","lightgoldenrodyellow");
                else
                    $(e.parentNode.parentElement).css("background-color","");
            }
        </script>
    </c:when>
    <c:otherwise>
        <h1 style="display: flex; align-items: center; justify-content: center; position: absolute; top: 0; left: 0; width: 100%; height: 100%">
            <span><fmt:message key="test_result_1"/></span>&nbsp;<b>"${testName}"</b>&nbsp;<span><fmt:message key="test_result_2"/>:</span>&nbsp;<b>${total}%</b>
        </h1>
    </c:otherwise>
</c:choose>
</body>
</html>