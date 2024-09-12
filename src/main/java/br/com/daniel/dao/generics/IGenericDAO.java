package br.com.daniel.dao.generics;

import java.io.Serializable;
import java.util.Collection;

import br.com.daniel.domain.Persistence;
import br.com.daniel.exeption.TipoChaveNaoEncontradaException;

//Interface genérica para métodos de CRUD
public interface IGenericDAO <T extends Persistence, E extends Serializable>{
	/**
	 * Método para cadastrar novos registros no database
	 * 
	 * @param entity a ser cadastrado
	 * @return retorna "true" para cadastrado e "false" para não cadastrado
	 */
	public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException;
	
	/**
	 * Método para excluir um registro no database
	 * 
	 * @param valor chave único do valor a ser excluído
	 */
	public void excluir(Long valor);
	
	/**
	 * Método para alterar um registro no database
	 * 
	 * @param entity a ser atualizado
	 */
	public void alterar(T entity) throws TipoChaveNaoEncontradaException;
	
	/**
	 * Método para consultar um registro no database
	 * 
	 * @param valor chave única do dado a ser consultado
	 */
	public T consultar(Long valor);
	
	/**
	 * Método que irá retornar todos os registros do database de um determinado dado ou tabela
	 * 
	 * @return Registros encontrados
	 */
	public Collection<T> buscarTodos();
}
