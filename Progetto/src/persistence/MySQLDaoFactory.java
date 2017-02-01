package persistence;

import DAO.AcquistaDao;
import DAO.AcquistaProdottoDAO;
import DAO.AstaDAO;
import DAO.AstaProdottoDAO;
import DAO.CarrelloProdottoDAO;
import DAO.CategoriaDAO;
import DAO.NegozioDAO;
import DAO.NotificaDAO;
import DAO.OffertaDao;
import DAO.ProdottoDAO;
import DAO.UtenteDAO;
import DAO.VendeProdottoAstaDAO;
import DAO.VendeProdottoDAO;
import DAO.WishlistProdottoDAO;

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

	@Override
	public AstaProdottoDAO getAstaProdottoDao() {
		return new AstaProdottoDaoJDBC(dataSource);
	}

	@Override
	public CarrelloProdottoDAO getCarrelloProdottoDAO() {
		return new CarrelloProdottoDaoJDBC(dataSource);
	}

	@Override
	public AcquistaDao getAcquistaDAO() {
		return new AcquistaDaoJDBC(dataSource);
	}

	@Override
	public VendeProdottoDAO getVendeProdottoDAO() {
		return new VendeProdottoDaoJDBC(dataSource);
	}

	@Override
	public AstaDAO getAstaDao() {
		return new AstaDaoJDBC(dataSource);
	}

	@Override
	public AcquistaProdottoDAO getAcquistaProdottoDAO() {
		return new AcquistaProdottoDaoJDBC(dataSource);
	}

	@Override
	public DAO.OffertaVeloceDao getOffertaVeloceDao() {
		return new offertaVeloceDaoJDBC(dataSource);
	}

	@Override
	public OffertaDao getOfferta() {
		return new offertaDaoJDBC(dataSource);
	}

	@Override
	public AstaProdottoDAO OffertaVeloceDao() {
		return new AstaProdottoDaoJDBC(dataSource);
	}

	@Override
	public WishlistProdottoDAO getWishlistProdottoDAO() {
		return new WishlistProdottoDaoJDBC(dataSource);
	}

	@Override
	public NotificaDAO getNotificaDao() {
		return new NotificaDaoJDBC(dataSource);
	}

	@Override
	public VendeProdottoAstaDAO getVendeProdottoAstaDAO() {
		return new VendeProdottoAstaJDBC(dataSource);
	}

	@Override
	public NegozioDAO getNegozioDAO() {
		return new NegozioDaoJDBC(dataSource);
	}

}
