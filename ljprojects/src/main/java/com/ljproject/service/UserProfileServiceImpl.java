/**
 * 
 */
package com.ljproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljproject.dao.UserDetailsDao;
import com.ljproject.model.UserProfile;

/**
 * @author Nitesh
 *
 */
@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
    UserDetailsDao dao;
 
    public UserProfile findById(int id) {
        return dao.findById(id);
    }
 
    public List<UserProfile> findAll() {
        return dao.findAll();
    }
 
    public List<UserProfile> findAllByUserId(int userId) {
        return dao.findAllByUserId(userId);
    }
     
    public void saveDocument(UserProfile document){
        dao.save(document);
    }
 
    public void deleteById(int id){
        dao.deleteById(id);
    }

	/* (non-Javadoc)
	 * @see com.ljproject.service.UserProfileService#findById(long)
	 */
	@Override
	public UserProfile findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ljproject.service.UserProfileService#findAllByUserId(long)
	 */
	@Override
	public List<UserProfile> findAllByUserId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ljproject.service.UserProfileService#deleteById(long)
	 */
	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		
	}
	

}
