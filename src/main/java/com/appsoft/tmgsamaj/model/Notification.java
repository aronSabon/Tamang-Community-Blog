package com.appsoft.tmgsamaj.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.appsoft.tmgsamaj.constants.NotificationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String imageName;
	private String name;
	private String message;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate expiryDate;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate notificationDate;
	@Enumerated(EnumType.STRING)
	private NotificationStatus status;
}