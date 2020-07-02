<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html> 
<!-- ESTO ESTA ACTUALIZADO -->
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>MovieFinder</title>
        <link rel="icon" type="image/png" href="/images/Logo.png" />
        <link rel="stylesheet" href="css/paginaPrincipal.css" type="text/css" />
    </head>

    <body>
    <div class="titulo">
       <img src="./images/cabecera.jpg" class="cabecera"> 
        </div>
    	
        <a>${requestScope.message}</a>
        
        
        <!-- Auth Calendar -->
    	<div class ="authA">
		<span class="LoginC">
        <a href="/AuthController/GoogleCalendar" id="loginCalendar">
        Login Calendar</a>
        </span>
        
        <!-- Autentificación TMDB -->
        <form action="/paginaPrincipalController" method='POST'>
        	<input type="hidden" name="tmdbLogin" class ="tmdbLoginA" value="true" />
        	<button type="submit" class ="tmdbLoginA">Login Tmdb</button>
        
        </form>
        <%if(request.getAttribute("sessionID")!=null){ %>
            Logeado TMDB <!-- Ale pon tick mejor --> <br>
        <%} %>
        <br>
        
        <!-- Calendar -->
    	<form action='/paginaPrincipalController' method='post'>
    		<label >Para poder ver e insertar tu calendario, inserta tu email,habiéndose "logueado" previamente:</label><br>
    		<%if(request.getAttribute("sessionID")!=null){ %>
    		<input type="hidden" name="SessionID" id="SessionID" value="${requestScope.sessionID}"/>
    		<%} %>
			<input type='email' placeholder='Escribe tu email' pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}" class='busqueda' name='email' required/>
			<input type='submit' class='enviar' name='searchBtn' title='search' value='Enviar'>
		</form>
		</div>
        <h2 id="descubrirTitulo"> DESCUBRIR PELICULAS </h2>
        <!-- Formulario DESCUBRIR películas -->
        <div id="descubrirPeliculas">
        	<form action='/searchMoviesController' method='post'>
        		
        		<input type="hidden" name="sessionID" value="${requestScope.sessionID}">
        		<div class = "formulario">
        		<label>Actor o Director: </label>
        		<input type="text" name="person" placeholder="Director,Actor,etc" ><br>
        		</div>
        		<div class = "formulario">
        		<label>Género: </label>
        		<select name="genre">
        			<option value="all">Todos</option>
        			<option value="28">Acción</option>
        			<option value="16">Animación</option>
        			<option value="35">Comedia</option>
        			<option value="27">Horror</option>
        			<option value="10749">Romance</option>
        			<option value="878">Ciencia Ficción</option>
        			<option value="10752">Bélica</option>
        		</select><br>
        		</div>
        		<div class = "formulario">
        		<label>Año: </label>
        		<input type="number" name="year" placeholder="1985"><br>
        		</div>
        		<div class = "formulario">
        		<label>Puntuación media: </label>
        		<input type="number" name="voteAverage" placeholder="8.5"><br>
        		</div>
        		<div class = "formulario">
        		<label>Idioma: </label>
        		<select name="languages">
        			<option value="es-ES">Castellano</option>
        			<option value="en-US">Inglés</option>
        			<option value="fr-FR">Francés</option>
        		</select><br>
        		</div>
        		<div class = "formulario">
        		<label>Ordenar por: </label>
        		<select name="sortBy">
        			<option value="noOrder">Sin orden</option>
        			<option value="popularity.desc">Popularidad</option>
        			<option value="vote_average.desc">Puntuación</option>
        		</select><br>
        		</div>
        		<div class = "formulario">
        		<input type="radio" name="estreno" value="noestreno" checked class="est">Estrenadas<br>
  				<input type="radio" name="estreno" value="estreno" class="est"> Estreno<br>
        		</div>
        		<%if(request.getAttribute("email")!=null && !"".equals(request.getAttribute("email"))){ %>
        		<input type='hidden' placeholder='Escribe tu email' pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}"
        		 class='busqueda' name='email' value="${requestScope.email}" required/>	
        		<%} %>
        		
        		<input type="submit" value="Descubrir">
        	</form>
        	
        	
        </div>
        
        <!-- Formulario BUSCAR películas -->
       
        <h2 id="buscarTitulo"> BUSCAR PELICULA POR TITULO </h2>
        <div id="descubrirPeliculas">
        	<form action='/searchMoviesController' method='post'>
        	
        		<input type="hidden" name="sessionID" value="${requestScope.sessionID}">
        		<%if(request.getAttribute("email")!=null && !"".equals(request.getAttribute("email"))){ %>
        		<input type='hidden' placeholder='Escribe tu email' pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}"
        		 class='busqueda' name='email' value="${requestScope.email}" required/>	
        		<%} %>
   				<input type='text' placeholder='Busca una serie o película' class='busqueda' name='query' required/>
				<input type='submit' class='enviar' name='searchBtn' title='search' value='Buscar'>
			</form>
		</div>
			
	<!--  	</div> -->
        
        <div class="calendar">
        <%if(request.getAttribute("calendar")!=null && !"".equals(request.getAttribute("calendar"))){ %>
            <iframe src="https://calendar.google.com/calendar/embed?src=${requestScope.calendar.id}&ctz=${requestScope.calendar.timeZone}" style="border: 0" width="500" height="300" frameborder="0" scrolling="no"></iframe>
        <%} %>
        </div>
        <!-- Watchlist -->
        <%if(request.getAttribute("watchlist")!=null && !"".equals(request.getAttribute("watchlist"))){ %>
        	<div class="watchlist">
	        	<c:forEach items="${requestScope.watchlist.results}" var="movie">
	              
			        <div class="peliculasWatchlist"> 
			        
			        	<form action='/resultadoEspecificoController' method='post'>
			        		<input type='hidden' name='sessionID' value='${requestScope.sessionID}' required>
			        		<input type="hidden" value="${movie.id}" name='movieID' required/>
							<input type="hidden" value="${movie.title}" name='title' required/>
							<input type='hidden' name='estreno' value='estreno' required>
							<%if(request.getAttribute("email")!=null && !"".equals(request.getAttribute("email"))){ %>
							<input type='hidden' placeholder='Escribe tu email' pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}"
        		 			class='busqueda' name='email' value="${requestScope.email}" required/>	
        		 			<%} %>
        		 			<div class="pelii">
							<input type="image" src="http://image.tmdb.org/t/p/w92/${movie.posterPath}" alt="Submit"> <!-- Cambiar tamaño: "w92","w154","w185","w342","w500","w780" -->
						
						<p>${movie.title}</p>
						</div>
						 </form>
			       	</div>		
	            
	    		</c:forEach> 
    		</div>
        <%} %>
	<footer>
	<p>&copy;MovieFinder 2019 </p>
	<p>	Autores: Miguel Ángel Moreno Olmo, Alejandro Fuentes Gomez, Jose Manuel Cobo Guerrero, Alvaro Aguilar Alhama</p>
	</footer>
        
    </body>
</html>