/**
 * 
 */
package com.psp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psp.enums.AdminStatus;
import com.psp.model.Events;

/**
 * @author us
 * 
 */
@Repository
public interface EventRepository extends JpaRepository<Events, Long>, JpaSpecificationExecutor<Events> {

	List<Events> findByEventCategory_EventCategoryId(Long categoryId);

	@Query("select eventSlug from Events")
	List<String> getAllSlug();

	List<Events> findByEventSlug(String eventSlug);

	List<Events> findByEventStatusAndIsFeatured(AdminStatus eventStatus, Boolean isFeatured);

	List<Events> findByCreatedBy(String createdBy);

	@Query("select totalSeat from Events where eventId = :eventId")
	Integer getTotalSeatByEventId(@Param("eventId") Long eventId);
}
