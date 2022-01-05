package country.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import country.dao.CountryDAO;
import country.model.Country;
import country.service.IServiceWorker;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	@Qualifier("hibernate")
	private CountryDAO countryDAO;
	
	@Override
	public void InsertCountry(Country country,String nameOfContinet) {
		countryDAO.add(country,nameOfContinet);
	}

	@Override
	public void selectCountry(String code) {
		Country country=countryDAO.getByCode(code);
		if(country==null) System.err.println("no country with this code ");
		else {
	    System.out.println("Id :"+country.getId());
		System.out.println("Name :"+country.getName());
		System.out.println("Code :"+country.getCode());
		System.out.println("Devise :"+country.getDevise());
		System.out.println("Greeting :"+country.getGreetings());
		System.out.println("Continent :"+country.getContinent().getName());
		}
	}

	@Override
	public void deleteCountry(String code) {
		countryDAO.deleteByCode(code);
		
	}

	@Override
	public void updateCountry(Country country,String code,String nameOfContinet) {
		countryDAO.updateByCode(country, code,nameOfContinet);
		
	}

	@Override
	public void selectCountriesOfContinent(String code) {
		List<Country> countries=countryDAO.getCountrieByCode(code);
		if(countries.size()==0) 
			System.err.println("No country in this continent");
		else {
		for(Country country:countries) {
			System.out.println("Id :"+country.getId());
			System.out.println("Name :"+country.getName());
			System.out.println("Code :"+country.getCode());
			System.out.println("Devise :"+country.getDevise());
			System.out.println("Greeting :"+country.getGreetings());
			System.out.println("---------------------------------");
		}
		}	
	}
	 
	                

	

}
