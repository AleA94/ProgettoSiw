package persistence;

import DAO.AcquistaDao;
import DAO.AcquistaProdottoDAO;
import DAO.AstaDAO;
import DAO.AstaProdottoDAO;
import DAO.CarrelloProdottoDAO;
import DAO.CategoriaDAO;
import DAO.OffertaDao;
import DAO.OffertaVeloceDao;
import DAO.ProdottoDAO;
import DAO.UtenteDAO;
import DAO.VendeProdottoDAO;

public abstract class DAOFactory {

	public static DAOFactory getDAOFactory() {
		return new MySQLDaoFactory();
	}

	public abstract UtenteDAO getUtenteDao();

	public abstract ProdottoDAO getProdottoDao();

	public abstract CategoriaDAO getCategoriaDao();

	public abstract AstaProdottoDAO getAstaDao();

	public abstract OffertaVeloceDao getOffertaVeloceDao();

	public abstract CarrelloProdottoDAO getCarrelloProdottoDAO();

	public abstract AcquistaDao getAcquistaDAO();

	public abstract VendeProdottoDAO getVendeProdottoDAO();

	public abstract AstaDAO getADao();

	public abstract AcquistaProdottoDAO getAcquistaProdottoDAO();

	public abstract AstaProdottoDAO OffertaVeloceDao();

	public abstract OffertaDao getOfferta();

}
