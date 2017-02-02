package data;

import java.io.Serializable;

public class OffertaVeloce implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7843134639567878897L;

	public float getOfferta() {
		return offerta;
	}

	public void setOfferta(float offerta) {
		this.offerta = offerta;
	}

	public float getOffertaMassima() {
		return offertaMassima;
	}

	public void setOffertaMassima(float offertaMassima) {
		this.offertaMassima = offertaMassima;
	}

	int idAsta;
	float prezzoCorrente, offerta, offertaMassima;

	public OffertaVeloce() {
	}

	public OffertaVeloce(int idAsta, float offertaMassima, float offerta, float prezzoCorrente) {
		this.idAsta = idAsta;
		this.prezzoCorrente = prezzoCorrente;
		this.offerta = offerta;
		this.offertaMassima = offertaMassima;
	}

	public int getIdAsta() {
		return idAsta;
	}

	public void setIdAsta(int idAsta) {
		this.idAsta = idAsta;
	}

	public float getPrezzoCorrente() {
		return prezzoCorrente;
	}

	public void setPrezzoCorrente(float prezzoCorrente) {
		this.prezzoCorrente = prezzoCorrente;
	}

}
