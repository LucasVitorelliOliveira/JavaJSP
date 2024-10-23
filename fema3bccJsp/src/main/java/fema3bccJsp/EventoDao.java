package fema3bccJsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uteis.GerenciadorConexao;


public class EventoDao {
	private Connection cnn = null;

	public EventoDao(Connection cnn) {
		this.cnn = cnn;
	}
	
	private static List<Evento> eventos = new ArrayList<Evento>();
	
	public void add(Evento evento) {
		try {
			insert(evento);
			eventos.add(evento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void insert(Evento evento) throws SQLException {
		String sql = "insert into evento values (?,?,?)";
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setInt(1, evento.getCodigo());
		ps.setString(2, evento.getNome());
		ps.setString(3, evento.getTipo());
		ps.execute();
		ps.close();
	}
	
	public List<Evento> getEventos() {
		try {
			eventos = recuperarEventos();
			return eventos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Evento> recuperarEventos() throws SQLException {
		ArrayList<Evento> resultado = new ArrayList<Evento>();
		String sql = "select * from evento order by codigo";
		PreparedStatement ps = cnn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Evento evento = new Evento();
			evento.setCodigo(rs.getInt("codigo"));
			evento.setNome(rs.getString("nome"));
			evento.setTipo(rs.getString("tipo"));
			resultado.add(evento);
		}
		rs.close();
		ps.close();
		return resultado;
	}
	
	public void excluir(Integer id) {
		Evento eventoCadastrado = null;
		for (Evento evento: eventos) {
			if (evento.getCodigo().equals(id)) {
				eventoCadastrado = evento;
				break;
			}
		}
		if (eventoCadastrado != null) {
			try {
				delete(eventoCadastrado);
				eventos.remove(eventoCadastrado);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void delete(Evento evento) throws SQLException {
		String sql = "delete from evento where codigo = ?";
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setInt(1, evento.getCodigo());
		ps.execute();
		ps.close();
	}
}
