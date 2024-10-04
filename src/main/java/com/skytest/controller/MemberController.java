package com.skytest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.skytest.constants.MemberStatus;
import com.skytest.model.Family;
import com.skytest.model.Member;
import com.skytest.service.MemberService;
import com.skytest.utils.MailUtils;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	@Autowired
	private MailUtils mailUtils;
	
	
	@GetMapping("/member")
	private String getMember() {
		return "AddMember";
	}
	 String subject ="Membership Payment";
	 String message = " lskdafjslkdfjlsk \n Bank details :\n sldkfjsdlkjf  l;ksdjfldskj saldkjfsldkjf  \nclick this link http://localhost/memberList";


	@PostMapping("/member")
	private String postMember(@RequestParam MultipartFile memberImage,@RequestParam(required = false) List<MultipartFile>familyImages,@ModelAttribute Member member) {

		if (!memberImage.isEmpty()) {

			try {
				Files.copy(memberImage.getInputStream(), 
						Path.of("src/main/resources/static/memberImages/"+member.getFirstName()+member.getContact().getMobileNumber()+".jpg"), 
						StandardCopyOption.REPLACE_EXISTING);
				member.setImageName(member.getFirstName()+member.getContact().getMobileNumber()+".jpg");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Handle family images

		if(member.getFamily()!=null) {
			List<Family> validFamilyMembers = member.getFamily().stream()
					.filter(family -> family.getFirstName() != null && !family.getFirstName().isEmpty())
					.collect(Collectors.toList());

			member.setFamily(validFamilyMembers);
			if (member.getFamily().size() >= familyImages.size()) {

				for (int i = 0; i < member.getFamily().size(); i++) {
					Family family = member.getFamily().get(i);
					family.setImageName(family.getFirstName()+member.getContact().getMobileNumber()+".jpg");
					MultipartFile familyImage = familyImages.get(i); // Get the corresponding image for this family member

					if (!familyImage.isEmpty()) {

						try {
							Files.copy(familyImage.getInputStream(), 
									Path.of("src/main/resources/static/memberImages/"+family.getFirstName()+member.getContact().getMobileNumber()+".jpg"), 
									StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else {
				// Handle the case where the number of family members doesn't match the number of images
				// Maybe return an error or log the issue
				System.err.println("Number of family members doesn't match number of images");
			}
		}
		if(member.getFamily()!=null) {
			System.out.println("Number of family members: " + member.getFamily().size());
			System.out.println("Number of family images: " + familyImages.size());
		}
		member.setStatus(MemberStatus.PENDING);
		member.setFormSubmitDate(LocalDate.now());
		System.out.println(member);
		memberService.addMember(member);
		
		//send mail
		String subject ="Membership Payment";
		 String message = " lskdafjslkdfjlsk \n Bank details :\n sldkfjsdlkjf  l;ksdjfldskj saldkjfsldkjf  \nclick this link http://localhost/memberList";


		mailUtils.sendEmail(member.getContact().getEmail(), subject, message);
		

		
		
		return "redirect:/member";
	}

	@GetMapping("/memberList")
	private String memberList(Model model) {
		model.addAttribute("memberList",memberService.getAllMember());
		return "MemberList";
	}

	@GetMapping("/editMember")
	private String memberEdit(@RequestParam int id, Model model) {
		model.addAttribute("memberModel",memberService.getMemberById(id));
		return "EditMember";
	}
	@PostMapping("/updateMember")
	private String updateMember(@RequestParam MultipartFile memberImage,@RequestParam(required = false) List<MultipartFile>familyImages,@ModelAttribute Member member) {
		if (!memberImage.isEmpty()) {

			try {
				Files.copy(memberImage.getInputStream(), 
						Path.of("src/main/resources/static/memberImages/"+member.getFirstName()+member.getContact().getMobileNumber()+".jpg"), 
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Handle family images

		if(member.getFamily()!=null) {
			List<Family> validFamilyMembers = member.getFamily().stream()
					.filter(family -> family.getFirstName() != null && !family.getFirstName().isEmpty())
					.collect(Collectors.toList());

			member.setFamily(validFamilyMembers);
			if (member.getFamily().size() >= familyImages.size()) {

				for (int i = 0; i < member.getFamily().size(); i++) {
					Family family = member.getFamily().get(i);
					family.setImageName(family.getFirstName()+member.getContact().getMobileNumber()+".jpg");
					MultipartFile familyImage = familyImages.get(i); // Get the corresponding image for this family member

					if (!familyImage.isEmpty()) {

						try {
							Files.copy(familyImage.getInputStream(), 
									Path.of("src/main/resources/static/memberImages/"+family.getFirstName()+member.getContact().getMobileNumber()+".jpg"), 
									StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			else {
				// Handle the case where the number of family members doesn't match the number of images
				// Maybe return an error or log the issue
				System.err.println("Number of family members doesn't match number of images");
			}
		}
		if(member.getFamily()!=null) {
			System.out.println("Number of family members: " + member.getFamily().size());
			System.out.println("Number of family images: " + familyImages.size());
		}
		member.setImageName(member.getFirstName()+member.getContact().getMobileNumber()+".jpg");
		member.setStatus(memberService.getMemberById(member.getId()).getStatus());
		member.setFormSubmitDate(memberService.getMemberById(member.getId()).getFormSubmitDate());
		memberService.updateMember(member);

		return "redirect:/memberList";
	}

	@GetMapping("/viewMember")
	private String memberView(@RequestParam int id, Model model) {
		model.addAttribute("memberModel",memberService.getMemberById(id));
		return "ViewMember";
	}
	@GetMapping("/revokeMember")
	private String revoke(@RequestParam int id) {
		System.out.println(id);
		Member member = memberService.getMemberById(id);
		member.setStatus(MemberStatus.INACTIVE);
		memberService.updateMember(member);
		return "redirect:/memberList";
	}
}

