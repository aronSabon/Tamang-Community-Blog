package com.skytest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@PostMapping("/updateEvent")
	private String updateEvent(@ModelAttribute Event event) {
		event.setStatus(eventService.getEventById(event.getId()).getStatus());
		eventService.updateEvent(event);
		return "redirect:/eventList";
	}
	@GetMapping("/viewEvent")
	private String viewEvent(Model model,@RequestParam int id) {
		model.addAttribute("eventModel",eventService.getEventById(id));
		return"ViewEvent";
	}
	@GetMapping("/addEventImage")
	private String addEventImage(Model model,@RequestParam int id) {
		Event event =eventService.getEventById(id);
		model.addAttribute("eventModel", event);
		model.addAttribute("eventId", event.getId());
		return"AddEventImage";
	}
	@PostMapping("/addEventImage")
	private String postaddEventImage(Model model,@RequestParam int eventId,@RequestParam List<MultipartFile>eventImages, RedirectAttributes redirectAttribute) {
		Event event = eventService.getEventById(eventId);
		  if (event.getImageNames() == null) {
		        event.setImageNames(new ArrayList<>());
		    }
		
		int imageNumber = event.getImageNames().size()+1;
		if(eventImages!=null && !eventImages.isEmpty()) {
				for (int i = 0; i < eventImages.size(); i++) {
					MultipartFile eventImage = eventImages.get(i); // Get the corresponding image for this family member

					if (!eventImages.isEmpty()) {

						try {
		                    String imageName = event.getTitle() + imageNumber + ".jpg";

							event.addImageName(imageName);
							eventService.updateEvent(event);

							Files.copy(eventImages.get(i).getInputStream(), 
									Path.of("src/main/resources/static/eventImages/"+event.getTitle()+ imageNumber +".jpg"), 
									StandardCopyOption.REPLACE_EXISTING);
							imageNumber++;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			else {
				// Handle the case where the number of family members doesn't match the number of images
				// Maybe return an error or log the issue
				System.err.println("No Images");
			}
		
		
		redirectAttribute.addAttribute("id", eventId);
		System.out.println(event);
		return"redirect:addEventImage";
	}

}
