package com.country2.service.country2service.model;

import java.util.List;

public class CountrySearchResultsDTO {

	private List<Country> countryList;

	// empty constructor
	public CountrySearchResultsDTO() {
		// TODO Auto-generated constructor stub
	}

	// contructor with one arg
	public CountrySearchResultsDTO(List<Country> countryList) {
		super();
		this.countryList = countryList;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

}
