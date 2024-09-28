package com.skytest.service;

import java.util.List;

import com.skytest.model.Event;



public interface EventService {
	void addEvent(Event event);
	List<Event> getAllEvent();
	void deleteEventById(int id);
	Event getEventById(int id);
	void updateEvent(Event event);

}
