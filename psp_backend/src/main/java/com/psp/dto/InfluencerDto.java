/**
 * 
 */
package com.psp.dto;

import java.util.Date;

import com.psp.enums.GENDER;

/**
 * @author us
 * 
 */
public class InfluencerDto {
	private Long influencerId;
	private GENDER gender;
	private String mobileNo;
	private Date dateOfBirth;
	private String blogName;
	private String blogUrl;
	private Long blogCategoryId;// DropDown
	private String blogStartDate;// Wheno did you start your blog
	private String publishContent;// how often you publish content //DropDown
	private String collage;
	private String graduationYear;
	private String city;
	private String state;
	private String country;
	//private Long mediaId;
	private UserDto user;
	private Boolean isTopFiveInfluencer;

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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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

	public String getBlogStartDate() {
		return blogStartDate;
	}

	public void setBlogStartDate(String blogStartDate) {
		this.blogStartDate = blogStartDate;
	}

	public String getPublishContent() {
		return publishContent;
	}

	public void setPublishContent(String publishContent) {
		this.publishContent = publishContent;
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

	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
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

//	public Long getMediaId() {
//		return mediaId;
//	}
//
//	public void setMediaId(Long mediaId) {
//		this.mediaId = mediaId;
//	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Long getBlogCategoryId() {
		return blogCategoryId;
	}

	public void setBlogCategoryId(Long blogCategoryId) {
		this.blogCategoryId = blogCategoryId;
	}

	public Boolean getIsTopFiveInfluencer() {
		return isTopFiveInfluencer;
	}

	public void setIsTopFiveInfluencer(Boolean isTopFiveInfluencer) {
		this.isTopFiveInfluencer = isTopFiveInfluencer;
	}
}
