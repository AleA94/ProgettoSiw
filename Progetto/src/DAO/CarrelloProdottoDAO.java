package DAO;

import java.util.HashMap;
import java.util.List;

import data.CarrelloItem;

public interface CarrelloProdottoDAO {
	public HashMap<Integer, CarrelloItem> getCarrelloByUtente(String utente);

	public void removeItem(int id, String utente);

	public List<CarrelloItem> getProdottoinCarrellobyUtente(int id, String utente);

	public void setQuantity(int quantity, int prodotto, String utente);

	public void setQuantityAfterBuy(int qty, int id, String utente);

	public void cleanCart(String utente);

}
