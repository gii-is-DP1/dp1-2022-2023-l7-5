<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="play">

	<h1 
    	style ="
    		text-align: center;
    		font-size: 64px">
    	Play!
    </h1>

	<c:forEach items="${scoreboards }" var="scoreboard">
	<h2>${scoreboard.user.username }</h2>
	<table id="userGame"class="table table-striped">
		<thead>
		<tr>
			<th>Tiles Starting Side</th>
			<c:if test="${username == scoreboard.user.username }">
				<th>Tiles Filled Side</th>
			</c:if>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${scoreboard.user.tiles }" var="tile">
			<tr>
				<td>
					<c:out value="${tile.startingSide }"/>
				</td>
				<c:if test="${username == scoreboard.user.username }">
					<td>
						<c:out value="${tile.filledSide }"/>
					</td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:if test="${username == scoreboard.user.username}">
		<c:if test="${!game.bag.isEmpty()}">
			<a class="btn btn-default" href="/games/${game.id}/play/stealToken"> Steal Token</a>
		</c:if>
	</c:if>
	</c:forEach>
</petclinic:layout>