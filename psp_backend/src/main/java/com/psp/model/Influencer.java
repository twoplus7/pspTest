/**
 * 
 */
package com.psp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;

import com.psp.dto.InfluencerDto;
import com.psp.enums.GENDER;

/**
 * @author us
 * 
 */

@Entity
public class Influencer extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long influencerId;

	@Enumerated(EnumType.STRING)
	private GENDER gender;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	private String blogName;
	private String blogUrl;
	private Long blogCategory;// DropDown
	private String blogStartDate;// Wheno did you start your blog
	private String publishContent;// how often you publish content //DropDown
	private String collage;

	private String graduationYear;

//	@Enumerated(EnumType.STRING)
//	private UserProfileStatus userStatus;

	private String city;
	private String state;
	private String country;

	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean isTopFiveInfluencer;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	public void setDataFromDto(InfluencerDto dto) {
		BeanUtils.copyProperties(dto, this);
		/*
		 * if (dto.getMediaId() != null && dto.getMediaId().intValue() != 0) { Media
		 * media = new Media(); media.setMediaId(dto.getMediaId()); setMedia(media); }
		 */
	}

	public Long getInfluencerId() {
		return influencerId;
	}

	public void setInfluencerId(Long influencerId) {
		this.influencerId = influencerId;
	}

	public GENDER getGender() {
		return gender;
	}

	public void setGender(GENDER gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBlogName() {
		return blogName;
	}

	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	public String getBlogUrl() {
		return blogUrl;
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}

	public Long getBlogCategory() {
		return blogCategory;
	}

	public void setBlogCategory(Long blogCategory) {
		this.blogCategory = blogCategory;
	}

	public String getBlogDate() {
		return blogStartDate;
	}

	public void setBlogDate(String blogDate) {
		this.blogStartDate = blogDate;
	}

	public String getCollage() {
		return collage;
	}

	public void setCollage(String collage) {
		this.collage = collage;
	}

	public String getGraduationYear() {
		return graduationYear;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPublishContent() {
		return publishContent;
	}

	public void setPublishContent(String publishContent) {
		this.publishContent = publishContent;
	}

	public String getBlogStartDate() {
		return blogStartDate;
	}

	public void setBlogStartDate(String blogStartDate) {
		this.blogStartDate = blogStartDate;
	}

//	public UserProfileStatus getUserStatus() {
//		return userStatus;
//	}
//
//	public void setUserStatus(UserProfileStatus userStatus) {
//		this.userStatus = userStatus;
//	}

	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsTopFiveInfluencer() {
		return isTopFiveInfluencer;
	}

	public void setIsTopFiveInfluencer(Boolean isTopFiveInfluencer) {
		this.isTopFiveInfluencer = isTopFiveInfluencer;
	}
}
