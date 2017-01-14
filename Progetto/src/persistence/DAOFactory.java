package persistence;

import DAO.CategoriaDAO;
import DAO.ProdottoDAO;
import DAO.UtenteDAO;

public abstract class DAOFactory {

	public static DAOFactory getDAOFactory() {
		return new MySQLDaoFactory();
	}

	public abstract UtenteDAO getUtenteDao();

	public abstract ProdottoDAO getProdottoDao();

	public abstract CategoriaDAO getCategoriaDao();
}
