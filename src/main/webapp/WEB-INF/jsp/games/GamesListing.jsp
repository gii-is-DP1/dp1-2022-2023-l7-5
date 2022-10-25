<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    <h2>Games</h2>

    <table id="gamesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Mode</th>
            <th>Finished</th>
            <th>Number of Players</th>
            <th>Date of Creation</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${games}" var="game">
            <tr>
                <td>
                    <c:out value="${game.mode}"/>
                </td>
                <td>
                    <c:out value="${game.finished}"/>
                </td>
                <td>
                    <c:out value="${game.numberOfPlayers}"/>
                </td>
                <td>
                    <c:out value="${game.dateOfCreation}"/>
                </td>
				<td>
					<a href="<c:url value="/games/${game.id}/delete/" />"><span class="glyphicon glyphicon glyphicon-trash" aria-hidden="true"></span></a>
				</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-default" href="/games/new">Create new game</a>
</petclinic:layout>