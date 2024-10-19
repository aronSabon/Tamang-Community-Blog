package com.appsoft.tmgsamaj.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.appsoft.tmgsamaj.model.Condolence;
import com.appsoft.tmgsamaj.service.CondolenceService;

@Controller
public class CondolenceController {
	@Autowired
	CondolenceService condolenceService;
	
	@GetMapping("/condolence")
	private String getcondolence() {
		return "AddCondolence";
	}
	
	@PostMapping("/condolence")
	private String postCondolence(@ModelAttribute Condolence condolence,@RequestParam MultipartFile condolenceImage ) {
		if(!condolenceImage.isEmpty()) {
			try {
				Files.copy(condolenceImage.getInputStream(), 
						Path.of("src/main/resources/static/condolenceImages/"+ condolenceImage.getOriginalFilename()), 
						StandardCopyOption.REPLACE_EXISTING);
				condolence.setImageName(condolenceImage.getOriginalFilename());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		condolenceService.addCondolence(condolence);
		return "redirect:/condolence";
	}
	@GetMapping("/condolenceList")
	private String list(Model model) {
		model.addAttribute("condolenceList",condolenceService.getAllCondolence());
		return "CondolenceList";
	}
	@GetMapping("/editCondolence")
	private String edit(Model model,@RequestParam int id) {
		model.addAttribute("condolenceModel",condolenceService.getCondolenceById(id));
		return "EditCondolence";
	}
	
	@PostMapping("/editCondolence")
	private String editCondolence(@ModelAttribute Condolence condolence) {
		condolence.setImageName(condolenceService.getCondolenceById(condolence.getId()).getImageName());
		condolenceService.updateCondolence(condolence);
		return "redirect:/condolenceList";
	}
	
	@GetMapping("/deleteCondolence")
	private String delete(@RequestParam int id) {
		condolenceService.deleteCondolenceById(id);
		return "redirect:/condolenceList";
	}

}
