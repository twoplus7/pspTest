/**
 * 
 */
package com.psp.enums;

/**
 * @author us
 * 
 */
public enum EventsType {

	FREE("Free"), PAID("Paid"), DONATION("Donation");

	private String eventType;

	private EventsType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventType() {
		return this.eventType;
	}
}
