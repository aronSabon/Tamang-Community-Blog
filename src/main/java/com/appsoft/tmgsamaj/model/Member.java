package com.appsoft.tmgsamaj.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.appsoft.tmgsamaj.constants.MemberStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Member {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;
	@OneToOne(cascade = CascadeType.ALL)
	private Contact contact;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Family> family;
	@OneToOne(cascade = CascadeType.ALL)
	private PaymentInfo paymentInfo;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate formSubmitDate;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate memberSince;	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate expiryDate;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate renewDate;
	private String imageName;
	private String membershipType;
	@Enumerated(EnumType.STRING)
	private MemberStatus status;
	
	
	
}
