/**
 * 
 */
package com.psp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.psp.common.ResponseData;
import com.psp.enums.HttpStatus;
import com.psp.exception.PspException;
import com.psp.model.BookmarkData;
import com.psp.model.Events;
import com.psp.repository.BookMarkRepository;
import com.psp.service.IBookMarkService;
import com.psp.service.IEventService;

/**
 * @author us
 * 
 */

@Service
public class BookMarkServiceImpl implements IBookMarkService {

	@Resource
	BookMarkRepository bookMarkRepo;

	@Resource
	IEventService eventService;

	@Override
	public ResponseData saveBookMarkData(BookmarkData bookMarkData) {
		bookMarkRepo.save(bookMarkData);
		return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage());
	}

	@Override
	public ResponseData getAllBookMark() {
		List<BookmarkData> bookMarkList = bookMarkRepo.findAll();
		if (bookMarkList.isEmpty()) {
			return new ResponseData(HttpStatus.NO_DATA.getStatus(), HttpStatus.NO_DATA.getMessage());
		}
		return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), bookMarkList);
	}

	@Override
	public ResponseData getBookMarkById(Long bookMarkId) {
		Optional<BookmarkData> data = bookMarkRepo.findById(bookMarkId);
		if (data.isPresent()) {
			return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), data);
		}
		return new ResponseData(HttpStatus.NO_DATA.getStatus(), HttpStatus.NO_DATA.getMessage());
	}

	@Override
	public ResponseData getBookMarkByEventUserId(Long userId) throws PspException {
		List<Integer> bookMarkEventIds = bookMarkRepo.getBookMarkEventByUser(userId);
		List<Events> eventList = new ArrayList<>();
		for (Integer id : bookMarkEventIds) {
			Events event = eventService.getEventById(Long.valueOf(id));
			eventList.add(event);
		}
		return (!eventList.isEmpty())
				? new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), eventList)
				: new ResponseData(HttpStatus.NO_DATA.getStatus(), HttpStatus.NO_DATA.getMessage());
	}

	@Override
	public ResponseData getBookMarkByBlogUserId(Long userId) {
		return null;
	}

	@Override
	public ResponseData getBookMarkByJobUserId(Long userId) {
		return null;
	}
}
