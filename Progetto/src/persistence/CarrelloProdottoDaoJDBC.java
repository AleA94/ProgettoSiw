package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DAO.CarrelloProdottoDAO;
import data.CarrelloItem;
import data.Prodotto;

public class CarrelloProdottoDaoJDBC implements CarrelloProdottoDAO {
	DataSource dataSource;

	public CarrelloProdottoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public HashMap<Integer, CarrelloItem> getCarrelloByUtente(String utente) {
		Connection connection = this.dataSource.getConnection();
		HashMap<Integer, CarrelloItem> carrello = new HashMap<Integer, CarrelloItem>();
		try {
			PreparedStatement statement;
			String query = "select * from Carrello c inner join Prodotto p on c.Prodotto=p.idProdotto where Utente=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, utente);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				CarrelloItem c = new CarrelloItem();
				Prodotto p = new Prodotto();
				p.setIdProdotto(result.getInt("idProdotto"));
				p.setNome(result.getString("Nome"));
				p.setDescrizione(result.getString("Descrizione"));
				p.setInAsta(result.getInt("inAsta"));
				p.setPrezzo(result.getFloat("Prezzo"));
				p.setIdCategoria(result.getInt("idCategoria"));
				p.setDataInizio(result.getDate("DataInizio"));
				p.setDataFine(result.getDate("DataFine"));
				p.setQuantita(result.getInt("p.quantita"));
				c.setProdotto(p);
				c.setQuantita(result.getInt("Quantita"));
				carrello.put(p.getIdProdotto(), c);
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
		return carrello;
	}

	@Override
	public void removeItem(int id, String utente) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "delete from Carrello where Prodotto=? and Utente=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.setString(2, utente);
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

	@Override
	public List<CarrelloItem> getProdottoinCarrellobyUtente(int id, String utente) {
		Connection connection = this.dataSource.getConnection();
		List<CarrelloItem> prodotto = new ArrayList<CarrelloItem>();
		try {
			PreparedStatement statement;
			String query = "select * from Carrello c inner join Prodotto p on c.Prodotto=p.idProdotto where Prodotto=? and Utente=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.setString(2, utente);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				CarrelloItem c = new CarrelloItem();
				Prodotto p = new Prodotto();
				p.setIdProdotto(result.getInt("idProdotto"));
				p.setNome(result.getString("Nome"));
				p.setDescrizione(result.getString("Descrizione"));
				p.setInAsta(result.getInt("inAsta"));
				p.setPrezzo(result.getFloat("Prezzo"));
				p.setIdCategoria(result.getInt("idCategoria"));
				p.setDataInizio(result.getDate("DataInizio"));
				p.setDataFine(result.getDate("DataFine"));
				p.setQuantita(result.getInt("p.quantita"));
				c.setProdotto(p);
				c.setQuantita(result.getInt("Quantita"));
				prodotto.add(c);
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
		return prodotto;
	}

	@Override
	public void setQuantity(int quantity, int prodotto, String utente) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "update Carrello c inner join Prodotto p on c.Prodotto=p.idProdotto set c.quantita=? where Prodotto=? and Utente=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, quantity);
			statement.setInt(2, prodotto);
			statement.setString(3, utente);
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

	@Override
	public void setQuantityAfterBuy(int qty, int id, String utente) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "update Carrello set quantita=quantita-? where Prodotto=? and Utente=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, qty);
			statement.setInt(2, id);
			statement.setString(3, utente);
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

	@Override
	public void cleanCart(String utente) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "delete from Carrello where Utente=? and quantita=0";
			statement = connection.prepareStatement(query);
			statement.setString(1, utente);
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
