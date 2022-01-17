package country.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import country.model.Continent;
import country.model.Country;
@Repository("hibernate")
public class CountryDAOImp implements CountryDAO{
	@Autowired
    SessionFactory  sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public int add(Country country,String nameOfContinet) {
		country.setContinent(getByName(nameOfContinet));
        if(country.getContinent()==null) {
        	System.err.println("there is no continent with this name");
        return -1;
        }
        else {
        
        	Query query=getSession().createSQLQuery("INSERT INTO country(name, code, devise, greetings,continent_id) VALUES(:name, :code, :devise,:greeting,:continent);");
            query.setParameter("name",country.getName()).setParameter("code",country.getCode()).setParameter("devise",country.getDevise()).setParameter("greeting",country.getGreetings()).setParameter("continent",country.getContinent());
           return query.executeUpdate();
        	
	}
	}
	@Override
	public Continent getByName(String name) {
		Query query=getSession().createQuery("from Continent C where C.name =:name");
		query.setParameter("name", name);
		return (Continent) query.uniqueResult();
	}

   private Session getSession() {
	  return sessionFactory.getCurrentSession();
   }
}
