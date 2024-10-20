package com.appsoft.tmgsamaj.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appsoft.tmgsamaj.constants.CommitteeMemberStatus;
import com.appsoft.tmgsamaj.model.CommitteeMember;
import com.appsoft.tmgsamaj.model.Member;
import com.appsoft.tmgsamaj.service.CommitteeMemberService;
import com.appsoft.tmgsamaj.service.MemberService;


import org.springframework.web.bind.annotation.RequestBody;


@Controller

public class CommitteeMemberController {
	@Autowired
	MemberService memberService;
	@Autowired
	CommitteeMemberService committeeMemberService;

	@GetMapping("/committeeMember")
	private String getCommiteeMember(Model model) {
		model.addAttribute("memberList",memberService.getAllMember());
		return "AddCommitteeMember";
	}
	@PostMapping("/committeeMember")
	public String saveCommitteeMembers(
			@RequestParam("member[]") List<Member> members,
			@RequestParam("designation[]") List<String> designations,
			@RequestParam("activeFrom[]") List<String> activeFromDates,
			@RequestParam("activeTo[]") List<String> activeToDates,
			Model model
			) {
		for (int i = 0; i < members.size(); i++) {
			CommitteeMember member = new CommitteeMember();
			member.setMember(members.get(i));
			member.setDesignation(designations.get(i));
			member.setActiveFrom(LocalDate.parse(activeFromDates.get(i)));  // You can format this as per your date format
			member.setActiveTo(LocalDate.parse(activeToDates.get(i)));
			member.setStatus(CommitteeMemberStatus.ACTIVE);

			// Save the committee member in the database
			committeeMemberService.addCommitteMember(member);
		}

		model.addAttribute("message", "Committee members added successfully!");
		return "redirect:/committeeMember";  // Redirect to any page after successful submission
	}
	

	@GetMapping("/committeeMemberList")
	private String getCommiteeMemberList(Model model) {
		model.addAttribute("committeeMemberList",committeeMemberService.getAllCommitteMember());
		return "CommitteeMemberList";
	}
	@GetMapping("/editCommitteeMember")
	private String edit(Model model,@RequestParam int id) {
		model.addAttribute("memberList",memberService.getAllMember());
		model.addAttribute("committeeMemberModel",committeeMemberService.getCommitteMemberById(id));
		return "EditCommitteeMember";
	}
	@PostMapping("/updateCommitteeMember")
	public String update(@ModelAttribute CommitteeMember committeeMember) {
		committeeMember.setStatus(committeeMemberService.getCommitteMemberById(committeeMember.getId()).getStatus());
		committeeMemberService.updateCommitteMember(committeeMember);
		
		return "redirect:/committeeMemberList";
	}
	@GetMapping("/deleteCommitteeMember")
	public String delete(@RequestParam int id) {
		committeeMemberService.deleteCommitteMemberById(id);
		
		return "redirect:/committeeMemberList";
	}
	
	
	@GetMapping("/committeeMemberList1")
	@ResponseBody
	public List<CommitteeMember> getallcommitteeMember(Model model) {
		return committeeMemberService.getAllCommitteMember();
	}
	
}
