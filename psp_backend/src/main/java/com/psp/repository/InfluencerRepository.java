/**
 * 
 */
package com.psp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.psp.model.Influencer;

/**
 * @author us
 * 
 */
@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {

	/**
	 * @param email
	 * @return
	 */
	// Optional<Influencer> findByEmail(String email);

	@Query(value = "SELECT * FROM influencer WHERE is_top_five_influencer = TRUE LIMIT 5;", nativeQuery = true)
	List<Influencer> getTop5ByIsTopFiveInfluencer(Boolean isTrue);

}
