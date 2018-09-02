/**
 * 
 */
package com.ljproject.service;

import java.util.List;

import com.ljproject.model.UserProfile;

/**
 * @author Nitesh
 *
 */
public interface UserProfileService {
	 UserProfile findById(long id);
	 List<UserProfile> findAll();
	 List<UserProfile> findAllByUserId(long id);
	 void saveDocument(UserProfile document);
	 void deleteById(long id);

}
