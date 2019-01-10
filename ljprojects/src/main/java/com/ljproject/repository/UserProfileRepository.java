/**
 * 
 */
package com.ljproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ljproject.model.UserProfile;

/**
 * @author Nitesh
 *
 */
@Repository("userProfileRepository")
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

}
