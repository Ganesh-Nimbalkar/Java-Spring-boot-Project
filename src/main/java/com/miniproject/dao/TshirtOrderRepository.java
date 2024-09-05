package com.miniproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.model.TshirtOrder;

@Repository
public interface TshirtOrderRepository extends JpaRepository<TshirtOrder, Long> {
	TshirtOrder findByIdAndFullname(Long id, String fullname);
}
