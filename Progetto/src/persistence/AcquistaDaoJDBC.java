package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import DAO.AcquistaDao;
import data.Acquisto;

public class AcquistaDaoJDBC implements AcquistaDao {
	private DataSource dataSource;

	public AcquistaDaoJDBC(DataSource s) {
		dataSource = s;
	}

	@Override
	public void save(Acquisto a) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into Acquista (Utente, Prodotto, Data, Quantita) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, a.getUtente());
			statement.setInt(2, a.getProdotto());
			statement.setDate(3, new java.sql.Date(a.getData().getTime()));
			statement.setInt(4, a.getQuantita());

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
	public void saveAll(List<Acquisto> l) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into Acquista (Utente, Prodotto, Data, Quantita) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			for (Acquisto a : l) {
				statement.setString(1, a.getUtente());
				statement.setInt(2, a.getProdotto());
				statement.setDate(3, new java.sql.Date(a.getData().getTime()));
				statement.setInt(4, a.getQuantita());

				statement.executeUpdate();
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
	}

}
