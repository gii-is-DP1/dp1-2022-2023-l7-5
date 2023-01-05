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
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell11.tile == null}">
  								${cell11.id}
  							</c:if>
  							<c:if test="${cell11.isFlipped}">
  							<spring:url value="${cell11.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell11.isFlipped}">
								<spring:url value="${cell11.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="12">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell12.tile == null}">
  								${cell12.id}
  							</c:if>
  							<c:if test="${cell12.isFlipped}">
  							<spring:url value="${cell12.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell12.isFlipped}">
								<spring:url value="${cell12.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="13">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell13.tile == null}">
  								${cell13.id}
  							</c:if>
  							<c:if test="${cell13.isFlipped}">
  							<spring:url value="${cell13.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell13.isFlipped}">
								<spring:url value="${cell13.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div></td>
			<td style="padding: 0px;"><div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="21">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell21.tile == null}">
  								${cell21.id}
  							</c:if>
  							<c:if test="${cell21.isFlipped}">
  							<spring:url value="${cell21.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell21.isFlipped}">
								<spring:url value="${cell21.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="22">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell22.tile == null}">
  								${cell22.id}
  							</c:if>
  							<c:if test="${cell22.isFlipped}">
  							<spring:url value="${cell22.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell22.isFlipped}">
								<spring:url value="${cell22.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="23">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell23.tile == null}">
  								${cell23.id}
  							</c:if>
  							<c:if test="${cell23.isFlipped}">
  							<spring:url value="${cell23.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell23.isFlipped}">
								<spring:url value="${cell23.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div>
  						<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="24">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell24.tile == null}">
  								${cell24.id}
  							</c:if>
  							<c:if test="${cell24.isFlipped}">
  							<spring:url value="${cell24.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell24.isFlipped}">
								<spring:url value="${cell24.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div></td>
			<td style="padding: 0px;"><div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="31">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell31.tile == null}">
  								${cell31.id}
  							</c:if>
  							<c:if test="${cell31.isFlipped}">
  							<spring:url value="${cell31.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell31.isFlipped}">
								<spring:url value="${cell31.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="32">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell32.tile == null}">
  								${cell32.id}
  							</c:if>
  							<c:if test="${cell32.isFlipped}">
  							<spring:url value="${cell32.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell32.isFlipped}">
								<spring:url value="${cell32.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="33">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell33.tile == null}">
  								${cell33.id}
  							</c:if>
  							<c:if test="${cell33.isFlipped}">
  							<spring:url value="${cell33.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell33.isFlipped}">
								<spring:url value="${cell33.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="34">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell34.tile == null}">
  								${cell34.id}
  							</c:if>
  							<c:if test="${cell34.isFlipped}">
  							<spring:url value="${cell34.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell34.isFlipped}">
								<spring:url value="${cell34.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="35">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell35.tile == null}">
  								${cell35.id}
  							</c:if>
  							<c:if test="${cell35.isFlipped}">
  							<spring:url value="${cell35.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell35.isFlipped}">
								<spring:url value="${cell35.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div></td>
			<td style="padding: 0px;"><div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="41">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell41.tile == null}">
  								${cell41.id}
  							</c:if>
  							<c:if test="${cell41.isFlipped}">
  							<spring:url value="${cell41.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell41.isFlipped}">
								<spring:url value="${cell41.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="42">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell42.tile == null}">
  								${cell42.id}
  							</c:if>
  							<c:if test="${cell42.isFlipped}">
  							<spring:url value="${cell42.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell42.isFlipped}">
								<spring:url value="${cell42.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="43">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell43.tile == null}">
  								${cell43.id}
  							</c:if>
  							<c:if test="${cell43.isFlipped}">
  							<spring:url value="${cell43.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell43.isFlipped}">
								<spring:url value="${cell43.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="44">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell44.tile == null}">
  								${cell44.id}
  							</c:if>
  							<c:if test="${cell44.isFlipped}">
  							<spring:url value="${cell44.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell44.isFlipped}">
								<spring:url value="${cell44.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div></td>
			<td style="padding: 0px;"><div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="51">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell51.tile == null}">
  								${cell51.id}
  							</c:if>
  							<c:if test="${cell51.isFlipped}">
  							<spring:url value="${cell51.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell51.isFlipped}">
								<spring:url value="${cell51.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="52">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell52.tile == null}">
  								${cell52.id}
  							</c:if>
  							<c:if test="${cell52.isFlipped}">
  							<spring:url value="${cell52.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
  							</c:if>
  							<c:if test="${!cell52.isFlipped}">
								<spring:url value="${cell52.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div>
  				<div style="background: darkgray;
						-webkit-clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);
  						clip-path: polygon(25% 5%, 75% 5%, 100% 50%, 75% 95%, 25% 95%, 0% 50%);height: 100px; width: 100px;"
  						id="53">
  							<h1 style="text-align: center; padding: 9px 6px 6px 6px;">
  							<c:if test="${cell53.tile == null}">
  								${cell53.id}
  							</c:if>
  							<c:if test="${cell53.isFlipped}">
  							<spring:url value="${cell53.tile.filledSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" style="display:block; margin: auto; align-self:center;"/>
  							</c:if>
  							<c:if test="${!cell53.isFlipped}">
								<spring:url value="${cell53.tile.startingSide}" var="image"/>
								<img class="img-responsive" src="${image}" width="120" height="120" />
							</c:if>
  							</h1></div></td>
		</tr>
	</table>
	
	<a class="btn btn-default" href="/games/${id}/play/restartGame">Restart</a>
	<a class="btn btn-default" href="/games/${id}/play/finishGame">Finish</a>

	
	<c:forEach items="${scoreboards}" var="scoreboard">
	<h2>${scoreboard.user.username}</h2>
	<h2> Puntuación: ${scoreboard.score}</h2>
	<table id="userGame"class="table table-striped">
		<thead>
		<tr>
			<th>Tiles Starting Side</th>
			<c:if test="${username == scoreboard.user.username}">
				<th>Tiles Filled Side</th>
			</c:if>
			<c:if test="${username == scoreboard.user.username}">
				<th>Actions</th>
			</c:if>
			
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${scoreboard.user.tiles}" var="tile">
			<tr>
				<td>
					<spring:url value="${tile.startingSide}" var="image"/>
						<img class="img-responsive" src="${image}" width="60" height="60" />
				</td>
				<c:if test="${username == scoreboard.user.username}">
					<td>
						<spring:url value="${tile.filledSide}" var="image"/>
							<img class="img-responsive" src="${image}" width="60" height="60" />
					</td>
				</c:if>
				<td>
					<petclinic:menuItem active="${name eq 'play'}" url="/"
					title="Play" dropdown="${true}">
						<ul class="dropdown-menu">
							<c:forEach items="${cells}" var="cell">
								<c:if test="${cell.tile == null}">
									<li>
										<a href="<c:url value="/games/${game.id}/play/playTile/${tile.id}/${cell.id}" />" >${cell.id} </a>
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
		<c:forEach items="${scoreboards}" var="scoreboard">
			<c:if test="${handCondition}">
				<a class="btn btn-default" href="/games/${game.id}/play/stealToken"> Steal Token</a>
		 	</c:if>
		 </c:forEach>
		</c:if>
	</c:if>
	</c:forEach>
</petclinic:layout>