<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html> 

<html>

<head>
	<meta charset="ISO-8859-1">
	<title>MovieFinder</title>
	<link rel="icon" type="image/png" href="/images/Logo.png" />
	<link rel="stylesheet" href="/css/resultadoGenerales.css" type="text/css" />
</head>

<body>
<div class="titulo">
       <img src="./images/cabecera.jpg" class="cabecera"> 
        </div>

	<p>${requestScope.message}</p>
	
	<c:forEach items="${requestScope.movies.results}" var="movie">
        
        <div id="pelis" class="pelis		">
        	<form action='/resultadoEspecificoController' method='post'>
        		<input type='hidden' name='sessionID' value='${requestScope.sessionID}'>
        		<input type="hidden" value="${movie.id}" name='movieID' required/>
				<input type="hidden" value="${movie.title}" name='title' required/>
				<input type="hidden" value="${requestScope.estreno}" name='estreno'/>
				<%if(request.getAttribute("email")!=null){ %>
        		<input type='hidden' placeholder='Escribe tu email' pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}"
        		 class='busqueda' name='email' value="${requestScope.email}" required/>	
        		<%} %>
				<input id="imagen" class="imagen" type="image" src="http://image.tmdb.org/t/p/w185/${movie.posterPath}" alt="Submit"> <!-- Cambiar tamaño: "w92","w154","w185","w342","w500","w780" -->
			</form>
			<p class="nombre">${movie.title}</p>
			<p class="puntuacion">Puntuacion: ${movie.voteAverage}</p> 
			<p class="overview">${movie.overview}</p> 
        </div>
            
    </c:forEach>

	<a href="javascript:history.back()"> Volver Atrás</a>

<footer>
		<br>
		&copy;MovieFinder 2019 
		<br>
		AutorList: Miguel Ángel Moreno Olmo, Alejandro Fuentes Gomez, Jose Manuel Cobo Guerrero, Alvaro Aguilar Alhama
	</footer>
</body>
</html>