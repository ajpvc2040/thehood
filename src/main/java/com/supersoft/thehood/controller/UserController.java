package com.supersoft.thehood.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.supersoft.thehood.dto.UserDTO;
import com.supersoft.thehood.hibernate.entity.Hood;
import com.supersoft.thehood.hibernate.entity.User;
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
public class UserController {

    @GetMapping("users")
    public List<UserDTO> getUsers(@RequestParam int hoodId) {

        Transaction tran = null;
        List<User> users = new ArrayList<User>();
        List<UserDTO> returnableList = new ArrayList<UserDTO>();

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            tran = session.beginTransaction();

            Query query = session.createQuery("from House H where H.hoodId = :hoodID");
            
            query.setParameter("hoodID", hoodId);
            users = query.getResultList();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(User user : users)
            returnableList.add(new UserDTO(user));

        return returnableList;
    }

    @PostMapping("newUser")
    public UserDTO newUser(@RequestBody UserDTO user) throws Exception {

        Hood parentHood = null;
        User newUser = new User(user);
        Transaction tran = null;

        if(!newUser.validateUser().equals("OK"))
            throw new Exception(newUser.validateUser());
        if(user.getHoodId() == 0)
            throw new Exception("Please select a Hood.");

		try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();

            parentHood = session.get(Hood.class, user.getHoodId());

            parentHood.addUser(newUser);

			session.saveOrUpdate(parentHood);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}
        return new UserDTO(newUser);
    }

    @DeleteMapping("deleteUser")
    public void deleteUser(@RequestBody UserDTO user){
        User deleteUser = new User(user);
        Transaction tran = null;

        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
			tran = session.beginTransaction();
			session.delete(deleteUser);
			tran.commit();
		}
		catch(Exception e){
			if(tran != null) tran.rollback();
			e.printStackTrace();
		}

    }
    
}
