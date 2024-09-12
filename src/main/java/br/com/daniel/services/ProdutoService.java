package br.com.daniel.services;

import br.com.daniel.domain.Produto;
import br.com.daniel.services.generics.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService{
	public ProdutoService(IProdutoService dao) {
		super(dao);
	}
}
