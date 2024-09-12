package br.com.daniel.dao;

import br.com.daniel.dao.generics.GenericDAO;
import br.com.daniel.domain.Client;

public class ClientDAO extends GenericDAO<Client> implements IClientDAO{
	
	public ClientDAO() {
		super();
	}

	@Override
	public Class<Client> getTipoClasse() {
		return Client.class;
	}

	@Override
	public void atualizarDados(Client entity, Client entityCadastrado) {
		entityCadastrado.setCity(entity.getCity());
		entityCadastrado.setCpf(entity.getCpf());
		entityCadastrado.setAdress(entity.getAdress());
		entityCadastrado.setState(entity.getState());
		entityCadastrado.setName(entity.getName());
		entityCadastrado.setHouseNumber(entity.getHouseNumber());
		entityCadastrado.setTel(entity.getTel());
	}
	
}
