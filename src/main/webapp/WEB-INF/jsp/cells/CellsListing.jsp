<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="cells">
    <h2>Cells</h2>

    <table id="cells" class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Position</th>
            <th>Is Flipped</th>
            <th>Is Blocked</th>
            <th>Adjacents</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cells}" var="cell">
            <tr>
                <td>
                    <c:out value="${cell.id}"/>
                </td>
                <td>
                    <c:out value="${cell.position}"/>
                </td>
                <td>
                    <c:out value="${cell.isFlipped}"/>
                </td>
                <td>
                    <c:out value="${cell.isBlocked}"/>
                </td>
                <td>
                    <c:out value="${cell.adjacents}"/>
                </td>
				<td>
					<a href="<c:url value="/cells/${cell.id}/delete/" />"><span class="glyphicon glyphicon glyphicon-trash" aria-hidden="true"></span></a>
				</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
