<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Evento</title>
</head>
<body>
	Cadastrar Novo Evento
	<form action="/fema3bccJsp/EventoController">
		<labe>Codigo</label>
		<input type="text" name="codigoEvento">
		<br>

		<label>Nome</label>
		<input type="text" name="nomeEvento">
		<br>

		<label>Tipo</label>
		<input type="text" name="tipoEvento">
		<br>
		<input type="submit" name="operacao" value="Cadastrar">

	</form>
</body>
</html>