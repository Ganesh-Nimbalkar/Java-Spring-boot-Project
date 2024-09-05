package com.miniproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.dao.HelpRepository;
import com.miniproject.model.Help;

@Service
public class HelpService {
    @Autowired
    private HelpRepository helpRepository;

    public List<Help> getAllHelps() {
        return helpRepository.findAll();
    }

    public Help getHelp(Long id) {
        Optional<Help> helpOptional = helpRepository.findById(id);
        return helpOptional.orElse(null);
    }

    public void deleteHelp(Long id) {
        helpRepository.deleteById(id);
    }

    public Help updateHelp(Long id, Help updatedHelp) {
        Help existingHelp = helpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Help not found with id: " + id));

        existingHelp.setFirstname(updatedHelp.getFirstname());
        existingHelp.setLastname(updatedHelp.getLastname());
        existingHelp.setContactnumber(updatedHelp.getContactnumber());
        existingHelp.setEmail(updatedHelp.getEmail());
        existingHelp.setMessage(updatedHelp.getMessage());

        return helpRepository.save(existingHelp);
    }

    public Help saveHelp(Help help) {
        Help existingHelp = helpRepository.findByEmail(help.getEmail());
        if (existingHelp != null) {
            throw new RuntimeException("Help with the same email already exists");
        } else {
            return helpRepository.save(help);
        }
    }
}

