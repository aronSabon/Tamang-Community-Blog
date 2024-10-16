package com.appsoft.tmgsamaj.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.tmgsamaj.model.Event;
import com.appsoft.tmgsamaj.repository.EventRepository;
import com.appsoft.tmgsamaj.service.EventService;



@Service
public class EventServiceImpl implements EventService {
	@Autowired
	EventRepository eventRepository;
		@Override
		public void addEvent(Event event) {
			// TODO Auto-generated method stub
			eventRepository.save(event);
		}

		@Override
		public List<Event> getAllEvent() {
			// TODO Auto-generated method stub
			return eventRepository.findAll();
		}

		@Override
		public void deleteEventById(int id) {
			// TODO Auto-generated method stub
			eventRepository.deleteById(id);
		}

		@Override
		public Event getEventById(int id) {
			// TODO Auto-generated method stub
			return eventRepository.findById(id).get();
		}

		@Override
		public void updateEvent(Event event) {
			// TODO Auto-generated method stub
			eventRepository.save(event);
		}

}
