/**
 * 
 */
package com.psp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.beans.BeanUtils;

import com.psp.dto.EventDto;
import com.psp.enums.AdminStatus;
import com.psp.enums.EventsType;

/**
 * @author us
 * 
 */

@Entity
public class Events extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eventId;

	private String eventTitle;

	private LocalDate eventStartDate;

	private LocalDate eventEndDate;

	private String eventLocation;

	@Column(columnDefinition = "TEXT")
	private String eventDescription;

	private String eventSlug;
	private Integer eventAmount;

	@Enumerated(EnumType.STRING)
	private EventsType eventType;

	private Integer totalSeat;

	private String seoTitle;
	private String seoKeywords;

	@Column(columnDefinition = "TEXT")
	private String seoDescription;

	@ManyToOne
	@JoinColumn(name = "event_cat_id")
	private EventCategory eventCategory;

	@Enumerated(EnumType.STRING)
	private AdminStatus eventStatus;

	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean isFeatured;

	@Transient
	private String createdBy;

	public void setDataFromData(EventDto eventDto) {
		BeanUtils.copyProperties(eventDto, this);

		EventCategory eventCat = new EventCategory();
		if (eventDto.getEventCategory() != null || eventDto.getEventCategory().intValue() != 0) {
			eventCat.setEventCategoryId(eventDto.getEventCategory());
			setEventCategory(eventCat);
		}
	}

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

	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
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

	public EventsType getEventType() {
		return eventType;
	}

	public void setEventType(EventsType eventType) {
		this.eventType = eventType;
	}

	public AdminStatus getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(AdminStatus eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
}
