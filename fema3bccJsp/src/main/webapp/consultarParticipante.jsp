<%@page import="fema3bccJsp.Participante"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Lista de Participantes
	<%
		List<Participante> participantes = (List<Participante>) request.getAttribute("participantes");
		if (participantes != null && participantes.size() > 0) {
			for (Participante participante: participantes) {
			%>
			<table>
			<tr>
				<td><%= participante.getCodigo() %></td>
				<td><%= participante.getNome() %></td>
				<td><%= participante.getIdade() %></td>
				<td>
					<form action="/fema3bccJsp/ParticipanteController">
						<input type="submit" value="Excluir" onclick="return confirm('confirma a exclusão?')">
						<input type="hidden" name="operacao" value="Excluir">
						<input type="hidden" name="codigoParticipante"
						value="<%= participante.getCodigo() %>">
					
					</form>
				</td>
			</tr>
			</table>
		<%}
		} else { %>
		<h2>Não há participantes cadastrados</h2>
	<%}%>
</body>
</html>