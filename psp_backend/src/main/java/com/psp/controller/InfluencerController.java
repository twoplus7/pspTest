/**
 * 
 */
package com.psp.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.psp.common.ResponseData;
import com.psp.dto.InfluencerDto;
import com.psp.enums.HttpStatus;
import com.psp.enums.UserProfileStatus;
import com.psp.exception.PspException;
import com.psp.model.Influencer;
import com.psp.service.IInfluencerService;
import com.psp.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author us
 * 
 */
@RestController
public class InfluencerController {

	@Resource
	private IInfluencerService influencerService;

	@Resource
	private UserService userService;

	@ApiOperation(value = "Create User", response = Influencer.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Transaction successfull"),
			@ApiResponse(code = 201, message = "Transaction Fail") })
	@PostMapping("/create-influencer")
	public ResponseData createInfluencer(@RequestBody InfluencerDto dto)
			throws MessagingException, IOException, PspException {
		if (Boolean.TRUE.equals(userService.getProfile(dto.getUser().getEmail()))) {
			return new ResponseData(HttpStatus.USER_ALREADY_PRESENT.getStatus(),
					HttpStatus.USER_ALREADY_PRESENT.getMessage());
		}
		return influencerService.createInfluencer(dto);
	}

	@ApiOperation(value = "Update User", response = Influencer.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Transaction successfull"),
			@ApiResponse(code = 201, message = "Transaction Fail") })
	@PostMapping("update-influencer")
	public ResponseData updateInfluencer(@RequestBody InfluencerDto userDto)
			throws PspException, MessagingException, IOException {
		if (Boolean.FALSE.equals(userService.getProfile(userDto.getUser().getEmail()))) {
			return new ResponseData(HttpStatus.USER_NOT_FOUND.getStatus(), HttpStatus.USER_NOT_FOUND.getMessage());
		}
		return influencerService.createInfluencer(userDto);
	}

	@GetMapping("/get-influencer-by-id")
	public ResponseData getInfluencerById(@RequestParam Long id) {
		return influencerService.getInfluencerById(id);
	}

	@GetMapping("update-inf-status")
	public ResponseData updateProfileStatusWithoutToken(@RequestParam UserProfileStatus status,
			@RequestParam Long userId) throws PspException {
		return userService.updateUserProfileStatus(status, userId);
	}

	// Admin access
	@GetMapping("/get-all-influencers")
	public ResponseData getAllInfluencer() {
		return influencerService.getAllInfluencer();
	}

	@DeleteMapping("/delete-influencer-by-id")
	public ResponseData deleteInfluencerById(@RequestParam Long id) {
		return influencerService.deleteInfluencerById(id);
	}

	@GetMapping("/get-top-five-influencers")
	public ResponseData getTopFiveInfluencers() {
		return influencerService.getTopFiveInfluencers();
	}

	@GetMapping("/update-inf-is-top-five")
	public ResponseData updateInfIsTopFive(@RequestParam Long userId, @RequestParam Boolean isTopFive) {
		return influencerService.updateInfIsTopFive(isTopFive, userId);
	}
}
