package country.service;

import country.model.Country;

public interface IServiceWorker {
	void InsertCountry(Country country,String nameOfContinet);
	void selectCountry(String code);
}
