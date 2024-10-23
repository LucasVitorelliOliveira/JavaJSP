<%@page import="fema3bccJsp.Evento"%>
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
	Lista de Eventos
	<%
List<Evento> eventos = (List<Evento>) request.getAttribute("eventos");
		if (eventos != null && eventos.size() > 0) {
	for (Evento evento: eventos) {
%>
			<table>
			<tr>
				<td><%= evento.getCodigo() %></td>
				<td><%= evento.getNome() %></td>
				<td><%= evento.getTipo() %></td>
				<td>
					<form action="/fema3bccJsp/EventoController">
						<input type="submit" value="Excluir" onclick="return confirm('confirma a exclusão?')">
						<input type="hidden" name="operacao" value="Excluir">
						<input type="hidden" name="codigoEvento"
						value="<%= evento.getCodigo() %>">
					
					</form>
				</td>
			</tr>
			</table>
		<%}
		} else { %>
		<h2>Não há eventos cadastrados</h2>
	<%}%>
</body>
</html>