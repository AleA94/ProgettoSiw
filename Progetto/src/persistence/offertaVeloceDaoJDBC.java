package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.OffertaVeloceDao;
import data.Asta;

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
		vinciAsta();
	}

	@Override
	public void vinciAsta() {
		Connection connection = this.dataSource.getConnection();
		String a = "select id ,id_prodotto,prezzoCorrente, data_fine from asta where data_fine <current_timestamp()";
		String b = "insert into Acquista (Utente,Prodotto,Data,quantita) values(?,?,current_date(),1) ";
		String d = "insert into Notifica (Utente,Notifica,NonLetta,Data) values(?,?,1,current_timestamp()) ";
		String e = "DELETE FROM offerta WHERE asta=?";
		String f = "select prezzoCorrente from asta where id =?";
		String g = "UPDATE Prodotto SET Prezzo=? WHERE idProdotto=?";
		String h = "DELETE FROM asta WHERE id=?";
		String i = "DELETE FROM Vende WHERE idProdotto=?";
		String vincitore = "select email_utente, max(offerta_max)  from asta,offerta where   offerta.asta=asta.id and asta.id=?";
		String nomeProdotto = "select Nome from Prodotto where Prodotto.idProdotto=?";

		try {
			PreparedStatement statement = connection.prepareStatement(a);
			ResultSet result = statement.executeQuery();
			ArrayList<Asta> listaAsteScadute = new ArrayList<Asta>();

			while (result.next()) {
				Asta asta = new Asta();
				asta.setIdAsta(result.getInt("id"));
				asta.setDataFine(result.getDate("data_fine"));
				asta.setIdProdotto(result.getInt("id_prodotto"));
				asta.setPrezzoCorrente(result.getFloat("prezzoCorrente"));
				listaAsteScadute.add(asta);
			}

			for (Asta asta : listaAsteScadute) {
				PreparedStatement statement2 = connection.prepareStatement(vincitore);
				statement2.setInt(1, asta.getIdAsta());
				ResultSet result2 = statement2.executeQuery();
				String email_utente = null;
				while (result2.next()) {
					email_utente = result2.getString("email_utente");
				}

				if (email_utente != null) {
					PreparedStatement statement3 = connection.prepareStatement(b);
					statement3.setString(1, email_utente);
					statement3.setInt(2, asta.getIdProdotto());
					statement3.executeUpdate();

					PreparedStatement statement4 = connection.prepareStatement(nomeProdotto);
					statement4.setInt(1, asta.getIdProdotto());
					ResultSet result4 = statement4.executeQuery();

					String nomeProd = null;
					while (result4.next()) {
						nomeProd = result4.getString("Nome");
					}

					PreparedStatement statement5 = connection.prepareStatement(d);
					statement5.setString(1, email_utente);
					statement5.setString(2, "ti sei aggiudicato il prodotto: " + nomeProd);
					statement5.executeUpdate();

					PreparedStatement statement6 = connection.prepareStatement(e);
					statement6.setInt(1, asta.getIdAsta());
					statement6.executeUpdate();

					PreparedStatement statement7 = connection.prepareStatement(f);
					statement7.setInt(1, asta.getIdAsta());
					ResultSet result5 = statement7.executeQuery();

					Float prezzoCorrente = null;
					while (result5.next()) {
						prezzoCorrente = result5.getFloat("prezzoCorrente");
					}
					PreparedStatement statement8 = connection.prepareStatement(g);
					statement8.setFloat(1, prezzoCorrente);
					statement8.setInt(2, asta.getIdProdotto());
					statement8.executeUpdate();
				}
				PreparedStatement statement9 = connection.prepareStatement(h);
				statement9.setInt(1, asta.getIdAsta());
				statement9.executeUpdate();

				PreparedStatement statement10 = connection.prepareStatement(i);
				statement10.setInt(1, asta.getIdProdotto());
				statement10.executeUpdate();
			}
		} catch (SQLException p) {
			// TODO Auto-generated catch block
			p.printStackTrace();
		}
	}

}
