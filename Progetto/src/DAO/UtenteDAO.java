package DAO;

import data.Utente;

public interface UtenteDAO {
	public Utente getUtente(String user, String pass);

	public void save(Utente u);

	public void updateDatas(Utente u);

	void editPass(String user, String pass);

	public boolean exists(String mail);
}
