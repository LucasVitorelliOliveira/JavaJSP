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

public class ParticipanteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try (Connection cnn = GerenciadorConexao.getConnection()){
			String operacao = request.getParameter("operacao");
			if (operacao.equals("Cadastrar")) {
				cadastrar(request);
				redirecionar(request, response, "menu.jsp");
			} else if (operacao.equals("Cadastrar Participante")){
				redirecionar(request, response, "cadastrarParticipante.jsp");
			} else if (operacao.equals("Consultar Participante")){
				List<Participante> participantes = new ParticipanteDao(cnn).getParticipantes();
				request.setAttribute("participantes", participantes);
				redirecionar(request, response, "consultarParticipante.jsp");
			} else if (operacao.equals("Excluir")){
				Integer id = Integer.parseInt(request.getParameter("codigoParticipante"));
				new ParticipanteDao(cnn).excluir(id);
				redirecionar(request, response, "menu.jsp");
			}
			cnn.commit();
			cnn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Deu ruim");
		}
	}
	
	private void redirecionar(HttpServletRequest request, HttpServletResponse response, String nomePagina) 
			throws ServletException, IOException {
				RequestDispatcher rd = request.getRequestDispatcher(nomePagina);
				rd.forward(request, response);
			}
	
	private void cadastrar(HttpServletRequest req) {
		String codigo = req.getParameter("codigoParticipante");
		String nome = req.getParameter("nomeParticipante");
		String idade = req.getParameter("idadeParticipante");
		
		Participante participante = new Participante();
		participante.setCodigo(Integer.parseInt(codigo));
		participante.setNome(nome);
		participante.setIdade(Integer.parseInt(idade));
		try (Connection cnn = GerenciadorConexao.getConnection()){
			ParticipanteDao dao = new ParticipanteDao(cnn);
			dao.add(participante);
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
