package br.com.daniel.services;

import br.com.daniel.domain.Client;
import br.com.daniel.exeption.TipoChaveNaoEncontradaException;

public interface IClientService {

	Boolean save(Client Client) throws TipoChaveNaoEncontradaException;

	Client findByCpf(Long cpf);

	void delete(Long cpf);

	void update(Client client) throws TipoChaveNaoEncontradaException;

}
