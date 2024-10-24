package com.appsoft.tmgsamaj.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.appsoft.tmgsamaj.constants.NotificationStatus;
import com.appsoft.tmgsamaj.model.Notification;
import com.appsoft.tmgsamaj.service.NotificationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

//@Autowired
//NotificationService notificationService;
	
	private final NotificationService notificationService;

public ControllerAdvice(NotificationService notificationService) {
	this.notificationService=notificationService;
}

	    @ModelAttribute
	    public void populateNotificationData(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            List<Notification> notificationList = notificationService.getAllNotification()
	                    .stream()
	                    .filter(x -> x.getStatus() == NotificationStatus.UNSEEN)
	                    .collect(Collectors.toList());
	            
	            for (Notification notification : notificationList) {
	                Hibernate.initialize(notification.getMember().getFamily());
	            }

	            session.setAttribute("notificationList", notificationList);
	            session.setAttribute("notificationCount", notificationList.size());
	        }
	    }
	}