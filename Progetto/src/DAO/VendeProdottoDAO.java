package DAO;

import java.util.List;

import data.VendeProdotto;

public interface VendeProdottoDAO {
	public List<VendeProdotto> getProdottibyNegozio(String negozio);

	public int saveProdottoinNegozio(VendeProdotto v);

	public void removeProduct(String negozio, int idProdotto);

	public VendeProdotto getProductbyId(String email, int id);

	public void edit(VendeProdotto vende);

	public void updateQuantity(int qty, int id);

	public void cleanVendite();
}
