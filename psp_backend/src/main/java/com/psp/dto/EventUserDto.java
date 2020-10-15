/**
 * 
 */
package com.psp.dto;

import com.psp.model.Events;
import com.psp.model.User;

/**
 * @author us
 * 
 */
public class EventUserDto {
	private Events events;
	private User user;

	public Events getEvents() {
		return events;
	}

	public void setEvents(Events events) {
		this.events = events;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
