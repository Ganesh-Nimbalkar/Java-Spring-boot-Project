package com.miniproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.model.Help;

@Repository
public interface HelpRepository extends JpaRepository<Help, Long> {
    Help findByEmail(String email);
}
