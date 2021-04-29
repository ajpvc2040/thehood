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
@CrossOrigin(origins = "*")
@RequestMapping("api/")
public class BuddyController {

    @GetMapping("buddies")
    public List<BuddyDTO> getBuddies(@RequestParam int houseId) {

        Transaction tran = null;
        List<BuddyDTO> returnableList = new ArrayList<BuddyDTO>();
        List<Buddy> buddies = new ArrayList<Buddy>();

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tran = session.beginTransaction();

            Query query = session.createQuery("from Buddy B where B.houseId = :houseId");
            query.setParameter("houseId", houseId);
            buddies = query.getResultList();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Buddy buddy : buddies)
            returnableList.add(new BuddyDTO(buddy));

        return returnableList;
    }

    @PostMapping("newBuddy")
    public BuddyDTO newBuddy(@RequestBody BuddyDTO buddy) {

        House parentHouse;
        Buddy newBuddy = new Buddy(buddy);
        Transaction tran = null;

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            parentHouse = session.get(House.class, buddy.getHouseId());

            parentHouse.addBuddy(newBuddy);
			session.saveOrUpdate(parentHouse);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
        return new BuddyDTO(newBuddy);
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
