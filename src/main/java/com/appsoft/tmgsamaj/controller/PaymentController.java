package com.appsoft.tmgsamaj.controller;

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

import com.appsoft.tmgsamaj.constants.MemberStatus;
import com.appsoft.tmgsamaj.constants.PaymentStatus;
import com.appsoft.tmgsamaj.model.CommitteeMember;
import com.appsoft.tmgsamaj.model.Member;
import com.appsoft.tmgsamaj.model.PaymentInfo;
import com.appsoft.tmgsamaj.service.MemberService;
import com.appsoft.tmgsamaj.service.PaymentInfoService;
import com.appsoft.tmgsamaj.utils.MailUtils;

@Controller
public class PaymentController {
	@Autowired
	private PaymentInfoService paymentInfoService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MailUtils mailUtils;

	@GetMapping("/payment")
	private String getPayment() {
		return "Payment";
	}


	@PostMapping("/payment")
	private String postPayment(@ModelAttribute PaymentInfo paymentInfo,@RequestParam MultipartFile paymentSlip,Model model) {
		Member member= memberService.getMemberByEmail(paymentInfo.getEmail());
		if(member != null) {
			
			try {
				Files.copy(paymentSlip.getInputStream(), 
						Path.of("src/main/resources/static/paymentImages/"+ paymentSlip.getOriginalFilename()), 
						StandardCopyOption.REPLACE_EXISTING);
				paymentInfo.setPaymentReceiptImageName(paymentSlip.getOriginalFilename());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			paymentInfo.setStatus(PaymentStatus.PENDING);
			paymentInfo.setDate(LocalDate.now());
			paymentInfoService.addPaymentInfo(paymentInfo);
			return "redirect:http://tamusamajuk.com";
		}
		else {
			model.addAttribute("message" , "Email doesnot match!!!");
		}
		return"Payment";
	}
	@GetMapping("/paymentList")
	private String getPaymentList(Model model) {
		model.addAttribute("paymentList", paymentInfoService.getAllPaymentInfo());
		return "PaymentList";
	}
	
	@GetMapping("/approvePayment")
	private String approvePayment(@RequestParam int id) {
		PaymentInfo paymentInfo = paymentInfoService.getPaymentInfoById(id);
		paymentInfo.setStatus(PaymentStatus.APPROVED);
		paymentInfoService.updatePaymentInfo(paymentInfo);
		Member member = memberService.getMemberByEmail(paymentInfo.getEmail());
		if(member.getMemberSince()==null) {
			member.setMemberSince(LocalDate.now());

			member.setExpiryDate(member.getMemberSince().plusYears(2));
		}
		else {
			member.setRenewDate(LocalDate.now());
			member.setExpiryDate(member.getExpiryDate().plusYears(2));
		}
		
		member.setStatus(MemberStatus.ACTIVE);
		memberService.updateMember(member);
		//send mail
				String subject ="Membership Payment Success";
				 String message = "Your membership has been approved.\nThankyou for joining us.";
				mailUtils.sendEmail(paymentInfo.getEmail(), subject, message);
		return "redirect:/paymentList";
	}
	@GetMapping("/rejectPayment")
	private String rejectPayment(@RequestParam int id) {
		

		
		PaymentInfo paymentInfo = paymentInfoService.getPaymentInfoById(id);
		paymentInfo.setStatus(PaymentStatus.DECLINED);
		paymentInfoService.updatePaymentInfo(paymentInfo);
		
		//send mail
		String subject ="Membership Payment Error";
		 String message = "Please resend your Payment Slip.";
		mailUtils.sendEmail(paymentInfo.getEmail(), subject, message);
	
		return "redirect:paymentList";
	}
}
