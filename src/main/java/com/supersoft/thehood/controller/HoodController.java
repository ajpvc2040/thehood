package com.supersoft.thehood.controller;

import java.util.ArrayList;
import java.util.List;

import com.supersoft.thehood.hibernate.entity.Hood;
import com.supersoft.thehood.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class HoodController{

    @GetMapping("hoods")
    public List<Hood> getHoods(){

        List<Hood> hoods = new ArrayList<Hood>();

		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			hoods = session.createQuery("from Hood", Hood.class).list();
		}
		catch(Exception e){
			e.printStackTrace();
		}

        return hoods;
    }
}