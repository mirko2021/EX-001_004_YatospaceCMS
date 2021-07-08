package stup.app.shema.database.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Схема са профилом и модом апликација.
 * @author MV
 * @version 1.0 
 */
public class ApplicationSchema implements Serializable, Cloneable, Comparable<ApplicationSchema>{
	private static final long serialVersionUID = -694025786404595343L;
	private String applicationId          = ""; 
	private String applicationName        = ""; 
	private String applicationUsername    = ""; 
	private String applicationDescription = ""; 
	private List<String> modeIds                 = new ArrayList<>();
	private List<ApplicationProfile> profiles    = new ArrayList<>();
	

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		if(applicationId == null) applicationId = ""; 
		this.applicationId = applicationId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		if(applicationName == null) applicationName=""; 
		this.applicationName = applicationName;
	}

	public String getApplicationUsername() {
		return applicationUsername;
	}

	public void setApplicationUsername(String applicationUsername) {
		if(applicationUsername == null) applicationUsername = null; 
		this.applicationUsername = applicationUsername;
	}

	public String getApplicationDescription() {
		return applicationDescription;
	}

	public void setApplicationDescription(String applicationDescription) {
		if(applicationDescription == null) applicationDescription = "";
		this.applicationDescription = applicationDescription;
	}

	public List<String> getModeIds() {
		return new ArrayList<>(modeIds);
	}
	
	public List<ApplicationProfile> getProfile(){
		return new ArrayList<>(profiles);
	}
	
	public void addModeId(String modeId) {
		if(modeIds.contains(modeId)) return; 
		this.modeIds.add(modeId); 
	}
	
	public void addProfile(ApplicationProfile profile) {
		if(profiles.contains(profile)) return; 
		this.profiles.add(profile); 
	}
	
	public void addGeneralMode(ApplicationProfile profile) {
		if(modeIds.contains(profile.getApplicationId())) return; 
		if(profiles.contains(profile)) return; 
		modeIds.add(profile.getApplicationId());
		profiles.add(profile); 
	}
	
	public void putModeId(String modeId) {
		if(modeIds.contains(modeId)) modeIds.remove(modeId); 
		this.modeIds.add(modeId); 
	}
	
	public void putProfile(ApplicationProfile profile) {
		if(profile==null) return; 
		if(profiles.contains(profile)) profiles.remove(profile);
		this.profiles.add(profile); 
	}
	
	public void putGeneralMode(ApplicationProfile profile) {
		if(profile==null) return;
		putModeId(profile.getApplicationModeId());
		putProfile(profile); 
	}
		
	@Override 
	public int hashCode() {
		return applicationId.hashCode();
	}
	
	@Override
	public String toString() {
		return applicationId; 
	}
	
	@Override
	public ApplicationSchema clone() {
		ApplicationSchema schema = new ApplicationSchema();
		schema.applicationDescription = applicationDescription; 
		schema.applicationId          = applicationId; 
		schema.applicationName        = applicationName; 
		schema.applicationUsername    = applicationUsername; 
		schema.modeIds  = new ArrayList<>(modeIds);
		schema.profiles = new ArrayList<>(profiles);
		ArrayList<ApplicationProfile> profiles = new ArrayList<>();
		for(ApplicationProfile profile: schema.profiles) 
			profiles.add(profile.clone());
		schema.profiles = profiles; 
		return schema; 
	}
	
	
	@Override
	public int compareTo(ApplicationSchema o) {
		return applicationId.compareTo(o.applicationId);
	}
	
	@Override 
	public boolean equals(Object o) {
		if(o instanceof ApplicationSchema) {
			ApplicationSchema as = (ApplicationSchema) o; 
			return as.applicationId.contentEquals(applicationId);  
		}
		return false; 
	}
}
