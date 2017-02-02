package data;

import java.io.Serializable;

public class CarrelloItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 318446708226709555L;
	String utente;
	Prodotto prodotto;
	int quantita;

	public CarrelloItem() {
	}

	public CarrelloItem(String utente, Prodotto p, int quantita) {
		super();
		this.utente = utente;
		this.prodotto = p;
		this.quantita = quantita;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto p) {
		this.prodotto = p;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

}
