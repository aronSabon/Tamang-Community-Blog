package com.appsoft.tmgsamaj.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appsoft.tmgsamaj.model.Event;
import com.appsoft.tmgsamaj.constants.EventStatus;
import com.appsoft.tmgsamaj.constants.EventType;



public interface EventRepository extends JpaRepository<Event, Integer> {
	List<Event> findByStatus(EventStatus status);
	List<Event> findBytype(EventType type);

	@Query(value="SELECT * FROM EVENT WHERE STATUS = 'upcomming' ORDER BY DATE ASC LIMIT 3",nativeQuery = true)
	List<Event> findClosestEventsByStatus();
	
//	@Query("SELECT e FROM Event e WHERE e.status = :status AND e.date >= :today AND e.type != :excludedType ORDER BY e.date ASC")
//	List<Event> findClosestEventsByStatus(
//			@Param("status") EventStatus status, 
//			@Param("today") LocalDate today, 
//		    @Param("excludedType") EventType excludedType, 
//			Pageable pageable);
}
