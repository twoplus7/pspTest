/**
 * 
 */
package com.psp.service;

import java.time.LocalDate;
import java.util.List;

import com.psp.common.ResponseData;
import com.psp.dto.EventCategoryDto;
import com.psp.dto.EventDto;
import com.psp.dto.EventUserDto;
import com.psp.exception.PspException;
import com.psp.model.Events;

/**
 * @author us
 * 
 */

public interface IEventService {
	public ResponseData createEvent(EventDto eventDate);

	public Events getEventById(Long eventId) throws PspException;

	public ResponseData getAllEvent(Boolean isApproved, Boolean isFeatured);

	ResponseData searchEvent(String eventLocation, LocalDate eventDate, Long eventCategoryId);

	public ResponseData deleteEventById(Long eventId);

	public ResponseData totalEventCount();

	List<EventUserDto> getEventUserListByUser(Long userId);

	List<EventUserDto> userBind(List<Events> eventList);

	/* Event Category */

	public ResponseData createEventCategory(EventCategoryDto dto);

	public ResponseData getEventCategoryById(Long eventCategoryId);

	public ResponseData getEventCategoryByName(String eventCategoryName);

	public ResponseData getAllEventCategory();

	public ResponseData deleteEventCategoryById(Long id);

	public ResponseData deleteAllEventCategory();

	public ResponseData getEventByCategoryId(Long categoryId);

	String generateSlug(String eventSlug, String eventTitle, Boolean isBlog, Long id);

	List<Events> getEventListByUser(Long userId);

	Integer getTotalSeatByEventId(Long id);
}
