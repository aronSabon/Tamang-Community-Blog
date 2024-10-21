package com.appsoft.tmgsamaj.controller.Front;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.appsoft.tmgsamaj.model.Front.HomeSlider;
import com.appsoft.tmgsamaj.service.Front.HomeSliderService;

@Controller
public class HomeSliderController {
	@Autowired
	HomeSliderService homeSliderService;
	
	@GetMapping("/homeSlider")
	private String getHomeSlider() {
		return"frontend/AddHomeSlider";
	}
	@GetMapping("/homeSliderList")
	private String getHomeSliderList(Model model) {
		model.addAttribute("homeSliderList",homeSliderService.getAllHomeSlider());
		return"frontend/HomeSliderList";
	}
	
	@PostMapping("/homeSlider")
	private String postHomeSlider(@ModelAttribute HomeSlider homeSlider,@RequestParam MultipartFile homeSliderImage) {
		if(!homeSliderImage.isEmpty()) {
			try {
				Files.copy(homeSliderImage.getInputStream(), 
						Path.of("src/main/resources/static/homeSliderImages/"+homeSliderImage.getOriginalFilename()), 
						StandardCopyOption.REPLACE_EXISTING);
				homeSlider.setImageName(homeSliderImage.getOriginalFilename());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		homeSliderService.addHomeSlider(homeSlider);
		return"redirect:/homeSlider";
	}
	
	@GetMapping("/editHomeSlider")
	private String edit(Model model, @RequestParam int id) {
		model.addAttribute("homeSliderModel",homeSliderService.getHomeSliderById(id) );
		return "forntend/EditHomeSlider";
	}
	@PostMapping("/updateHomeSlider")
	private String update(@ModelAttribute HomeSlider homeSlider,@RequestParam MultipartFile homeSliderImage) {
		homeSlider.setImageName(homeSliderService.getHomeSliderById(homeSlider.getId()).getImageName());
		if (!homeSliderImage.isEmpty()) {

			try {
				Files.copy(homeSliderImage.getInputStream(), 
						Path.of("src/main/resources/static/homeSliderImages/"+homeSliderImage.getOriginalFilename()), 
						StandardCopyOption.REPLACE_EXISTING);
				homeSlider.setImageName(homeSliderImage.getOriginalFilename());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		homeSliderService.updateHomeSlider(homeSlider);
		return "redirect:/homeSliderList";
	}
	
	@GetMapping("/deleteHomeSlider")
	private String delete(@RequestParam int id) {
	    String imageName = homeSliderService.getHomeSliderById(id).getImageName();
	    
	    try {
	        Path imagePath = Paths.get("src/main/resources/static/eventImages/" + imageName);
	        Files.deleteIfExists(imagePath);
	        homeSliderService.deleteHomeSliderById(id);
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        System.out.println(ex);
	    }
	    return "redirect:/homeSliderList";
	}


}
