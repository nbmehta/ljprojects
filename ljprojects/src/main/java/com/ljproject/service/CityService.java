/**
 * 
 */
package com.ljproject.service;

import java.util.List;

import com.ljproject.model.City;
import com.ljproject.model.User;

/**
 * @author Nitesh
 *
 */
public interface CityService {
	public void saveCity(City city);
	public City findById(long id);
	public List<City> listCity();
	void deleteCityById(long id);

}
