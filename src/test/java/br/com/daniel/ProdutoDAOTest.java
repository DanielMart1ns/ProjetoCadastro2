package br.com.daniel;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.daniel.dao.IProdutoDAO;
import br.com.daniel.dao.ProdutoDAO;
import br.com.daniel.domain.Produto;
import br.com.daniel.exeption.TipoChaveNaoEncontradaException;

public class ProdutoDAOTest {
	private IProdutoDAO produtoDAO;
	
	private Produto produto;
	
	public ProdutoDAOTest() {
		this.produtoDAO = new ProdutoDAO();
	}
	
	@Before
	public void init() throws TipoChaveNaoEncontradaException{
		this.produto = new Produto();
		produto.setCodigo("A1");
		produto.setDescricao("Produto 1");
		produto.setNome("Monitor");
		produto.setValor(BigDecimal.TEN);
		this.produtoDAO.cadastrar(produto)
	}
	
	@Test
	public void pesquisar() {
		Produto produto = this.produtoDAO.consultar(this.produto.getCodigo());
		Assert.assertNotNull(produto);
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException{
		this.produto.setCodigo("A2");
		Boolean retorno = this.produtoDAO.cadastrar(produto);
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void excluir() {
		this.produtoDAO.excluir(this.produto.getCodigo());
	}
	
	@Test
	public void alterarProduto() throws TipoChaveNaoEncontradaException {
		produto.setNome("Teclado");
		produtoDAO.alterar(produto);
		
		Assert.assertEquals("Teclado", produto.getNome());
	}
	
	@Test
	public void buscarTodos() {
		Collection<Produto> list = produtoDAO.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
	}
}
