<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="tiles">
    <h2>Tiles</h2>

    <table id="tilesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Starting Side</th>
            <th>Filled Side</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tiles}" var="tile">
            <tr>
                <td>
                    <c:out value="${tile.id}"/>
                </td>
                <td>
                    <c:out value="${tile.startingSide}"/>
                </td>
                <td>
                    <c:out value="${tile.filledSide}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
