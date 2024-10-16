package com.appsoft.tmgsamaj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.tmgsamaj.model.Event;


public interface EventRepository extends JpaRepository<Event, Integer> {

}
