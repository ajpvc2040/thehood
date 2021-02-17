package com.supersoft.thehood;

import java.util.List;

import com.supersoft.thehood.hibernate.entity.Hood;
import com.supersoft.thehood.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThehoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThehoodApplication.class, args);

		Hood cerrada = new Hood("Primero");
		Transaction tran = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			tran = session.beginTransaction();
			session.save(cerrada);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}

		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			List<Hood> hoods = session.createQuery("from Hood", Hood.class).list();
			hoods.forEach(s->System.err.println(s.getName()));
		}catch(Exception e){
				e.printStackTrace();
		}

	}

}
