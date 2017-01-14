package DAO;

import java.util.List;

import data.Categoria;

public interface CategoriaDAO {
	public List<Categoria> getMacroCategorie();

	public List<Categoria> getSottoCategorie();
}
