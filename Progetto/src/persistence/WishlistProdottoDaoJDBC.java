package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.WishlistProdottoDAO;
import data.Prodotto;
import data.WishlistProdotto;

public class WishlistProdottoDaoJDBC implements WishlistProdottoDAO {
	DataSource dataSource;

	public WishlistProdottoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<WishlistProdotto> getWishByUtente(String utente) {
		Connection connection = this.dataSource.getConnection();
		List<WishlistProdotto> lista = new ArrayList<>();
		try {
			PreparedStatement statement;
			String query = "select * From WishList w inner join Prodotto p on w.Prodotto=p.idProdotto where Utente=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, utente);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				WishlistProdotto w = new WishlistProdotto();
				Prodotto p = new Prodotto();
				p.setIdProdotto(result.getInt("p.idProdotto"));
				p.setNome(result.getString("Nome"));
				p.setDescrizione(result.getString("Descrizione"));
				p.setInAsta(result.getInt("inAsta"));
				p.setPrezzo(result.getFloat("Prezzo"));
				p.setIdCategoria(result.getInt("idCategoria"));
				p.setImmagine(result.getString("ImmaginePrincipale"));
				if (result.getString("ImmaginiAggiuntive") != null)
					p.setImmaginiAggiuntive(result.getString("ImmaginiAggiuntive").split(";"));
				w.setUtente(utente);
				w.setProdotto(p);
				lista.add(w);
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
		return lista;
	}

	@Override
	public void removeItem(String utente, int id) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "delete From WishList where Prodotto=? and Utente=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.setString(2, utente);
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
	public void add(String utente, int id) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "insert into WishList (Utente,Prodotto) values (?,?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, utente);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				throw new PersistenceException(e.getMessage());
			}
		}

	}

}
