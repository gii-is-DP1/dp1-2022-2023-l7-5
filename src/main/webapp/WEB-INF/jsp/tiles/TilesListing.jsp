<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="tiles">
    <h2>Tiles</h2>

    <table id="tilesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Starting Side</th>
            <th>Filled Side</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tiles}" var="tile">
            <tr>
                <td>
                    <c:out value="${tile.id}"/>
                </td>
                <td>
                    <c:if test="${tile.startingSide == '' }">none </c:if> 
                    <c:if test="${tile.startingSide != '' }">
						<spring:url value="${tile.startingSide}" var="image"/>
						<img class="img-responsive" src="${image}" width="80" height="80" />
					</c:if>
                </td>
                <td>
                    <c:if test="${tile.filledSide == '' }">none </c:if> 
                    <c:if test="${tile.filledSide != '' }">
						<spring:url value="${tile.filledSide}" var="image"/>
						<img class="img-responsive" src="${image}" width="80" height="80" />
					</c:if>
                </td>
				<td>
					<a href="<c:url value="/tiles/${tile.id}/delete/" />"><span class="glyphicon glyphicon glyphicon-trash" aria-hidden="true"></span></a>
				</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
