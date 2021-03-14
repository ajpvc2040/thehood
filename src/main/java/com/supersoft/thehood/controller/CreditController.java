package com.supersoft.thehood.controller;

import javax.persistence.Query;

import com.supersoft.thehood.dto.CreditDTO;
import com.supersoft.thehood.dto.DebitDTO;
import com.supersoft.thehood.hibernate.entity.Credit;
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
public class CreditController {

    @PostMapping("newCredit")
    public Credit newCredit(@RequestBody CreditDTO credit) {

        Hood parentHood;
        Credit newCredit = new Credit(credit);
        Transaction tran = null;
        Query query;

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            query = session.createQuery("from Hood H where H.hoodId = :hoodId");
            query.setParameter("hoodId", credit.getHoodId());
            parentHood = (Hood) query.getSingleResult();

            for(House house : parentHood.getHouses())
                if(house.getHouseId() == credit.getHouseId()){
                    house.addCredit(newCredit);
                    parentHood.addBank(newCredit);
                }                    

            session.saveOrUpdate(parentHood);
            
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
        return newCredit;
    }
    
}
