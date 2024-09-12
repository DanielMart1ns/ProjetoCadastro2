package br.com.daniel.dao;

import java.util.Collection;

import br.com.daniel.domain.Client;
import br.com.daniel.exeption.TipoChaveNaoEncontradaException;

public class ClientDaoMock implements IClientDAO {

	@Override
	public Boolean cadastrar(Client entity) throws TipoChaveNaoEncontradaException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void excluir(Long valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Client entity) throws TipoChaveNaoEncontradaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client consultar(Long valor) {
		Client client = new Client();
		client.setCpf(valor);
		return client;
	}

	@Override
	public Collection<Client> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
