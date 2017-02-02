package data;

import java.io.Serializable;

public class Categoria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7631376796822082875L;
	Integer id, sottocategoria;
	String nome;

	public Categoria() {
	}

	public Categoria(int id, int sottocategoria, String nome) {
		super();
		this.id = id;
		this.sottocategoria = sottocategoria;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSottocategoria() {
		return sottocategoria;
	}

	public void setSottocategoria(Integer sottocategoria) {
		this.sottocategoria = sottocategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
