package tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import country.dao.CountryDAO;
import country.model.Continent;
import country.model.Country;

@Transactional

public class TestApp {
@Autowired
CountryDAO countryDAO;
	@Test
	public void addCountry() {
		Continent continent=new Continent();
		continent.setCode("am");
		continent.setName("america");
	   Country country=new Country();
	   country.setName("Maroc");
	   country.setCode("ar");
	   country.setDevise("DH");
	   country.setGreetings("salam");
	   country.setContinent(continent);
	   int c=countryDAO.add(country, continent.getName());
	   assertNotEquals(c,0);
	}
	@Test
	public void SelectCountryByCode(){
		assertThat(countryDAO.getByCode("fr")).isNotNull();
		
	}
    @Test
    public void deleteCountryByCode() {
	int c=countryDAO.deleteByCode("fr");
	assertNotEquals(c,0);
    }
	@Test
	public void updateCountryByCode() {
	   Country country=new Country();
	   country.setName("Maroc");
	   country.setCode("ar");
	   country.setDevise("DH");
	   country.setGreetings("salam");
	   int c=countryDAO.updateByCode(country, "fr", "Europe");
	   assertNotEquals(c,0);
	}
    @Test
    public void selectCountryOfContinent(){
	assertThat(countryDAO.getCountrieByCode("eu")).isNotNull();
    }
}
