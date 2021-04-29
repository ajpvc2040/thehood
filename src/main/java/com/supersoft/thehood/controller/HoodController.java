package com.supersoft.thehood.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.supersoft.thehood.dto.HoodDTO;
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
@CrossOrigin(origins = "*")
@RequestMapping("api/")
public class HoodController{

    @GetMapping("hoods")
    public List<HoodDTO> getHoods() {

        List<Hood> hoods = new ArrayList<Hood>();
        List<HoodDTO> returnableHoods = new ArrayList<HoodDTO>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            hoods = session.createQuery("from Hood", Hood.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Hood h : hoods)
            returnableHoods.add(new HoodDTO(h));

        return returnableHoods;
    }

    @GetMapping("hood")
    public HoodDTO getHood(@RequestParam int hoodId) {

        Transaction tran = null;
        Hood hood = new Hood();

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tran = session.beginTransaction();

            Query query = session.createQuery("from Hood H where H.hoodId = :hoodId");
            query.setParameter("hoodId", hoodId);
            hood = (Hood)query.getSingleResult();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new HoodDTO(hood);
    }

    @PostMapping("newHood")
    public HoodDTO newHood(@RequestBody HoodDTO hood) {

        Hood newHood = new Hood(hood);
        Transaction tran = null;

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();
			session.saveOrUpdate(newHood);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
        return new HoodDTO(newHood);
    }

    @DeleteMapping("deleteHood")
    public void deleteHood(@RequestBody HoodDTO hood){
        Hood deleteHood = new Hood(hood);
        Transaction tran = null;

        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();
			session.delete(deleteHood);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}

    }
}