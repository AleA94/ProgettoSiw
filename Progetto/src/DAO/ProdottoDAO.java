package DAO;

import java.util.List;

import data.Prodotto;

public interface ProdottoDAO {
	public void save(Prodotto p);

	public List<Prodotto> findByNegozio(String email);
	public List<Prodotto> findProdotto(String nameProduct);
}
