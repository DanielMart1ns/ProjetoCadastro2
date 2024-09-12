package br.com.daniel.services;

import java.util.HashMap;
import java.util.Map;

/**
 * A classe singleton que garante que o <code>map</code> será único em toda a vida da aplicação.
 */
public class SingletonMap {

	private static SingletonMap singletonMap;
	
	/**
	 * Contém todos os registros da aplicação
	 * Simula o database 
	 */
	protected Map<Class, Map<?, ?>> map;
	
	//constructor
	private SingletonMap() {
		map = new HashMap<>();
	}
	
	/**
	 * Método que garante o retorno de apenas uma instância desse objeto
	 * @return SingletonMap
	 */
	public static SingletonMap getInstance() {
		if(singletonMap == null) {
			singletonMap = new SingletonMap();
		}
		
		return singletonMap;
	}
	
	public Map<Class, Map<?, ?>> getMap(){
		return this.map;
	}
}
