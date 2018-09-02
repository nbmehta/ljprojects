/**
 * 
 */
package com.ljproject.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ljproject.model.UserProfile;

/**
 * @author Nitesh
 *
 */

@Repository("userDetailsDaoImpl")
public class UserDetailsDaoImpl extends AbstractDao<Integer, UserProfile> implements UserDetailsDao {
	
		@SuppressWarnings("unchecked")
		public List<UserProfile> findAll() {
			Criteria crit = createEntityCriteria();
			return (List<UserProfile>) crit.list();
		}
	
		public void save(UserProfile userProfile) {
			persist(userProfile);
		}
	
		public UserProfile findById(int id) {
			return getByKey(id);
		}
	
		@SuppressWarnings("unchecked")
		public List<UserProfile> findAllByUserId(int userId) {
			Criteria crit = createEntityCriteria();
			Criteria userCriteria = crit.createCriteria("user");
			userCriteria.add(Restrictions.eq("id", userId));
			return (List<UserProfile>) crit.list();
		}
	
		public void deleteById(int id) {
			UserProfile document = getByKey(id);
			delete(document);
		}
	
		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ljproject.dao.UserDetailsDao#updateFailAttempts(java.lang.String)
		 */
		@Override
		public void updateFailAttempts(String username) {
			// TODO Auto-generated method stub
	
		}
	
		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ljproject.dao.UserDetailsDao#resetFailAttempts(java.lang.String)
		 */
		@Override
		public void resetFailAttempts(String username) {
			// TODO Auto-generated method stub
	
		}
	
		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ljproject.dao.UserDetailsDao#deleteUserById(java.lang.Long)
		 */
		@Override
		public void deleteUserById(Long id) {
			// TODO Auto-generated method stub
	
		}
	
	}
