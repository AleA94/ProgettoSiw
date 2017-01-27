package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.ProdottoDAO;
import DAO.VendeProdottoDAO;
import data.Prodotto;
import data.VendeProdotto;

public class VendeProdottoDaoJDBC implements VendeProdottoDAO {
	DataSource dataSource;

	public VendeProdottoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<VendeProdotto> getProdottibyNegozio(String negozio) {
		Connection connection = this.dataSource.getConnection();
		List<VendeProdotto> lista = new ArrayList<>();
		try {
			PreparedStatement statement;
			String query = "select * From Vende v inner join Prodotto p on v.idProdotto=p.idProdotto where Negozio=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, negozio);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				VendeProdotto v = new VendeProdotto();
				Prodotto p = new Prodotto();
				p.setIdProdotto(result.getInt("v.idProdotto"));
				p.setNome(result.getString("Nome"));
				p.setDescrizione(result.getString("Descrizione"));
				p.setInAsta(result.getInt("inAsta"));
				p.setPrezzo(result.getFloat("Prezzo"));
				p.setIdCategoria(result.getInt("idCategoria"));
				v.setProdotto(p);
				v.setNegozio(negozio);
				v.setQuantita(result.getInt("Quantita"));
				lista.add(v);
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
	public int saveProdottoinNegozio(VendeProdotto v) {
		ProdottoDAO p = MySQLDaoFactory.getDAOFactory().getProdottoDao();
		int id = p.save(v.getProdotto());
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "insert into Vende (Negozio,idProdotto,Quantita) values (?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, v.getNegozio());
			statement.setInt(2, id);
			statement.setInt(3, v.getQuantita());
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
		return id;
	}

	@Override
	public void removeProduct(String negozio, int idProdotto) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "delete From Vende where Negozio=? and idProdotto=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, negozio);
			statement.setInt(2, idProdotto);
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
	public VendeProdotto getProductbyId(String negozio, int id) {
		Connection connection = this.dataSource.getConnection();
		VendeProdotto v = new VendeProdotto();
		try {
			PreparedStatement statement;
			String query = "select * From Vende v inner join Prodotto p on v.idProdotto=p.idProdotto where Negozio=? and v.idProdotto=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, negozio);
			statement.setInt(2, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Prodotto p = new Prodotto();
				p.setIdProdotto(result.getInt("v.idProdotto"));
				p.setNome(result.getString("Nome"));
				p.setDescrizione(result.getString("Descrizione"));
				p.setInAsta(result.getInt("inAsta"));
				p.setPrezzo(result.getFloat("Prezzo"));
				p.setIdCategoria(result.getInt("idCategoria"));
				v.setProdotto(p);
				v.setNegozio(negozio);
				v.setQuantita(result.getInt("Quantita"));
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
		return v;
	}

	@Override
	public void edit(VendeProdotto v) {
		ProdottoDAO p = MySQLDaoFactory.getDAOFactory().getProdottoDao();
		p.edit(v.getProdotto());
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "update Vende set Quantita=? where Negozio=? and idProdotto=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, v.getQuantita());
			statement.setString(2, v.getNegozio());
			statement.setInt(3, v.getProdotto().getIdProdotto());
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
	public void updateQuantity(int qty, int id) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "update Vende set Quantita=Quantita-? where idProdotto=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, qty);
			statement.setInt(2, id);
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
