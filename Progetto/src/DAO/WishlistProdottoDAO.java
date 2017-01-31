package DAO;

import java.util.List;

import data.WishlistProdotto;

public interface WishlistProdottoDAO {
	List<WishlistProdotto> getWishByUtente(String utente);

	void removeItem(String utente, int id);

	void setQuantityAfterBuy(int quantity, int id, String utente);

	void cleanWish(String utente);
}
