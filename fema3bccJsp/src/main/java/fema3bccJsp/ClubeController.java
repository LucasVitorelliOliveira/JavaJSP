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

public class ClubeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try (Connection cnn = GerenciadorConexao.getConnection()){
			String operacao = request.getParameter("operacao");
			if (operacao.equals("Cadastrar")) {
				cadastrar(request);
				redirecionar(request, response, "menu.jsp");
			} else if (operacao.equals("Cadastrar Clube")){
				redirecionar(request, response, "cadastrarClube.jsp");
			} else if (operacao.equals("Consultar Clube")){
				List<Clube> clubes = new ClubeDao(cnn).getClubes();
				request.setAttribute("clubes", clubes);
				redirecionar(request, response, "consultarClube.jsp");
			} else if (operacao.equals("Excluir")){
				Integer id = Integer.parseInt(request.getParameter("codigoClube"));
				new ClubeDao(cnn).excluir(id);
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
		String codigo = req.getParameter("codigoClube");
		String nome = req.getParameter("nomeClube");
		String endereco = req.getParameter("enderecoClube");
		
		Clube clube = new Clube();
		clube.setCodigo(Integer.parseInt(codigo));
		clube.setNome(nome);
		clube.setEndereco(endereco);
		try (Connection cnn = GerenciadorConexao.getConnection()){
			ClubeDao dao = new ClubeDao(cnn);
			dao.add(clube);
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
