/**
 * 
 */
package com.psp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author us
 * 
 */

@Entity
public class BookmarkData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookMarkId;

	private Long eventId;
	private Long blogId;
	private Long jobId;
	private Long userId;

	public Long getBookMarkId() {
		return bookMarkId;
	}

	public void setBookMarkId(Long bookMarkId) {
		this.bookMarkId = bookMarkId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
