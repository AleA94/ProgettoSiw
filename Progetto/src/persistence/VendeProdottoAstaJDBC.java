package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.tag.common.sql.ResultImpl;

import DAO.VendeProdottoAstaDAO;
import data.Asta;
import data.Prodotto;
import data.VendeProdottoAsta;

public class VendeProdottoAstaJDBC implements VendeProdottoAstaDAO{
	DataSource dataSource;

	public VendeProdottoAstaJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<VendeProdottoAsta> findProdotto(String nameProduct) {
		Connection connection = this.dataSource.getConnection();
		List<VendeProdottoAsta> prodotti = new ArrayList<VendeProdottoAsta>();
		try {
			PreparedStatement statement;
			String query = "select * From Vende v inner join Prodotto p on v.idProdotto=p.idProdotto LEFT JOIN asta a on p.idProdotto=a.id_prodotto where p.nome like ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, "%" + nameProduct + "%");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Prodotto prodotto = new Prodotto();
				VendeProdottoAsta p = new VendeProdottoAsta();
				p.setIdProdotto(result.getInt("idProdotto"));
				p.setDataInizio(result.getDate("data_inizio"));
				p.setDataFine(result.getDate("data_fine"));
				p.setBaseAsta(result.getFloat("base_asta"));
				p.setPrezzoCorrente(result.getFloat("prezzoCorrente"));
				prodotto.setNome(result.getString("Nome"));
				prodotto.setDescrizione(result.getString("Descrizione"));
				prodotto.setInAsta(result.getInt("inAsta"));
				prodotto.setPrezzo(result.getFloat("Prezzo"));
				prodotto.setIdCategoria(result.getInt("idCategoria"));
				prodotto.setImmagine(result.getString("ImmaginePrincipale"));
				p.setProdotto(prodotto);
				prodotti.add(p);
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
		return prodotti;
	}

	@Override
	public VendeProdottoAsta visitProdotto(int nameProduct) {
		Connection connection = this.dataSource.getConnection();
		VendeProdottoAsta p = new VendeProdottoAsta();
		try {
			PreparedStatement statement;
			String query = "select * From Vende v inner join Prodotto p on v.idProdotto=p.idProdotto LEFT JOIN asta a on p.idProdotto=a.id_prodotto where p.idProdotto=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, nameProduct);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Prodotto prodotto = new Prodotto();
				p.setIdProdotto(result.getInt("idProdotto"));
				p.setDataInizio(result.getDate("data_inizio"));
				p.setDataFine(result.getDate("data_fine"));
				p.setBaseAsta(result.getFloat("base_asta"));
				p.setPrezzoCorrente(result.getFloat("prezzoCorrente"));
				prodotto.setNome(result.getString("Nome"));
				prodotto.setDescrizione(result.getString("Descrizione"));
				prodotto.setInAsta(result.getInt("inAsta"));
				prodotto.setPrezzo(result.getFloat("Prezzo"));
				prodotto.setIdCategoria(result.getInt("idCategoria"));
				prodotto.setImmagine(result.getString("ImmaginePrincipale"));
				if (result.getString("ImmaginiAggiuntive") != null)
					prodotto.setImmaginiAggiuntive(result.getString("ImmaginiAggiuntive").split(";"));
				p.setProdotto(prodotto);
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
		return p;
	}

	@Override
	public List<VendeProdottoAsta> findProdottoByCategoria(String nameProduct, int categoria) {
		Connection connection = this.dataSource.getConnection();
		List<VendeProdottoAsta> prodotti = new ArrayList<VendeProdottoAsta>();
		try {
			PreparedStatement statement;
			String query = "select * From Vende v inner join Prodotto p on v.idProdotto=p.idProdotto JOIN Categoria c on p.idCategoria=c.idCategoria LEFT JOIN asta a on p.idProdotto=a.id_prodotto where p.nome like ? AND p.idCategoria = c.idCategoria AND c.sottoCategoria=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, "%" + nameProduct + "%");
			statement.setInt(2, categoria);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Prodotto prodotto = new Prodotto();
				VendeProdottoAsta p = new VendeProdottoAsta();
				p.setIdProdotto(result.getInt("idProdotto"));
				p.setDataInizio(result.getDate("data_inizio"));
				p.setDataFine(result.getDate("data_fine"));
				p.setBaseAsta(result.getFloat("base_asta"));
				p.setPrezzoCorrente(result.getFloat("prezzoCorrente"));
				prodotto.setNome(result.getString("Nome"));
				prodotto.setDescrizione(result.getString("Descrizione"));
				prodotto.setInAsta(result.getInt("inAsta"));
				prodotto.setPrezzo(result.getFloat("Prezzo"));
				prodotto.setIdCategoria(result.getInt("idCategoria"));
				prodotto.setImmagine(result.getString("ImmaginePrincipale"));
				p.setProdotto(prodotto);
				prodotti.add(p);
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
		return prodotti;
	}
	
}
