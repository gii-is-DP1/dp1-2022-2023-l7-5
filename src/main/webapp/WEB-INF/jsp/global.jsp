<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="players">

	<h2>Global Stadistics</h2>

	<table class="table table-striped">
		<tr>
			<th>Total Matches</th>
			<td><c:out value="${user.profile.matches}" /></td>
		</tr>
		<tr>
			<th>Total Steals</th>
			<td><c:out value="${user.profile.steals}" /></td>
		</tr>
		<tr>
			<th>Total Games</th>
			<td><c:out value="${user.profile.playedGames}" /></td>
		</tr>
	</table>
	
	
	<br />
	<br />
	<br />
	
	<h2>Hall of Fame</h2>


	<table class="table table-striped">
		<tr>
			<th>Max Wins</th>
			<td><c:out value="${user1.profile.wins} ${user1.username}" /></td>
		</tr>
		<tr>
			<th>Max Matches</th>
			<td><c:out value="${user2.profile.matches} ${user2.username}" /></td>
		</tr>
		<tr>
			<th>Max Steals</th>
			<td><c:out value="${user3.profile.steals} ${user3.username}" /></td>
		</tr>
		<tr>
			<th>Max Games</th>
			<td><c:out value="${user4.profile.playedGames} ${user4.username}" /></td>
		</tr>
	</table>
	
</petclinic:layout>