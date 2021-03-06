package data;

import java.io.Serializable;

public class AstaProdotto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3706133093638336765L;
	Integer idCategoriaProdotto, idAsta, idProdotto, tempoInizioAsta, tempoFineAsta;
	float baseAsta, prezzoRiserva, prezzoProdotto;
	String urlImg;
	String DataFineAsta, DataInizioAsta, NomeProdotto, descrizioneProdotto, Negozio;
	int prezzoCorrente;

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public int getPrezzoCorrente() {
		return prezzoCorrente;
	}

	public void setPrezzoCorrente(int prezzoCorrente) {
		this.prezzoCorrente = prezzoCorrente;
	}

	public Integer getIdCategoriaProdotto() {
		return idCategoriaProdotto;
	}

	public void setIdCategoriaProdotto(Integer idCategoriaProdotto) {
		this.idCategoriaProdotto = idCategoriaProdotto;
	}

	public Integer getIdAsta() {
		return idAsta;
	}

	public void setIdAsta(Integer idAsta) {
		this.idAsta = idAsta;
	}

	public Integer getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(Integer idProdotto) {
		this.idProdotto = idProdotto;
	}

	public Integer getTempoInizioAsta() {
		return tempoInizioAsta;
	}

	public void setTempoInizioAsta(Integer tempoInizioAsta) {
		this.tempoInizioAsta = tempoInizioAsta;
	}

	public Integer getTempoFineAsta() {
		return tempoFineAsta;
	}

	public void setTempoFineAsta(Integer tempoFineAsta) {
		this.tempoFineAsta = tempoFineAsta;
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

	public float getPrezzoProdotto() {
		return prezzoProdotto;
	}

	public void setPrezzoProdotto(float prezzoProdotto) {
		this.prezzoProdotto = prezzoProdotto;
	}

	public String getDataFineAsta() {
		return DataFineAsta;
	}

	public void setDataFineAsta(String dataFineAsta) {
		DataFineAsta = dataFineAsta;
	}

	public String getDataInizioAsta() {
		return DataInizioAsta;
	}

	public void setDataInizioAsta(String dataInizioAsta) {
		DataInizioAsta = dataInizioAsta;
	}

	public String getNomeProdotto() {
		return NomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		NomeProdotto = nomeProdotto;
	}

	public String getDescrizioneProdotto() {
		return descrizioneProdotto;
	}

	public void setDescrizioneProdotto(String descrizioneProdotto) {
		this.descrizioneProdotto = descrizioneProdotto;
	}

	public String getNegozio() {
		return Negozio;
	}

	public void setNegozio(String negozio) {
		Negozio = negozio;
	}

}
