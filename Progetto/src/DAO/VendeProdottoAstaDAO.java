package DAO;

import java.util.List;

import data.VendeProdottoAsta;

public interface VendeProdottoAstaDAO {

	public List<VendeProdottoAsta> findProdotto(String nameProduct);

	public List<VendeProdottoAsta> findProdottoByCategoria(String nameProduct, int categoria);

	public VendeProdottoAsta visitProdotto(int nameProduct);

	public List<VendeProdottoAsta> getProdottiByCategoriaAndOrder(String nomeProdotto, List<String> categorie,
			String orderBy, String inAsta);

	public List<VendeProdottoAsta> getProdottiOrdered(String nomeProdotto, String orderBy, String inAsta);
}
