package com.appsoft.tmgsamaj;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.appsoft.tmgsamaj.constants.EventStatus;
import com.appsoft.tmgsamaj.model.Event;
import com.appsoft.tmgsamaj.service.EventService;
@EnableScheduling
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Autowired
    private EventService eventService;
	//<seconds> <minutes> <hours> <day-of-month> <month> <day-of-week> <year>

    // This method will run daily at midnight (00:00)
    @Scheduled(cron = "0 9 15 * * ?",zone = "Asia/Kathmandu")
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
}
