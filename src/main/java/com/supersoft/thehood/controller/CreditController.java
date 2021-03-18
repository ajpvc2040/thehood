package com.supersoft.thehood.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.supersoft.thehood.dto.CreditDTO;
import com.supersoft.thehood.dto.DebitDTO;
import com.supersoft.thehood.hibernate.entity.Bank;
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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/")
public class CreditController {

    @GetMapping("credits")
    public List<Credit> getCredits(@RequestParam int houseId) {

        Transaction tran = null;
        House parentHouse = new House();
        List<Credit> returnableList = new ArrayList<Credit>();

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tran = session.beginTransaction();

            Query query = session.createQuery("from House H where H.houseId = :houseId");
            query.setParameter("houseId", houseId);
            parentHouse = (House)query.getSingleResult();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Credit credit : parentHouse.getCredits())
            returnableList.add(credit);

        return returnableList;
    }

    @PostMapping("newCredit")
    public Credit newCredit(@RequestBody CreditDTO credit) {

        Hood parentHood = new Hood();
        House parentHouse = new House();
        Credit newCredit = new Credit(credit);
        Debit debitPaid = new Debit();
        Double alreadyPaid = 0.0;
        Transaction tran = null;
        Query query;

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            query = session.createQuery("from Hood H where H.hoodId = :hoodId");
            query.setParameter("hoodId", credit.getHoodId());
            parentHood = (Hood) query.getSingleResult();

            for(House house : parentHood.getHouses())
                if(house.getHouseId() == credit.getHouseId()){
                    parentHouse = house;
                    break;
                }

            for(Debit debit : parentHouse.getDebits())
                if(debit.getDebitId() == credit.getDebitId()){
                    debitPaid = debit;
                    break;
                }
            
            for(Credit creditAux : parentHouse.getCredits())
                if(creditAux.getDebitId() == credit.getDebitId())
                    alreadyPaid += creditAux.getAmount();

            if(alreadyPaid + credit.getAmount() > debitPaid.getAmount())
                throw new Exception("amount is greater than debit");
            else if (alreadyPaid + credit.getAmount() == debitPaid.getAmount())
                debitPaid.setPaid(true);

            parentHouse.addCredit(newCredit);

            session.saveOrUpdate(parentHood);
            tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}

        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
            tran = session.beginTransaction();
            parentHood.addBank(newCredit);
            session.saveOrUpdate(parentHood);
            tran.commit();
        }
        catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
        return new Credit(credit);
    }

    @DeleteMapping("deleteCredit")
    public void deleteCredit(@RequestBody CreditDTO credit){
        Hood parentHood = new Hood();
        House parentHouse = new House();
        Credit deleteCredit = new Credit(credit);
        Bank deleteBank = new Bank();
        Transaction tran = null;
        Query query;

        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();
            query = session.createQuery("from Hood H where H.hoodId = :hoodId");
            query.setParameter("hoodId", credit.getHoodId());
            parentHood = (Hood) query.getSingleResult();

            for(House house : parentHood.getHouses())
                if(house.getHouseId() == credit.getHouseId()){
                    parentHouse = house;
                    break;
                }
            
            for(Bank bank : parentHood.getBankEntries())
                if(bank.getCreditId() == credit.getCreditId()){
                    deleteBank = bank;
                    break;
                }

            for(Debit debit : parentHouse.getDebits())
                if(debit.getDebitId() == deleteCredit.getDebitId()){
                    debit.setPaid(false);
                    break;
                }
            
            parentHood.setBalance(parentHood.getBalance() - credit.getAmount());
            parentHouse.setBalance(parentHouse.getBalance() - credit.getAmount());

			session.saveOrUpdate(parentHood);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}

        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();
            session.delete(deleteBank);
            session.delete(deleteCredit);
            tran.commit();
        }
        catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}

    }
    
}
