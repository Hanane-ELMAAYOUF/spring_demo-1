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

}          