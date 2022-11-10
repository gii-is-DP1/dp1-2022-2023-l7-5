<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    <jsp:body>
        <h2>
            <c:if test="${game['new']}">New </c:if> Game
        </h2>
        <form:form modelAttribute="game" class="form-horizontal">
            <input type="hidden" name="id" value="${game.id}"/>
            <div class="form-group has-feedback">
                <petclinic:inputField label="Mode" name="mode"/>
                <petclinic:inputField label="Finished" name="finished"/>
                <petclinic:inputField label="Number of Players" name="numberOfPlayers"/>
                <petclinic:inputField label="Date of Creation" name="dateOfCreation"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${game['new']}">
                            <button class="btn btn-default" type="submit">Add game</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update game</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
        <c:if test="${!game['new']}">
        </c:if>
    </jsp:body>
</petclinic:layout>