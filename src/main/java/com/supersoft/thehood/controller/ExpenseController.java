package com.supersoft.thehood.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.supersoft.thehood.dto.ExpenseDTO;
import com.supersoft.thehood.hibernate.entity.Expense;
import com.supersoft.thehood.hibernate.entity.Hood;
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
public class ExpenseController {

    @PostMapping("newExpense")
    public Expense newExpense(@RequestBody ExpenseDTO expense) {

        Hood parentHood;
        Expense newExpense = new Expense(expense);
        Transaction tran = null;

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            parentHood = (Hood) session.get(Hood.class, expense.getHoodId());
            parentHood.addExpense(newExpense);

            session.saveOrUpdate(parentHood);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
        return newExpense;
    }

    @GetMapping("expenses")
    public List<ExpenseDTO> getExpenses(@RequestParam int hoodId) {

        Transaction tran = null;
        
        List<Expense> expensesList = new ArrayList<Expense>();
        List<ExpenseDTO> returnableList = new ArrayList<ExpenseDTO>();

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tran = session.beginTransaction();

            Query query = session.createQuery(
                "select E " + 
                "from " + 
                "   Expense E " +
                "where " +
                "   E.hoodId = :hoodId " +
                "order by " + 
                "   E.expenseId desc").setMaxResults(10);
            query.setParameter("hoodId", hoodId);

            expensesList = query.getResultList();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Expense expense : expensesList)
            returnableList.add(new ExpenseDTO(expense));

        return returnableList;
    }

    @DeleteMapping("deleteExpense")
    public void deleteExpense(@RequestBody ExpenseDTO expense){
        Hood parentHood = new Hood();
        Expense deleteExpense = new Expense(expense);
        Transaction tran = null;

        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            parentHood = (Hood) session.get(Hood.class, expense.getHoodId());
            parentHood.removeExpense(deleteExpense);

            session.saveOrUpdate(parentHood);
			session.delete(deleteExpense);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}

    }
    
}
