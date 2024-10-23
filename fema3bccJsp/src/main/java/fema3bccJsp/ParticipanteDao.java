package fema3bccJsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uteis.GerenciadorConexao;


public class ParticipanteDao {
	private Connection cnn = null;

	public ParticipanteDao(Connection cnn) {
		this.cnn = cnn;
	}
	
	private static List<Participante> participantes = new ArrayList<Participante>();
	
	public void add(Participante participante) {
		try {
			insert(participante);
			participantes.add(participante);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void insert(Participante participante) throws SQLException {
		String sql = "insert into participante values (?,?,?)";
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setInt(1, participante.getCodigo());
		ps.setString(2, participante.getNome());
		ps.setInt(3, participante.getIdade());
		ps.execute();
		ps.close();
	}
	
	public List<Participante> getParticipantes() {
		try {
			participantes = recuperarParticipantes();
			return participantes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Participante> recuperarParticipantes() throws SQLException {
		ArrayList<Participante> resultado = new ArrayList<Participante>();
		String sql = "select * from participante order by codigo";
		PreparedStatement ps = cnn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Participante participante = new Participante();
			participante.setCodigo(rs.getInt("codigo"));
			participante.setNome(rs.getString("nome"));
			participante.setIdade(rs.getInt("idade"));
			resultado.add(participante);
		}
		rs.close();
		ps.close();
		return resultado;
	}
	
	public void excluir(Integer id) {
		Participante participanteCadastrado = null;
		for (Participante passageiro: participantes) {
			if (passageiro.getCodigo().equals(id)) {
				participanteCadastrado = passageiro;
				break;
			}
		}
		if (participanteCadastrado != null) {
			try {
				delete(participanteCadastrado);
				participantes.remove(participanteCadastrado);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void delete(Participante participante) throws SQLException {
		String sql = "delete from participante where codigo = ?";
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setInt(1, participante.getCodigo());
		ps.execute();
		ps.close();
	}
}
