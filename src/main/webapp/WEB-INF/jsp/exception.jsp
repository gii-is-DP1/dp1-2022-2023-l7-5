<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/pets.png" var="petsImage"/>
    <img src="https://media.tenor.com/5eqkf-c99OEAAAAi/beeings-beeing.gif"/>

    <h2>Something happened...</h2>

    <p>${exception.message}</p>

</petclinic:layout>
