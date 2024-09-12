package br.com.daniel;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.daniel.dao.ClientDAO;
import br.com.daniel.dao.ClientDaoMock;
import br.com.daniel.dao.IClientDAO;
import br.com.daniel.domain.Client;
import br.com.daniel.exeption.TipoChaveNaoEncontradaException;

public class ClientDAOTest {
	
	private IClientDAO clientDAO;
	
	private Client client;
	
	public ClientDAOTest() {
		clientDAO = new ClientDaoMock();
	}
	
	@Before //antes de cada teste faça isso (before each test do it)
	public void init() throws TipoChaveNaoEncontradaException {
		//Creating client
		client = new Client(); 
		client.setName("Daniel");
		client.setCpf(99900099911L);
		client.setTel(11940980077L);
		client.setAdress("Av.Teste");
		client.setHouseNumber(77);
		client.setCity("SBC");
		client.setState("SP");
		
		//saving
		clientDAO.cadastrar(client);
	}
	
	@Test
	public void searchClient() {		
		//searching
		Client clientConsulted = clientDAO.consultar(client.getCpf());
		
		Assert.assertNotNull(clientConsulted);
	}
	
	@Test 
	public void save() throws TipoChaveNaoEncontradaException {
		//saving
		Boolean result = clientDAO.cadastrar(client);
		Assert.assertTrue(result);
	}
	
	@Test
	public void delete() {
		//delete client
		clientDAO.excluir(client.getCpf());
	}
	
	@Test
	public void update() throws TipoChaveNaoEncontradaException {
		//update client
		client.setName("Daniel Martins");
		clientDAO.alterar(client);
		
		Assert.assertEquals("Daniel Martins", client.getName());
	}
	
	@Test
	public void buscarTodos() {
		Collection<Client> list = clientDAO.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
	}
}
