package com.country2.service.country2service.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {

	@Id
	private String id;
	private String continent;
	private String name;
	private String capital;

	// constructors
	public Country() {
		// TODO Auto-generated constructor stub
	}

	public Country(String id, String continent, String name, String capital) {
		super();
		this.id = id;
		this.continent = continent;
		this.name = name;
		this.capital = capital;
	}

	// getters & setters
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

}
