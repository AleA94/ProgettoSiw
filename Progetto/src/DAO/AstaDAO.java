package DAO;

import data.Asta;

public interface AstaDAO {

	void save(Asta a);

	public Asta getAsta(int idProdotto);

	void edit(Asta asta);
}
