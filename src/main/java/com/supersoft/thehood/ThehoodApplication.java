package com.supersoft.thehood;

import com.supersoft.thehood.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThehoodApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ThehoodApplication.class, args);		
	}

	@Override
	public void run(String... args) throws Exception {
		Transaction tran = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			tran = session.beginTransaction();

			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
	}

}
