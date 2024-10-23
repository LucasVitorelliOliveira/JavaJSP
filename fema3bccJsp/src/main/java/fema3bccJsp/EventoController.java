package fema3bccJsp;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uteis.GerenciadorConexao;

public class EventoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try (Connection cnn = GerenciadorConexao.getConnection()){
			String operacao = request.getParameter("operacao");
			if (operacao.equals("Cadastrar")) {
				cadastrar(request);
				redirecionar(request, response, "menu.jsp");
			} else if (operacao.equals("Cadastrar Evento")){
				redirecionar(request, response, "cadastrarEvento.jsp");
			} else if (operacao.equals("Consultar Evento")){
				List<Evento> eventos = new EventoDao(cnn).getEventos();
				request.setAttribute("eventos", eventos);
				redirecionar(request, response, "consultarEvento.jsp");
			} else if (operacao.equals("Excluir")){
				Integer id = Integer.parseInt(request.getParameter("codigoEvento"));
				new EventoDao(cnn).excluir(id);
				redirecionar(request, response, "menu.jsp");
			}
			cnn.commit();
			cnn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void redirecionar(HttpServletRequest request, HttpServletResponse response, String nomePagina) 
			throws ServletException, IOException {
				RequestDispatcher rd = request.getRequestDispatcher(nomePagina);
				rd.forward(request, response);
			}
	
	private void cadastrar(HttpServletRequest req) {
		String codigo = req.getParameter("codigoEvento");
		String nome = req.getParameter("nomeEvento");
		String tipo = req.getParameter("tipoEvento");
		
		Evento evento = new Evento();
		evento.setCodigo(Integer.parseInt(codigo));
		evento.setNome(nome);
		evento.setTipo(tipo);
		try (Connection cnn = GerenciadorConexao.getConnection()){
			EventoDao dao = new EventoDao(cnn);
			dao.add(evento);
			cnn.commit();
			cnn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
