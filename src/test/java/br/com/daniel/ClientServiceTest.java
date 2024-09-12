package br.com.daniel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.daniel.dao.ClientDaoMock;
import br.com.daniel.dao.IClientDAO;
import br.com.daniel.domain.Client;
import br.com.daniel.exeption.TipoChaveNaoEncontradaException;
import br.com.daniel.services.ClientService;
import br.com.daniel.services.IClientService;

public class ClientServiceTest {
	
	private IClientService clientService;
	
	private Client client;
	
	public ClientServiceTest() {
		IClientDAO dao = new ClientDaoMock();
		clientService = new ClientService(dao);
	}
	
	@Before //antes de cada teste faça isso (before each test do it)
	public void init() {
		//Creating client
		client = new Client(); 
		client.setName("Daniel");
		client.setCpf(99900099911L);
		client.setTel(11940980077L);
		client.setAdress("Av.Teste");
		client.setHouseNumber(77);
		client.setCity("SBC");
		client.setState("SP");
	}
	
	@Test
	public void search() {		
		//searching
		Client clientConsulted = clientService.findByCpf(client.getCpf());
		
		Assert.assertNotNull(clientConsulted);
	}
	
	@Test 
	public void save() throws TipoChaveNaoEncontradaException {
		//saving
		Boolean result = clientService.save(client);
		Assert.assertTrue(result);
	}
	
	@Test
	public void delete() {
		//delete client
		clientService.delete(client.getCpf());
	}
	
	@Test
	public void update() throws TipoChaveNaoEncontradaException {
		//update client
		client.setName("Daniel Martins");
		clientService.update(client);
		
		Assert.assertEquals("Daniel Martins", client.getName());
	}
}
