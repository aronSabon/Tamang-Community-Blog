package com.appsoft.tmgsamaj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontEndController {
	@GetMapping("/fHome")
	private String getHome() {
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
	private String getEvent() {
		return"frontend/Event";
	}
	@GetMapping("/fExecutive")
	private String getExecutiveMember() {
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
