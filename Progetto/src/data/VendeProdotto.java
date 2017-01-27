package data;

public class VendeProdotto {
	String negozio;
	Prodotto prodotto;
	int quantita;

	public VendeProdotto() {

	}

	public VendeProdotto(String negozio, Prodotto prodotto, int quantita) {
		super();
		this.negozio = negozio;
		this.prodotto = prodotto;
		this.quantita = quantita;
	}

	public String getNegozio() {
		return negozio;
	}

	public void setNegozio(String negozio) {
		this.negozio = negozio;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

}
