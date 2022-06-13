package com.country2.service.country2service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.country2.service.country2service.model.Country;
import com.country2.service.country2service.model.SearchCriteria;
import com.country2.service.country2service.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	CountryRepository countryRepository;

	// get all records - findAll() method comes from CrudRepository
	public List<Country> getAllCountries() {

		Iterable<Country> results = countryRepository.findAll();

		// convert results to an arraylist
		List<Country> countries = new ArrayList<>();
		results.forEach(countries::add);

		return countries;
	}

	// get one record - findById() method comes from CrudRepository
	public Optional<Country> getCountryById(String countryId) {
		return countryRepository.findById(countryId);
	}

	// get one record - findById() method comes from CrudRepository
	public Optional<Country> getCountryByIdParam(String countryId) {
		return countryRepository.findById(countryId);
	}

	// add one record - save() method comes from CrudRepository
	public void addCountry(Country country) {
		countryRepository.save(country);
	}

	// add one record - save() method comes from CrudRepository
	public void updateCountry(Country country) {
		countryRepository.save(country);
	}

	// add one record - delete() method comes from CrudRepository
	public void deleteCountry(Country country) {
		countryRepository.delete(country);
	}

	// add one record - saveAll() method comes from CrudRepository
	public void populateCountryTable(List<Country> countryList) {
		countryRepository.saveAll(countryList);
	}

	public List<Country> findByCriteria(SearchCriteria searchCriteria) {

		System.out.println(" :::: CountryService.findByCriteria() .... ");

		System.out.println(
				" id: " + searchCriteria.getId() + " | continent: " + searchCriteria.getContinent() + " | name: " + searchCriteria.getCountry() + " | capital: " + searchCriteria.getCapital());

		List<Country> countries = countryRepository.findAll(

				new Specification<Country>() {

					private static final long serialVersionUID = -6398701486397154818L;

					@Override
					public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

						List<Predicate> predicates = new ArrayList<>();

						// ::: ID filtering - if ID is not null
						// http://localhost:8581/findCountries?id=64&continent=&name=&capital=
						if (searchCriteria.getId() != null) {
							predicates.add(criteriaBuilder.equal(root.get("id"), searchCriteria.getId()));
						}

						// ::: CONTINENT filtering - if continent is not null
						// http://localhost:8581/findCountries?id=&continent=Europe&name=&capital=
						if (searchCriteria.getContinent() != null) {
							Predicate continentPredicate = criteriaBuilder.equal(root.get("continent"), searchCriteria.getContinent());
							predicates.add(continentPredicate);
						}

						// ::: COUNTRY filtering - if name is not null
						// http://localhost:8581/findCountries?id=&continent=&name=Spain&capital=
//						if (searchCriteria.getCountry() != null) {
//							predicates.add(criteriaBuilder.equal(root.get("name"), searchCriteria.getCountry()));
//						}

						if (searchCriteria.getCountry() != null) {
							// (
							Predicate countryAPredicate = criteriaBuilder.equal(root.get("name"), searchCriteria.getCountry());
							Predicate countryBPredicate = criteriaBuilder.equal(root.get("name"), "Sweden");
							predicates.add(criteriaBuilder.or(countryAPredicate, countryBPredicate));
							// A or B
							// )
							// and C
//							Predicate predicateC = criteriaBuilder.equal(root.get("name"), searchCriteria.getCountry());
//							predicates.add(predicateC);
						}

						// ::: CAPITAL filtering - if capital is not null
						// http://localhost:8581/findCountries?id=&continent=&name=&capital=Prague
						if (searchCriteria.getCapital() != null) {
							Predicate capitalPredicate = criteriaBuilder.equal(root.get("capital"), searchCriteria.getCapital());
							predicates.add(capitalPredicate);
						}

						return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
					} // toPredicate();

				} // new Specification
		);

		return countries;
	}

}
