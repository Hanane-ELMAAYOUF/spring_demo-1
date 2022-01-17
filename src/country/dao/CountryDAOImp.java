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
        if(country.getContinent()==null) 
             return -1;
        else if(getByCode(country.getCode())!=null)
        	return -2;
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

@Override
	public Country getByCode(String code) {
		Query query=getSession().createQuery("from Country C where C.code =:code");
		query.setParameter("code", code);
		Country country=(Country) query.uniqueResult();
        return country;
	}
@Override
public int deleteByCode(String code) {
	Query query = getSession().createQuery("delete from Country where code = :code");
	query.setParameter("code",code);
	return query.executeUpdate();
}

@Override

public int updateByCode(Country country,String code,String nameOfContinet) {
	country.setContinent(getByName(nameOfContinet));
    if(country.getContinent()==null) {
    	
    return -1;
    }
    else if(getByCode(country.getCode())!=null)
    	return -2;
    else {
	Query query = getSession().createQuery("update Country c set c.name=:name,c.code=:code,c.devise=:devise,c.greetings=:greeting,c.continent=:continent where c.code=:codeToUpdate");
	 query.setParameter("name",country.getName())
	      .setParameter("code",country.getCode())
	      .setParameter("devise",country.getDevise())
	      .setParameter("greeting",country.getGreetings())
	      .setParameter("continent",country.getContinent())
	      .setParameter("codeToUpdate", code);
	  return query.executeUpdate();
	
}
}

   private Session getSession() {
	  return sessionFactory.getCurrentSession();
   }
}
