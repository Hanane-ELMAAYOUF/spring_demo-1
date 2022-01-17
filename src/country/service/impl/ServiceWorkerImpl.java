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

	@Override
	public void deleteCountry(String code) {
		int rowsAffected=countryDAO.deleteByCode(code);
		if (rowsAffected > 0) {
		    System.out.println("Deleted " + rowsAffected + " rows.");
		}
		else
			System.err.println("delete not successful");
	}

	@Override
	public void updateCountry(Country country,String code,String nameOfContinet) {
		int rowsAffected=countryDAO.updateByCode(country, code,nameOfContinet);
		 if (rowsAffected > 0) 
			    System.out.println("Updates " + rowsAffected + " rows.");
			else
				System.err.println("update not successful");
		    	}
	}
	 
	/*if(getByCode(country.getCode())==null)               
	
    else 
    	System.err.println("this code already exists");
    
    
    if(getByCode(country.getCode())==null)
   
    	 else 
         	System.err.println("this code already exists");
	
*/

