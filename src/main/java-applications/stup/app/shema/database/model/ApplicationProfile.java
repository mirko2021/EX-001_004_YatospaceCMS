package stup.app.shema.database.model;

import java.io.Serializable;

/**
 * Објекат који се веже за информације о модовима апликације. 
 * @author VM
 * @version 1.0
 */
public class ApplicationProfile implements Serializable, Cloneable, Comparable<ApplicationProfile>{
	private static final long serialVersionUID = -4191816979465743283L;
	
	private String applicationModeId = ""; 
	private String applicationModeName = ""; 
	private String applcationModeDescription = "";
	private String applicationId = ""; 

	public String getApplicationModeId() {
		return applicationModeId;
	}

	public void setApplicationModeId(String applicationModeId) {
		if(applicationModeId==null) applicationModeId = ""; 
		this.applicationModeId = applicationModeId;
	}

	public String getApplicationModeName() {
		return applicationModeName;
	}

	public void setApplicationModeName(String applicationModeName) {
		if(applicationModeName==null) applicationModeId = ""; 
		this.applicationModeName = applicationModeName;
	}

	public String getApplcationModeDescription() {
		return applcationModeDescription;
	}

	public void setApplcationModeDescription(String applcationModeDescription) {
		if(applcationModeDescription==null) applcationModeDescription = "";
		this.applcationModeDescription = applcationModeDescription;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		if(applicationId==null) applicationId = "";
		this.applicationId = applicationId;
	}

	@Override
	public int hashCode() {
		return applicationModeId.hashCode(); 
	}
	
	@Override 
	public String toString() {
		return applicationModeId; 
	}
	
	@Override
	public int compareTo(ApplicationProfile o) {
		return applicationModeId.compareTo(o.applicationModeId);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof ApplicationProfile) {
			ApplicationProfile profile = (ApplicationProfile) o; 
			return applicationId.contentEquals(profile.getApplicationId());
		}
		return false;
	}
	
	@Override
	public ApplicationProfile clone() {
		ApplicationProfile profile = new ApplicationProfile();
		profile.applcationModeDescription = this.applcationModeDescription;
		profile.applicationId             = this.applicationId; 
		profile.applicationModeId         = this.applicationModeId; 
		profile.applicationModeName       = this.applicationModeName; 
		return profile; 
	}
}
