<%@ attribute name="pages" %>
<%@ attribute name="pagination" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var = "i" begin = "1" end = "${pages}">
    <button style="<c:if test="${offset == (i*pagination)-pagination}">background-color:#007bff; color: white</c:if>" value="${(i*pagination)-pagination}" onclick="forward(this)" type="button" class="btn btn-outline-primary">${i}</button>
</c:forEach>