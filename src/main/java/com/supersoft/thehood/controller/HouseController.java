package com.supersoft.thehood.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import com.supersoft.thehood.dto.HouseDTO;
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
public class HouseController{

    @GetMapping("houses")
    public List<House> getHouses(@RequestParam int hoodId) {

        Transaction tran = null;
        Hood parentHood = new Hood();
        List<House> returnableList = new ArrayList<House>();

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tran = session.beginTransaction();

            Query query = session.createQuery("from Hood H where H.hoodId = :hoodID");
            query.setParameter("hoodID", hoodId);
            parentHood = (Hood)query.getSingleResult();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(House house : parentHood.getHouses())
            returnableList.add(house);

        return returnableList;
    }

    @PostMapping("newHouse")
    public House newHouse(@RequestBody HouseDTO house) {

        Hood parentHood;
        House newHouse = new House(house);
        Transaction tran = null;

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            Query query = session.createQuery("from Hood H where H.hoodId = :hoodID");
            query.setParameter("hoodID", house.getHoodId());
            parentHood = (Hood)query.getSingleResult();

            parentHood.getHouses().add(newHouse);
			session.saveOrUpdate(parentHood);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
        return newHouse;
    }

    @DeleteMapping("deleteHouse")
    public void deleteHood(@RequestBody HouseDTO house){
        House deleteHouse = new House(house);
        Transaction tran = null;

        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();
			session.delete(deleteHouse);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}

    }

}