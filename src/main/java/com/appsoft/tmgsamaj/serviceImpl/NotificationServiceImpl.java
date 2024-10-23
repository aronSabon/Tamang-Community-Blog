package com.appsoft.tmgsamaj.serviceImpl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appsoft.tmgsamaj.model.Notification;
import com.appsoft.tmgsamaj.repository.NotificationRepository;
import com.appsoft.tmgsamaj.service.NotificationService;



@Service
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	NotificationRepository notificationRepository;
		@Override
		public void addNotification(Notification notification) {
			// TODO Auto-generated method stub
			notificationRepository.save(notification);
		}

		@Override
		  @Transactional(readOnly = true)
	    public List<Notification> getAllNotification() {
	        List<Notification> notifications = notificationRepository.findAll();
	        for (Notification notification : notifications) {
	            // Ensure the member's family collection is initialized
	            Hibernate.initialize(notification.getMember().getFamily());
	        }
	        return notifications;
	    }

		@Override
		public void deleteNotificationById(int id) {
			// TODO Auto-generated method stub
			notificationRepository.deleteById(id);
		}

		@Override
		public Notification getNotificationById(int id) {
			// TODO Auto-generated method stub
			return notificationRepository.findById(id).get();
		}

		@Override
		public void updateNotification(Notification notification) {
			// TODO Auto-generated method stub
			notificationRepository.save(notification);
		}

}
