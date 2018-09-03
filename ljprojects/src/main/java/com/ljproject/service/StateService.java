/**
 * 
 */
package com.ljproject.service;

import java.util.List;

import com.ljproject.model.State;


/**
 * @author Nitesh
 *
 */
public interface StateService {
	public void saveState(State user);
	public State findById(long id);
	public List<State> listSate();
	void deleteStateById(long id);
}
