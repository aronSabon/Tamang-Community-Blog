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
	public String getgallery(Model model){

		List<Member> members=memberService.getAllMember();
		List<String> memberImages = new ArrayList<>();

		for(Member m : members) {
			if(m.getImageName()!= null) {
			memberImages.add(m.getImageName());
			}
		}
		model.addAttribute("memberImageList", memberImages);
		System.out.println(memberImages);	
		List<EventType> eventTypeList = Arrays.asList(EventType.values());
		model.addAttribute("eventType", eventTypeList);
	
		return "Gallery";
	}

}
