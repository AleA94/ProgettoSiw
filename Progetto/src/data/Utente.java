package data;

public class Utente {
	public Utente(String email, String nome, String cognome, String indirizzo, String password, Integer eVenditore,
			Integer eAttivo) {
		super();
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.password = password;
		this.eVenditore = eVenditore;
		this.eAttivo = eAttivo;
	}

	String email, nome, cognome, indirizzo, password;
	Integer eVenditore, eAttivo;

	public Utente() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer geteVenditore() {
		return eVenditore;
	}

	public void seteVenditore(Integer eVenditore) {
		this.eVenditore = eVenditore;
	}

	public Integer geteAttivo() {
		return eAttivo;
	}

	public void seteAttivo(Integer eAttivo) {
		this.eAttivo = eAttivo;
	}
}
