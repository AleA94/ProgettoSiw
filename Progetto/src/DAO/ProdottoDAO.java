package DAO;

import java.util.List;

import data.Prodotto;

public interface ProdottoDAO {
	public void save(Prodotto p);

	public List<Prodotto> findByNegozio(String email);

	public void removeById(int id);

	Prodotto findById(int id);

	public void edit(Prodotto p);

	public List<Prodotto> findProdotto(String nameProduct);
	
	public List<Prodotto> findProdottoByCategoria(String nameProduct,String categoria);

}
