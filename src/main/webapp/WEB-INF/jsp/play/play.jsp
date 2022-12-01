<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="play">

	<h2>Play</h2>
	
	<table id="playGame"class="table table-striped">
		<thead>
		<tr>
			<th>Username</th>
			<th>Tiles</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${scoreboards}" var="scoreboard">
			<tr>
				<td>
					<c:out value="${scoreboard.user.username}"/>
				</td>
				<td>
					<c:out value="${scoreboard.user.tiles }"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</petclinic:layout>