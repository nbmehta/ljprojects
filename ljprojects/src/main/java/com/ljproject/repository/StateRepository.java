/**
 * 
 */
package com.ljproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ljproject.model.State;

/**
 * @author Nitesh
 *
 */
@Repository("stateRepository")
public interface StateRepository extends JpaRepository<State, Integer> {

}
