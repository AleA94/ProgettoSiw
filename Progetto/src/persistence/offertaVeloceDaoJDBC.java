package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DAO.OffertaVeloceDao;

public class offertaVeloceDaoJDBC implements OffertaVeloceDao {

	private DataSource dataSource;

	public offertaVeloceDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insertOfferta(String emaiUtente, float offerta, float offertaMax, String idAsta) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into offerta (email_utente, asta, importo,offerta_max) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, emaiUtente);
			statement.setInt(2, Integer.parseInt(idAsta));
			statement.setFloat(3, offerta);
			statement.setFloat(4, offertaMax);

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
