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
    
    <table style="margin: auto;
  			border: darkgray solid 3px;
  			background-color: whitesmoke;">
		<tr>
			<td style="padding: 0px;"><div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						height: 100px; width: 100px;"
  						id="11"><h1 style="text-align: center; padding: 30px;">11</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="12"><h1 style="text-align: center; padding: 30px;">12</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="13"><h1 style="text-align: center; padding: 30px;">13</h1></div></td>
			<td style="padding: 0px;"><div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="21"><h1 style="text-align: center; padding: 30px;">21</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="22"><h1 style="text-align: center; padding: 30px;">22</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="23"><h1 style="text-align: center; padding: 30px;">23</h1></div>
  						<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="24"><h1 style="text-align: center; padding: 30px;">24</h1></div></td>
			<td style="padding: 0px;"><div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="31"><h1 style="text-align: center; padding: 30px;">31</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="32"><h1 style="text-align: center; padding: 30px;">32</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="33"><h1 style="text-align: center; padding: 30px;">33</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="34"><h1 style="text-align: center; padding: 30px;">34</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="35"><h1 style="text-align: center; padding: 30px;">35</h1></div></td>
			<td style="padding: 0px;"><div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="41"><h1 style="text-align: center; padding: 30px;">41</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="42"><h1 style="text-align: center; padding: 30px;">42</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="43"><h1 style="text-align: center; padding: 30px;">43</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="44"><h1 style="text-align: center; padding: 30px;">44</h1></div></td>
			<td style="padding: 0px;"><div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="51"><h1 style="text-align: center; padding: 30px;">51</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="52"><h1 style="text-align: center; padding: 30px;">52</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="53"><h1 style="text-align: center; padding: 30px;">53</h1></div></td>
		</tr>
	</table>
    

	<c:forEach items="${scoreboards }" var="scoreboard">
	<h2>${scoreboard.user.username }</h2>
	<table id="userGame"class="table table-striped">
		<thead>
		<tr>
			<th>Tiles Starting Side</th>
			<c:if test="${username == scoreboard.user.username }">
				<th>Tiles Filled Side</th>
			</c:if>
			<c:if test="${username == scoreboard.user.username }">
				<th>Actions</th>
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
				<td>
					<petclinic:menuItem active="${name eq 'play'}" url="/"
					title="Play" dropdown="${true}">
						<ul class="dropdown-menu">
							<c:forEach items="${cells }" var="cell">
								<c:if test="${cell.tile == null }">
									<li>
										<a href="<c:url value="/games/${game.id }/play/playTile/${tile.id}/${cell.id}" />" >${cell.id } </a>
									</li>
								</c:if>
							</c:forEach>
						</ul>
					</petclinic:menuItem>
				</td>
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