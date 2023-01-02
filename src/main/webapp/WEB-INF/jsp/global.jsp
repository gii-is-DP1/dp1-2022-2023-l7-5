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
			<td><c:out value="${user1.profile.wins}" /></td>
			<td><c:out value="${user1.username}" /></td>
		</tr>
		<tr>
			<th>Max Matches</th>
			<td><c:out value="${user4.profile.matches}" /></td>
			<td><c:out value="${user4.username}" /></td>
		</tr>
		<tr>
			<th>Max Steals</th>
			<td><c:out value="${user2.profile.steals}" /></td>
			<td><c:out value="${user2.username}" /></td>
		</tr>
		<tr>
			<th>Max Games</th>
			<td><c:out value="${user3.profile.playedGames}" /></td>
			<td><c:out value="${user3.username}" /></td>
		</tr>
	</table>

	<h2>Rankings</h2>

	<table id="ranking" class="table table-striped">
		<thead>
			<tr>
				<th>Wins</th>
			</tr>
		</thead>
		<tbody>

			<td><c:forEach items="${wins}" var="w">
					<tr>
						<td width = 25%><c:out value="${w.wins}" /></td>
						<td width = 25%><c:out value="${w.user.username}" /></td>
					</tr>
				</c:forEach>
			</td>
		</tbody>
	</table>
	
	<table id="ranking" class="table table-striped">
		<thead>
			<tr>
				<th>Steals</th>
			</tr>
		</thead>
		<tbody>

			<td><c:forEach items="${steals}" var="s">
					<tr>
						<td width = 25%><c:out value="${s.steals}" /></td>
						<td width = 25%><c:out value="${s.user.username}" /></td>
					</tr>
				</c:forEach>
			</td>
		</tbody>
	</table>
	
	<table id="ranking" class="table table-striped">
		<thead>
			<tr>
				<th>Played Games</th>
			</tr>
		</thead>
		<tbody>

			<td><c:forEach items="${playedGames}" var="p">
					<tr>
						<td width = 25%><c:out value="${p.playedGames}" /></td>
						<td width = 25%><c:out value="${p.user.username}" /></td>
					</tr>
				</c:forEach>
			</td>
		</tbody>
	</table>
	
	<table id="ranking" class="table table-striped">
		<thead>
			<tr>
				<th>Matches</th>
			</tr>
		</thead>
		<tbody>

			<td><c:forEach items="${matches}" var="m">
					<tr>
						<td width = 25%><c:out value="${m.matches}" /></td>
						<td width = 25%><c:out value="${m.user.username}" /></td>
					</tr>
				</c:forEach>
			</td>
		</tbody>
	</table>

</petclinic:layout>