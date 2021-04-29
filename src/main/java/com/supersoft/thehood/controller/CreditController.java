package com.supersoft.thehood.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.supersoft.thehood.dto.CreditDTO;
import com.supersoft.thehood.hibernate.entity.Credit;
import com.supersoft.thehood.hibernate.entity.Debit;
import com.supersoft.thehood.hibernate.entity.Hood;
import com.supersoft.thehood.hibernate.entity.House;
import com.supersoft.thehood.hibernate.util.HibernateUtil;

import org.hibernate.Transaction;
import org.hibernate.Session;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/")
public class CreditController {

    @GetMapping("credits")
    public List<CreditDTO> getCredits(@RequestParam int houseId) {

        Transaction tran = null;
        List<CreditDTO> returnableList = new ArrayList<CreditDTO>();
        List<Credit> creditList = new ArrayList<Credit>();

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tran = session.beginTransaction();

            Query query = session.createQuery(
                " from " +
                "     Credit C " +
                " where " +
                "     C.houseId = :houseId ");
            
            query.setParameter("houseId", houseId);
            
            creditList = query.getResultList();

            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Credit credit : creditList)
            returnableList.add(new CreditDTO(credit));

        return returnableList;
    }

    @PostMapping("newCredit")
    public CreditDTO newCredit(@RequestBody CreditDTO credit) {

        Credit newCredit = new Credit(credit);

        Hood parentHood = new Hood();
        House parentHouse = new House();
        Debit parentDebit = new Debit();

        Transaction tran = null;

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            parentHood = session.get(Hood.class, credit.getHoodId());
            parentHouse = session.get(House.class, credit.getHouseId());
            parentDebit = session.get(Debit.class, credit.getDebitId());

            parentDebit.loadLazyCredits();

            if(credit.getAmount() > parentDebit.getUnpaidAmount())
                throw new Exception("amount is greater than debit");
            else if (credit.getAmount() == parentDebit.getUnpaidAmount())
                parentDebit.setPaid(true);

            session.persist(newCredit);

            parentDebit.addCredit(newCredit);
            session.persist(parentDebit);

            parentHouse.setBalance(parentHouse.getBalance() + newCredit.getAmount());
            session.persist(parentHouse);

            parentHood.setBalance(parentHood.getBalance() + newCredit.getAmount());
            session.persist(parentHood);
            session.flush();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}

        return new CreditDTO(newCredit);
    }

    @DeleteMapping("deleteCredit")
    public void deleteCredit(@RequestBody CreditDTO credit){

        Hood parentHood = new Hood();
        House parentHouse = new House();
        Debit parentDebit = new Debit();
        Credit deleteCredit = new Credit();

        Transaction tran = null;

        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            parentHood = session.get(Hood.class, credit.getHoodId());
            parentHouse = session.get(House.class, credit.getHouseId());
            parentDebit = session.get(Debit.class, credit.getDebitId());
            deleteCredit = session.get(Credit.class, credit.getCreditId());

            deleteCredit.loadLazyBank();

            parentDebit.setPaid(false);            
            parentHood.setBalance(parentHood.getBalance() - credit.getAmount());
            parentHouse.setBalance(parentHouse.getBalance() - credit.getAmount());

            session.saveOrUpdate(parentDebit);
            session.saveOrUpdate(parentHouse);
            session.saveOrUpdate(parentHood);

            session.delete(deleteCredit);

			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
    }
    
}
