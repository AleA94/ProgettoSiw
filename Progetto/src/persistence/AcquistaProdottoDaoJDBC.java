package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.AcquistaProdottoDAO;
import data.AcquistaProdotto;
import data.Prodotto;

public class AcquistaProdottoDaoJDBC implements AcquistaProdottoDAO {
	DataSource dataSource;

	public AcquistaProdottoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<AcquistaProdotto> getAcquisti(String utente) {
		Connection connection = this.dataSource.getConnection();
		List<AcquistaProdotto> l = new ArrayList<>();
		try {
			String query = "select * from Acquista a inner join Prodotto p on a.Prodotto=p.idProdotto where Utente=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, utente);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				AcquistaProdotto a = new AcquistaProdotto();
				a.setIdAcquisto(result.getInt("idAcquisto"));
				a.setUtente(utente);
				a.setData(result.getDate("Data"));
				a.setQuantita(result.getInt("quantita"));
				Prodotto p = new Prodotto();
				p.setIdProdotto(result.getInt("p.idProdotto"));
				p.setNome(result.getString("Nome"));
				p.setDescrizione(result.getString("Descrizione"));
				p.setPrezzo(result.getFloat("Prezzo") * a.getQuantita());
				a.setProdotto(p);
				l.add(a);
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
		return l;
	}

}
