/**
 * 
 */
package com.ljproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ljproject.model.City;
/**
 * @author Nitesh
 *
 */
@Repository("cityRepository")
public interface CityRepository extends JpaRepository<City, Integer> {

}
