<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="achievements">
	<h2>
		<c:if test="${achievement['new']}">New </c:if>
		Achievement
	</h2>
	<table class="table table-stripped">
		<thead>
			<tr>
				<th>Name</th>
				<th>Badge Image</th>
				<th>Blocked Image</th>
				<th>Threshold</th>
				<th>Description</th>
			</tr>
		</thead>
		<tr>
			<td><c:out value="${achievement.name}"></c:out></td>
			<td><c:if test="${achievement.badgeImage == ''}"></c:if>
			<c:if test="${achievement.badgeImage != ''}">
				<spring:url value="${achievement.badgeImage}" var="badgeImage"></spring:url>
				<img class="img-responsive" src="${badgeImage}" width="80"
					height="80"></c:if></td>
			<td><c:if test="${achievement.blockedImage == ''}"></c:if>
			<c:if test="${achievement.blockedImage != ''}">
				<spring:url value="${achievement.blockedImage}" var="blockedImage"></spring:url>
				<img class="img-responsive" src="${blockedImage}" width="80"
					height="80"></c:if></td>
			<td><c:out value="${achievement.threshold}"></c:out></td>
			<td><c:out value="${achievement.description}"></c:out></td>
		</tr>
	</table>
	<form:form modelAttribute="achievement" class="form-horizontal">
		<input type="hidden" name="id" value="${achievement.id}" />
		<div class="form-group has-feedback">
			<h3>Name</h3>
			<petclinic:inputField label="Name" name="name" />
			<h3>Badge Image</h3>
			<petclinic:inputField label="Badge Image" name="badgeImage" />
			<h3>Blocked Image</h3>
			<petclinic:inputField label="Blocked Image" name="blockedImage" />
			<h3>Threshold</h3>
			<petclinic:inputField label="Threshold" name="threshold" />
			<h3>Description</h3>
			<petclinic:inputField label="Description" name="description" />
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${achievement['new']}">
						<button class="btn btn-default" type="submit">Add
							achievement</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" type="submit">Update
							achievement</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
	<c:if test="${!achievement['new']}">
	</c:if>
</petclinic:layout>
