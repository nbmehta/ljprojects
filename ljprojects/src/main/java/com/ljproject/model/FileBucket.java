/**
 * 
 */
package com.ljproject.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Nitesh
 *
 */
public class FileBucket {
	
	MultipartFile file;
    
    String description;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
