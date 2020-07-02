<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html> 

<html>

<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="/css/resultadoEspecifico.css">
	<title>MovieFinder</title>
	<link rel="icon" type="image/png" href="/images/Logo.png" />
	
</head>

<body>

<div class="titulo">
       <img src="./images/cabecera.jpg" class="cabecera"> 
</div>
	
	<c:set var = "movie" value = "${requestScope.movie}"/>
	
	<%if(request.getAttribute("estreno").equals("estreno")){ %>
		<c:set var = "fechaestreno" value = "${movie.releaseDate}"/>
		<c:set var = "msgestreno" value = "Estreno Cine: "/>
	<% } %>
	
	<div id="tmdbInfo">
	
		<img id="imagen" class="imagen" src="http://image.tmdb.org/t/p/w342/${movie.posterPath}" alt="Poster"><br><!-- Cambiar tamaño: "w92","w154","w185","w342","w500","w780" -->
		<a class="titulo">${movie.title}</a><br>
		<a class="puntuacion">Puntuación: ${movie.voteAverage}</a><br>
		<a class="fecha">Fecha de Estreno: ${movie.releaseDate}</a><br>
		<a class="generos">Géneros: <c:forEach items="${movie.genres}" var="genre">${genre.name}, </c:forEach></a>
		<p class="overview">${movie.overview}</p>
		<% if(request.getAttribute("message")!=null){ %>
			<a class="tupuntuacion"> ${requestScope.message}</a>
		<%}%>
		
		<div id="interactuar" >
			<form id="addWatchlist" action='/resultadoEspecificoController' method='post'>
				<input type='hidden' name='addwl' value='true' required>
		 		<input type='hidden' name='sessionID' value='${requestScope.sessionID}' required>
		 		<input type='hidden' name='estreno' value='${requestScope.estreno}' required>
		 		<input type='hidden' name='movieID' value='${requestScope.movie.id}' required>
		 		<input type='hidden' name='title' value='${requestScope.title}' required>
		 		<%if(request.getAttribute("email")!=null){ %>
        		<input type='hidden' placeholder='Escribe tu email' pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}"
        		 class='busqueda' name='email' value="${requestScope.email}" required/>	
        		<%} %>
		 		<input type='submit' class='enviar' name='addBtn' title='Añadir Watchlist' value='Añadir Watchlist'>
 			</form>
 			<a class="watchlist">${status.watchlist}</a>
 
			<form class="puntuar" id="puntuar" action='/resultadoEspecificoController' method='post'>
		 		<input type='hidden' name='sessionID' value='${requestScope.sessionID}' required>
		 		<input type='hidden' name='movieID' value='${requestScope.movie.id}' required>
		 		<input type='hidden' name='title' value='${requestScope.title}' required>
		 		<input type='hidden' name='estreno' value='${requestScope.estreno}' required>
		 		<input type='number' name='puntuacion' min='0.5' max='10' step='0.1' required>
		 		<%if(request.getAttribute("email")!=null){ %>
        		<input type='hidden' placeholder='Escribe tu email' pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}"
        		 class='busqueda' name='email' value="${requestScope.email}" required/>	
        		<%} %>
		 		<input type='submit' class='enviar' name='puntuarBtn' title='puntuar' value='Puntuar'>
 			</form>
			
			
			<form class="calendarcontrol" action='/calendarController' method='post'>
		 		<input type='hidden' name='sessionID' value='${requestScope.sessionID}' required>
				<input type='date' name='fecha' required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" value="${fechaestreno}">
				<input type='text' name='summary' placeholder='Escribe el titulo del evento' required>
				<input type='hidden' name='description' value='${requestScope.movie.title}'>
				<input type='hidden' name='title' value='${requestScope.title}'>
				<input type='hidden' name='movieID' value='${requestScope.movie.id}' required>
				<input type='hidden' name='estreno' value='${requestScope.estreno}' required>
				<%if(request.getAttribute("email")!=null){ %>
		        <input type='hidden' placeholder='Escribe tu email' pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}"
		         class='busqueda' name='email' value="${requestScope.email}" required/>	
		        <%} %>
				<input type='submit' class='enviar' name='searchBtn' title='search' value='Enviar'>
 			</form>
		
			<p class="dondeverpeli">Dónde puedo ver la película: <a href="https://www.justwatch.com/es/buscar?q=${requestScope.movie.title}" target="_blank">Click aquí</a> </p>
		</div>
		
	</div>

    <br>
    
    
     	<div class="trailersound">
    <!-- Descomentar las lineas necesarias cuando hagan falta para probar y después volver a comentar
    	 Está primero el video de youtube, después el div es AliExpress y lo último es la playlist de youtube
    	 Descomentar sólo la parte que vayais a utilizar y al acabar volver a comentar EXACTAMENTE igual -->
   
    <h2 class="h2">Búsqueda de trailler en youtube</h2>
    	<iframe class="trailer" width="560" height="315" src="https://www.youtube.com/embed/${requestScope.videos.id.videoId}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    
    <h3 class="h3">Búsqueda de soundtracks en youtube</h3>
        <iframe class="sound" width="560" height="315" src="https://www.youtube.com/embed/videoseries?list=${requestScope.playlists.id.playlistId}&autoplay=1" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
 		
		</div>
 		
    	
    	<h2>Búsqueda de merchandising en aliexpress</h2>
        	<c:forEach items="${requestScope.products.result.products}" var="product">
        	<div class="mercha">
        		<a class="tituloAli" href="${product.productUrl}"><b>${product.productTitle}</a>
        		<br>
        		<img src="${product.imageUrl}" width="200" height="200"/>
        		</div>
        	</c:forEach>

    <a href="javascript:history.back()"> Volver Atrás</a>
    <form action="/paginaPrincipalController">
    	<input type='hidden' name='SessionID' value='${requestScope.sessionID}' required>
    	<input type='hidden' placeholder='Escribe tu email' pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}"
		 class='busqueda' name='email' value="${requestScope.email}" required/>
		<input type='submit' class='enviar' name='searchBtn' title='search' value='Volver Inicio'>
    </form>

<footer class="footer">
		<br>
		&copy;MovieFinder 2019 
		<br>
		AutorList: Miguel Ángel Moreno Olmo, Alejandro Fuentes Gomez, Jose Manuel Cobo Guerrero, Alvaro Aguilar Alhama
	</footer>
</body>

</html>