/**
 * 
 */
package com.ljproject.serviceImpl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljproject.model.UserProfile;
import com.ljproject.repository.CityRepository;
import com.ljproject.repository.UserProfileRepository;
import com.ljproject.service.UserProfileService;

/**
 * @author Nitesh
 *
 */
@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	UserProfileRepository userProfileRepository;

	 public static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.ljproject.service.UserProfileService#saveUserProfile(com.ljproject.model.UserProfile)
	 */
	@Override
	public void saveUserProfile(UserProfile userProfile) {
		// TODO Auto-generated method stub
		userProfileRepository.save(userProfile);
		
	}

}
