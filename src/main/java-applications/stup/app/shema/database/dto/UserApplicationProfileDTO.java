package stup.app.shema.database.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import stup.app.shema.database.model.ApplicationProfile;

/**
 * Апликацје са профилима/модовима за одређеног корисника.
 * @author VM
 * @version 1.0
 */
public class UserApplicationProfileDTO implements Serializable{
	private static final long serialVersionUID = 406030148808919794L;
	private String username = ""; 
	private HashMap<String, ApplicationProfile> applicationMap = new HashMap<>();
	
	public String getUsername() {
		return username;
	}
	
	public Map<String, ApplicationProfile> getApplicationMap() {
		return new HashMap<>(applicationMap);
	}
	
	public void setUsername(String username) {
		if(username==null) username = ""; 
		this.username = username;
	}
	
	public void addApplicationMode(ApplicationProfile schema) {
		if(getApplicationMode(schema.getApplicationId())==null)
			applicationMap.put(schema.getApplicationId(), schema); 
	}
	
	public void putApplicationMode(ApplicationProfile schema) {
		applicationMap.put(schema.getApplicationId(), schema);
	}
	
	public ApplicationProfile getApplicationMode(String id) {
		if(id==null) return null;
		for(ApplicationProfile schema: applicationMap.values()) 
			if(id.contentEquals(schema.getApplicationId())) return schema; 
		return null;
	}
	
	public void removeApplicationProfile(String id) {
		if(id==null) return;
		if(getApplicationMode(id)==null) return; 
		applicationMap.remove(id); 
	}
}
