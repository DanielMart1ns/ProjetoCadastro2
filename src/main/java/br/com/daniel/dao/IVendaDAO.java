package br.com.daniel.dao;

import br.com.daniel.dao.generics.IGenericDAO;
import br.com.daniel.domain.Venda;
import br.com.daniel.exeption.TipoChaveNaoEncontradaException;

public interface IVendaDAO extends IGenericDAO<Venda, String>{
	
	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException;
}
