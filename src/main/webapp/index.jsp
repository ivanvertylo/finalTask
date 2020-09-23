<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Главная</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body>
    <header>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <img class="nav_bar_logo" src="img/QuickTest.png" alt="Logo">
                </div>
                <div class="col-md-8 nav_bar_btn">
                    <button type="button" class="btn btn-primary">Тесты</button>
                    <c:choose>
                        <c:when test="${role != null}">
                            <button type="button" class="btn btn-success"><%=session.getAttribute("username")%></button>
                            <form style="display: inline-block; margin: 0;" method="post" action="controller">
                                <input type="hidden" name="command" value="logout">
                                <button type="submit" class="btn btn-outline-danger">Выход</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#staticBackdrop">Вход|Регистрация</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Вход|Регистрация</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body login_area">
                        <form action="controller" method="POST">
                            <input type="hidden" name="command" value="loginReg">

                            <label for="login">Login</label>
                            <br>
                            <input type="text" name="login" id="login"/>
                            <br>
                            <label style="margin-top: 10px;" for="password">Password</label>
                            <br>
                            <input type="text" name="password" id="password" />
                            <br>
                            <label class="is_new" for="isNew">У меня нет аккаунта
                                <input type="checkbox" name="isNew" id="isNew">
                            </label>
                            <br>
                            <button type="submit" class="btn btn-success login_submit">Войти</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </header>
</body>
</html>
