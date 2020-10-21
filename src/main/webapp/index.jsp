<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ taglib prefix="myPagination" tagdir="/WEB-INF/tags" %>
<html>
<c:set var="title" value="Главная" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<fmt:setLocale value="${currentLocale}" scope="session"/>
<fmt:setBundle basename="resources"/>

<style>
    div.sticky {
        position: -webkit-sticky;
        position: sticky;
        top: 0;
        background-color: white;
        padding: 25px 25px 0;
        z-index: 1;
        margin-bottom: 25px;
    }
    .nav label{
        margin:0;
    }
    .tests a{
        -webkit-transition: .3s;
    }
    .tests a:hover{
        background-color: lightgreen;
        color:white !important;
        padding: 30px !important;
        font-size: x-large;
    }
    .tests a:hover .mySmall{
        opacity: 1 !important;
        margin-right: 10px !important;
        font-size: initial;
        font-style: italic;
    }
</style>
<body style="overflow-y:scroll;">
<div style="position: absolute; top: 0; right: 0; margin: 5px;">
    <a style="text-decoration: none; color: black;" href="controller?command=changeLocale&locale=ru">RU</a>
    <a style="text-decoration: none; color: black;" href="controller?command=changeLocale&locale=en">EN</a>
</div>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="sticky">
    <form id="setFilter" action="main" method="get">
        <div class="row">
            <div class="col-md-12" style="margin-bottom: 15px; display: flex; justify-content: center;">
                <div style="position: relative">
                    <input id="subjectInput" oninput="onChange(this)" type="text" class="form-control" name="subject" placeholder="<fmt:message key="main_subject"/>" style="width: 400px; display: inline" value="${subject}">
                    <div id="sortBar" style="z-index: 1; position: absolute; top: 100%; left: 0; width: 100%; display: flex; flex-direction: column; background-color: lightgoldenrodyellow; text-align: center"></div>
                </div>
            </div>
            <div class="col-md-6" style="display: flex; flex-direction: column; justify-content: center; align-items: flex-end">
                <select style="width: max-content" class="form-control" name="type">
                    <option value="asc" <c:if test="${type == 'asc'}">selected</c:if>><fmt:message key="main_sort_up"/></option>
                    <option value="desc" <c:if test="${type == 'desc'}">selected</c:if>><fmt:message key="main_sort_down"/></option>
                </select>
                <label style="display: flex; align-items: center;">
                    <fmt:message key="main_view_tests"/>:
                    <select style="width: max-content; margin: 10px 0 10px 10px;" class="form-control" name="pagination">
                        <option value="5"<c:if test="${pagination == 5}">selected</c:if>>5</option>
                        <option value="10" <c:if test="${pagination == 10}">selected</c:if>>10</option>
                        <option value="15" <c:if test="${pagination == 15}">selected</c:if>>15</option>
                    </select>
                </label>
            </div>
            <div class="col-md-6">
            <div class="nav" style="display: flex; flex-direction: column;">
                <label>
                    <fmt:message key="main_sort_by_name"/>
                    <input style="position: relative; float: left; margin: 7px" class="form-check-input" type="radio" name="sort" value="name" <c:if test="${sort == 'name'}">checked</c:if>>
                </label>
                <label>
                    <fmt:message key="main_sort_by_hard"/>
                    <input style="position: relative; float: left; margin: 7px" class="form-check-input" type="radio" name="sort" value="hard" <c:if test="${sort == 'hard'}">checked</c:if>>
                </label>
                <label>
                    <fmt:message key="main_sort_by_count_question"/>
                    <input style="position: relative; float: left; margin: 7px" class="form-check-input" type="radio" name="sort" value="question" <c:if test="${sort == 'question'}">checked</c:if>>
                </label>
            </div>
            </div>
            <div class="col-md-12" style="text-align: center; margin-top: 10px">
                <button style="width: 400px" type="submit" class="btn btn-outline-primary"><fmt:message key="main_apply"/></button>
            </div>
            <div class="col-md-12" style="text-align: center; margin-top: 10px">
                <input type="hidden" name="offset" id = "offset">
                <myPagination:pagination pages="${pages}" pagination="${pagination}"/>
            </div>
        </div>
    </form>
    <hr style="margin-bottom: 0">
</div>
<div class="container">
    <div class="row tests">
        <c:forEach items="${tests}" var="item">
            <a <c:if test="${role != null}">href="test?id=${item.id}"</c:if> <c:if test="${role == null}">onclick="register()" </c:if> class="col-md-12" style="display: flex; align-items: center; justify-content: space-between; text-decoration: none;margin-bottom: 10px; padding: 15px; border-radius: 3px; border: 2px #007bff solid; color: #007bff">
                    <span>${item.name}</span>
                    <span>${item.time} <fmt:message key="main_min"/>.</span>
                    <small class="mySmall" style="position: absolute; right:0; top: 0; margin: 4px; opacity: 0">${item.author}</small>
            </a>
        </c:forEach>
    </div>
</div>
<script>
    function forward(e) {
        $("#offset").val(e.value);
        $("#setFilter").submit();
    }
    function register() {
        $("#loginReg").trigger('click');
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
