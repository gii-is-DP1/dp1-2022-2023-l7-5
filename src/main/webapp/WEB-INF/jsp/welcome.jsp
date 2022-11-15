<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  


<sec:authorize access="!isAuthenticated()">
<petclinic:layout pageName="home">
    <h1 
    	style ="
    		text-align: center;
    		font-size: 64px">
    	Welcome to Honey!
    </h1>
    
    <div style ="text-align: center">
    	<img src="https://media.tenor.com/ySxJTHN0H9UAAAAi/the-blobs-live-on-bee.gif"/>
    </div>
	<div style ="text-align: center">
	    <a href="/login" 
	    	style = "
	    		box-shadow:inset 0px 1px 0px 0px #fff6af;
	    		text-align: center;
				background-color:#ffde04;
				border-radius:6px;
				border:1px solid #ffaa22;
				display:inline-block;
				cursor:pointer;
				color:#333333;
				font-size:32px;
				font-weight:bold;
				padding:6px 24px;
				text-decoration:none;
				text-shadow:0px 1px 0px #ffee66;">
			LogIn
		</a>
		<a href="/users/new" 
	    	style = "
	    		box-shadow:inset 0px 1px 0px 0px #fff6af;
	    		text-align: center;
				background-color:#ffde04;
				border-radius:6px;
				border:1px solid #ffaa22;
				display:inline-block;
				cursor:pointer;
				color:#333333;
				font-size:32px;
				font-weight:bold;
				padding:6px 24px;
				text-decoration:none;
				text-shadow:0px 1px 0px #ffee66;">
			Register
		</a>
	 </div>
</petclinic:layout>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<petclinic:layout pageName="home">
	<h1 
    	style ="
    		text-align: center;
    		font-size: 64px">
    	Welcome to Honey!
    </h1>
    
    <div style ="text-align: center">
    	<img src="https://media.tenor.com/ySxJTHN0H9UAAAAi/the-blobs-live-on-bee.gif"/>
    </div>
    
    <div style ="text-align: center">
    	<a href="/${principal.getUser}" 
	    	style = "
	    		box-shadow:inset 0px 1px 0px 0px #fff6af;
	    		text-align: center;
				background-color:#ffde04;
				border-radius:6px;
				border:1px solid #ffaa22;
				display:inline-block;
				cursor:pointer;
				color:#333333;
				font-size:32px;
				font-weight:bold;
				padding:6px 24px;
				text-decoration:none;
				text-shadow:0px 1px 0px #ffee66;">
			Play
		</a>
    	<a href="https://docdro.id/9EGL9gM" target="_blank"
	    	style = "
	    		box-shadow:inset 0px 1px 0px 0px #fff6af;
	    		text-align: center;
				background-color:#ffde04;
				border-radius:6px;
				border:1px solid #ffaa22;
				display:inline-block;
				cursor:pointer;
				color:#333333;
				font-size:32px;
				font-weight:bold;
				padding:6px 24px;
				text-decoration:none;
				text-shadow:0px 1px 0px #ffee66;">
			See Rules
		</a>
    </div>
    <div style ="text-align: center">
    	<a href="/player/${username}/edit"
	    	style = "
	    		box-shadow:inset 0px 1px 0px 0px #fff6af;
	    		text-align: center;
				background-color:#ffde04;
				border-radius:6px;
				border:1px solid #ffaa22;
				display:inline-block;
				cursor:pointer;
				color:#333333;
				font-size:32px;
				font-weight:bold;
				padding:6px 24px;
				text-decoration:none;
				text-shadow:0px 1px 0px #ffee66;">
			See my profile
		</a>
    </div>
    
    </petclinic:layout>				
</sec:authorize>

