package stup.app.shema.database.dto;

import java.io.Serializable;

import stup.app.shema.database.model.ApplicationProfile;

/**
 * Транспортни објекти кад је у питању транспорт једног профила 
 * апликације. 
 * @author VM
 * @version 1.0
 */
public class ApplicationProfileDTO implements Serializable{
	private static final long serialVersionUID = -5617939068205220072L;
	
	private ApplicationProfile mode;

	public ApplicationProfile getMode() {
		return mode;
	}

	public void setMode(ApplicationProfile mode) {
		this.mode = mode;
	} 
}
