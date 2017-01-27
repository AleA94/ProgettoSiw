package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.AstaDAO;
import data.Asta;

public class AstaDaoJDBC implements AstaDAO {
	DataSource dataSource;

	public AstaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Asta getAsta(int idProdotto) {
		Connection connection = this.dataSource.getConnection();
		Asta a = new Asta();
		try {
			String query = "select * from asta where id_prodotto=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idProdotto);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				a.setBaseAsta(result.getInt("base_asta"));
				a.setDataFine(result.getTimestamp("data_fine"));
				a.setDataInizio(result.getTimestamp("data_inizio"));
				a.setPrezzoRiserva(result.getFloat("prezzo_riserva"));
				a.setPrezzoCorrente(result.getFloat("prezzoCorrente"));
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
		return a;
	}

	@Override
	public void save(Asta a) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "insert into asta (data_inizio,data_fine,id_prodotto,base_asta,prezzo_riserva,prezzoCorrente) values (?,?,?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setObject(1, new java.sql.Timestamp(a.getDataInizio().getTime()));
			statement.setObject(2, new java.sql.Timestamp(a.getDataFine().getTime()));
			statement.setInt(3, a.getIdProdotto());
			statement.setFloat(4, a.getBaseAsta());
			statement.setFloat(5, a.getPrezzoRiserva());
			statement.setFloat(6, a.getPrezzoCorrente());
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
	public void edit(Asta asta) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "update asta set data_inizio=?, data_fine=?,base_asta=?,prezzo_riserva=? where id_prodotto=?";
			statement = connection.prepareStatement(query);
			statement.setTimestamp(1, new java.sql.Timestamp(asta.getDataInizio().getTime()));
			statement.setTimestamp(2, new java.sql.Timestamp(asta.getDataFine().getTime()));
			statement.setFloat(3, asta.getBaseAsta());
			statement.setFloat(4, asta.getPrezzoRiserva());
			statement.setInt(5, asta.getIdProdotto());
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
