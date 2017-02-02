package data;

import java.io.Serializable;

public class Negozio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5168049897170076364L;
	String email;
	String nome;

	public Negozio(String email, String nome) {
		super();
		this.email = email;
		this.nome = nome;
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

}
