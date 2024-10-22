package com.appsoft.tmgsamaj.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.tmgsamaj.constants.NotificationStatus;
import com.appsoft.tmgsamaj.model.Notification;
import com.appsoft.tmgsamaj.service.NotificationService;

@Controller
public class NotificationController {
	@Autowired
	NotificationService notificationService;
	
	@GetMapping("/notificationList")
	public String getNotificationList(Model model) {
		model.addAttribute("notificationListAll", notificationService.getAllNotification());
		return"Notification";
				}

	@GetMapping("/deleteNotification")
	public String getMethodName(@RequestParam int id) {
		String imageName= notificationService.getNotificationById(id).getImageName();
	    String imagePath = "src/main/resources/static/eventImages/" + imageName;

        // Remove the image file from the folder
        try {
            Files.deleteIfExists(Paths.get(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error (e.g., add an error message to the model)
        }
		notificationService.deleteNotificationById(id);
		
		return "redirect:/notificationList";
	}
	
	@GetMapping("/clearAll")
	
	public String markallseen() {
		List<Notification> notificationList=notificationService.getAllNotification().stream().filter(x-> x.getStatus()==NotificationStatus.UNSEEN).collect(Collectors.toList());
		for(Notification n : notificationList) {
			n.setStatus(NotificationStatus.SEEN);
			notificationService.updateNotification(n);
		}
		return "redirect:/dashboard";
	}
	
	@GetMapping("/clear/{id}")
	public String markseen(@PathVariable int id) {
		Notification notification = notificationService.getNotificationById(id);
		notification.setStatus(NotificationStatus.SEEN);
		notificationService.updateNotification(notification);
		return "redirect:/dashboard";
	}
	
	/* this allows the model to be accessible in all the pages */
	/*
	 * @ModelAttribute public void addNotifications(Model model) {
	 * System.out.println("not check"); List<Notification> notificationList =
	 * notificationService.getAllNotification() .stream() .filter(x -> x.getStatus()
	 * == NotificationStatus.UNSEEN) .collect(Collectors.toList());
	 * model.addAttribute("notificationList", notificationList); }
	 */
	
}
