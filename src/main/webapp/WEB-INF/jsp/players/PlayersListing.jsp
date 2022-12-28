<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">
    <h2>Players</h2>

    <table id="playersTable" class="table table-striped">
        <thead>
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Password</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${players}" var="player">
            <tr>
                <td>
                    <c:out value="${player.username}"/>
                </td>
                <td>
                    <c:out value="${player.email}"/>
                </td>
                <td>
                    <c:out value="${player.password}"/>
                </td>
                <sec:authorize access="hasAnyAuthority('admin')">
					<td>
						<a href="<c:url value="/users/${player.username}/delete/" />"><span class="glyphicon glyphicon glyphicon-trash" aria-hidden="true"></span></a>
					</td>
				</sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div style="float:right">
	    <c:if test="${previous}">
	  		<a class="btn btn-default" href="/users/page/${page - 1}">Previous Page</a>
	  	</c:if>
	    <c:if test="${next}">
	  		<a class="btn btn-default" href="/users/page/${page + 1}">Next Page</a>
	  	</c:if>
    </div>
    <a class="btn btn-default" href="/users/new">Create new player</a>
</petclinic:layout >
