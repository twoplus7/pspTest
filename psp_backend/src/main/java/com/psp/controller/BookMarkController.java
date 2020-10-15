/**
 * 
 */
package com.psp.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.psp.common.ResponseData;
import com.psp.exception.PspException;
import com.psp.model.BookmarkData;
import com.psp.service.IBookMarkService;

/**
 * @author us
 * 
 */

@RestController
public class BookMarkController {

	@Resource
	IBookMarkService bookMarkService;

	@PostMapping("/save-bookmark")
	public ResponseData saveBookMark(@RequestBody BookmarkData bookMarkData) {
		return bookMarkService.saveBookMarkData(bookMarkData);
	}

	@GetMapping("/get-all-bookmark")
	public ResponseData getAllBookMark() {
		return bookMarkService.getAllBookMark();
	}

	@GetMapping("/get-bookmark-by-id")
	public ResponseData getBookMarkById(@RequestParam Long bookMarkId) {
		return bookMarkService.getBookMarkById(bookMarkId);
	}

	@GetMapping("/get-bookmark-by-event-user")
	public ResponseData getBookMarkByEventAndUserId(@RequestParam Long userId) throws PspException {
		return bookMarkService.getBookMarkByEventUserId(userId);
	}

	@GetMapping("/get-bookmark-by-blog-user")
	public ResponseData getBookMarkByBlogAndUserId(@RequestParam Long userId) {
		return bookMarkService.getBookMarkByBlogUserId(userId);
	}

	@GetMapping("/get-bookmark-by-job-user")
	public ResponseData getBookMarkByJobAndUserId(@RequestParam Long userId) {
		return bookMarkService.getBookMarkByJobUserId(userId);
	}

}
