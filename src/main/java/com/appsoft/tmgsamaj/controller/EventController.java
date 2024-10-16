package com.appsoft.tmgsamaj.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appsoft.tmgsamaj.constants.EventStatus;
import com.appsoft.tmgsamaj.constants.EventType;
import com.appsoft.tmgsamaj.model.Event;
import com.appsoft.tmgsamaj.model.Guest;
import com.appsoft.tmgsamaj.service.EventService;

@Controller
public class EventController {
	@Autowired
	EventService eventService;
	

	@GetMapping("/event")
	private String getEvent(Model model) {
		
	    List<EventType> eventTypeList = Arrays.asList(EventType.values());
		model.addAttribute("eventType", eventTypeList);
		System.out.println(eventTypeList);
		return"AddEvent";
	}
	@PostMapping("/event")
	private String postEvent(@ModelAttribute Event event) {
		System.out.println(event.getType());

		if(event.getGuest()!=null) {
			List<Guest> validGuests = event.getGuest().stream()
					.filter(guest -> guest.getFirstName() != null && !guest.getFirstName().isEmpty())
					.collect(Collectors.toList());

			event.setGuest(validGuests);
		}
		event.setStatus(EventStatus.UPCOMMING);
		eventService.addEvent(event);
		System.out.println(event.getType());
		return"redirect:/event";
	}
	@GetMapping("/eventList")
	private String getEventList(Model model) {
		model.addAttribute("eventList",eventService.getAllEvent());
		return"EventList";
	}
	@GetMapping("/editEvent")
	private String editEvent(Model model,@RequestParam int id) {
	    List<EventType> eventTypeList = Arrays.asList(EventType.values());
		model.addAttribute("eventType", eventTypeList);
		model.addAttribute("eventModel",eventService.getEventById(id));
		return"EditEvent";
	}
	@PostMapping("/updateEvent")
	private String updateEvent(@ModelAttribute Event event) {
		event.setImageNames(eventService.getEventById(event.getId()).getImageNames());
		event.setStatus(eventService.getEventById(event.getId()).getStatus());
		eventService.updateEvent(event);
		return "redirect:/eventList";
	}
	@GetMapping("/deleteEvent")
	private String deleteEvent(Model model,@RequestParam int id) {
	eventService.deleteEventById(id);
		return"redirect:/eventList";
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


		if(eventImages!=null && !eventImages.isEmpty()) {
				for (int i = 0; i < eventImages.size(); i++) {
					MultipartFile eventImage = eventImages.get(i); // Get the corresponding image for this family member

					if (!eventImages.isEmpty()) {

						try {
		                    String imageName = eventImage.getOriginalFilename();

							event.addImageName(imageName);
							eventService.updateEvent(event);

							Files.copy(eventImages.get(i).getInputStream(), 
									Path.of("src/main/resources/static/eventImages/"+eventImage.getOriginalFilename()), 
									StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e) {
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
	 @PostMapping("/deleteImage/{eventId}")
	    public String deleteImage(@PathVariable int eventId, @RequestParam String imageName, Model model , RedirectAttributes redirectAttribute) {
	        // Path to the images folder (adjust according to your server setup)
	        String imagePath = "src/main/resources/static/eventImages/" + imageName;

	        // Remove the image file from the folder
	        try {
	            Files.deleteIfExists(Paths.get(imagePath));
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Handle the error (e.g., add an error message to the model)
	        }

	        // Update the event model by removing the image from the list (if you store images in the DB)
	        Event event = eventService.getEventById(eventId);
	        event.getImageNames().remove(imageName);
	        eventService.updateEvent(event); // Save the event after removing the image
	        redirectAttribute.addAttribute("id",eventId);
	        return "redirect:/viewEvent"; // Redirect to the event details page
	    }

}
