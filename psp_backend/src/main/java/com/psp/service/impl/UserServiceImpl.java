package com.psp.service.impl;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.psp.common.UserChangePwd;
import com.psp.common.ConstData;
import com.psp.common.LoginModel;
import com.psp.common.ResponseData;
import com.psp.dto.UserDto;
import com.psp.enums.HttpStatus;
import com.psp.enums.Status;
import com.psp.enums.UserProfileStatus;
import com.psp.exception.PspException;
import com.psp.model.Role;
import com.psp.model.User;
import com.psp.repository.UserRepository;
import com.psp.service.IEventService;
import com.psp.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Resource
	private UserRepository userRespository;

	@Resource
	private BCryptPasswordEncoder bcryptEncoder;

	@Resource
	private IEventService eventService;

	@Value("${invitation.url}")
	private String invitationUrl;

	@Value("${frontend.endpoint}")
	private String frontEndEndpoint;

	public UserDetails loadUserByUsername(String username) {
		User user = userRespository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRole()));
		return authorities;
	}

	@Override
	public ResponseData createUser(UserDto userDto) throws PspException {

		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		String password = userDto.getPassword();

		user.setPassword(bcryptEncoder.encode(password));
		user.setStatus(Status.ACTV);

		try {
			Role role = new Role();
			role.setRoleId(ConstData.ROLE_USER);

			user.setRole(role);
			userRespository.save(user);

			return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage());
		} catch (Exception e) {
			// throw new PspException(HttpStatus.FAIL, e);
			return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), e);
		}
	}

	@Override
	public ResponseData userLoginService(LoginModel loginModel) throws PspException {

		try {
			final Authentication authentication = null;

			SecurityContextHolder.getContext().setAuthentication(authentication);

			if (Boolean.TRUE.equals(getProfile(loginModel.getEmail()))) {
				final String token = null;
				return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), null, token);
			} else {
				return new ResponseData(HttpStatus.YOUR_ACCOUNT_IS_INACTIVE.getStatus(),
						HttpStatus.YOUR_ACCOUNT_IS_INACTIVE.getMessage());
			}
		} catch (Exception e) {
			throw new PspException(HttpStatus.EMAIL_PASSSWORD_INCORRECT, e);
		}
	}

	@Override
	public ResponseData passwordGenerator() throws PspException {
		try {
			// chose a Character random from this String
			byte[] bytes = new byte[7];
			new SecureRandom().nextBytes(bytes);
			String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);

			return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), token);
		} catch (Exception e) {
			throw new PspException(HttpStatus.SOMETHING_WRONG, e);
		}
	}

	@Override
	public Boolean getProfile(String email) throws PspException {
		User user = userRespository.findByEmail(email);
		return (user != null && !user.getStatus().equals(Status.DELETED)) ? Boolean.TRUE : Boolean.FALSE;

	}

	@Override
	public ResponseData updateUserProfileStatus(UserProfileStatus status, Long userId) throws PspException {
		Optional<User> user = userRespository.findById(userId);
		if (user.isPresent()) {
			if (status.equals(UserProfileStatus.ACTV)) {
				user.get().setStatus(Status.ACTV);
			} else if (status.equals(UserProfileStatus.INACTV)) {
				user.get().setStatus(Status.INACTV);
			}
			userRespository.save(user.get());
			return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage());
		}
		return new ResponseData(HttpStatus.ID_NOT_VALID.getStatus(), HttpStatus.ID_NOT_VALID.getMessage());
	}

	@Override
	public ResponseData deleteInfluencer(Long userId) throws PspException {
		Optional<User> user = userRespository.findById(userId);
		if (user.isPresent()) {
			user.get().setStatus(Status.DELETED);
			userRespository.save(user.get());
			return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage());
		} else {
			return new ResponseData(HttpStatus.ID_NOT_VALID.getStatus(), HttpStatus.ID_NOT_VALID.getMessage());
		}
	}

	@Override
	public ResponseData forgetPassword(String email) throws PspException {
		return null;
	}

	@Override
	public ResponseData userChangePwd(UserChangePwd userChangePwd) throws PspException {
		Optional<User> user = userRespository.findById(userChangePwd.getUserId());
		if (user.isPresent()) {
			user.get().setPassword(bcryptEncoder.encode(userChangePwd.getPassword()));
			userRespository.save(user.get());
			return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), "PWD HAS BEEN CHANGED");
		} else {
			return new ResponseData(HttpStatus.TOKEN_EXPIRED.getStatus(), HttpStatus.TOKEN_EXPIRED.getMessage());
		}
	}

	@Override
	public ResponseData getUserById(Long userId) {
		Optional<User> userData = userRespository.findById(userId);
		if (userData.isPresent()) {
			return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), userData.get());
		} else {
			return new ResponseData(HttpStatus.USER_NOT_FOUND.getStatus(), HttpStatus.USER_NOT_FOUND.getMessage());
		}

	}

	@Override
	public ResponseData getUserByEmail(String email) {
		User user = userRespository.findByEmail(email);
		return (user != null) ? new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), user)
				: new ResponseData(HttpStatus.FAIL.getStatus(), HttpStatus.FAIL.getMessage());
	}

	@Override
	public ResponseData verifyToken(String token) {
		return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage());
	}

	@Override
	public ResponseData totalUserCount() {
		List<User> userList = userRespository.findAll();
		return (userList.isEmpty())
				? new ResponseData(HttpStatus.NO_DATA.getStatus(), HttpStatus.NO_DATA.getMessage(), "No Data")
				: new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage(), userList.size());
	}

	@Override
	public ResponseData updateUserPassword(Long userId, String oldPwd, String newPwd) {
		Optional<User> user = userRespository.findById(userId);
		if (user.isPresent()) {
			String dbPassword = user.get().getPassword();
			if (bcryptEncoder.matches(oldPwd, dbPassword)) {
				user.get().setPassword(bcryptEncoder.encode(newPwd));
				userRespository.save(user.get());
				return new ResponseData(HttpStatus.OK.getStatus(), HttpStatus.OK.getMessage());
			}
			return new ResponseData(HttpStatus.INVALID_PWD.getStatus(), HttpStatus.INVALID_PWD.getMessage());
		}
		return new ResponseData(HttpStatus.ID_NOT_VALID.getStatus(), HttpStatus.ID_NOT_VALID.getMessage());
	}

	@Override
	public ResponseData getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData getAllDataByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
