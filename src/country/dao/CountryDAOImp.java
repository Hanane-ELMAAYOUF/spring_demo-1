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

	public void add(Country c,String nameOfContinet) {
		c.setContinent(getByName(nameOfContinet));
        if(c.getContinent()==null)
        	System.err.println("there is no continent with this name");
        else {
        // save() and persist() don't work ---> to review
        Connection connection=null;
       try {
        connection=dataSource.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Country(name, code, devise, greetings,continent_id) VALUES(?,?, ?,?,?);");
		preparedStatement.setString(1, c.getName());
		preparedStatement.setString(2, c.getCode());
		preparedStatement.setString(3, c.getDevise());
		preparedStatement.setString(4, c.getGreetings());
		preparedStatement.setInt(5, c.getContinent().getId());
		int successfulInd = preparedStatement.executeUpdate();
       
           System.out.println("Insertion successful");
            
        } catch (SQLException exception) {
        	System.err.println("Insertion not successful");
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

	

}
