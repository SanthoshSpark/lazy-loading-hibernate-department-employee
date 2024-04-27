package com.hibernate.demo;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App 
{
    public static void main( String[] args )
    {

    	SessionFactory sf = null;
    	Session s = null;
    	Transaction tx = null;
    	
    	try {
			sf = new Configuration().configure().buildSessionFactory();
			s  = sf.openSession();
			tx = s.beginTransaction();
			
//			Department d1 = new Department("Software Team");
//			
//			Employee e1 = new Employee("Sreenu",d1);
//			Employee e2 = new Employee("Guna",d1);
//			
//			s.save(d1);
//			
//			s.save(e1);
//			s.save(e2); 
			
			Department d = s.get(Department.class, 1L);
			
			List<Employee> e = d.getEmployee();
			
			for(Employee employee : e ) {
				System.out.println(employee);
			}
		} 
    	catch (HibernateException e) {
			if(tx != null )
				tx.rollback();
			e.printStackTrace();
		}
    	catch (Exception e) {
    		if(tx != null )
				tx.rollback();
			e.printStackTrace();
		}
        finally {
        	if(tx != null )
				tx.commit();
			if(s != null)
				s.close();
			if(sf != null)
				sf.close();
		}
    	
    }
}
