<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="profiles">
    <h2>Profiles</h2>

    <table id="profiles" class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Username</th>
            <th>Played Games</th>
            <th>Matches</th>
            <th>Wins</th>
            <th>Steals</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${profile}" var="profile">
            <tr>
                <td>
                    <c:out value="${profile.id}"/>
                </td>
                <td>
                    <c:out value="${profile.user.username}"/>
                </td>
                <td>
                    <c:out value="${profile.playedGames}"/>
                </td>
                <td>
                    <c:out value="${profile.matches}"/>
                </td>
                <td>
                    <c:out value="${profile.wins}"/>
                </td>
                <td>
                    <c:out value="${profile.steals}"/>
                </td>
				<td>
					<a href="<c:url value="/profiles/${profile.id}/delete/"/>"><span class="glyphicon glyphicon glyphicon-trash" aria-hidden="true"></span></a>
				</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
