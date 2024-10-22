package com.appsoft.tmgsamaj.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		public List<Notification> getAllNotification() {
			// TODO Auto-generated method stub
			return notificationRepository.findAll();
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
