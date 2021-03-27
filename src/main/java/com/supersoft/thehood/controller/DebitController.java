package com.supersoft.thehood.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.supersoft.thehood.dto.DebitDTO;
import com.supersoft.thehood.hibernate.entity.Credit;
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
    public DebitDTO newDebit(@RequestBody DebitDTO debit) {

        List<House> housesToCharge = new ArrayList<House>();
        Debit newDebit = new Debit(debit);
        Transaction tran = null;
        Query query;

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            if(debit.getHouseId() > 0){
                House temp = (House) session.get(House.class, debit.getHouseId());
                housesToCharge.add(temp);
            }
            else{
                query = session.createQuery("from House H where H.hoodId = :hoodId");
                query.setParameter("hoodId", debit.getHoodId());
                housesToCharge = query.getResultList();
            }
            
            for(House house : housesToCharge){
                house.addDebit(new Debit(newDebit));
                session.saveOrUpdate(house);
            }

			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
        return new DebitDTO(newDebit);
    }

    @GetMapping("unpaidDebits")
    public List<DebitDTO> getUnpaidDebits(@RequestParam int houseId) {

        Transaction tran = null;
        List<DebitDTO> returnableList = new ArrayList<DebitDTO>();
        List<Debit> unpaidDebits = new ArrayList<Debit>();
        Query query;

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tran = session.beginTransaction();

            query = session.createQuery(
                " from " +
                "     Debit D " +
                " where " + 
                "     D.houseId = :houseId and " +
                "     D.paid = :paid ");

            query.setParameter("houseId", houseId);
            query.setParameter("paid", false);

            unpaidDebits = query.getResultList();

            for(Debit temp : unpaidDebits)
                temp.loadLazyCredits();

            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Debit retDebit : unpaidDebits)
            returnableList.add(new DebitDTO(retDebit));

        return returnableList;
    }
    
}
