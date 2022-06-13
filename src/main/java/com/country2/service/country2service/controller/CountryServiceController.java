package com.country2.service.country2service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.country2.service.country2service.model.Country;
import com.country2.service.country2service.model.CountrySearchResultsDTO;
import com.country2.service.country2service.model.SearchCriteria;
import com.country2.service.country2service.service.CountryService;

@RestController
public class CountryServiceController {

	@Autowired
	CountryService countryService;

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// SCENARIO 1 (get all records) : no url params or anything in the request body
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	@GetMapping("/getAllCountries")
	public CountrySearchResultsDTO getAllCountries() {

		System.out.println(" #### 1. CountryServiceController.getAllCountries()");

		CountrySearchResultsDTO resultsDTO = new CountrySearchResultsDTO();
		resultsDTO.setCountryList(countryService.getAllCountries());

		return resultsDTO;
		// return getAllCountryResults();
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// SCENARIO 2 (get a single record) : url variable used and nothing in the request body
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	@GetMapping("/getCountry/{countryId}")
	public Country getCountryById(@PathVariable("countryId") String countryId) {

		System.out.println(" #### 2. CountryServiceController.getCountryById(" + countryId + ")");

		Optional<Country> optCountry = countryService.getCountryById(countryId);
		Country country = optCountry.get();

		return country;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// SCENARIO 3 (get a single record) : url param and nothing in the request body
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	@GetMapping(value = "/getCountry")
	public Country getCountryByIdParam(@RequestParam("countryId") String countryId) {

		System.out.println(" #### 3. CountryServiceController.getCountryByIdParam(" + countryId + ")");

		Optional<Country> optCountry = countryService.getCountryByIdParam(countryId);
		Country country = optCountry.get();
		return country;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// SCENARIO 4 (get records matching search criteria)
	// >>>> no params and UI search criteria sent in the request body
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	@PostMapping("/findByCriteria")
	public CountrySearchResultsDTO findMatchingCountries(@RequestBody SearchCriteria searchCriteria) {

		System.out.println(" #### 4. CountryServiceController.findMatchingCountries()");

		String id = searchCriteria.getId();
		String continent = searchCriteria.getContinent();
		String name = searchCriteria.getCountry();
		String capital = searchCriteria.getCapital();
		System.out.println(id + " | " + continent + " | " + name + " | " + capital);

		// RESULTS: service call
		List<Country> filteredCountries = countryService.findByCriteria(searchCriteria);

		CountrySearchResultsDTO dto = new CountrySearchResultsDTO();
		dto.setCountryList(filteredCountries);

		// return getAllCountryResults();
		return dto;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// SCENARIO 5 (get records matching search criteria)
	// >>>> filterging done based on params and UI search criteria sent in the request body
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	@PostMapping("/findCountriesByParams")
	public CountrySearchResultsDTO findCountriesByParams(@RequestBody SearchCriteria searchCriteria) {

		System.out.println(" #### 5. CountryServiceController.findCountriesByParams()");

		String id = searchCriteria.getId();
		String continent = searchCriteria.getContinent();
		String name = searchCriteria.getCountry();
		String capital = searchCriteria.getCapital();
		System.out.println(id + " | " + continent + " | " + name + " | " + capital);

		List<Country> filteredCountries = countryService.findByCriteria(searchCriteria);
		CountrySearchResultsDTO dto = new CountrySearchResultsDTO();
		dto.setCountryList(filteredCountries);

		return dto;
	}

//	@PostMapping("/byContinent/{continent}")
//	public CountrySearchResultsDTO findByContinent(@PathVariable("continent") String continent) {
//		
//		CountrySearchResultsDTO dto = new CountrySearchResultsDTO();
//		dto.setCountryList(filteredCountries);
//		return 
//	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% UTIL METHODS) %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	// -- start the service and go to POSTMAN:
	// - type: POST
	// - URL: http://localhost:8582/addCountry
	// - Body (tab) : raw (input) JSON(application/json)
	// - paste the following in the body and send
	/*
	 * { "id": "1", "continent": "Europe", "name": "Sweden", "capital": "Stockholm" }
	 * 
	 */
	@PostMapping("/addCountry") // > http://localhost:8582/addCountry
	public void addCountry(@RequestBody Country country) {
		countryService.addCountry(country);
	}

	@PostMapping("/populateCountryTable")
	public void populateCountryList() {
		countryService.populateCountryTable(getAllCountryResults().getCountryList());
	}

	private CountrySearchResultsDTO getAllCountryResults() {

		CountrySearchResultsDTO results = new CountrySearchResultsDTO();

		List<Country> countries = new ArrayList<>();

		countries.add(new Country("1", "Africa", "Algeria", "Algiers"));
		countries.add(new Country("2", "Africa", "Angola", "Luanda"));
		countries.add(new Country("3", "Africa", "Benin", "Porto Novo, Cotonou"));
		countries.add(new Country("4", "Africa", "Botswana", "Gaborone"));
		countries.add(new Country("5", "Africa", "Burkina Faso", "Ouagadougou"));
		countries.add(new Country("6", "Africa", "Burundi", "Gitega"));
		countries.add(new Country("7", "Africa", "Cameroon", "Yaoundé"));
		countries.add(new Country("8", "Africa", "Cape Verde", "Praia"));
		countries.add(new Country("9", "Africa", "Central African Republic", "Bangui"));
		countries.add(new Country("10", "Africa", "Chad", "N'Djamena"));
		countries.add(new Country("11", "Africa", "Comoros", "Moroni"));
		countries.add(new Country("12", "Africa", "Republic of the Congo", "Brazzaville"));
		countries.add(new Country("13", "Africa", "Democratic Republic of the Congo", "Kinshasa"));
		countries.add(new Country("14", "Africa", "Côte d'Ivoire ", "Yamoussoukro"));
		countries.add(new Country("15", "Africa", "Djibouti", "Djibouti"));
		countries.add(new Country("16", "Africa", "Egypt", "Cairo"));
		countries.add(new Country("17", "Africa", "Equatorial Guinea", "Malabo"));
		countries.add(new Country("18", "Africa", "Eritrea", "Asmara"));
		countries.add(new Country("19", "Africa", "Ethiopia", "Addis Ababa"));
		countries.add(new Country("20", "Africa", "Gabon", "Libreville"));
		countries.add(new Country("21", "Africa", "The Gambia", "Banjul"));
		countries.add(new Country("22", "Africa", "Ghana", "Accra"));
		countries.add(new Country("23", "Africa", "Guinea", "Conakry"));
		countries.add(new Country("24", "Africa", "Guinea-Bissau", "Bissau"));
		countries.add(new Country("25", "Africa", "Kenya", "Nairobi"));
		countries.add(new Country("26", "Africa", "Lesotho", "Maseru"));
		countries.add(new Country("27", "Africa", "Liberia", "Monrovia"));
		countries.add(new Country("28", "Africa", "Libya", "Tripoli"));
		countries.add(new Country("29", "Africa", "Madagascar", "Antananarivo"));
		countries.add(new Country("30", "Africa", "Malawi", "Lilongwe"));
		countries.add(new Country("31", "Africa", "Mali", "Bamako"));
		countries.add(new Country("32", "Africa", "Mauritania", "Nouakchott"));
		countries.add(new Country("33", "Africa", "Mauritius", "Port Louis"));
		countries.add(new Country("34", "Africa", "Morocco", "Rabat"));
		countries.add(new Country("35", "Africa", "Mozambique", "Maputo"));
		countries.add(new Country("36", "Africa", "Namibia", "Windhoek"));
		countries.add(new Country("37", "Africa", "Niger", "Niamey"));
		countries.add(new Country("38", "Africa", "Nigeria", "Abuja"));
		countries.add(new Country("39", "Africa", "Rwanda", "Kigali"));
		countries.add(new Country("40", "Africa", "São Tomé and Príncipe", "São Tomé"));
		countries.add(new Country("41", "Africa", "Senegal", "Dakar"));
		countries.add(new Country("42", "Africa", "Seychelles", "Victoria"));
		countries.add(new Country("43", "Africa", "Sierra Leone", "Freetown"));
		countries.add(new Country("44", "Africa", "Somalia", "Mogadishu"));
		countries.add(new Country("45", "Africa", "South Africa", "Pretoria"));
		countries.add(new Country("46", "Africa", "South Sudan", "Juba"));
		countries.add(new Country("47", "Africa", "Sudan", "Khartoum"));
		countries.add(new Country("48", "Africa", "Swaziland", "Mbabane"));
		countries.add(new Country("49", "Africa", "Tanzania", "Dodoma"));
		countries.add(new Country("50", "Africa", "Togo", "Lome"));
		countries.add(new Country("51", "Africa", "Tunisia", "Tunis"));
		countries.add(new Country("52", "Africa", "Uganda", "Kampala"));
		countries.add(new Country("53", "Africa", "Western Sahara", "El Aaiún"));
		countries.add(new Country("54", "Africa", "Zambia", "Lusaka"));
		countries.add(new Country("55", "Africa", "Zimbabwe", "Harare"));
		countries.add(new Country("56", "Asia", "Afghanistan", "Kabul"));
		countries.add(new Country("57", "Asia", "Armenia", "Yerevan"));
		countries.add(new Country("58", "Asia", "Azerbaijan", "Baku"));
		countries.add(new Country("59", "Asia", "Bahrain", "Manama"));
		countries.add(new Country("60", "Asia", "Bangladesh", "Dhaka"));
		countries.add(new Country("61", "Asia", "Bhutan", "Thimphu"));
		countries.add(new Country("62", "Asia", "Brunei", "Bandar Seri Begawan"));
		countries.add(new Country("63", "Asia", "Cambodia", "Phnom Penh"));
		countries.add(new Country("64", "Asia", "China", "Beijing"));
		countries.add(new Country("65", "Asia", "Cyprus", "Nicosia"));
		countries.add(new Country("66", "Asia", "East Timor", "Dili"));
		countries.add(new Country("67", "Asia", "Georgia", "Tbilisi"));
		countries.add(new Country("68", "Asia", "India", "New Delhi"));
		countries.add(new Country("69", "Asia", "Indonesia", "Jakarta"));
		countries.add(new Country("70", "Asia", "Iran", "Tehran"));
		countries.add(new Country("71", "Asia", "Iraq", "Baghdad"));
		countries.add(new Country("72", "Asia", "Israel", "Jerusalem"));
		countries.add(new Country("73", "Asia", "Japan", "Tokyo"));
		countries.add(new Country("74", "Asia", "Jordan", "Amman"));
		countries.add(new Country("75", "Asia", "Kazakhstan", "nursultan"));
		countries.add(new Country("76", "Asia", "Kuwait", "Kuwait"));
		countries.add(new Country("77", "Asia", "Kyrgyzstan", "Bishkek"));
		countries.add(new Country("78", "Asia", "Laos", "Vientiane"));
		countries.add(new Country("79", "Asia", "Lebanon", "Beirut"));
		countries.add(new Country("80", "Asia", "Malaysia", "Kuala Lumpur"));
		countries.add(new Country("81", "Asia", "Maldives", "Malé"));
		countries.add(new Country("82", "Asia", "Mongolia", "Ulaanbaatar"));
		countries.add(new Country("83", "Asia", "Myanmar", "Naypyidaw"));
		countries.add(new Country("84", "Asia", "Nepal", "Kathmandu"));
		countries.add(new Country("85", "Asia", "North Korea", "Pyongyang"));
		countries.add(new Country("86", "Asia", "Oman", "Muscat"));
		countries.add(new Country("87", "Asia", "Pakistan", "Islamabad"));
		countries.add(new Country("88", "Asia", "Palestine", "Jerusalem"));
		countries.add(new Country("89", "Asia", "Philippines", "Manila"));
		countries.add(new Country("90", "Asia", "Qatar", "Doha"));
		countries.add(new Country("91", "Asia", "Russia", "Moscow"));
		countries.add(new Country("92", "Asia", "Saudi Arabia", "Riyadh"));
		countries.add(new Country("93", "Asia", "Singapore", "Singapore"));
		countries.add(new Country("94", "Asia", "South Korea", "Seoul"));
		countries.add(new Country("95", "Asia", "Sri Lanka", "Colombo "));
		countries.add(new Country("96", "Asia", "Syria", "Damascus"));
		countries.add(new Country("97", "Asia", "Taiwan", "Taipei"));
		countries.add(new Country("98", "Asia", "Tajikistan", "Dushanbe"));
		countries.add(new Country("99", "Asia", "Thailand", "Bangkok"));
		countries.add(new Country("100", "Asia", "Turkey", "Ankara"));
		countries.add(new Country("101", "Asia", "Turkmenistan", "Aşgabat"));
		countries.add(new Country("102", "Asia", "United Arab Emirates", "Abu Dhabi"));
		countries.add(new Country("103", "Asia", "Uzbekistan", "Tashkent"));
		countries.add(new Country("104", "Asia", "Vietnam", "Hanoi"));
		countries.add(new Country("105", "Asia", "Yemen", "Sana'a"));
		countries.add(new Country("106", "Europe", "Albania ", "Tirana"));
		countries.add(new Country("107", "Europe", "Andorra", "Andorra la Vella"));
		countries.add(new Country("108", "Europe", "Austria ", "Vienna"));
		countries.add(new Country("109", "Europe", "Belarus ", "Minsk"));
		countries.add(new Country("110", "Europe", "Belgium ", "Brussels"));
		countries.add(new Country("111", "Europe", "Bosnia and Herzegovina ", "Sarajevo"));
		countries.add(new Country("112", "Europe", "Bulgaria", "Sofia"));
		countries.add(new Country("113", "Europe", "Croatia", "Zagreb"));
		countries.add(new Country("114", "Europe", "Czech Republic ", "Prague"));
		countries.add(new Country("115", "Europe", "Denmark", "Copenhagen"));
		countries.add(new Country("116", "Europe", "Estonia", "Tallinn"));
		countries.add(new Country("117", "Europe", "Finland", "Helsinki"));
		countries.add(new Country("118", "Europe", "France", "Paris"));
		countries.add(new Country("119", "Europe", "Germany ", "Berlin"));
		countries.add(new Country("120", "Europe", "Greece", "Athens"));
		countries.add(new Country("121", "Europe", "Hungary ", "Budapest"));
		countries.add(new Country("122", "Europe", "Iceland", "Reykjavik"));
		countries.add(new Country("123", "Europe", "Republic of Ireland ", "Dublin"));
		countries.add(new Country("124", "Europe", "Italy ", "Rome"));
		countries.add(new Country("125", "Europe", "Kosovo", "Pristina"));
		countries.add(new Country("126", "Europe", "Latvia", "Riga"));
		countries.add(new Country("127", "Europe", "Liechtenstein", "Vaduz"));
		countries.add(new Country("128", "Europe", "Lithuania ", "Vilnius"));
		countries.add(new Country("129", "Europe", "Luxembourg", "Luxembourg City"));
		countries.add(new Country("130", "Europe", "Macedonia", "Skopje"));
		countries.add(new Country("131", "Europe", "Malta", "Valletta"));
		countries.add(new Country("132", "Europe", "Moldova", "Chisinau"));
		countries.add(new Country("133", "Europe", "Monaco", "Monte Carlo"));
		countries.add(new Country("134", "Europe", "Montenegro", "Podgorica"));
		countries.add(new Country("135", "Europe", "Netherlands ", "Amsterdam "));
		countries.add(new Country("136", "Europe", "Norway ", "Oslo"));
		countries.add(new Country("137", "Europe", "Poland", "Warsaw"));
		countries.add(new Country("138", "Europe", "Portugal", "Lisbon"));
		countries.add(new Country("139", "Europe", "Romania", "Bucharest"));
		countries.add(new Country("140", "Europe", "Russia", "Moscow"));
		countries.add(new Country("141", "Europe", "San Marino", "San Marino"));
		countries.add(new Country("142", "Europe", "Serbia ", "Belgrade"));
		countries.add(new Country("143", "Europe", "Slovakia", "Bratislava"));
		countries.add(new Country("144", "Europe", "Slovenia ", "Ljubljana"));
		countries.add(new Country("145", "Europe", "Spain", "Madrid"));
		countries.add(new Country("146", "Europe", "Sweden", "Stockholm"));
		countries.add(new Country("147", "Europe", "Switzerland", "Bern"));
		countries.add(new Country("148", "Europe", "Ukraine ", "Kyiv or Kiev"));
		countries.add(new Country("149", "Europe", "United Kingdom", "London"));
		countries.add(new Country("150", "Europe", "Vatican City", "Vatican City"));
		countries.add(new Country("151", "North America", "Antigua and Barbuda", "St. John's"));
		countries.add(new Country("152", "North America", "Anguilla", "The Valley"));
		countries.add(new Country("153", "North America", "Aruba", "Oranjestad"));
		countries.add(new Country("154", "North America", "The Bahamas", "Nassau"));
		countries.add(new Country("155", "North America", "Barbados", "Bridgetown"));
		countries.add(new Country("156", "North America", "Belize", "Belmopan"));
		countries.add(new Country("157", "North America", "Bermuda", "Hamilton"));
		countries.add(new Country("158", "North America", "Bonaire", "part of the Netherlands"));
		countries.add(new Country("159", "North America", "British Virgin Islands", "Road Town"));
		countries.add(new Country("160", "North America", "Canada", "Ottawa"));
		countries.add(new Country("161", "North America", "Cayman Islands", "George Town"));
		countries.add(new Country("162", "North America", "Costa Rica", "San José"));
		countries.add(new Country("163", "North America", "Cuba", "Havana"));
		countries.add(new Country("164", "North America", "Curaçao", "Willemstad"));
		countries.add(new Country("165", "North America", "Dominica", "Roseau"));
		countries.add(new Country("166", "North America", "Dominican Republic", "Santo Domingo"));
		countries.add(new Country("167", "North America", "El Salvador", "San Salvador"));
		countries.add(new Country("168", "North America", "Greenland", "Nuuk"));
		countries.add(new Country("169", "North America", "Grenada", "St George's"));
		countries.add(new Country("170", "North America", "Guatemala", "Guatemala"));
		countries.add(new Country("171", "North America", "Haiti", "Port-au-Prince"));
		countries.add(new Country("172", "North America", "Honduras", "Tegucigalpa"));
		countries.add(new Country("173", "North America", "Jamaica", "Kingston"));
		countries.add(new Country("174", "North America", "Martinique", "Fort-de-France Bay"));
		countries.add(new Country("175", "North America", "Mexico", "Mexico City"));
		countries.add(new Country("176", "North America", "Montserrat", "Plymouth, Brades, Little Bay "));
		countries.add(new Country("177", "North America", "Navassa Island", "Washinton, D.C. "));
		countries.add(new Country("178", "North America", "Nicaragua", "Managua"));
		countries.add(new Country("179", "North America", "Panama", "Panama City"));
		countries.add(new Country("180", "North America", "Puerto Rico", "San Juan"));
		countries.add(new Country("181", "North America", "Saba", "The Bottom"));
		countries.add(new Country("182", "North America", "Saint Barthelemy", "Gustavia"));
		countries.add(new Country("183", "North America", "Saint Kitts and Nevis", "Basseterre"));
		countries.add(new Country("184", "North America", "Saint Lucia", "Castries"));
		countries.add(new Country("185", "North America", "Saint Martin", "Marigot"));
		countries.add(new Country("186", "North America", "Saint Pierre and Miquelon", "Saint-Pierre"));
		countries.add(new Country("187", "North America", "Saint Vincent and the Grenadines", "Kingstown"));
		countries.add(new Country("188", "North America", "Sint Eustatius", "Oranjestad"));
		countries.add(new Country("189", "North America", "Sint Maarten", "Philipsburg"));
		countries.add(new Country("190", "North America", "Trinidad and Tobago", "Port of Spain"));
		countries.add(new Country("191", "North America", "Turks and Caicos", "Cockburn Town"));
		countries.add(new Country("192", "North America", "United States of America", "Washington, D.C."));
		countries.add(new Country("193", "North America", "US Virgin Islands", "Charlotte Amalie "));
		countries.add(new Country("194", "South America", "Argentina", "Buenos Aires"));
		countries.add(new Country("195", "South America", "Bolivia", "Sucré"));
		countries.add(new Country("196", "South America", "Brazil", "Brasília"));
		countries.add(new Country("197", "South America", "Chile", "Santiago"));
		countries.add(new Country("198", "South America", "Colombia", "Bogotá"));
		countries.add(new Country("199", "South America", "Ecuador", "Quito"));
		countries.add(new Country("200", "South America", "Falkland Islands", "Stanley"));
		countries.add(new Country("201", "South America", "French Guiana", "Cayenne"));
		countries.add(new Country("202", "South America", "Guyana", "Georgetown"));
		countries.add(new Country("203", "South America", "Paraguay", "Asunción"));
		countries.add(new Country("204", "South America", "Peru", "Lima"));
		countries.add(new Country("205", "South America", "Suriname", "Paramaribo"));
		countries.add(new Country("206", "South America", "Uruguay", "Montevideo"));
		countries.add(new Country("207", "South America", "Venezuela", "Caracas"));
		countries.add(new Country("208", "Oceania", "Australia", "Canberra"));
		countries.add(new Country("209", "Oceania", "Federated States of Micronesia", "Palikir"));
		countries.add(new Country("210", "Oceania", "Fiji", "suva"));
		countries.add(new Country("211", "Oceania", "Kiribati", "South Tarawa"));
		countries.add(new Country("212", "Oceania", "Marshall Islands", "Majuro"));
		countries.add(new Country("213", "Oceania", "Nauru", "Yaren"));
		countries.add(new Country("214", "Oceania", "New Zealand", "Wellington"));
		countries.add(new Country("215", "Oceania", "Palau", "Melekeok"));
		countries.add(new Country("216", "Oceania", "Papua New Guinea", "Port Moresby"));
		countries.add(new Country("217", "Oceania", "Samoa", "Apia"));
		countries.add(new Country("218", "Oceania", "Solomon Islands", "Honiara"));
		countries.add(new Country("219", "Oceania", "Tonga", "Nuku'alofa"));
		countries.add(new Country("220", "Oceania", "Tuvalu", "Funafuti"));
		countries.add(new Country("221", "Oceania", "Vanuatu", "Port Vila"));

		results.setCountryList(countries);

		return results;

	}
}
