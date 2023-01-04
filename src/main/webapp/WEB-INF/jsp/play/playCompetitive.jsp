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
			<td style="padding: 0px;">
				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						height: 100px; width: 100px;"
  						id="11">
  							<h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell11.tile == null}">
  							${cell11.id}
  							</c:if>
  							<c:if test="${cell11.isFlipped}">
  							F${cell11.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell11.isFlipped}">
  							${cell11.tile.startingSide}
  							</c:if>
  							</h1>
  				</div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="12">
  							<h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell12.tile == null}">
  							${cell12.id}
  							</c:if>
  							<c:if test="${cell12.isFlipped}">
  							F${cell12.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell12.isFlipped}">
  							${cell12.tile.startingSide}
  							</c:if>
  							</h1>
  				</div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="13"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell13.tile == null}">
  							${cell13.id}
  							</c:if>
  							<c:if test="${cell13.isFlipped}">
  							F${cell13.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell13.isFlipped}">
  							${cell13.tile.startingSide}
  							</c:if>
  							</h1></div></td>
			<td style="padding: 0px;"><div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="21"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell21.tile == null}">
  							${cell21.id}
  							</c:if>
  							<c:if test="${cell21.isFlipped}">
  							F${cell21.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell21.isFlipped}">
  							${cell21.tile.startingSide}
  							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="22"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell22.tile == null}">
  							${cell22.id}
  							</c:if>
  							<c:if test="${cell22.isFlipped}">
  							F${cell22.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell13.isFlipped}">
  							${cell22.tile.startingSide}
  							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="23"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell23.tile == null}">
  							${cell23.id}
  							</c:if>
  							<c:if test="${cell23.isFlipped}">
  							F${cell23.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell23.isFlipped}">
  							${cell23.tile.startingSide}
  							</c:if>
  							</h1></div>
  						<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="24"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell24.tile == null}">
  							${cell24.id}
  							</c:if>
  							<c:if test="${cell24.isFlipped}">
  							F${cell24.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell24.isFlipped}">
  							${cell24.tile.startingSide}
  							</c:if>
  							</h1></div></td>
			<td style="padding: 0px;"><div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="31"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell31.tile == null}">
  							${cell31.id}
  							</c:if>
  							<c:if test="${cell31.isFlipped}">
  							F${cell31.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell31.isFlipped}">
  							${cell31.tile.startingSide}
  							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="32"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell32.tile == null}">
  							${cell32.id}
  							</c:if>
  							<c:if test="${cell32.isFlipped}">
  							F${cell32.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell32.isFlipped}">
  							${cell32.tile.startingSide}
  							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="33"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell33.tile == null}">
  							${cell33.id}
  							</c:if>
  							<c:if test="${cell33.isFlipped}">
  							F${cell33.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell33.isFlipped}">
  							${cell33.tile.startingSide}
  							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="34"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell34.tile == null}">
  							${cell34.id}
  							</c:if>
  							<c:if test="${cell34.isFlipped}">
  							F${cell34.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell34.isFlipped}">
  							${cell34.tile.startingSide}
  							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="35"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell35.tile == null}">
  							${cell35.id}
  							</c:if>
  							<c:if test="${cell35.isFlipped}">
  							F${cell35.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell35.isFlipped}">
  							${cell35.tile.startingSide}
  							</c:if>
  							</h1></div></td>
			<td style="padding: 0px;"><div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="41"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell41.tile == null}">
  							${cell41.id}
  							</c:if>
  							<c:if test="${cell41.isFlipped}">
  							F${cell41.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell41.isFlipped}">
  							${cell41.tile.startingSide}
  							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="42"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell42.tile == null}">
  							${cell42.id}
  							</c:if>
  							<c:if test="${cell42.isFlipped}">
  							F${cell42.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell42.isFlipped}">
  							${cell42.tile.startingSide}
  							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="43"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell43.tile == null}">
  							${cell43.id}
  							</c:if>
  							<c:if test="${cell43.isFlipped}">
  							F${cell43.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell43.isFlipped}">
  							${cell43.tile.startingSide}
  							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="44"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell44.tile == null}">
  							${cell44.id}
  							</c:if>
  							<c:if test="${cell44.isFlipped}">
  							F${cell44.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell44.isFlipped}">
  							${cell44.tile.startingSide}
  							</c:if>
  							</h1></div></td>
			<td style="padding: 0px;"><div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="51"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell51.tile == null}">
  							${cell51.id}
  							</c:if>
  							<c:if test="${cell51.isFlipped}">
  							F${cell51.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell51.isFlipped}">
  							${cell51.tile.startingSide}
  							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="52"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell52.tile == null}">
  							${cell52.id}
  							</c:if>
  							<c:if test="${cell52.isFlipped}">
  							F${cell52.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell52.isFlipped}">
  							${cell52.tile.startingSide}
  							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="53"><h1 style="text-align: center; padding: 30px;">
  							<c:if test="${cell53.tile == null}">
  							${cell53.id}
  							</c:if>
  							<c:if test="${cell53.isFlipped}">
  							F${cell53.tile.filledSide}
  							</c:if>
  							<c:if test="${!cell53.isFlipped}">
  							${cell53.tile.startingSide}
  							</c:if>
  							</h1></div></td>
		</tr>
	</table>
	<c:forEach items="${scoreboards }" var="scoreboard">
	<h2>${scoreboard.user.username }</h2>
	<h2> Puntuación: ${scoreboard.score }</h2>
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
				<td>
					<c:if test="${scoreboard.orden ==  game.turn}">
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
					</c:if>
				</td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:if test="${username == scoreboard.user.username}">
		<c:if test="${scoreboard.orden ==  game.turn}">
			<c:if test="${!game.bag.isEmpty()}">
				<a class="btn btn-default" href="/games/${game.id}/play/stealToken"> Steal Token</a>
		 	</c:if>
		 	<a class="btn btn-default" href="/games/${game.id}/play/skipTurn"> Skip Turn</a>
		</c:if>
	</c:if>
	</c:forEach>
</petclinic:layout>