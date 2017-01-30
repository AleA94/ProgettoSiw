package DAO;

import java.util.List;

import data.Prodotto;

public interface ProdottoDAO {
	public int save(Prodotto p);

	public List<Prodotto> findByNegozio(String email);

	public void removeById(int id);

	Prodotto findById(int id);

	public void edit(Prodotto p);

	

}
