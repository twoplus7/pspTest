package com.psp.service;

import com.psp.common.UserChangePwd;
import com.psp.common.LoginModel;
import com.psp.common.ResponseData;
import com.psp.dto.UserDto;
import com.psp.enums.UserProfileStatus;
import com.psp.exception.PspException;

public interface UserService {
	public ResponseData createUser(UserDto user) throws PspException;

	public ResponseData passwordGenerator() throws PspException;

	public Boolean getProfile(String email) throws PspException;

	public ResponseData updateUserProfileStatus(UserProfileStatus status, Long userId) throws PspException;

	public ResponseData forgetPassword(String email) throws PspException;

	public ResponseData userChangePwd(UserChangePwd userChangePwd) throws PspException;

	public ResponseData deleteInfluencer(Long userId) throws PspException;

	public ResponseData userLoginService(LoginModel loginModel) throws PspException;

	public ResponseData getUserById(Long userId);

	public ResponseData verifyToken(String token);

	ResponseData getUserByEmail(String email);

	public ResponseData getAllUsers();

	public ResponseData totalUserCount();

	ResponseData getAllDataByUserId(String userId);

	public ResponseData updateUserPassword(Long userId, String oldPassword, String newPassword);
}
