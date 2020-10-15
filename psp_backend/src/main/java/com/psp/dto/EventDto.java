/**
 * 
 */
package com.psp.dto;

import java.time.LocalDate;

import com.psp.enums.AdminStatus;
import com.psp.enums.EventsType;

/**
 * @author us
 * 
 */
public class EventDto {
	private Long eventId;
	private String eventTitle;
	private LocalDate eventStartDate;
	private LocalDate eventEndDate;
	private String eventLocation;
	private String eventDescription;
	private Long mediaId;
	private Long eventCategory;
	private String eventSlug;
	private Integer eventAmount;
	private EventsType eventType;
	private AdminStatus eventStatus;
	private Integer totalSeat;
	private Boolean isFeatured;

	private String seoTitle;
	private String seoKeywords;
	private String seoDescription;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public LocalDate getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(LocalDate eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public LocalDate getEventEndDate() {
		return eventEndDate;
	}

	public void setEventEndDate(LocalDate eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	public Long getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(Long eventCategory) {
		this.eventCategory = eventCategory;
	}

	public String getEventSlug() {
		return eventSlug;
	}

	public void setEventSlug(String eventSlug) {
		this.eventSlug = eventSlug;
	}

	public Integer getEventAmount() {
		return eventAmount;
	}

	public void setEventAmount(Integer eventAmount) {
		this.eventAmount = eventAmount;
	}

	public EventsType getEventType() {
		return eventType;
	}

	public void setEventType(EventsType eventType) {
		this.eventType = eventType;
	}

	public Integer getSeatBooked() {
		return totalSeat;
	}

	public void setSeatBooked(Integer seatBooked) {
		this.totalSeat = seatBooked;
	}

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public String getSeoKeywords() {
		return seoKeywords;
	}

	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	public Integer getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(Integer totalSeat) {
		this.totalSeat = totalSeat;
	}

	public Boolean getIsFeatured() {
		return isFeatured;
	}

	public void setIsFeatured(Boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	public AdminStatus getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(AdminStatus eventStatus) {
		this.eventStatus = eventStatus;
	}
}
