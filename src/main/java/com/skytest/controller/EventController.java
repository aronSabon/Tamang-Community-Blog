package com.skytest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skytest.constants.EventStatus;
import com.skytest.model.Event;
import com.skytest.model.Family;
import com.skytest.model.Guest;
import com.skytest.service.EventService;

@Controller
public class EventController {
	@Autowired
	EventService eventService;

	@GetMapping("/event")
	private String getEvent() {
		return"AddEvent";
	}
	@PostMapping("/event")
	private String postEvent(@ModelAttribute Event event) {
		if(event.getGuest()!=null) {
			List<Guest> validGuests = event.getGuest().stream()
					.filter(guest -> guest.getFirstName() != null && !guest.getFirstName().isEmpty())
					.collect(Collectors.toList());

			event.setGuest(validGuests);
		}
		event.setStatus(EventStatus.UPCOMMING);
		eventService.addEvent(event);
		return"redirect:/event";
	}
	@GetMapping("/eventList")
	private String getEventList(Model model) {
		model.addAttribute("eventList",eventService.getAllEvent());
		return"EventList";
	}
	@GetMapping("/editEvent")
	private String editEvent(Model model,@RequestParam int id) {
		model.addAttribute("eventModel",eventService.getEventById(id));
		return"EditEvent";
	}

}
