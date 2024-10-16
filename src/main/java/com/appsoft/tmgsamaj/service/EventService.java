package com.appsoft.tmgsamaj.service;

import java.util.List;

import com.appsoft.tmgsamaj.model.Event;



public interface EventService {
	void addEvent(Event event);
	List<Event> getAllEvent();
	void deleteEventById(int id);
	Event getEventById(int id);
	void updateEvent(Event event);

}
