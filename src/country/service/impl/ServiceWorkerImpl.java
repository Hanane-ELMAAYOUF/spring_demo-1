package country.service.impl;

import country.dao.CountryDAO;
import country.model.Country;
import country.service.IServiceWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ServiceWorkerImpl implements IServiceWorker {
	@Autowired
	@Qualifier("hibernate")
	private CountryDAO countryDAO;
	
	@Override
	public void InsertCountry(Country country,String nameOfContinet) {
		int rowsAffected=countryDAO.add(country,nameOfContinet);
		if (rowsAffected > 0) {
    	    System.out.println("Inserted " + rowsAffected + " rows.");
    	}
    	else
    		System.err.println("Insertion not successful");
	}

	@Override
	public void selectCountry(String code) {
		Country country=countryDAO.getByCode(code);
		if(country==null) System.err.println("no country with this code ");
		else {
		System.out.println("Name :"+country.getName());
		System.out.println("Code :"+country.getCode());
		System.out.println("Devise :"+country.getDevise());
		System.out.println("Greeting :"+country.getGreetings());
		System.out.println("Continent :"+country.getContinent().getName());
		}
	}
	 
	                

	

}
