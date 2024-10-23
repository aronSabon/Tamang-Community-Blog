package com.appsoft.tmgsamaj.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appsoft.tmgsamaj.constants.NotificationStatus;
import com.appsoft.tmgsamaj.model.Notification;
import com.appsoft.tmgsamaj.service.NotificationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class NotificationController {
	@Autowired
	NotificationService notificationService;
	
	@GetMapping("/notificationList")
	public String getNotificationList(Model model,HttpServletRequest request) {
		model.addAttribute("notificationListAll", notificationService.getAllNotification());
		 HttpSession session = request.getSession(false);
		 System.out.println(session.getAttribute("notificationList"));
 	    
 	   	return"Notification";
				}

	@GetMapping("/deleteNotification")
	public String getMethodName(@RequestParam int id) {
	  
		notificationService.deleteNotificationById(id);
		
		return "redirect:/notificationList";
	}
	
	/*
	 * @GetMapping("/clearAll")
	 * 
	 * public String markallseen() { List<Notification>
	 * notificationList=notificationService.getAllNotification().stream().filter(x->
	 * x.getStatus()==NotificationStatus.UNSEEN).collect(Collectors.toList());
	 * for(Notification n : notificationList) {
	 * n.setStatus(NotificationStatus.SEEN);
	 * notificationService.updateNotification(n); } return "redirect:/dashboard"; }
	 * 
	 * @GetMapping("/clear/{id}") public String markseen(@PathVariable int id) {
	 * Notification notification = notificationService.getNotificationById(id);
	 * notification.setStatus(NotificationStatus.SEEN);
	 * notificationService.updateNotification(notification); return
	 * "redirect:/dashboard"; }
	 */
	
	/* this allows the model to be accessible in all the pages */
	/*
	 * @ModelAttribute public void addNotifications(Model model) {
	 * System.out.println("not check"); List<Notification> notificationList =
	 * notificationService.getAllNotification() .stream() .filter(x -> x.getStatus()
	 * == NotificationStatus.UNSEEN) .collect(Collectors.toList());
	 * model.addAttribute("notificationList", notificationList); }
	 */
	
	@GetMapping("/clearAll")
	@ResponseBody // This indicates that the response will be the body content, not a view
	public ResponseEntity<String> markallseen() {
	    List<Notification> notificationList = notificationService.getAllNotification()
	        .stream()
	        .filter(x -> x.getStatus() == NotificationStatus.UNSEEN)
	        .collect(Collectors.toList());

	    for (Notification n : notificationList) {
	        n.setStatus(NotificationStatus.SEEN);
	        notificationService.updateNotification(n);
	    }

	    return ResponseEntity.ok("All notifications marked as seen."); // Return a success message
	}

	@GetMapping("/clear/{id}")
	@ResponseBody
	public ResponseEntity<String> markseen(@PathVariable int id) {
	    Notification notification = notificationService.getNotificationById(id);
	    notification.setStatus(NotificationStatus.SEEN);
	    notificationService.updateNotification(notification);
	    
	    return ResponseEntity.ok("Notification marked as seen."); // Return a success message
	}

	
}
