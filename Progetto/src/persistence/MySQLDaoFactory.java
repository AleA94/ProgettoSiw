package persistence;

import DAO.CategoriaDAO;
import DAO.ProdottoDAO;
import DAO.UtenteDAO;

public class MySQLDaoFactory extends DAOFactory {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static DataSource dataSource;

	static {
		try {
			Class.forName(DRIVER).newInstance();
			dataSource = new DataSource();
		} catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n" + e);
			e.printStackTrace();
		}
	}

	@Override
	public UtenteDAO getUtenteDao() {
		return new UtenteDaoJDBC(dataSource);
	}

	@Override
	public ProdottoDAO getProdottoDao() {
		return new ProdottoDaoJDBC(dataSource);
	}

	@Override
	public CategoriaDAO getCategoriaDao() {
		return new CategoriaDaoJDBC(dataSource);
	}

}
