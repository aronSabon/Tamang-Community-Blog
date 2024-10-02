package com.skytest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skytest.constants.EventType;
import com.skytest.model.Member;
import com.skytest.service.MemberService;


@Controller
public class GalleryController {
	@Autowired
	MemberService memberService;

	@GetMapping("/gallery")
	public String getgallery(Model model,@RequestParam(defaultValue = "1") int page) {

		List<Member> members=memberService.getAllMember();
		List<String> memberImages = new ArrayList<>();

		for(Member m : members) {
			if(m.getImageName()!= null) {
			memberImages.add(m.getImageName());
			}
		}
		model.addAttribute("memberImages", memberImages);
		System.out.println(memberImages);	
		List<EventType> eventTypeList = Arrays.asList(EventType.values());
		model.addAttribute("eventType", eventTypeList);
		
		
		//for pagination
		
	    int pageSize = 6;  // Only show 6 images per page

	    // Retrieve all images (replace this with your own logic)
//	    List<String> allImages = getAllImagesFromDatabase();
	    
	    // Calculate the start and end indexes for the current page
//	    int start = (page - 1) * pageSize;
//	    int end = Math.min(start + pageSize, memberImages.size());

	    // Calculate the start and end indexes for the current page
	    int totalImages = memberImages.size();  // Total number of images
	    int start = (page - 1) * pageSize;
	    
	    // Ensure the start index is not greater than the number of available images
	    if (start >= totalImages) {
	        start = Math.max(0, totalImages - pageSize);  // Move to the last page if page out of bounds
	    }

	    int end = Math.min(start + pageSize, totalImages);

	    // Sublist for the current page
	    List<String> paginatedImages = memberImages.subList(start, end);

	    // Total number of pages
	    int totalPages = (int) Math.ceil((double) memberImages.size() / pageSize);

	    model.addAttribute("memberImages", paginatedImages);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", totalPages);
		return "Gallery";
	}

}
