package com.miniproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	//Feedback findByUsernameAndPassword(String username, String password);
    // Define custom query methods if needed

	Feedback findByEmail(String email);

}
