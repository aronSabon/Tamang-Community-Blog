package com.appsoft.tmgsamaj.controller;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.appsoft.tmgsamaj.constants.EventStatus;
import com.appsoft.tmgsamaj.constants.EventType;
import com.appsoft.tmgsamaj.model.CommitteeMember;
import com.appsoft.tmgsamaj.repository.CondolenceRepository;
import com.appsoft.tmgsamaj.repository.EventRepository;
import com.appsoft.tmgsamaj.service.CommitteeMemberService;
import com.appsoft.tmgsamaj.service.DonorService;
import com.appsoft.tmgsamaj.service.EventService;

@Controller
//@RequestMapping("/user")

public class FrontEndController {
	@Autowired
	EventRepository eventRepo;
	@Autowired
	DonorService donorService;
	@Autowired 
	CondolenceRepository condolenceRepo;
	@Autowired
	CommitteeMemberService committeeMemberService;
	
	
	@GetMapping("/fHome")
	private String getHome(Model model) {
		model.addAttribute("upcommingEventList" ,eventRepo.findClosestEventsByStatus());
		model.addAttribute("donorList",donorService.getAllDonor());
		model.addAttribute("condolenceList", condolenceRepo.findRecentCondolenceByStatus());
		return"frontend/index";
	}
	@GetMapping("/fAbout")
	private String getabout() {
		return"frontend/about";
	}
	@GetMapping("/fContact")
	private String getContact() {
		return"frontend/contact";
	}
	@GetMapping("/fEvent")
	private String getEvent(Model model) {
		model.addAttribute("socialList",eventRepo.findBytype(EventType.Social));
		model.addAttribute("musicList",eventRepo.findBytype(EventType.Music));

		return"frontend/Event";
	}
	@GetMapping("/fExecutive")
	private String getExecutiveMember(Model model) {
		model.addAttribute("committeePresidentList", committeeMemberService.getAllCommitteMember()
				.stream()
				.filter(x ->x.getDesignation()
				.equals("President"))
			    .sorted(Comparator.comparing(CommitteeMember::getActiveFrom).reversed()) // Sort by activeFrom in descending order
				.collect(Collectors.toList()));
		return"frontend/executive-member";
	}
	@GetMapping("/fNews")
	private String getNews() {
		return"frontend/news";
	}
	@GetMapping("/fGallery")
	private String getGallery() {
		return"frontend/gallery";
	}
	

}
