/**
 * 
 */
package com.ljproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ljproject.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
	
	@Query("select p.content from UserProfile p where p.id = ?1")
	public byte[] findContentById(Integer id);
}
