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
        <form:form modelAttribute="tile"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${tile.id}"/>
            <div class="form-group has-feedback">
            <h3>Starting Side</h3>
                <form:select label="Starting Side" path="startingSide" selected="startingSide">
                	<form:option value="red"></form:option>
                	<form:option value="blue"></form:option>
                	<form:option value="purple"></form:option>
                	<form:option value="green"></form:option>
                	<form:option value="yellow"></form:option>
                	<form:option value="orange"></form:option>
                </form:select>
                <h3>Filled Side</h3>
                <form:select label="Filled Side" path="filledSide" selected="filledSide">
                	<form:option value="red"></form:option>
                	<form:option value="blue"></form:option>
                	<form:option value="purple"></form:option>
                	<form:option value="green"></form:option>
                	<form:option value="yellow"></form:option>
                	<form:option value="orange"></form:option>
                </form:select>
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
