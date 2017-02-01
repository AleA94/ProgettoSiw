package data;

import java.io.Serializable;

public class WishlistProdotto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Utente;
	Prodotto prodotto;
	int quantita;

	public WishlistProdotto() {
	}

	public WishlistProdotto(String utente, Prodotto prodotto) {
		Utente = utente;
		this.prodotto = prodotto;
	}

	public WishlistProdotto(String utente, Prodotto prodotto, int quantita) {
		Utente = utente;
		this.prodotto = prodotto;
		this.quantita = quantita;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getUtente() {
		return Utente;
	}

	public void setUtente(String utente) {
		Utente = utente;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
}
