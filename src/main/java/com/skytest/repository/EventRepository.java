package com.skytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skytest.model.Event;


public interface EventRepository extends JpaRepository<Event, Integer> {

}
