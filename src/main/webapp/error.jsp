<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/error.css" type="text/css" />
<title>Un error se ha producido</title>
</head>
<body>
	<form action="/paginaPrincipalController">
    	<input type='hidden' name='SessionID' value='${requestScope.sessionID}' required>
    	<input type='hidden' pattern="[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{1,5}"
		 class='busqueda' name='email' value="${requestScope.email}" required/>
		<input type='submit' class='enviar' name='searchBtn' title='search' value='Volver Inicio'>
    </form>

	<h1>ERROR</h1>
	<h2>Algo no ha salido como esperabas</h2>
	<a>"No me mires asi, colega. Puedo sentir tu mirada."</a>
	
	<footer>
		<br>
		&copy;MovieFinder 2019 
		<br>
		AutorList: Miguel Ángel Moreno Olmo, Alejandro Fuentes Gomez, Jose Manuel Cobo Guerrero, Alvaro Aguilar Alhama
	</footer>
</body>
</html>