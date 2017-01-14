package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.CategoriaDAO;
import data.Categoria;

public class CategoriaDaoJDBC implements CategoriaDAO {
	private DataSource dataSource;

	public CategoriaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Categoria> getMacroCategorie() {
		Connection connection = this.dataSource.getConnection();
		List<Categoria> categorie = new ArrayList<Categoria>();
		try {
			PreparedStatement statement;
			String query = "select * FROM Categoria where sottocategoria is null";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Categoria c = new Categoria();
				c.setNome(result.getString("Nome"));
				categorie.add(c);
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
		return categorie;
	}

	@Override
	public List<Categoria> getSottoCategorie() {
		Connection connection = this.dataSource.getConnection();
		List<Categoria> categorie = new ArrayList<Categoria>();
		try {
			PreparedStatement statement;
			String query = "select * FROM Categoria where sottocategoria is not null";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Categoria c = new Categoria();
				c.setNome(result.getString("Nome"));
				categorie.add(c);
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
		return categorie;
	}

}
