/**
 * 
 */
package com.ljproject.dao;

import org.springframework.stereotype.Repository;


/**
 * @author Nitesh
 *
 */

@Repository("userDetailsDaoImpl")
public class UserDetailsDaoImpl implements UserDetailsDao {
	
	
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

		/* (non-Javadoc)
		 * @see com.ljproject.dao.UserDetailsDao#deleteById(int)
		 */
		@Override
		public void deleteById(int id) {
			// TODO Auto-generated method stub
			
		}
	
	}
