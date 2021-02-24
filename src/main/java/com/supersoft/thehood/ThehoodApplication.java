package com.supersoft.thehood;

import java.util.Calendar;
import java.util.List;

import com.supersoft.thehood.hibernate.entity.Bank;
import com.supersoft.thehood.hibernate.entity.Buddy;
import com.supersoft.thehood.hibernate.entity.Credit;
import com.supersoft.thehood.hibernate.entity.Debit;
import com.supersoft.thehood.hibernate.entity.Expense;
import com.supersoft.thehood.hibernate.entity.Hood;
import com.supersoft.thehood.hibernate.entity.House;
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
		Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);

		Hood cerrada = new Hood("Primero");
		House micasa = new House("16", 0);
		Buddy yomero = new Buddy("Art", "sdaf", "sadf");
		Debit febrero2021 = new Debit("Febrero 2021", today.getTime(), 400);
		Credit cfebrero2021 = new Credit("Febrero 2021", today.getTime(), 350);
		Bank bankEntry = new Bank("Febrero 2021", today.getTime(), 350, cfebrero2021);
		Expense gasto = new Expense("Don Manuel", today.getTime(), 800);

		cerrada.getExpenses().add(gasto);
		cerrada.getBankEntries().add(bankEntry);
		cerrada.getHouses().add(micasa);

		micasa.getBuddies().add(yomero);
		micasa.getCredits().add(cfebrero2021);
		micasa.getDebits().add(febrero2021);
		

		Transaction tran = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			tran = session.beginTransaction();
			session.saveOrUpdate(cerrada);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			List<Hood> hoods = session.createQuery("from Hood", Hood.class).list();
			hoods.forEach(s->System.err.println(s.toString()));
		}catch(Exception e){
				e.printStackTrace();	
		}


	}

}
