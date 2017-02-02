package data;

import java.io.Serializable;

public class Notifica implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3881474517416572624L;
	String notifica;
	int nonLetta;

	public Notifica() {

	}

	public Notifica(String notifica, int nonLetta) {
		this.notifica = notifica;
		this.nonLetta = nonLetta;
	}

	public String getNotifica() {
		return notifica;
	}

	public void setNotifica(String notifica) {
		this.notifica = notifica;
	}

	public int getNonLetta() {
		return nonLetta;
	}

	public void setNonLetta(int nonLetta) {
		this.nonLetta = nonLetta;
	}

}
