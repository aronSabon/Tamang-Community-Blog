package com.appsoft.tmgsamaj.service;

import java.util.List;

import com.appsoft.tmgsamaj.model.Notification;



public interface NotificationService {
	void addNotification(Notification notification);
	List<Notification> getAllNotification();
	void deleteNotificationById(int id);
	Notification getNotificationById(int id);
	void updateNotification(Notification notification);

}
