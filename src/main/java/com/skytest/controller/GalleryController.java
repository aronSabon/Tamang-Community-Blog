package com.skytest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skytest.constants.EventType;
import com.skytest.model.CommitteeMember;
import com.skytest.model.Event;
import com.skytest.model.Member;
import com.skytest.service.EventService;
import com.skytest.service.MemberService;


@Controller
public class GalleryController {
	@Autowired
	MemberService memberService;
	@Autowired
	EventService eventService;

	@GetMapping("/gallery")
	public String getgallery(Model model){
		
		//for static viewing the member images
//		List<Member> members=memberService.getAllMember();
//		List<String> memberImages = new ArrayList<>();
//
//		for(Member m : members) {
//			if(m.getImageName()!= null) {
//				memberImages.add(m.getImageName());
//			}
//		}
//		model.addAttribute("memberImageList", memberImages);
//		System.out.println(memberImages);	
		List<EventType> eventTypeList = Arrays.asList(EventType.values());
		model.addAttribute("eventType", eventTypeList);

		return "Gallery";
	}

	@GetMapping("/gallery/{type}")
	@ResponseBody
	public List<String> getEventImagesByEventType(@PathVariable String type,Model model) {
		if(type.equals("Member")) {
			List<Member> members=memberService.getAllMember();
			List<String> memberImages = new ArrayList<>();
			for(Member m : members) {
				if(m.getImageName()!= null) {
					memberImages.add(m.getImageName());
				}
			}
			return memberImages;
		}
		List<Event> eventList = eventService.getAllEvent();
		List<Event> eventByType = eventList.stream()
				.filter(x -> x.getType().equals(EventType.valueOf(type)))
				.collect(Collectors.toList());

		List<String> imageNames = new ArrayList<>();
		for(Event e : eventByType) {
			List<String> eventImageNames = e.getImageNames();
			for(String s : eventImageNames) {
				imageNames.add(s);
			}
		}
		return imageNames;

	}
	@DeleteMapping("gallery/{type}/{imageName}")
	@ResponseBody
	public ResponseEntity<String> delete(@PathVariable String imageName , @PathVariable String type) {
		if(type.equals("Member")) {
			List<Member> memberList = memberService.getAllMember();
			boolean imageDeletedd=false;

			for(Member m : memberList) {
				if(m.getImageName() != null && m.getImageName().equals(imageName)) {
					m.setImageName(null);
					memberService.updateMember(m);


					String imagePath = "src/main/resources/static/memberImages/" + imageName;
					System.out.println("Attempting to delete file at: " + imagePath);
					Path path = Paths.get(imagePath);

					if (Files.exists(path)) {
						System.out.println("File exists, attempting to delete: " + path.toAbsolutePath());
					} else {
						System.out.println("File does not exist: " + path.toAbsolutePath());
					}


					// Remove the image file from the folder
					try {
						Files.deleteIfExists(Paths.get(imagePath));
						imageDeletedd = true;
		                break; // Break after deleting the first matched image


					} catch (IOException ex) {
						ex.printStackTrace();
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete image");

					}
				}
				
			}
			if (imageDeletedd) {
				return ResponseEntity.ok("Image deleted successfully");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
			}	
		}
		else {


			List<Event> eventList = eventService.getAllEvent();
			List<Event> eventByType = eventList.stream()
					.filter(x -> x.getType().equals(EventType.valueOf(type)))
					.collect(Collectors.toList());
			boolean imageDeleted=false;

			//cant use advanced for loop to remove a item from the list so use iterator
			//		 for(Event e : eventByType) {
			//				List<String> eventImageNames = e.getImageNames();
			//				for(String s : eventImageNames) {
			//					if(s.equals(imageName)) {
			//						e.getImageNames().remove(imageName);
			//						eventService.updateEvent(e);

			for(Event e : eventByType) {
				List<String> eventImageNames = e.getImageNames();
				// Using an iterator to safely remove items while iterating
				Iterator<String> iterator = eventImageNames.iterator();
				while (iterator.hasNext()) {
					String s = iterator.next();
					if(s.equals(imageName)) {
						// Safely remove the image name from the list using the iterator
						iterator.remove();
						eventService.updateEvent(e);
						// Path to the images folder (adjust according to your server setup)
						String imagePath = "src/main/resources/static/eventImages/" + imageName;
						System.out.println("Attempting to delete file at: " + imagePath);
						Path path = Paths.get(imagePath);

						if (Files.exists(path)) {
							System.out.println("File exists, attempting to delete: " + path.toAbsolutePath());
						} else {
							System.out.println("File does not exist: " + path.toAbsolutePath());
						}


						// Remove the image file from the folder
						try {
							Files.deleteIfExists(Paths.get(imagePath));
							imageDeleted = true;
			                break; // Break after deleting the first matched image


						} catch (IOException ex) {
							ex.printStackTrace();
							return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete image");

							// Handle the error (e.g., add an error message to the model)
						}
					}
				}

			}
			if (imageDeleted) {
				return ResponseEntity.ok("Image deleted successfully");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
			}	
		}
	}
}
