/**
 * 
 */
package com.psp.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.psp.common.ResponseData;
import com.psp.dto.EventDto;
import com.psp.dto.EventUserDto;
import com.psp.enums.HttpStatus;
import com.psp.exception.PspException;
import com.psp.model.Events;
import com.psp.service.IEventService;

/**
 * @author us
 * 
 */
@RestController
public class EventController {

	@Resource
	IEventService eventService;

	@PostMapping("/create-event")
	public ResponseData createEvent(@RequestBody EventDto eventDto) {
		return eventService.createEvent(eventDto);
	}

	@GetMapping("/get-event-by-id")
	public ResponseData getEventById(@RequestParam Long eventId) throws PspException {
		Events event = eventService.getEventById(eventId);
		List<EventUserDto> eventUserList = eventService.userBind(Arrays.asList(event));
		if (eventUserList.isEmpty()) {
			return new ResponseData(HttpStatus.FAIL.getStatus(), HttpStatus.FAIL.getMessage());
		}
		return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), eventUserList);
	}

	@GetMapping("/get-all-events")
	public ResponseData getAllEvents(@RequestParam Boolean isApproved, @RequestParam Boolean isFeatured) {
		return eventService.getAllEvent(isApproved, isFeatured);
	}

	@GetMapping("/delete-event-by-id")
	public ResponseData deleteEventById(Long eventId) {
		return eventService.deleteEventById(eventId);
	}

	@GetMapping("/get-event-by-category-id")
	public ResponseData getEventByCategoryId(@RequestParam Long categoryId) {
		return eventService.getEventByCategoryId(categoryId);
	}

	@PostMapping("/search-event")
	public ResponseData searchEvent(@RequestParam(required = false) String eventLocation,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "eventDate", required = false) LocalDate eventDate,
			@RequestParam(required = false) Long eventCategoryId) {
		return eventService.searchEvent(eventLocation, eventDate, eventCategoryId);
	}

	@GetMapping("/get-event-by-user")
	public ResponseData getEventByUser(@RequestParam Long userId) {
		List<EventUserDto> eventUserList = eventService.getEventUserListByUser(userId);
		return (eventUserList.isEmpty()) ? new ResponseData(HttpStatus.FAIL.getStatus(), HttpStatus.FAIL.getMessage())
				: new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), eventUserList);
	}

	@GetMapping("/total-event-count")
	public ResponseData totalEvent() {
		return eventService.totalEventCount();
	}

	@PostMapping("/event-seat-book")
	public ResponseData eventSeatBook() {
		return null;
	}

	@GetMapping("/get-booking-details")
	public ResponseData getBookingDetailsByEventId(@RequestParam Long eventId) throws PspException {
		return null;
	}
}
