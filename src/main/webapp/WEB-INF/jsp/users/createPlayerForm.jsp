<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="players">
    <h2>
        <c:if test="${!user.enabled}">New </c:if> Player
    </h2>
    <form:form modelAttribute="user" class="form-horizontal" id="add-player-form">
        <div class="form-group has-feedback">
        	<c:if test="${!user.enabled}">
            <petclinic:inputField label="Username" name="username"/>
            </c:if>
            <petclinic:inputField label="E-mail" name="email"/>
            <petclinic:inputField label="Password" name="password"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${!user.enabled}">
                        <button class="btn btn-default" type="submit">Add Player</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Player</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
