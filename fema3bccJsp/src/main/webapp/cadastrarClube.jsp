<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Clube</title>
</head>
<body>
	Criar Novo Clube
	<form action="/fema3bccJsp/ClubeController">
		<label>Codigo</label>
		<input type="text" name="codigoClube">
		<br>

		<label>Nome</label>
		<input type="text" name="nomeClube">
		<br>

		<label>Endereço</label>
		<input type="text" name="enderecoClube">
		<br>

		<input type="submit" name="operacao" value="Cadastrar">
	</form>
</body>
</html>