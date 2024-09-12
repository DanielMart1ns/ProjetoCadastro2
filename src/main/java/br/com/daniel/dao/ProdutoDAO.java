package br.com.daniel.dao;

import br.com.daniel.dao.generics.GenericDAO;
import br.com.daniel.domain.Produto;

public class ProdutoDAO extends GenericDAO<Produto, String> implements IProdutoDAO{
	public ProdutoDAO() {
		super();
	}
	
	@Override
	public Class<Produto> getTipoClasse() {
		return Produto.class;
	}

	@Override
	public void atualiarDados(Produto entity, Produto entityCadastrado) {
		entityCadastrado.setCodigo(entity.getCodigo());
		entityCadastrado.setDescricao(entity.getDescricao());
		entityCadastrado.setNome(entity.getNome());
		entityCadastrado.setValor(entity.getValor());
	}
}
