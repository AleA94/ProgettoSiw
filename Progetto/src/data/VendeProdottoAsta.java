package data;

import java.io.Serializable;
import java.util.Date;

public class VendeProdottoAsta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5025329750886472166L;
	String negozio;
	Prodotto prodotto;
	int quantita;
	Date dataInizio, dataFine;
	int idProdotto, idAsta;
	float baseAsta, prezzoRiserva, prezzoCorrente;

	public VendeProdottoAsta() {

	}

	public VendeProdottoAsta(String negozio, Prodotto prodotto, int quantita, Date dataInizio, Date datafine,
			int idProdotto, float baseAsta, float prezzoRiserva, float prezzoCorrente) {
		super();
		this.negozio = negozio;
		this.prodotto = prodotto;
		this.quantita = quantita;
		this.dataInizio = dataInizio;
		dataFine = datafine;
		this.idProdotto = idProdotto;
		this.baseAsta = baseAsta;
		this.prezzoRiserva = prezzoRiserva;
		this.prezzoCorrente = prezzoCorrente;
	}

	public int getIdAsta() {
		return idAsta;
	}

	public void setIdAsta(int idAsta) {
		this.idAsta = idAsta;
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

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public float getBaseAsta() {
		return baseAsta;
	}

	public void setBaseAsta(float baseAsta) {
		this.baseAsta = baseAsta;
	}

	public float getPrezzoRiserva() {
		return prezzoRiserva;
	}

	public void setPrezzoRiserva(float prezzoRiserva) {
		this.prezzoRiserva = prezzoRiserva;
	}

	public float getPrezzoCorrente() {
		return prezzoCorrente;
	}

	public void setPrezzoCorrente(float prezzoCorrente) {
		this.prezzoCorrente = prezzoCorrente;
	}

}
