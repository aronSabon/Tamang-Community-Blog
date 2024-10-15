package com.skytest.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skytest.model.Donor;
import com.skytest.service.DonorService;


@Controller
public class DonorController {
	
	@Autowired
	DonorService donorService;
	@GetMapping("/donor")
	private String getDonor() {
		return "AddDonor";
	}
	@GetMapping("/donorList")
	private String getDonorlist(Model model) {
		model.addAttribute("donorList",donorService.getAllDonor() );
		
		return "DonorList";
	}
	
	@PostMapping("/donor")
	private String postDonor(@ModelAttribute Donor donor) {
		donor.setDate(LocalDate.now());
		donorService.addDonor(donor);
		return "AddDonor";
	}
	@GetMapping("/editDonor")
	private String edit(Model model, @RequestParam int id) {
		model.addAttribute("donorModel",donorService.getDonorById(id) );
		
		return "EditDonor";
	}
	@PostMapping("/updateDonor")
	private String update(@ModelAttribute Donor donor) {
		donorService.updateDonor(donor);
		return "redirect:/donorList";
	}
	
	@GetMapping("/deleteDonor")
	private String delete(@RequestParam int id) {
		donorService.deleteDonorById(id);
		return "redirect:/donorList";
	}

}
