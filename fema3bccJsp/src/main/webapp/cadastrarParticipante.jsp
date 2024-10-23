<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Participante</title>
</head>
<body>
	Cadastrar Novo Participante
	<form action="/fema3bccJsp/ParticipanteController">
		<label>Codigo</label>
		<input type="text" name="codigoParticipante">
		<br>

		<label>Nome</label>
		<input type="text" name="nomeParticipante">
		<br>

		<label>Idade</label>
		<input type="text" name="idadeParticipante">
		<br>

		<input type="submit" name="operacao" value="Cadastrar">

	</form>
</body>
</html>