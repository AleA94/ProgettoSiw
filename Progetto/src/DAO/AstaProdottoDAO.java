package DAO;

import java.util.List;

import data.AstaProdotto;

public interface AstaProdottoDAO {
	public List<AstaProdotto> getAste(int giorni, int ore);

	void updateAsta(int idAsta, float prezzoCorrente);

}
