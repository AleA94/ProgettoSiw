package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.UtenteDAO;
import data.Utente;

public class UtenteDaoJDBC implements UtenteDAO {
	private DataSource dataSource;

	public UtenteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Utente getUtente(String user, String pass) {
		Connection connection = this.dataSource.getConnection();
		Utente u = null;
		try {
			PreparedStatement statement;
			String query = "select * From Utente where Email=? and Password=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, user);
			statement.setString(2, pass);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				u = new Utente();
				u.setEmail(user);
				u.setNome(result.getString("Nome"));
				u.setCognome(result.getString("Cognome"));
				u.seteAttivo(result.getInt("eAttivo"));
				u.seteVenditore(result.getInt("eVenditore"));
				u.setIndirizzo(result.getString("Indirizzo"));
				u.setPassword(pass);
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
		return u;
	}

	@Override
	public void save(Utente u) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into Utente (Email, Password, Nome, Cognome, Indirizzo, eVenditore, eAttivo) values (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);

			statement.setString(1, u.getEmail());
			statement.setString(2, u.getPassword());
			statement.setString(3, u.getNome());
			statement.setString(4, u.getCognome());
			statement.setString(5, u.getIndirizzo());
			statement.setInt(6, u.geteVenditore());
			statement.setInt(7, u.geteAttivo());

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
	public void updateDatas(Utente u) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update Utente set Nome=?, Cognome=?, Indirizzo=? where Email=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, u.getNome());
			statement.setString(2, u.getCognome());
			statement.setString(3, u.getIndirizzo());
			statement.setString(4, u.getEmail());

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
	public void editPass(String user, String pass) {

		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update Utente set Password=? where Email=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, pass);
			statement.setString(2, user);

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
	public boolean exists(String mail) {
		Connection connection = this.dataSource.getConnection();
		int i = 0;
		try {
			PreparedStatement statement;
			String query = "select Count(*) as c From Utente where Email=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, mail);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				i = result.getInt("c");
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
		return i == 1;
	}

}
