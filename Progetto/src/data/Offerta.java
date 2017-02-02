package data;

import java.io.Serializable;

public class Offerta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7396158922279962861L;
	String email_utente;
	int asta, id_offerta;
	float importo, offerta_max;

	public Offerta() {

	}

	public Offerta(String email, int asta, int id_offerta, float importo, float offerta_max) {
		this.email_utente = email;
		this.asta = asta;
		this.id_offerta = id_offerta;
		this.importo = importo;
		this.offerta_max = offerta_max;
	}

	public String getEmail_utente() {
		return email_utente;
	}

	public void setEmail_utente(String email_utente) {
		this.email_utente = email_utente;
	}

	public int getAsta() {
		return asta;
	}

	public void setAsta(int asta) {
		this.asta = asta;
	}

	public int getId_offerta() {
		return id_offerta;
	}

	public void setId_offerta(int id_offerta) {
		this.id_offerta = id_offerta;
	}

	public float getImporto() {
		return importo;
	}

	public void setImporto(float importo) {
		this.importo = importo;
	}

	public float getOfferta_max() {
		return offerta_max;
	}

	public void setOfferta_max(float offerta_max) {
		this.offerta_max = offerta_max;
	}

}
