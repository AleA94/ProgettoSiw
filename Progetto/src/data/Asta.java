package data;

import java.util.Date;

public class Asta {
	Date dataInizio, dataFine;
	int idAsta, idProdotto;
	float baseAsta, prezzoRiserva, prezzoCorrente;

	public Asta() {
	}

	public Asta( Date dataInizio, Date datafine, int idProdotto, float baseAsta, float prezzoRiserva,
			float prezzoCorrente) {
		this.idAsta = idAsta;
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

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date datafine) {
		dataFine = datafine;
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
