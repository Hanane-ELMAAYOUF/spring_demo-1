package country.dao;

import org.springframework.transaction.annotation.Transactional;

import country.model.Continent;
import country.model.Country;
@Transactional
public interface CountryDAO {
	void add(Country country,String nameOfContinet);
	Continent getByName(String name);
	Country getByCode(String code);
}
