package com.psp.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.psp.common.UserChangePwd;
import com.psp.common.LoginModel;
import com.psp.common.ResponseData;
import com.psp.dto.UserDto;
import com.psp.enums.HttpStatus;
import com.psp.enums.UserProfileStatus;
import com.psp.exception.PspException;
import com.psp.model.User;
import com.psp.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Resource
	private UserService userService;

	@Resource
	private AuthenticationManager authenticationManager;

	@ApiOperation(value = "Create User", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Transaction successfull"),
			@ApiResponse(code = 201, message = "Transaction Fail") })
	@PostMapping(UserControllerUrl.CREATE_USER)
	public ResponseData createAdmin(@RequestBody UserDto userDto) throws PspException {
		if (Boolean.TRUE.equals(userService.getProfile(userDto.getEmail()))) {
			return new ResponseData(HttpStatus.USER_ALREADY_PRESENT.getStatus(),
					HttpStatus.USER_ALREADY_PRESENT.getMessage());
		}
		userDto.setUserRole(true);
		return userService.createUser(userDto);
	}

	@ApiOperation(value = "Update User", response = User.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Transaction successfull"),
			@ApiResponse(code = 201, message = "Transaction Fail") })
	@PostMapping(UserControllerUrl.UPDATE_USER)
	public ResponseData createUser(@RequestBody UserDto userDto) throws PspException {
		if (Boolean.FALSE.equals(userService.getProfile(userDto.getEmail()))) {
			return new ResponseData(HttpStatus.USER_NOT_FOUND.getStatus(), HttpStatus.USER_NOT_FOUND.getMessage());
		}
		userDto.setUserRole(true);
		return userService.createUser(userDto);
	}

	@ApiIgnore
	@ApiOperation("Create Manager")
	@PostMapping(UserControllerUrl.CREATE_MANAGER)
	public ResponseData createManager(@RequestBody UserDto managerDto) throws PspException {
		if (Boolean.TRUE.equals(userService.getProfile(managerDto.getEmail()))) {
			return new ResponseData(HttpStatus.USER_ALREADY_PRESENT.getStatus(),
					HttpStatus.USER_ALREADY_PRESENT.getMessage());
		}
		// managerDto.setManagerRole(true);
		return userService.createUser(managerDto);
	}

	@PostMapping(value = UserControllerUrl.LOGIN)
	public ResponseData login(@RequestBody LoginModel loginModel) throws PspException {
		return userService.userLoginService(loginModel);
	}

	@DeleteMapping(UserControllerUrl.DELETE_USER)
	public ResponseData deleteInfluencerById(@RequestParam Long userId) throws PspException {
		return userService.deleteInfluencer(userId);
	}

	@GetMapping("get-all-users")
	public ResponseData getAllUsers() {
		return userService.getAllUsers();
	}

	// For Email Verification
	@GetMapping(UserControllerUrl.UPDATE_STATUS_EMAIL)
	public ResponseData updateProfileStatusWithoutToken(@RequestParam UserProfileStatus status,
			@RequestParam String userId, @RequestParam(required = false) String token) throws PspException {
		Long longuserId = Long.parseLong(userId);
		return userService.updateUserProfileStatus(status, longuserId);
	}

	@GetMapping(UserControllerUrl.GET_USER_BY_ID)
	public ResponseData getUserById(@RequestParam Long userId) {
		return userService.getUserById(userId);
	}

	@GetMapping("/user/get-user-by-email")
	public ResponseData getUserByEmail(@RequestParam String email) {
		return userService.getUserByEmail(email);
	}

	@PostMapping(UserControllerUrl.FORGET_PASSWORD)
	public ResponseData forgetPassword(@RequestParam String email) throws PspException {
		return userService.forgetPassword(email);
	}

	@PostMapping(UserControllerUrl.CHANGE_PASSWORD)
	public ResponseData userChangePwd(@RequestBody UserChangePwd userChangePwd) throws PspException {
		return userService.userChangePwd(userChangePwd);

	}

	@PostMapping("/verify-token")
	public ResponseData verifyToken(@RequestParam String token) {
		return userService.verifyToken(token);
	}

	@GetMapping("/get-all-data-by-user")
	public ResponseData getAllDataByUser(@RequestParam String userId) {
		return userService.getAllDataByUserId(userId);
	}

	@GetMapping("/user-newsletter")
	public ResponseData userNewsLetter(@RequestParam String email) throws IOException {

		return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage());
	}

	@GetMapping("/total-user-count")
	public ResponseData totalUserCount() {
		return userService.totalUserCount();
	}

	@PostMapping("/update-user-password")
	public ResponseData updatePassword(@RequestParam Long userId, @RequestParam String oldPassword,
			@RequestParam String newPassword) {
		return userService.updateUserPassword(userId, oldPassword, newPassword);
	}
}
