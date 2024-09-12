package br.com.daniel.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import anotacao.TipoChave;

public class Venda implements Persistence{

	public enum Status{
		INICIADA, CONCLUIDA, CANCELADA;
	}
	
	@TipoChave("getCodigo")
	private String codigo;
	private Client cliente;
	private Set<ProdutoQuantidade> produtos;
	private BigDecimal valorTotal;
	private Instant dataVenda;
	private Status status;
	
	public Venda() {
		produtos = new HashSet<>();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Client getCliente() {
		return cliente;
	}

	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}
	
	public Instant getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Instant dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public BigDecimal getValorTotal() {
		return this.valorTotal;
	}

	public Set<ProdutoQuantidade> getProdutos(){
		return produtos;
	}
	
	public void adicionarProduto(Produto produto, Integer quantidade) {
		validarStatus();
		Optional<ProdutoQuantidade> optional = 
				produtos.stream()
					.filter(filter -> filter.getProduto().getCodigo().equals(produto.getCodigo()))
					.findAny();
		
		if(optional.isPresent()) {
			ProdutoQuantidade prodQtd = optional.get();
			prodQtd.adicionar(quantidade);
		}else {
			ProdutoQuantidade prod = new ProdutoQuantidade();
			prod.setProduto(produto);
			prod.adicionar(quantidade);
			this.produtos.add(prod);
		}
		recalcularValorTotalVenda();
	}
	
	public void removerProduto(Produto produto, Integer quantidade) {
		validarStatus();
		Optional<ProdutoQuantidade> optional = 
				produtos.stream()
					.filter(filter -> filter.getProduto().getCodigo().equals(produto.getCodigo()))
					.findAny();
		
		if(optional.isPresent()) {
			ProdutoQuantidade produtoQntd = optional.get();
			if(produtoQntd.getQuantidade() > quantidade) {
				produtoQntd.remover(quantidade);
				recalcularValorTotalVenda();
			} else {
				produtos.remove(optional.get());
				recalcularValorTotalVenda();
			}
		}
	}
	
	public void removerTodosOsProdutos() {
		validarStatus();
		produtos.clear();
		this.valorTotal = BigDecimal.ZERO;
	}
	
	public Integer getQuantidadeTotalProdutos() {
		//Soma a quantidade "getQuantidade()" de todos os objetos ProdutoQuantidade
		int result = this.produtos.stream()
								.reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getQuantidade(), Integer::sum);
		return result;
	}
	
	private void validarStatus() {
		if(this.status == Status.CONCLUIDA) {
			throw new UnsupportedOperationException("Impossível alterar. Venda finalizada.");
		}
	}
	
	private void recalcularValorTotalVenda() {
		validarStatus();
		BigDecimal valortotal = BigDecimal.ZERO;
		for(ProdutoQuantidade prod : this.produtos) {
			valorTotal = valortotal.add(prod.getValorTotal());
		}
		this.valorTotal = valortotal;
	}
}
