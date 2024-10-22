package com.appsoft.tmgsamaj;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.appsoft.tmgsamaj.constants.EventStatus;
import com.appsoft.tmgsamaj.constants.MemberStatus;
import com.appsoft.tmgsamaj.constants.NotificationStatus;
import com.appsoft.tmgsamaj.model.Event;
import com.appsoft.tmgsamaj.model.Member;
import com.appsoft.tmgsamaj.model.Notification;
import com.appsoft.tmgsamaj.service.EventService;
import com.appsoft.tmgsamaj.service.MemberService;
import com.appsoft.tmgsamaj.service.NotificationService;
import com.appsoft.tmgsamaj.utils.MailUtils;
@EnableScheduling
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Autowired
    private EventService eventService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
	private MailUtils mailUtils;
	//<seconds> <minutes> <hours> <day-of-month> <month> <day-of-week> <year>

    // This method will run daily at midnight (00:00)
    @Scheduled(cron = "0 0 0 * * ?",zone = "Asia/Kathmandu")
    public void updateAllEventStatuses() {
        List<Event> events = eventService.getAllEvent();
        
        // Iterate through all events and update their statuses
        for (Event event : events) {
        	if(event.getDate().isEqual(LocalDate.now()) || event.getDate().isBefore(LocalDate.now())) {
        		event.setStatus(EventStatus.COMPLETED);
        		event.setImageNames(eventService.getEventById(event.getId()).getImageNames());
        		eventService.updateEvent(event);
        	}
        }
    }
    @Scheduled(cron = "0 0 0 * * ?",zone = "Asia/Kathmandu")
    public void updateMemberStatus() {
    	List<Member> members = memberService.getAllMember();
    	for(Member m : members) {
    		if( LocalDate.now().equals(m.getExpiryDate())) {
    			m.setStatus(MemberStatus.INACTIVE);
    			memberService.updateMember(m);
    		}
    	}
    }
    
    @Scheduled(cron = "0 37 9 * * ?",zone = "Asia/Kathmandu")
    public void notification() {
    	System.out.println("notification Added");
    	List<Member> members = memberService.getAllMember();
    	for(Member m : members) {
    		
    		if( LocalDate.now().equals(m.getExpiryDate().minusDays(15))) {
    		
    			Notification notification = new Notification();
    			notification.setStatus(NotificationStatus.UNSEEN);
    			notification.setNotificationDate(LocalDate.now());
    			notification.setExpiryDate(m.getExpiryDate());
    			notification.setImageName(m.getImageName());
    			notification.setMessage("Membership will be expired in 15 days.");
    			notification.setName(m.getTitle() +" "+ m.getFirstName() + " "+ (m.getMiddleName()!=null ? m.getMiddleName() + " " : "" )+m.getLastName());
    			notificationService.addNotification(notification);
    			
    			String subject ="Membership Renew";
    			 String message = "Your membership for Tamang Community will expire in 15 days.\n"
    					+"Please renew your membership.\n"
    			 		+ "Please provide Your Payment Slip in this Link \n http://localhost/fpayment";

    			mailUtils.sendEmail(m.getContact().getEmail(), subject, message);
    		}
    		if( LocalDate.now().equals(m.getExpiryDate())) {
        		
    			Notification notification = new Notification();
    			notification.setStatus(NotificationStatus.UNSEEN);
    			notification.setNotificationDate(LocalDate.now());
    			notification.setExpiryDate(m.getExpiryDate());
    			notification.setImageName(m.getImageName());
    			notification.setMessage("Membership expired.");
    			notification.setName(m.getTitle() +" "+ m.getFirstName() + " "+ (m.getMiddleName()!=null ? m.getMiddleName() + " " : "" )+m.getLastName());
    			notificationService.addNotification(notification);
    			
    			String subject ="Membership Expired";
   			 String message = "Your membership for Tamang Community has expired.\n"
   					+"Please renew your membership to continue being a part of us.\n"
   			 		+ "Please provide Your Payment Slip in this Link \n http://localhost/fpayment";

   			mailUtils.sendEmail(m.getContact().getEmail(), subject, message);
    		}
    		
    	}
    }
}
