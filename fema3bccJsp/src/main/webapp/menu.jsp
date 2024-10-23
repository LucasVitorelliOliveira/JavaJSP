<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu</title>
</head>
<body>
	<form action="/fema3bccJsp/ClubeController">
		<input type="submit" name="operacao" value="Cadastrar Clube">
	</form>
	<form action="/fema3bccJsp/ClubeController">
		<input type="submit" name="operacao" value="Consultar Clube">
	</form>
	<form action="/fema3bccJsp/EventoController">
		<input type="submit" name="operacao" value="Cadastrar Evento">
	</form>
	<form action="/fema3bccJsp/EventoController">
		<input type="submit" name="operacao" value="Consultar Evento">
	</form>
	<form action="/fema3bccJsp/ParticipanteController">
		<input type="submit" name="operacao" value="Cadastrar Participante">
	</form>
	<form action="/fema3bccJsp/ParticipanteController">
		<input type="submit" name="operacao" value="Consultar Participante">
	</form>


</body>
</html>