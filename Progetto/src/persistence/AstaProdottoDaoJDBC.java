package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public List<AstaProdotto> getAste() {
		Connection connection = this.dataSource.getConnection();
		List<AstaProdotto> aste = new ArrayList<AstaProdotto>();
		try {
			PreparedStatement statement;
			String queryScadenzaOggi = "SELECT Nome,Descrizione,prezzoCorrente FROM asta , Prodotto where asta.id_prodotto=Prodotto.idProdotto  and data_fine<current_date()+1;";
			String queryScadenzaInUnOra = "SELECT asta.id ,current_time(),timediff(tempo_fine,current_time())+1,data_fine,asta.tempo_fine, Prodotto.Nome ,Prodotto.Descrizione, Prodotto.NegozioFROM asta , Prodottowhere asta.id_prodotto=Prodotto.idProdotto and data_fine=current_date() and timediff(tempo_fine,current_time())<5956 ;";

			statement = connection.prepareStatement(queryScadenzaOggi);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				AstaProdotto a = new AstaProdotto();
				a.setNomeProdotto(result.getString("Nome"));
				a.setDescrizioneProdotto(result.getString("Descrizione"));
				a.setPrezzoCorrente(result.getFloat("prezzoCorrente"));
				System.out.println(a.getDescrizioneProdotto() + a.getNomeProdotto() + a.getPrezzoCorrente());
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

}
