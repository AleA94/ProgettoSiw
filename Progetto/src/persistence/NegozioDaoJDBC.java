package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.NegozioDAO;
import data.Negozio;

public class NegozioDaoJDBC implements NegozioDAO {
	DataSource dataSource;

	public NegozioDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Negozio n) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into Negozio (Email, Nome) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);

			statement.setString(1, n.getEmail());
			statement.setString(2, n.getNome());

			statement.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public String getNome(String email) {
		Connection connection = this.dataSource.getConnection();
		String s = null;
		try {
			PreparedStatement statement;
			String query = "select * From Negozio where Email=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				s = result.getString("Nome");
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return s;
	}

}
