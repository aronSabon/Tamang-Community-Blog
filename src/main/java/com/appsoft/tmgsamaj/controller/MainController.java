package com.appsoft.tmgsamaj.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.appsoft.tmgsamaj.constants.NotificationStatus;
import com.appsoft.tmgsamaj.model.Notification;
import com.appsoft.tmgsamaj.model.User;
import com.appsoft.tmgsamaj.repository.UserRepository;
import com.appsoft.tmgsamaj.service.NotificationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	NotificationService notificationService;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

//	@GetMapping({"/","dashboard"})
	@GetMapping("/dashboard")

    public String homePage(HttpServletRequest request,Model model) {
    	  HttpSession session = request.getSession(false);
    	    if (session != null) {
    	        System.out.println("Session ID: " + session.getId());
    	    }
			/*
			 * List<Notification> notificationList =
			 * notificationService.getAllNotification().stream().filter(x -> x.getStatus()
			 * == NotificationStatus.UNSEEN).collect(Collectors.toList());
			 * System.out.println(notificationList);
			 * model.addAttribute("notificationList",notificationList);
			 */
    	      List<Notification> notificationList = notificationService.getAllNotification()
    	              .stream()
    	              .filter(x -> x.getStatus() == NotificationStatus.UNSEEN)
    	              .collect(Collectors.toList());
    	          model.addAttribute("notificationList", notificationList);
    	    session.setAttribute("notificationList", notificationList);
    	    
        return "AdminDashboard";
    }
	@GetMapping ("/")
	public String frontend() {
		return"redirect:/fHome";
	}

	@GetMapping("/login")
    public String loginPage() {
        return "Login"; 
	}
    
    @GetMapping("/register")
    public String registerPage(Model model) {
        return "Register";
    }
    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
    	user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }

}
