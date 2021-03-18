package com.supersoft.thehood.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.supersoft.thehood.dto.DebitDTO;
import com.supersoft.thehood.hibernate.entity.Debit;
import com.supersoft.thehood.hibernate.entity.Hood;
import com.supersoft.thehood.hibernate.entity.House;
import com.supersoft.thehood.hibernate.util.HibernateUtil;

import org.hibernate.Transaction;
import org.hibernate.Session;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/")
public class DebitController {

    @PostMapping("newDebit")
    public Debit newDebit(@RequestBody DebitDTO debit) {

        Hood parentHood;
        House parentHouse;
        Debit newDebit = new Debit(debit);
        Transaction tran = null;
        Query query;

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            if(debit.getHouseId() > 0){
                query = session.createQuery("from House H where H.houseId = :houseId");
                query.setParameter("houseId", debit.getHouseId());
                parentHouse = (House) query.getSingleResult();
                parentHouse.addDebit(newDebit);
                session.saveOrUpdate(parentHouse);
            }
            else{
                query = session.createQuery("from Hood H where H.hoodId = :hoodId");
                query.setParameter("hoodId", debit.getHoodId());
                parentHood = (Hood) query.getSingleResult();
                for(House house : parentHood.getHouses())
                    house.addDebit(new Debit(newDebit));
                session.saveOrUpdate(parentHood);
            }
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
        return newDebit;
    }

    @GetMapping("unpaidDebits")
    public List<Debit> getUnpaidDebits(@RequestParam int houseId) {

        Transaction tran = null;
        House parentHouse = new House();
        List<Debit> returnableList = new ArrayList<Debit>();

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tran = session.beginTransaction();

            Query query = session.createQuery("from House H where H.houseId = :houseId");
            query.setParameter("houseId", houseId);
            parentHouse = (House)query.getSingleResult();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Debit debit : parentHouse.getDebits())
            if(!debit.isPaid())
                returnableList.add(debit);

        return returnableList;
    }
    
}
