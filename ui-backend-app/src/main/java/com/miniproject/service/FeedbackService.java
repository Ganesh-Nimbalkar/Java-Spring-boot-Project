package com.miniproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.dao.FeedbackRepository;
import com.miniproject.model.Feedback;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
    
    public Feedback getFeedback(Long id) {
        Optional<Feedback> feedbackOptional = feedbackRepository.findById(id);
        return feedbackOptional.orElse(null);
    }
    
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
    
    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {
        Feedback existingFeedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found with id: " + id));

        existingFeedback.setFirstname(updatedFeedback.getFirstname());
        existingFeedback.setLastname(updatedFeedback.getLastname());
        existingFeedback.setContactnumber(updatedFeedback.getContactnumber());
        existingFeedback.setEmail(updatedFeedback.getEmail());
        existingFeedback.setMessage(updatedFeedback.getMessage());
        
        return feedbackRepository.save(existingFeedback);
    }
    
    public Feedback saveFeedback(Feedback feedback) {
        Feedback existingFeedback = feedbackRepository.findByEmail(feedback.getEmail());
        if (existingFeedback != null) {
            throw new RuntimeException("Feedback with the same email already exists");
        } else {
            return feedbackRepository.save(feedback);
        }
    }
}
