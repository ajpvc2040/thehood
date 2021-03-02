package com.supersoft.thehood.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.supersoft.thehood.dto.BuddyDTO;
import com.supersoft.thehood.hibernate.entity.Buddy;
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
public class BuddyController {

    @GetMapping("buddies")
    public List<Buddy> getBuddies(@RequestParam int houseId) {

        Transaction tran = null;
        House parentHouse = new House();
        List<Buddy> returnableList = new ArrayList<Buddy>();

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tran = session.beginTransaction();

            Query query = session.createQuery("from House H where H.houseId = :houseId");
            query.setParameter("houseId", houseId);
            parentHouse = (House)query.getSingleResult();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Buddy buddy : parentHouse.getBuddies())
            returnableList.add(buddy);

        return returnableList;
    }

    @PostMapping("newBuddy")
    public Buddy newBuddy(@RequestBody BuddyDTO buddy) {

        House parentHouse;
        Buddy newBuddy = new Buddy(buddy);
        Transaction tran = null;

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            Query query = session.createQuery("from House H where H.houseId = :houseId");
            query.setParameter("houseId", buddy.getHouseId());
            parentHouse = (House)query.getSingleResult();

            parentHouse.getBuddies().add(newBuddy);
			session.saveOrUpdate(parentHouse);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
        return newBuddy;
    }

    @DeleteMapping("deleteBuddy")
    public void deleteBuddy(@RequestBody BuddyDTO buddy){
        Buddy deleteBuddy = new Buddy(buddy);
        Transaction tran = null;

        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();
			session.delete(deleteBuddy);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}

    }
    
}
