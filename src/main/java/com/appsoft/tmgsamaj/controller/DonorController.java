package com.appsoft.tmgsamaj.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.appsoft.tmgsamaj.model.Donor;
import com.appsoft.tmgsamaj.service.DonorService;


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
	private String postDonor(@ModelAttribute Donor donor,@RequestParam MultipartFile donorImage) {
		if (!donorImage.isEmpty()) {

			try {
				Files.copy(donorImage.getInputStream(), 
						Path.of("src/main/resources/static/donorImages/"+donorImage.getOriginalFilename()), 
						StandardCopyOption.REPLACE_EXISTING);
				donor.setImageName(donorImage.getOriginalFilename());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
	private String update(@ModelAttribute Donor donor,@RequestParam MultipartFile donorImage) {
		donor.setImageName(donorService.getDonorById(donor.getId()).getImageName());
		if (!donorImage.isEmpty()) {

			try {
				Files.copy(donorImage.getInputStream(), 
						Path.of("src/main/resources/static/donorImages/"+donorImage.getOriginalFilename()), 
						StandardCopyOption.REPLACE_EXISTING);
				donor.setImageName(donorImage.getOriginalFilename());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		donorService.updateDonor(donor);
		return "redirect:/donorList";
	}
	
	@GetMapping("/deleteDonor")
	private String delete(@RequestParam int id) {
		donorService.deleteDonorById(id);
		return "redirect:/donorList";
	}

}
