package com.miniproject.service;

import com.miniproject.dao.LoginRepository;
import com.miniproject.dao.UserRepository;
import com.miniproject.model.Login;
import com.miniproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

	
	  @Autowired
	    private LoginRepository loginRepo;
	  @Autowired
	  private UserRepository userRepo;

	  
	    
	    
//	    public Login findByUsername(String username) {
//	        return userRepo.findByUsername(username);
//	    }
	    
	  
	  public List<User> getAllUsr(){
		  return userRepo.findAll();
		  }
	    
	    public List<Login> getAllData(){
	    	return loginRepo.findAll();
	    }
}
