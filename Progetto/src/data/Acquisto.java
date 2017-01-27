package data;

import java.util.Date;

public class Acquisto {
	int idAcquisto, prodotto, quantita;
	String utente;
	Date data;

	public Acquisto() {

	}

	public Acquisto(int idAcquisto, int prodotto, int quantita, String utente, Date data) {
		super();
		this.idAcquisto = idAcquisto;
		this.prodotto = prodotto;
		this.quantita = quantita;
		this.utente = utente;
		this.data = data;
	}

	public int getIdAcquisto() {
		return idAcquisto;
	}

	public void setIdAcquisto(int idAcquisto) {
		this.idAcquisto = idAcquisto;
	}

	public int getProdotto() {
		return prodotto;
	}

	public void setProdotto(int prodotto) {
		this.prodotto = prodotto;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
