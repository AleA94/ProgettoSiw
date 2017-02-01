package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import DAO.ProdottoDAO;
import data.Prodotto;

public class ProdottoDaoJDBC implements ProdottoDAO {

	private DataSource dataSource;

	public ProdottoDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int save(Prodotto p) {
		Connection connection = this.dataSource.getConnection();
		int id = -1;
		try {
			String insert = "insert into Prodotto (Nome, Descrizione, inAsta, Prezzo, idCategoria, ImmaginePrincipale, ImmaginiAggiuntive) values (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, p.getNome());
			statement.setString(2, p.getDescrizione());
			statement.setInt(3, p.getInAsta());
			statement.setFloat(4, p.getPrezzo());
			statement.setInt(5, p.getIdCategoria());
			statement.setString(6, p.getImmagine());
			statement.setString(7, p.getImmaginiAggiuntiveString());
			statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
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
		return id;
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
			fillProductBean(prodotti, result);
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
	public void removeById(int id) {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement;
		String query = "delete From Prodotto where idProdotto=?";
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
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
	public Prodotto findById(int id) {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement;
		List<Prodotto> prodotti = new ArrayList<Prodotto>();
		String query = "select * From Prodotto where idProdotto=?";
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			fillProductBean(prodotti, result);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return prodotti.get(0);
	}

	private void fillProductBean(List<Prodotto> prodotti, ResultSet result) throws SQLException {
		while (result.next()) {
			Prodotto p = new Prodotto();
			p.setIdProdotto(result.getInt("idProdotto"));
			p.setNome(result.getString("Nome"));
			p.setDescrizione(result.getString("Descrizione"));
			p.setInAsta(result.getInt("inAsta"));
			p.setPrezzo(result.getFloat("Prezzo"));
			p.setIdCategoria(result.getInt("idCategoria"));
			p.setImmagine(result.getString("ImmaginePrincipale"));
			if (result.getString("ImmaginiAggiuntive") != null)
				p.setImmaginiAggiuntive(result.getString("ImmaginiAggiuntive").split(";"));
			prodotti.add(p);
		}
	}

	@Override
	public void edit(Prodotto p) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update Prodotto Set Nome=?, Descrizione=?, inAsta=?, Prezzo=?, idCategoria=?, ImmaginePrincipale=?, ImmaginiAggiuntive=? where idProdotto=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, p.getNome());
			statement.setString(2, p.getDescrizione());
			statement.setInt(3, p.getInAsta());
			statement.setFloat(4, p.getPrezzo());
			statement.setInt(5, p.getIdCategoria());
			statement.setString(6, p.getImmagine());
			statement.setString(7, p.getImmaginiAggiuntiveString());
			statement.setInt(8, p.getIdProdotto());

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
	public void UpdatePrice(int idProduct, float newPrice) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update Prodotto Set Prezzo=? where idProdotto=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setFloat(1, newPrice);
			statement.setFloat(2, idProduct);

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
