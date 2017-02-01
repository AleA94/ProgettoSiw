package DAO;

import data.Asta;

public interface AstaDAO {

	void save(Asta a);

	public Asta getAstabyProdotto(int idProdotto);

	void edit(Asta asta);

	public Asta getAsta(int idAsta);
}
