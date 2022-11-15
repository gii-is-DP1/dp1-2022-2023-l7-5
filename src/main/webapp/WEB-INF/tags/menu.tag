<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>
	
<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a style ="font-size: 46px; color: #FCDC04;" href="<spring:url value="/" htmlEscape="true" />"> Honey!</a>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="hasAnyAuthority('admin')">
    				<petclinic:menuItem active="${name eq 'adminMenu'}" url="/"
					title="Admin Menu" dropdown="${true}">										
						<ul class="dropdown-menu">
							<li>
								<a href="<c:url value="/users" />">Player listing</a>		
							</li>
							<li class="divider"></li>
							<li>
								<a href="<c:url value="/achievements/AchievementsListing" />">Achievements listing</a>		
							</li>
						</ul>					
				</petclinic:menuItem>		
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li><a href="<c:url value="/logout" />">Logout</a></li>
					<li><a> <span class="glyphicon glyphicon-user"></span> 
							<strong><sec:authentication property="name" /></strong>
					</a>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>
