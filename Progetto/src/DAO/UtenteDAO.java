package DAO;

import data.Utente;

public interface UtenteDAO {
	public Utente getUtente(String user, String pass);

	public void save(Utente u);
}
