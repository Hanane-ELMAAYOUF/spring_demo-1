package country.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import country.model.Continent;
import country.model.Country;
@Repository("hibernate")
public class CountryDAOImp implements CountryDAO{
	@Autowired
    SessionFactory  sessionFactory;
	@Autowired
	DataSource dataSource;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override

	public void add(Country country,String nameOfContinet) {
		country.setContinent(getByName(nameOfContinet));
        if(country.getContinent()==null)
        	System.err.println("there is no continent with this name");
        else {
        
        	Session session=getSessionFactory().openSession();
        	Transaction txn = session.beginTransaction();
            Query query=session.createSQLQuery("INSERT INTO country(name, code, devise, greetings,continent_id) VALUES(:name, :code, :devise,:greeting,:continent);");
            query.setParameter("name",country.getName()).setParameter("code",country.getCode()).setParameter("devise",country.getDevise()).setParameter("greeting",country.getGreetings()).setParameter("continent",country.getContinent());
            int rowsAffected = query.executeUpdate();
        	txn.commit();
        	if (rowsAffected > 0) {
        	    System.out.println("Inserted " + rowsAffected + " rows.");
        	}
       
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

}
