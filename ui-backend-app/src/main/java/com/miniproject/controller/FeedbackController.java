package com.miniproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.miniproject.model.Feedback;
import com.miniproject.service.FeedbackService;

@RestController
@CrossOrigin("*")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    
    @PostMapping("/save-feedback")
    public Feedback saveFeedback(@RequestBody Feedback feedback) {
        return feedbackService.saveFeedback(feedback);
    }

    @GetMapping("/get-all-feedbacks")
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/get-feedback")
    public Feedback getFeedback(@RequestParam("id") Long id) {
        return feedbackService.getFeedback(id);
    }

    @DeleteMapping("/delete-feedback/{id}")
    public void deleteFeedback(@PathVariable("id") Long id) {
        feedbackService.deleteFeedback(id);
    }
    
    @PutMapping("/update-feedback/{id}")
    public Feedback updateFeedback(@PathVariable("id") Long id, @RequestBody Feedback updatedFeedback) {
        return feedbackService.updateFeedback(id, updatedFeedback);
    }
}
