package com.appsoft.tmgsamaj.model;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.appsoft.tmgsamaj.constants.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PaymentInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String paymentReceiptImageName;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate date;
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	private String paymentType;

}
