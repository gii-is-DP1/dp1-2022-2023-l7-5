<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="players">

	<h2>Player Information</h2>

	<table class="table table-striped">
		<tr>
			<th>Username</th>
			<td><b><c:out value="${user.username}" /></b></td>
		</tr>
		<tr>
			<th>Email</th>
			<td><c:out value="${user.email}" /></td>
		</tr>
	</table>

	<spring:url value="{username}/edit" var="editUrl">
		<spring:param name="username" value="${user.username}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit
		User</a>

	<br />
	<br />
	<br />
	<h2>Stadistics</h2>
	
	<table class="table table-striped">
		<tr>
			<th>Wins</th>
			<td><c:out value="${user.profile.wins}"/></td>
		</tr>
		<tr>
			<th>Matches</th>
			<td><c:out value="${user.profile.matches}"/></td>
		</tr>
		<tr>
			<th>Steals</th>
			<td><c:out value="${user.profile.steals}"/></td>
		</tr>
		<tr>
			<th>Games</th>
			<td><c:out value="${user.profile.playedGames}"/></td>
		</tr>
		<tr>
			<th>Record</th>
			<td><c:out value="${user.profile.record}"/></td>
		</tr>
	</table>
	<br />
	<h2>Achievements</h2>

	<br />

	<table class="table table-striped">

		<tr>
			<c:forEach items="${user.profile.achievements}" var="ach">
				<tr>
					<td><c:out value="${ach.name}" /></td>
					<td><c:if test="${ach.badgeImage == '' }">none </c:if> <c:if
							test="${ach.badgeImage != '' }">
							<spring:url value="${ach.badgeImage}" var="image" />
							<img class="img-responsive" src="${image}" width="80" height="80" />
						</c:if></td>
				</tr>
			</c:forEach>
		</tr>
	</table>
	<spring:url value="/player/{username}/achievements" var="achUrl">
		<spring:param name="username" value="${user.username}" />
	</spring:url>
	<a class="btn btn-default" href="${fn:escapeXml(achUrl)}">See all
		achievements</a>
</petclinic:layout>