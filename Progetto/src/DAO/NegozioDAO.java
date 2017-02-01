package DAO;

import data.Negozio;

public interface NegozioDAO {
	public void save(Negozio n);

	public String getNome(String email);
}
