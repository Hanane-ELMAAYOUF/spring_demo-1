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
		
		String hql="from Continent C where C.name =:name";
		Query query=getSessionFactory().openSession().createQuery(hql);
		query.setParameter("name", name);
		return (Continent) query.uniqueResult();
	}

@Override
	
	public Country getByCode(String code) {
		String hql="from Country C where C.code =:code";
		Query query=getSessionFactory().openSession().createQuery(hql);
		query.setParameter("code", code);
		Country country=(Country) query.uniqueResult();
        return country;
	}
private Session getSession() {
	return sessionFactory.getCurrentSession();
}

}
