package br.com.daniel.dao;

import br.com.daniel.dao.generics.GenericDAO;
import br.com.daniel.domain.Venda;
import br.com.daniel.domain.Venda.Status;
import br.com.daniel.exeption.TipoChaveNaoEncontradaException;

public class VendaDAO extends GenericDAO<Venda, String> implements IVendaDAO{

	@Override
	public Class<Venda> getTipoClasse() {
		return Venda.class;
	}

	@Override
	public void atualizarDados(Venda entity, Venda entityCadastrado) {
		entityCadastrado.setCodigo(entity.getCodigo());
		entityCadastrado.setStatus(entity.getStatus());
	}
	
	public void excluir(String valor) {
		throw new UnsupportedOperationException("Operação não permitida");
	}
	
	@Override
	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException {
		venda.setStatus(Status.CONCLUIDA);
		super.alterar(venda);
	}
	
}
