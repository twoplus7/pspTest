/**
 * 
 */
package com.psp.service;

import java.io.IOException;

import javax.mail.MessagingException;

import com.psp.common.ResponseData;
import com.psp.dto.InfluencerDto;
import com.psp.enums.Status;
import com.psp.exception.PspException;

/**
 * @author us
 * 
 */

public interface IInfluencerService {
	public ResponseData createInfluencer(InfluencerDto influencerDto)
			throws MessagingException, IOException, PspException;

	public ResponseData getInfluencerById(Long Id);

	public ResponseData getAllInfluencer();

	public ResponseData deleteInfluencerById(Long Id);

	public ResponseData updateInfUserStatus(Status status, Long longuserId);

	public ResponseData getTopFiveInfluencers();

	ResponseData updateInfIsTopFive(Boolean isTopFiveInf, Long userId);

}
