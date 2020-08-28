<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 4/16/2020
  Time: 4:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Pagination -->
<ul class="pagination justify-content-center" style="margin: 20px 0;">

    <c:choose>
        <c:when test="${page == 1}">
            <li class="page-item disabled"><a href="#" class="page-link">Previous</a></li>
        </c:when>
        <c:otherwise>
            <li class="page-item"><a href="/band/music?id=${page-1}" class="page-link">Previous</a></li>
        </c:otherwise>
    </c:choose>


    <c:choose>
        <c:when test="${page-1 > 1}">
            <li class="page-item disabled"><a class="page-link">...</a></li>
            <c:set var="begin" value="${page-1}"></c:set>
        </c:when>
        <c:otherwise>
            <c:set var="begin" value="1"></c:set>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${page+1 < pageNumber}">
            <c:set var="end" value="${page+1}"></c:set>
        </c:when>
        <c:otherwise>
            <c:set var="end" value="${pageNumber}"></c:set>
        </c:otherwise>
    </c:choose>

    <c:forEach var="i" begin="${begin}" end="${end}">
        <c:choose>
            <c:when test="${page == i}">
                <li class="page-item active"><a href="#" class="page-link">${i}</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a href="/band/music?id=${i}" class="page-link">${i}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${page+1 < pageNumber}">
        <li class="page-item disabled"><a class="page-link">...</a></li>
    </c:if>

    <c:choose>
        <c:when test="${page == pageNumber || pageNumber==0}">
            <li class="page-item disabled"><a href="#" class="page-link">Next</a></li>
        </c:when>
        <c:otherwise>
            <li class="page-item"><a href="/band/music?id=${page+1}" class="page-link">Next</a></li>
        </c:otherwise>
    </c:choose>
</ul>
<!-- -->
