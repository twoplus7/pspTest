package com.psp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psp.model.EventCategory;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
	List<EventCategory> findByEventCategoryName(String name);
}
