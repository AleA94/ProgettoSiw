package data;

public class Notifica {
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
