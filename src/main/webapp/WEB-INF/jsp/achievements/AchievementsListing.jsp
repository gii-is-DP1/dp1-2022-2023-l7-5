<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">
    <h2>Achievements</h2>

    <table id="achievements" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Threshold</th>
            <th>Description</th>
            <th>Badge Image</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${achievements}" var="achievement">
            <tr>
                <td>
                    <c:out value="${achievement.name}"/>
                </td>
                <td>
                    <c:out value="${achievement.threshold}"/>
                </td>
                <td>
                    <c:out value="${achievement.description}"/>
                </td>
               <td><c:if test="${achievement.badgeImage == '' }">none </c:if> <c:if
						test="${achievement.badgeImage != '' }">
						<spring:url value="${achievement.badgeImage}" var="image"/>
						<img class="img-responsive" src="${image}" width="80"
							height="80" />
					</c:if></td>
				<td>
					<a href="<c:url value="/achievements/${achievement.id}/delete/"/>"><span class="glyphicon glyphicon glyphicon-trash" aria-hidden="true"></span></a>
				</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
