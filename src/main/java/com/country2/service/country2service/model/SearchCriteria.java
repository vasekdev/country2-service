package com.country2.service.country2service.model;

public class SearchCriteria {

	private String id;
	private String continent;
	private String country;
	private String capital;

	public SearchCriteria() {
		// TODO Auto-generated constructor stub
	}

	public SearchCriteria(String id, String continent, String country, String capital) {
		super();
		this.id = id;
		this.continent = continent;
		this.country = country;
		this.capital = capital;
	}

	// getters and setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

}
