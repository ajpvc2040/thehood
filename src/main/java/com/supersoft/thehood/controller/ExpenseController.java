package com.supersoft.thehood.controller;

import javax.persistence.Query;

import com.supersoft.thehood.dto.ExpenseDTO;
import com.supersoft.thehood.hibernate.entity.Expense;
import com.supersoft.thehood.hibernate.entity.Hood;
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
public class ExpenseController {

    @PostMapping("newExpense")
    public Expense newExpense(@RequestBody ExpenseDTO expense) {

        Hood hood;
        Expense newExpense = new Expense(expense);
        Transaction tran = null;
        Query query;

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            query = session.createQuery("from Hood H where H.hoodId = :hoodId");
            query.setParameter("hoodId", expense.getHoodId());
            hood = (Hood) query.getSingleResult();
            hood.addExpense(newExpense);
            session.saveOrUpdate(hood);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
        return newExpense;
    }

    
}
