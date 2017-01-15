package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.ProdottoDAO;
import data.Prodotto;

public class ProdottoDaoJDBC implements ProdottoDAO {

	private DataSource dataSource;

	public ProdottoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Prodotto p) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into Prodotto (Nome, Descrizione, inAsta, Prezzo, idCategoria, DataInizio, DataFine, Negozio) values (?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, p.getNome());
			statement.setString(2, p.getDescrizione());
			statement.setInt(3, p.getInAsta());
			statement.setFloat(4, p.getPrezzo());
			statement.setInt(5, p.getIdCategoria());
			statement.setDate(6, (Date) p.getDataInizio());
			statement.setDate(7, (Date) p.getDataFine());
			statement.setString(8, p.getNegozio());

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
	public List<Prodotto> findByNegozio(String email) {
		Connection connection = this.dataSource.getConnection();
		List<Prodotto> prodotti = new ArrayList<Prodotto>();
		try {
			PreparedStatement statement;
			String query = "select * From Prodotto where Negozio=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Prodotto p = new Prodotto();
				p.setIdProdotto(result.getInt("idProdotto"));
				p.setNome(result.getString("Nome"));
				p.setDescrizione(result.getString("Descrizione"));
				p.setInAsta(result.getInt("inAsta"));
				p.setPrezzo(result.getFloat("Prezzo"));
				p.setIdCategoria(result.getInt("idCategoria"));
				p.setDataInizio(result.getDate("DataInizio"));
				p.setDataFine(result.getDate("DataFine"));
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
	public List<Prodotto> findProdotto(String nameProduct) {
		Connection connection = this.dataSource.getConnection();
		List<Prodotto> prodotti = new ArrayList<Prodotto>();
		try {
			PreparedStatement statement;
			String query = "select * From Prodotto where nome like ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, "%"+nameProduct+"%");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Prodotto p = new Prodotto();
				p.setIdProdotto(result.getInt("idProdotto"));
				p.setNome(result.getString("Nome"));
				p.setDescrizione(result.getString("Descrizione"));
				p.setInAsta(result.getInt("inAsta"));
				p.setPrezzo(result.getFloat("Prezzo"));
				p.setIdCategoria(result.getInt("idCategoria"));
				p.setDataInizio(result.getDate("DataInizio"));
				p.setDataFine(result.getDate("DataFine"));
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
