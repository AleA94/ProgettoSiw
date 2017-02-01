package DAO;

public interface OffertaVeloceDao {
	public void insertOfferta(String idUtente, float offerta, float offertaMax, String idAsta);

	public void vinciAsta();
}
