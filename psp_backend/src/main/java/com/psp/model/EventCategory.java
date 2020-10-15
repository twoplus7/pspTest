/**
 * 
 */
package com.psp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author us
 * 
 */
@Entity
public class EventCategory extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("event_category_id")
	private Long eventCategoryId;

	private String eventCategoryName;
	private String eventCategoryStatus;

	// @OneToMany
	// @JoinTable(name = "events", joinColumns = { @JoinColumn(name =
	// "event_category_id") })
//	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true)
//	@JoinColumn(name = "event_cat_id")
//	private List<Events> events;

	public Long getEventCategoryId() {
		return eventCategoryId;
	}

	public void setEventCategoryId(Long eventCategoryId) {
		this.eventCategoryId = eventCategoryId;
	}

	public String getEventCategoryName() {
		return eventCategoryName;
	}

	public void setEventCategoryName(String eventCategoryName) {
		this.eventCategoryName = eventCategoryName;
	}

	public String getEventCategoryStatus() {
		return eventCategoryStatus;
	}

	public void setEventCategoryStatus(String eventCategoryStatus) {
		this.eventCategoryStatus = eventCategoryStatus;
	}
}
