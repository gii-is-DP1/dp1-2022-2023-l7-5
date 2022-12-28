<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="restartGame">
	<h1 
    	style ="
    		text-align: center;
    		font-size: 64px">
    	¿Seguro qué quiere reiniciar la partida?
    </h1>
    
    <a class="btn btn-default" href="/games/${id }/play/restart">SÍ</a>
    <a class="btn btn-default" href="/games/${id }/play">NO</a>

</petclinic:layout>