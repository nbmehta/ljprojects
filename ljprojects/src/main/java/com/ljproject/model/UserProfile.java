/**
 * 
 */
package com.ljproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author Nitesh
 *
 */
@Entity
@Table(name = "userProfile")
public class UserProfile {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 private String model;
	 
	 private String firstName;
	 
	 private String lastName;
	 
	 
	 
	 
	 public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@Lob
	 private Byte[] image;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public Byte[] getImage() {
		return image;
	}


	public void setImage(Byte[] image) {
		this.image = image;
	}
	 
	 

}
