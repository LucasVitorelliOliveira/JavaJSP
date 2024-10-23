package fema3bccJsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uteis.GerenciadorConexao;


public class ClubeDao {
	private Connection cnn = null;

	public ClubeDao(Connection cnn) {
		this.cnn = cnn;
	}
	
	private static List<Clube> clubes = new ArrayList<Clube>();
	
	public void add(Clube clube) {
		try {
			insert(clube);
			clubes.add(clube);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void insert(Clube clube) throws SQLException {
		String sql = "insert into clube values (?,?,?)";
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setInt(1, clube.getCodigo());
		ps.setString(2, clube.getNome());
		ps.setString(3, clube.getEndereco());
		ps.execute();
		ps.close();
	}
	
	public List<Clube> getClubes() {
		try {
			clubes = recuperarClubes();
			return clubes;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Deu ruim");
			
		}
		return null;
	}
	
	public ArrayList<Clube> recuperarClubes() throws SQLException {
		ArrayList<Clube> resultado = new ArrayList<Clube>();
		String sql = "select * from clube order by codigo";
		PreparedStatement ps = cnn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Clube clube = new Clube();
			clube.setCodigo(rs.getInt("codigo"));
			clube.setNome(rs.getString("nome"));
			clube.setEndereco(rs.getString("endereco"));
			resultado.add(clube);
		}
		rs.close();
		ps.close();
		return resultado;
	}
	
	public void excluir(Integer id) {
		Clube clubeRecuperada = null;
		for (Clube clube: clubes) {
			if (clube.getCodigo().equals(id)) {
				clubeRecuperada = clube;
				break;
			}
		}
		if (clubeRecuperada != null) {
			try {
				delete(clubeRecuperada);
				clubes.remove(clubeRecuperada);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void delete(Clube clube) throws SQLException {
		String sql = "delete from clube where codigo = ?";
		PreparedStatement ps = cnn.prepareStatement(sql);
		ps.setInt(1, clube.getCodigo());
		ps.execute();
		ps.close();
	}
}
