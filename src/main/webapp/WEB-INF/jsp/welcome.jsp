<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    <div class="row">
    <h2> Project ${title}</h2>
    <p><h2> Group ${group}</h2></p>
    <p><ul>
    <c:forEach items="${persons}" var="person">
    	<li>${person.firstName} ${person.lastName}</li>
    </c:forEach>
    </ul></p>
    </div>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/ranagato.jpg" htmlEscape="true" var="ranagato"/>
            <img class="img-responsive" src="${ranagato}"/>
            <spring:url value="/resources/images/patito.jpg" htmlEscape="true" var="patito"/>
            <img class="img-responsive" src="${patito}"/>
            <spring:url value="/resources/images/conejito.jpg" htmlEscape="true" var="conejito"/>
            <img class="img-responsive" src="${conejito}"/>
        </div>
    </div>
</petclinic:layout>
