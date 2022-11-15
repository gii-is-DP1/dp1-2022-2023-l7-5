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
        	<th>id</th>
            <th>Mode</th>
            <th>Finished</th>
            <th>Number of Players</th>
            <th>Date of Creation</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${games}" var="game">
            <tr>
            	<td>
                    <c:out value="${game.id}"/>
                </td>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-default" href="/games/new">Create new game</a>
</petclinic:layout>