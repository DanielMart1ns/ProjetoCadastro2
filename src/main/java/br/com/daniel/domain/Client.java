package br.com.daniel.domain;

public class Client implements Persistence{
	private String name;
	private Long cpf;
	private Long tel;
	private String adress;
	private Integer houseNumber;
	private String city;
	private String state;
	
	//Getters and setters
	//Name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//CPF
	public Long getCpf() {
		return this.cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	//Telephone
	public Long getTel() {
		return tel;
	}

	public void setTel(Long tel) {
		this.tel = tel;
	}

	//Adress
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	//HouseNumber
	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	//City
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	//State
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
