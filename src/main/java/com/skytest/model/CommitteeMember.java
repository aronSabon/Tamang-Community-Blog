package com.skytest.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.skytest.constants.CommitteeMemberStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class CommitteeMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//many to one because same member can be elected in a position in future
	
	//and when using one to many,the members were added in database but which member has which designation was not 
	//properly shown the committeeMember-member had one to many relation and the designation was shown in a list in the same 
	//committeeMember row, so it would be hard to access the designation of members.
	@ManyToOne
	private Member member;
	private String designation;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate activeFrom;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate activeTo;
	@Enumerated(EnumType.STRING)
	private CommitteeMemberStatus status;
}
