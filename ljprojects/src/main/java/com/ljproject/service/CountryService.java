/**
 * 
 */
package com.ljproject.service;

import java.util.List;

import com.ljproject.model.Country;

/**
 * @author Nitesh
 *
 */
public interface CountryService {
	public void saveCountry(Country country);
	public Country findById(long id);
	public List<Country> listCountry();
	void deleteCountryById(long id);

}
