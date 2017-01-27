package data;

import java.util.Date;

public class AcquistaProdotto {
	int idAcquisto, quantita;
	String utente;
	Prodotto prodotto;
	Date data;

	public AcquistaProdotto() {

	}

	public AcquistaProdotto(int idAcquisto, int prodotto, int quantita, String utente, Prodotto p) {
		super();
		this.idAcquisto = idAcquisto;
		this.quantita = quantita;
		this.utente = utente;
		this.prodotto = p;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getIdAcquisto() {
		return idAcquisto;
	}

	public void setIdAcquisto(int idAcquisto) {
		this.idAcquisto = idAcquisto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
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
}
