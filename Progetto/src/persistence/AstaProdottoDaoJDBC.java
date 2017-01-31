package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.AstaProdottoDAO;
import data.AstaProdotto;

public class AstaProdottoDaoJDBC implements AstaProdottoDAO {
	private DataSource dataSource;

	public AstaProdottoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<AstaProdotto> getAste(int giorni, int ore) {
		Connection connection = this.dataSource.getConnection();
		List<AstaProdotto> aste = new ArrayList<AstaProdotto>();
		try {
			PreparedStatement statement;
			String queryScadenzaOggi = "SET @giorni=?;";
			statement = connection.prepareStatement(queryScadenzaOggi);
			statement.setInt(1, giorni);
			statement.executeQuery();

			queryScadenzaOggi = "SET @ore=?;";
			statement = connection.prepareStatement(queryScadenzaOggi);
			statement.setInt(1, ore);
			statement.executeQuery();

			queryScadenzaOggi = "SET @tot=@giorni*24+@ore;";
			statement = connection.prepareStatement(queryScadenzaOggi);
			statement.executeQuery();

			queryScadenzaOggi = "SELECT asta.id, Nome,Descrizione,prezzoCorrente,idProdotto,ImmaginePrincipale FROM asta , Prodotto where asta.id_prodotto=Prodotto.idProdotto and data_fine>=current_timestamp() and data_fine<=DATE_ADD(current_timestamp(),INTERVAL  @tot hour)";
			statement = connection.prepareStatement(queryScadenzaOggi);
			statement.executeQuery();

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				AstaProdotto a = new AstaProdotto();
				a.setIdAsta(result.getInt("id"));
				a.setNomeProdotto(result.getString("Nome"));
				a.setDescrizioneProdotto(result.getString("Descrizione"));
				a.setPrezzoCorrente(result.getFloat("prezzoCorrente"));
				a.setUrlImg(result.getString("ImmaginePrincipale"));
				aste.add(a);
			}
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return aste;
	}

	@Override
	public void updateAsta(int idAsta, float prezzoCorrente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update asta Set prezzoCorrente=? where id=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setFloat(1, prezzoCorrente);
			statement.setInt(2, idAsta);

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
