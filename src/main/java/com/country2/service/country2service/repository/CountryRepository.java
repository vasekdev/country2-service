package com.country2.service.country2service.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.country2.service.country2service.model.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, String>, JpaSpecificationExecutor<Country> {

//	public List<Country> filterByCriteria(SearchCriteria searchCriteria);
//	List<Country> findAllByContinent(String continent);
//
//	List<Country> findAllByName(String countryName);
//
//	List<Country> findAllByCapital(String capital);

}
