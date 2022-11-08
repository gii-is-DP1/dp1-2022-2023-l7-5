<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="cells">
	<jsp:body>
		<h2>
			<c:if test="${cell['new']}">New</c:if>Cell
		</h2>
		<form:form modelAttribute="cell"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${cell.id}"/>
            <div class="form-group has-feedback">
           		<h3>Empty Cell</h3>
            		<form:button label= "Empty Cell" path="emptyCell" selected="emptyCell">Empty Cell</form:button>
            	<h3>Blocked Cell</h3>
            		<form:button label= "Blocked Cell" path="blockedCell" selected="blockedCell">Blocked Cell</form:button>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${cell['new']}">
                            <button class="btn btn-default" type="submit">Add Cell</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update Cell</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
        <c:if test="${!cell['new']}">
        </c:if>
    </jsp:body>
</petclinic:layout>