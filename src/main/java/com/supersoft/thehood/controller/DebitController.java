package com.supersoft.thehood.controller;

import javax.persistence.Query;

import com.supersoft.thehood.dto.DebitDTO;
import com.supersoft.thehood.hibernate.entity.Debit;
import com.supersoft.thehood.hibernate.entity.Hood;
import com.supersoft.thehood.hibernate.entity.House;
import com.supersoft.thehood.hibernate.util.HibernateUtil;

import org.hibernate.Transaction;
import org.hibernate.Session;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/")
public class DebitController {

    @PostMapping("newDebit")
    public Debit newDebit(@RequestBody DebitDTO debit) {

        House parentHouse;
        Debit newDebit = new Debit(debit);
        Transaction tran = null;

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            Query query = session.createQuery("from House H where H.houseId = :houseId");
            query.setParameter("houseId", debit.getHouseId());
            parentHouse = (House)query.getSingleResult();

            parentHouse.getDebits().add(newDebit);
			session.saveOrUpdate(parentHouse);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
        return newDebit;
    }

    @PostMapping("newHoodDebit")
    public Debit newHoodDebit(@RequestBody DebitDTO debit) {

        Hood parentHood;
        House parentHouse;
        Debit newDebit = new Debit(debit);
        Transaction tran = null;
        Query query;

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            query = session.createQuery("from House H where H.houseId = :houseId");
            query.setParameter("houseId", debit.getHouseId());
            parentHouse = (House)query.getSingleResult();

            query = session.createQuery("from Hood H where H.hoodId = :hoodId");
            query.setParameter("hoodId", parentHouse.geth());
            parentHouse = (House)query.getSingleResult();

            parentHouse.getDebits().add(newDebit);
			session.saveOrUpdate(parentHouse);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
        return newDebit;
    }
    
}
