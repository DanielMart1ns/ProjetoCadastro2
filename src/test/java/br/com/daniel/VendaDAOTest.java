package br.com.daniel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import br.com.daniel.dao.ClientDAO;
import br.com.daniel.dao.IClientDAO;
import br.com.daniel.dao.IProdutoDAO;
import br.com.daniel.dao.IVendaDAO;
import br.com.daniel.dao.ProdutoDAO;
import br.com.daniel.dao.VendaDAO;
import br.com.daniel.domain.Client;
import br.com.daniel.domain.Produto;
import br.com.daniel.domain.Venda;
import br.com.daniel.domain.Venda.Status;
import br.com.daniel.exeption.TipoChaveNaoEncontradaException;

public class VendaDAOTest {
	private IVendaDAO vendaDAO;
	private IClientDAO clientDAO;
	private IProdutoDAO produtoDAO;
	
	private Client cliente;
	private Produto produto;
	
	public VendaDAOTest() {
		this.vendaDAO = new VendaDAO();
		this.clientDAO = new ClientDAO();
		this.produtoDAO = new ProdutoDAO();
	}
	
	@Before
	public void init() throws TipoChaveNaoEncontradaException{
		this.client = cadastrarCliente();
		this.produto = cadastrarProduto("A1", BigDecimal.TEN);
	}
	
	
	@Test
	public void pesquisar() throws TipoChaveNaoEncontradaException{
		Venda venda = criarVenda("A1");
		Boolean retorno = vendaDAO.cadastrar(venda);
		assertTrue(retorno);
		
		Venda vendaConsultada = vendaDAO.consultar(venda.getCodigo());
		assertNotNull(vendaConsultada);
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException{
		Venda venda = criarVenda("A2");
		Boolean retorno = vendaDAO.cadastrar(venda);
		assertTrue(retorno);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(20)));
		assertTrue(venda.getStatus().equals(Status.INICIADA));
	}
	
	@Test
	public void cancelarVenda() throws TipoChaveNaoEncontradaException{
		String codigoVenda = "A3";
		Venda venda = criarVenda(codigoVenda);
		Boolean rotorno = vendaDAO.cadastrar(venda);
		
		assertTrue(rotorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		venda.setStatus(Status.CANCELADA);
		vendaDAO.alterar(venda);
		
		Venda vendaConsultada = vendaDAO.consultar(codigoVenda);
		assertEquals(codigoVenda, vendaConsultada.getCodigo());
		assertEquals(Status.CANCELADA, vendaConsultada.getStatus())
	}
	
	@Test
	public void adicionarMaisProdutosDoMesmo() throws TipoChaveNaoEncontradaException{
		String codigoVenda = "A4";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaDAO.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Venda vendaConsultada = vendaDAO.consultar(codigoVenda);
		vendaConsultada.adicionarProduto(produto, 1);
		
		assertTrue(venda.getQuantidadeTotalProdutos() == 3);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(30)));
		assertTrue(venda.getStatus().equals(Status.INICIADA));
	}
	
	@Test
	public void adicionarMaisProdutosDiferentes() throws TipoChaveNaoEncontradaException{
		String codigoVenda = "A5";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaDAO.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoVenda, prod.getCodigo());
		
		Venda vendaConsultada = vendaDAO.consultar(codigoVenda);
		vendaConsultada.adicionarProduto(prod, 1);
		
		assertTrue(venda.getQuantidadeTotalProdutos() == 3);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(70)));
		assertTrue(venda.getStatus().equals(Status.INICIADA));
	}
	
	@Test
	public void salvarProdutoExistente() {
		Venda venda = criarVenda("A6");
		Boolean retorno = vendaDao.cadastrar(venda);
		assertTrue(retorno);
		
		Boolean retorno1 = vendaDAO.cadastrar(venda);
		assertFalse(retorno1);
		assertTrue(venda.getStatus().equals(Status.INICIADA));
	}
	
	@Test
	public void removerProduto() throws TipoChaveNaoEncontradaException{
		String codigoVenda = "A7";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaDAO.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
		assertNotNull(venda);
		assertEquals(codigoVenda, prod.getCodigo())
		
		Venda vendaConsultada = vendaDao.consultar(codigoVenda);
		vendaConsultada.adicionarProduto(prod, 1);
		assertTrue(venda.getQuantidadeTotalProdutos() == 3);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(70)));
		
		vendaConsultada.removerProduto(prod, 1);
		assertTrue(venda.getQuantidadeTotalProdutos() == 2);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(20)));
		assertTrue(venda.getStatus().equals(Status.INICIADA));
	}
	
	@Test
	public void removerAPenasUmProduto() throws TipoChaveNaoEncontradaException{
		String codigoVenda = "A8";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaDao.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoVenda, prod.getCodigo());
		
		Venda vendaConsultada = vendaDao.consultar(codigoVenda);
		vendaConsultada.adicionarProduto(prod, 1);
		assertTrue(venda.getQuantidadeTotalProdutos() == 3);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(70)));
		
		vendaConsultada.removerProduto(prod, 1);
		assertTrue(venda.getQuantidadeTotalProdutos() == 2);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(20)));
		assertTrue(venda.getStatus().equals(Status.INICIADA));
	}
	
	@Test
	public void removerTodosOsProdutos() throws TipoChaveNaoEncontradaException{
		String codigoVenda = "A9";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaDAO.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoVenda, prod.getCodigo());
		
		Venda vendaConsultada = vendaDAO.consultar(codigoVenda);
		vendaConsultada.adicionarProduto(prod, 1);
		assertTrue(venda.getQuantidadeTotalProdutos() == 3);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(70)));
		
		vendaConsultada.removerTodosOsProdutos();
		assertTrue(venda.getQuantidadeTotalProdutos() == 0);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(0)));
		assertTrue(venda.getStatus().equals(Status.INICIADA));
	}
	
	@Test
	public void finalizarVenda() throws TipoChaveNaoEncontradaException{
		String codigoVenda = "A10";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaDao.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		vendaDAO.finalizarVenda(venda);
		
		Venda vendaConsultada = this.vendaDAO.consultar(codigoVenda);
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
		assertEquals(venda.getStatus(), vendaConsultada.getStatus());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void adicionarProdutoAVendaFinalizadaError() throws TipoChaveNaoEncontradaException{
		String codigoVenda = "A11";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaDAO.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		vendaDAO.finalizarVenda(venda);
		Venda vendaConsultada = vendaDAO.consultar(codigoVenda);
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
		assertEquals(venda.getStatus(), vendaConsultada.getStatus());
		
		vendaConsultada.adicionarProduto(this.produto, 1);
	}
	
	private Client cadastrarCliente() throws TipoChaveNaoEncontradaException{
		Client client = new Client(); 
		client.setName("Daniel");
		client.setCpf(99900099911L);
		client.setTel(11940980077L);
		client.setAdress("Av.Teste");
		client.setHouseNumber(77);
		client.setCity("SBC");
		client.setState("SP");
		
		this.clientDAO.cadastrar(client);
		return client;
	}
	
	private Produto cadastrarProduto(String codigo, BigDecimal valor) throws TipoChaveNaoEncontradaException{
		this.produto = new Produto();
		produto.setCodigo(codigo);
		produto.setDescricao("Produto 1");
		produto.setNome("Monitor");
		produto.setValor(valor);
		
		this.produtoDAO.cadastrar(produto);
		return produto;
	}
	
	private Venda criarVenda(String codigo) {
		Venda venda = new Venda();
		venda.setCodigo(codigo);
		venda.setDataVenda(Instant.now());
		venda.setCliente(this.cliente);
		venda.setStatus(Status.INICIADA);
		venda.adicionarProduto(this.produto, 2);
		return venda;
	}
}
