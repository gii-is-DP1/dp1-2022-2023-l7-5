<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="finishGame">

<h1 
    	style ="
    		text-align: center;
    		font-size: 64px">
    	Game Over
</h1>
 <c:forEach items="${scoreboards }" var="scoreboard">
 	<h2> Score: ${scoreboard.score }</h2>
 	<c:if test="${game.mode == 'SURVIVAL'}"><h2> Record : ${profile.record}</h2>
 	<c:if test="${scoreBoard.newRecord == true}">
 		<h3>New Record!!!!</h3>
 	</c:if>
 	</c:if>
 </c:forEach>
 <a class="btn btn-default" href="/">Go home</a>
    

</petclinic:layout>