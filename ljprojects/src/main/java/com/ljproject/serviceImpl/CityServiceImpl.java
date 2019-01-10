/**
 * 
 */
package com.ljproject.serviceImpl;

import java.util.List;


import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ljproject.model.City;
import com.ljproject.repository.CityRepository;
import com.ljproject.service.CityService;

/**
 * @author Nitesh
 *
 */
@Service("cityService")
@Transactional
public class CityServiceImpl implements CityService{
	
	@Autowired
	CityRepository cityRepository;

	 public static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

	
	 
	@Override
	public void saveCity(City city) {
		cityRepository.save(city);
	}

	/* (non-Javadoc)
	 * @see com.ljproject.service.CityService#findById(long)
	 */
	@Override
	public City findById(long id) {

		return cityRepository.findOne((int) id);
	}

	/* (non-Javadoc)
	 * @see com.ljproject.service.CityService#listCity()
	 */
	@Override
	public List<City> listCity() {
		List<City> listcity=cityRepository.findAll();
		return listcity;
	}

	/* (non-Javadoc)
	 * @see com.ljproject.service.CityService#deleteCityById(long)
	 */
	public void deleteCityById(Integer id) {
		cityRepository.delete(id);
		
	}

	/* (non-Javadoc)
	 * @see com.ljproject.service.CityService#deleteCityById(long)
	 */
	@Override
	public void deleteCityById(long id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.ljproject.service.CityService#deleteCityById(long)
	 */
	
}
