package data;

import java.util.Date;

public class Prodotto {
	int idProdotto, inAsta, idCategoria;
	Date dataInizio, dataFine;
	float prezzo;
	String nome, descrizione, negozio;

	public Prodotto() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public int getInAsta() {
		return inAsta;
	}

	public void setInAsta(int inAsta) {
		this.inAsta = inAsta;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
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

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public Prodotto(int idProdotto, int inAsta, int idCategoria, Date dataInizio, Date dataFine, float prezzo,
			String nome, String descrizione) {
		this.idProdotto = idProdotto;
		this.inAsta = inAsta;
		this.idCategoria = idCategoria;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.prezzo = prezzo;
		this.nome = nome;
		this.descrizione = descrizione;
	}

	public Prodotto(int inAsta, int idCategoria, float prezzo, String nome, Date dataInizio, Date dataFine,
			String descrizione) {
		this.inAsta = inAsta;
		this.idCategoria = idCategoria;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.prezzo = prezzo;
		this.nome = nome;
		this.descrizione = descrizione;
	}

	public Prodotto(int inAsta, int idCategoria, Date dataInizio, Date dataFine, float prezzo, String nome,
			String descrizione, String negozio) {
		super();
		this.inAsta = inAsta;
		this.idCategoria = idCategoria;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.prezzo = prezzo;
		this.nome = nome;
		this.descrizione = descrizione;
		this.negozio = negozio;
	}

	public String getNegozio() {
		return negozio;
	}

	public void setNegozio(String s) {
		negozio = s;
	}
}
