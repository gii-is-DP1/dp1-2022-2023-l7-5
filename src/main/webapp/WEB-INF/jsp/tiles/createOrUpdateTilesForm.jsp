<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="tiles">
    <jsp:body>
        <h2>
            <c:if test="${tile['new']}">New </c:if> Tile
        </h2>
        <table class="table table-stripped">
        	<thead><tr>
        		<th>Starting Side</th>
        		<th>Filled Side</th>
        	</tr></thead>
        	<tr>
        		<td><c:out value="${tile.startingSide}"></c:out></td>
        		<td><c:out value="${tile.filledSide}"></c:out></td>
        	</tr>
        </table>
        <form:form modelAttribute="tile"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${tile.id}"/>
            <div class="form-group has-feedback">
            <h3>Starting Side</h3>
                <c:if test="${tile.startingSide == ''}">none</c:if>
                <c:if test="${tile.startingSide != ''}">
                	<spring:url value="${tile.startingSide}" var="startingSide"></spring:url>
                	<img class="img-responsive" src="${startingSide}" width="80" height="80">
                </c:if>
            <h3>Filled Side</h3>
                <c:if test="${tile.filledSide == ''}">none</c:if>
                <c:if test="${tile.filledSide != ''}">
                	<spring:url value="${tile.filledSide}" var="filledSide"></spring:url>
                	<img class="img-responsive" src="${filledSide}" width="80" height="80">
                </c:if>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${tile['new']}">
                            <button class="btn btn-default" type="submit">Add tile</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update tile</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
        <c:if test="${!tile['new']}">
        </c:if>
    </jsp:body>
</petclinic:layout>
