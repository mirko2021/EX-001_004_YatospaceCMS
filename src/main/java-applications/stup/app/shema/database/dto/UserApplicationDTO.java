package stup.app.shema.database.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import stup.app.shema.database.model.ApplicationSchema;

/**
 * Елеменат за апликације по кориснику. 
 * @author VM
 * @version 1.0
 */
public class UserApplicationDTO implements Serializable{
	private static final long serialVersionUID = 406030148808919794L;
	
	private String username = ""; 
	private HashMap<String, ApplicationSchema> applicationMap = new HashMap<>();
	
	public String getUsername() {
		return username;
	}
	
	public Map<String, ApplicationSchema> getApplicationMap() {
		return new HashMap<>(applicationMap);
	}
	
	public void setUsername(String username) {
		if(username==null) username = ""; 
		this.username = username;
	}
	
	public void addApplicationSchema(ApplicationSchema schema) {
		if(getApplicationSchema(schema.getApplicationId())==null)
			applicationMap.put(schema.getApplicationId(), schema); 
	}
	
	public void putApplicationSchema(ApplicationSchema schema) {
		applicationMap.put(schema.getApplicationId(), schema);
	}
	
	public ApplicationSchema getApplicationSchema(String id) {
		if(id==null) return null;
		for(ApplicationSchema schema: applicationMap.values()) 
			if(id.contentEquals(schema.getApplicationId())) return schema; 
		return null;
	}
	
	public void removeApplicationSchema(String id) {
		if(id==null) return;
		if(getApplicationSchema(id)==null) return; 
		applicationMap.remove(id); 
	}
}
