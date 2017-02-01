package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.OffertaDao;
import data.Offerta;

public class offertaDaoJDBC implements OffertaDao {

	private DataSource dataSource;

	public offertaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Offerta getOffertaMax(int idAsta) {
		Connection connection = this.dataSource.getConnection();
		Offerta o = new Offerta();
		try {
			String query = "select email_utente,asta,id_offerta,importo,offerta_max  from offerta where asta=? and offerta_max=(select max(offerta_max) from offerta where asta=?)";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, idAsta);
			statement.setInt(2, idAsta);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				o.setEmail_utente(result.getString("email_utente"));
				o.setAsta(result.getInt("asta"));
				o.setId_offerta(result.getInt("id_offerta"));
				o.setImporto(result.getFloat("importo"));
				o.setOfferta_max(result.getFloat("offerta_max"));
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
		return o;
	}
}
