package com.appsoft.tmgsamaj.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.appsoft.tmgsamaj.constants.EventStatus;
import com.appsoft.tmgsamaj.constants.EventType;
import com.appsoft.tmgsamaj.model.CommitteeMember;
import com.appsoft.tmgsamaj.model.Event;
import com.appsoft.tmgsamaj.model.Member;
import com.appsoft.tmgsamaj.repository.CondolenceRepository;
import com.appsoft.tmgsamaj.repository.EventRepository;
import com.appsoft.tmgsamaj.service.CommitteeMemberService;
import com.appsoft.tmgsamaj.service.DonorService;
import com.appsoft.tmgsamaj.service.EventService;
import com.appsoft.tmgsamaj.service.MemberService;

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
	@Autowired
	MemberService memberService;
	@Autowired
	EventService eventService;
	
	
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
	private String getGallery(Model model) {
		List<Member> members=memberService.getAllMember();
		List<String> memberImages = new ArrayList<>();
		for(Member m : members) {
			if(m.getImageName()!= null) {
				memberImages.add(m.getImageName());
			}
		}
		model.addAttribute("memberImages",memberImages);
		
//	    List<Member> members = memberService.getAllMember();
//	    List<String> memberImages = members.stream()
//	            .map(Member::getImageName)
//	            .filter(Objects::nonNull)
//	            .collect(Collectors.toList());
//	    model.addAttribute("memberImages", memberImages);
		
		List<Event> eventList = eventService.getAllEvent();
		List<Event> eventByType = eventList.stream()
				.filter(x -> x.getType().equals(EventType.Festival))
				.collect(Collectors.toList());
		List<String> festivalImages = new ArrayList<>();
		for(Event e : eventByType) {
			List<String> eventImageNames = e.getImageNames();
			for(String s : eventImageNames) {
				festivalImages.add(s);
			}
		}
		model.addAttribute("festivalImages",festivalImages);
		
		    List<String> charityImages = eventList.stream()
		            .filter(event -> event.getType().equals(EventType.Charity))
		            .flatMap(event -> event.getImageNames().stream())
		            .collect(Collectors.toList());
		    model.addAttribute("charityImages", charityImages);
		
		
		
		return"frontend/gallery";

	}
	

}
