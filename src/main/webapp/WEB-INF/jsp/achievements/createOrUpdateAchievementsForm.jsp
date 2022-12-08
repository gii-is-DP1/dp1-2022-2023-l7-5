<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="achievements">
    <jsp:body>
        <h2>
            <c:if test="${achievement['new']}">New </c:if> Achievement
        </h2>
        <table class="table table-stripped">
        	<thead><tr>
        		<th>Name</th>
        		<th>Badge Image</th>
        		<th>Threshold</th>
        		<th>Description</th>
        	</tr></thead>
        	<tr>
        		<td><c:out value="${achievement.name}"></c:out></td>
        		<td><c:out value="${achievement.badgeImage}"></c:out></td>
        		<td><c:out value="${achievement.threshold}"></c:out></td>
        		<td><c:out value="${achievement.description}"></c:out></td>
        	</tr>
        </table>
        <form:form modelAttribute="achievement" class="form-horizontal">
            <input type="hidden" name="id" value="${achievement.id}"/>
            <div class="form-group has-feedback">
            <h3>Badge Image</h3>
                <c:if test="${achievement.badgeImage == ''}">none</c:if>
                <c:if test="${achievement.badgeImage != ''}">
                	<spring:url value="${achievement.badgeImage~}" var="badgeImage"></spring:url>
                	<img class="img-responsive" src="${badgeImage}" width="80" height="80">
                </c:if>
          	<h3>Threshold</h3>
                <petclinic:inputField label="Threshold" name="threshold"/>
            <h3>Description</h3>
            	<petclinic:inputField label="Description" name="description"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${achievement['new']}">
                            <button class="btn btn-default" type="submit">Add achievement</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update achievement</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
        <c:if test="${!achievement['new']}">
        </c:if>
    </jsp:body>
</petclinic:layout>
