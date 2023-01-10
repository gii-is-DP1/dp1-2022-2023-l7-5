<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    <h2>List of current games</h2>

    <table id="gamesTable" class="table table-striped">
        <thead>
        <tr>
        	<th>Game</th>
            <th>Players</th>
            <th>Creator</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${games}" var="game">
            <tr>
                <td>
                    <c:out value="${game.mode} #${game.id}"/>
                </td>
                <td>
                    <c:out value="${game.numberCurrentPlayers} / ${game.numberOfPlayers}"/>
                </td>
                <td>
                	<c:out value="${game.scoreboards.get(0).user.username}"/>
                </td>
                <c:if test="${game.numberCurrentPlayers != game.numberOfPlayers}"> 
                <td>
                    <a class="btn btn-default" href="/games/join/${game.id}/${username}">Join</a>
                </td>
                </c:if> 
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-default" href="/games/new">Create new game</a>
</petclinic:layout>