package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.NotificaDAO;
import data.Notifica;

public class NotificaDaoJDBC implements NotificaDAO {
	DataSource dataSource;

	public NotificaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Notifica> getNotifichebyUtente(String utente) {
		Connection connection = this.dataSource.getConnection();
		List<Notifica> lista = new ArrayList<>();
		try {
			PreparedStatement statement;
			String query = "select Notifica, NonLetta from Notifica where Utente=? order by NonLetta desc, Data desc";
			statement = connection.prepareStatement(query);
			statement.setString(1, utente);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Notifica n = new Notifica(result.getString("Notifica"), result.getInt("NonLetta"));
				lista.add(n);
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
		return lista;
	}

	@Override
	public int getUnread(String utente) {
		Connection connection = this.dataSource.getConnection();
		int n = 0;
		try {
			PreparedStatement statement;
			String query = "select Count(*) as c from Notifica where NonLetta=1 and Utente=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, utente);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				n = result.getInt("c");
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
		return n;

	}

	@Override
	public void clear(String utente) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "update Notifica set NonLetta=0 where NonLetta=1 and Utente=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, utente);
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
}
