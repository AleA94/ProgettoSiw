package DAO;

import java.util.List;

import data.VendeProdottoAsta;

public interface VendeProdottoAstaDAO {

	public List<VendeProdottoAsta> findProdotto(String nameProduct);

	public List<VendeProdottoAsta> findProdottoByCategoria(String nameProduct, int categoria);

	public VendeProdottoAsta visitProdotto(int nameProduct);
}
