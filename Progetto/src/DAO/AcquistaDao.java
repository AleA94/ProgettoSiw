package DAO;

import java.util.List;

import data.Acquisto;

public interface AcquistaDao {
	public void save(Acquisto a);

	public void saveAll(List<Acquisto> l);
}
