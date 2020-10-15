/**
 * 
 */
package com.psp.service.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.psp.common.ConstData;
import com.psp.common.ResponseData;
import com.psp.dto.InfluencerDto;
import com.psp.enums.HttpStatus;
import com.psp.enums.Status;
import com.psp.exception.PspException;
import com.psp.model.Influencer;
import com.psp.model.Role;
import com.psp.model.User;
import com.psp.repository.InfluencerRepository;
import com.psp.repository.UserRepository;
import com.psp.service.IInfluencerService;

/**
 * @author us
 * 
 */

@Service
public class InfluencerServiceImpl implements IInfluencerService {

	@Resource
	InfluencerRepository infRepository;

	@Resource
	private BCryptPasswordEncoder bcryptEncoder;

	@Resource
	private UserRepository userRepo;

	@Override
	public ResponseData createInfluencer(InfluencerDto influencerDto) throws PspException {
		Influencer influencer = new Influencer();
		influencer.setDataFromDto(influencerDto);

		User user = new User();
		BeanUtils.copyProperties(influencerDto.getUser(), user);
		user.setPassword(bcryptEncoder.encode(user.getPassword()));

		if (influencerDto.getUser().getUserId() == null || influencerDto.getUser().getUserId().intValue() == 0) {
			user.setStatus(Status.INVITED);
		}

		Role role = new Role();
		role.setRoleId(ConstData.ROLE_INFLUENCER);
		user.setRole(role);

		influencer.setUser(user);
		infRepository.save(influencer);

		return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage());
	}

	@Override
	public ResponseData getInfluencerById(Long Id) {
		Optional<Influencer> influencerData = infRepository.findById(Id);
		if (!influencerData.isPresent()) {
			return new ResponseData(HttpStatus.FAIL.getStatus(), HttpStatus.FAIL.getMessage());
		}
		return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), influencerData.get());
	}

	@Override
	public ResponseData getAllInfluencer() {
		List<Influencer> influencerList = infRepository.findAll();
		return (influencerList.isEmpty())
				? new ResponseData(HttpStatus.FAIL.getStatus(), HttpStatus.FAIL.getMessage(),
						"Influencer list is empty")
				: new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), influencerList);
	}

	@Override
	public ResponseData deleteInfluencerById(Long Id) {
		if (infRepository.findById(Id).isPresent()) {
			infRepository.deleteById(Id);
			return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(),
					"Influencer deleted successfully");
		}
		return new ResponseData(HttpStatus.FAIL.getStatus(), HttpStatus.FAIL.getMessage(), "Invalid influencer-Id");
	}

	@Override
	public ResponseData updateInfUserStatus(Status status, Long userId) {

		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			if (status.equals(Status.ACTV)) {
				user.get().setStatus(Status.ACTV);
			} else if (status.equals(Status.INACTV)) {
				user.get().setStatus(Status.INACTV);
			}
			userRepo.save(user.get());
			return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage());
		}
		return new ResponseData(HttpStatus.ID_NOT_VALID.getStatus(), HttpStatus.ID_NOT_VALID.getMessage());
	}

	@Override
	public ResponseData getTopFiveInfluencers() {
		List<Influencer> infList = infRepository.getTop5ByIsTopFiveInfluencer(Boolean.TRUE);
		return (infList.isEmpty()) ? new ResponseData(HttpStatus.NO_DATA.getStatus(), HttpStatus.NO_DATA.getMessage())
				: new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), infList);
	}

	@Override
	public ResponseData updateInfIsTopFive(Boolean isTopFiveInf, Long userId) {
		Optional<Influencer> user = infRepository.findById(userId);
		if (user.isPresent()) {
			user.get().setIsTopFiveInfluencer(isTopFiveInf);
			infRepository.save(user.get());
			return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage());
		}
		return new ResponseData(HttpStatus.ID_NOT_VALID.getStatus(), HttpStatus.ID_NOT_VALID.getMessage());
	}
}
