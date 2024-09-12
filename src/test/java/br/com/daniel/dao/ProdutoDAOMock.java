package br.com.daniel.dao;

import java.util.Collection;

import br.com.daniel.domain.Produto;
import br.com.daniel.exeption.TipoChaveNaoEncontradaException;

public class ProdutoDAOMock implements IProdutoDAO{

	@Override
	public Boolean cadastrar(Produto entity) throws TipoChaveNaoEncontradaException {
		return true;
	}

	@Override
	public void excluir(Long valor) {
		
	}

	@Override
	public void alterar(Produto entity) throws TipoChaveNaoEncontradaException {
	
	}

	@Override
	public Produto consultar(Long valor) {
		Produto produto = new Produto();
		produto.setCodigo(valor);
		return produto;
	}

	@Override
	public Collection<Produto> buscarTodos() {
		return null;
	}
	
}
