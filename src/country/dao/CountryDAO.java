package country.dao;

import java.util.List;

import country.model.Continent;
import country.model.Country;

public interface CountryDAO {
	void add(Country country,String nameOfContinet);
	Continent getByName(String name);
	Country getByCode(String code);
	void deleteByCode(String code);
	void updateByCode(Country country,String code,String nameOfContinet);
	Continent getContinentByCode(String code); 
	List<Country> getCountrieByCode(String code);
}
