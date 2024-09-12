package br.com.daniel.services;

import br.com.daniel.dao.ClientDAO;
import br.com.daniel.dao.IClientDAO;
import br.com.daniel.domain.Client;
import br.com.daniel.exeption.TipoChaveNaoEncontradaException;

public class ClientService implements IClientService {
	private IClientDAO clientDAO;

	public ClientService(IClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}
	
	@Override
	public Boolean save(Client client) throws TipoChaveNaoEncontradaException {
		return clientDAO.cadastrar(client);
	}

	@Override
	public Client findByCpf(Long cpf) {
		return clientDAO.consultar(cpf);
	}

	@Override
	public void delete(Long cpf) {
		clientDAO.excluir(cpf);
	}

	@Override
	public void update(Client client) throws TipoChaveNaoEncontradaException {
		clientDAO.alterar(client);
		
	}

}
