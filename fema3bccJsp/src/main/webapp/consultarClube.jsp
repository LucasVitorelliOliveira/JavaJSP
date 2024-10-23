<%@page import="fema3bccJsp.Clube"%>
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
	Lista de Clubes
	<%
		List<Clube> clubes = (List<Clube>) request.getAttribute("clubes");
		if (clubes != null && clubes.size() > 0) {
	for (Clube clube: clubes) {
%>
			<table>
			<tr>
				<td><%= clube.getCodigo() %></td>
				<td><%= clube.getNome() %></td>
				<td><%= clube.getEndereco() %></td>
				<td>
					<form action="/fema3bccJsp/ClubeController">
						<input type="submit" value="Excluir" onclick="return confirm('confirma a exclusão?')">
						<input type="hidden" name="operacao" value="Excluir">
						<input type="hidden" name="codigoClube"
						value="<%= clube.getCodigo() %>">
					</form>
				</td>
			</tr>
			</table>
		<%}
		} else { %>
		<h2>Não há clubes cadastrados</h2>
	<%}%>
</body>
</html>