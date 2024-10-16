package com.appsoft.tmgsamaj.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.tmgsamaj.model.CommitteeMember;
import com.appsoft.tmgsamaj.service.CommitteeMemberService;


@Controller
public class CommitteeController {
	@Autowired
	CommitteeMemberService committeeMemberService;

	@GetMapping("/committeeList")
	public String getCommitteeList(Model model) {
		List<CommitteeMember> committeeMemberList = committeeMemberService.getAllCommitteMember();
		List<CommitteeMember> committeePresidentList = committeeMemberList.stream().filter(x ->x.getDesignation().equals("President")).collect(Collectors.toList());
		model.addAttribute("committeePresidentList",committeePresidentList);
		return"CommitteeList";
	}
	
	@GetMapping("/committeeView")
	public String viewCommittee(@RequestParam LocalDate activeFrom,@RequestParam LocalDate activeTo,Model model ) {
		List<CommitteeMember> committeeMemberList = committeeMemberService.getAllCommitteMember();
		List<CommitteeMember> committeeFromToList = committeeMemberList.stream().filter(x ->x.getActiveFrom().equals(activeFrom) && x.getActiveTo().equals(activeTo)).collect(Collectors.toList());
		model.addAttribute("committeeFromToList", committeeFromToList);
		model.addAttribute("activeFrom",activeFrom);
		model.addAttribute("activeTo",activeTo);
		return "ViewCommittee";
	}
	
}
