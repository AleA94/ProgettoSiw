package DAO;

import java.util.List;

import data.Notifica;

public interface NotificaDAO {
	public List<Notifica> getNotifichebyUtente(String utente);

	public int getUnread(String utente);

	public void clear(String utente);
}
