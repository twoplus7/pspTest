/**
 * 
 */
package com.psp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.psp.model.BookmarkData;

/**
 * @author us
 * 
 */

@Repository
public interface BookMarkRepository extends JpaRepository<BookmarkData, Long> {

	@Query("select b.eventId from BookmarkData b where b.userId = :userId AND b.eventId != 0")
	List<Integer> getBookMarkEventByUser(Long userId);

	@Query("select b.blogId from BookmarkData b where b.userId = :userId AND b.blogId != 0")
	List<Integer> getBookMarkBlogByUser(Long userId);

	@Query("select b.jobId from BookmarkData b where b.userId = :userId AND b.jobId != 0")
	List<Integer> getBookMarkJobByUser(Long userId);
}
