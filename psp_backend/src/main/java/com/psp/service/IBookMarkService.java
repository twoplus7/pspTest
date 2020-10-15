/**
 * 
 */
package com.psp.service;

import com.psp.common.ResponseData;
import com.psp.exception.PspException;
import com.psp.model.BookmarkData;

/**
 * @author us
 * 
 */

public interface IBookMarkService {

	public ResponseData saveBookMarkData(BookmarkData bookMarkData);

	public ResponseData getAllBookMark();

	public ResponseData getBookMarkByEventUserId(Long userId) throws PspException;

	public ResponseData getBookMarkByBlogUserId(Long userId);

	public ResponseData getBookMarkByJobUserId(Long userId);

	public ResponseData getBookMarkById(Long bookMarkId);
}
