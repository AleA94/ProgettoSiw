package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prodotto {
	int idProdotto, inAsta, idCategoria;
	float prezzo;
	String nome, descrizione, immagine;
	List<String> immaginiAggiuntive = new ArrayList<>();

	public Prodotto() {
	}

	public List<String> getImmaginiAggiuntive() {
		return immaginiAggiuntive;
	}

	public String getImmaginiAggiuntiveString() {
		if (immaginiAggiuntive != null) {
			StringBuilder sb = new StringBuilder();
			for (String s : immaginiAggiuntive)
				sb.append(s + ";");
			return sb.toString();
		} else
			return null;
	}

	public void setImmaginiAggiuntive(String[] immaginiAggiuntive) {
		if (immaginiAggiuntive != null)
			for (String s : immaginiAggiuntive)
				this.immaginiAggiuntive.add(s);
		else
			this.immaginiAggiuntive = null;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
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

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public Prodotto(int inAsta, int idCategoria, Date dataInizio, Date dataFine, float prezzo, String nome,
			String descrizione, String negozio, String immagine) {
		super();
		this.inAsta = inAsta;
		this.idCategoria = idCategoria;
		this.prezzo = prezzo;
		this.nome = nome;
		this.descrizione = descrizione;
		this.immagine = immagine;
	}

	public void addImmaginiAggiuntive(String[] immagini) {
		for (String s : immagini)
			immaginiAggiuntive.add(s);

	}
}
