package persistence;

import DAO.AcquistaDao;
import DAO.AstaProdottoDAO;
import DAO.CarrelloProdottoDAO;
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

	public abstract AstaProdottoDAO getAstaDao();

	public abstract CarrelloProdottoDAO getCarrelloProdottoDAO();

	public abstract AcquistaDao getAcquistaDAO();
}
