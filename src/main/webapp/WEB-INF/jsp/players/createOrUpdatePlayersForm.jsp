<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">
    <jsp:body>
        <h2>
            <c:if test="${player['new']}">New </c:if> Player
        </h2>
        <form:form modelAttribute="player"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${player.id}"/>
            <div class="form-group has-feedback">
                <div class="form-group">
                    
                </div>
                <petclinic:inputField label="Name" name="name"/>
                <petclinic:inputField label="Price" name="price"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${player['new']}">
                            <button class="btn btn-default" type="submit">Add Player</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update Player</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
        <c:if test="${!player['new']}">
        </c:if>
    </jsp:body>
</petclinic:layout >
