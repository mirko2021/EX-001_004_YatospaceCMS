package stup.app.shema.database.controller;

import java.util.List;

import stup.app.shema.database.dao.ApplicationSpaceDAO;
import stup.app.shema.database.model.ApplicationProfile;
import stup.app.shema.database.model.ApplicationSchema;

/**
 * Контролер за информације од контролера. 
 * @author VM
 * @version 1.0
 */
public class ApplicationInfoController{
	private ApplicationSpaceDAO dao;
	
	public ApplicationInfoController(ApplicationSpaceDAO dao) {
		if(dao==null) throw new NullPointerException();
		this.dao =  dao; 
	}
	
	public ApplicationSpaceDAO getDao() {
		return dao;
	}
	
	public void putApplicationMode(ApplicationSchema schema, ApplicationProfile profile) {
		try {
			if(schema==null)   return;
			if(profile==null)  return;
			if(!schema.getApplicationId().contentEquals(profile.getApplicationId())) return; 
			if(dao.getApplications(schema.getApplicationName(), schema.getApplicationUsername(), profile.getApplicationModeName()).size()!=1) {
				dao.insertApplication(schema);
				dao.instertApplicationMode(profile);
			}else {
				dao.upadteApplication(schema.getApplicationId(), schema); 
				dao.updateApplicationMode(profile.getApplicationId(), profile); 
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void removeApplication(String applicationName, String username, String modeName) {
		if(applicationName==null) return; 
		if(username==null) return; 
		if(modeName==null) return; 
		List<ApplicationSchema> application = dao.getApplications(applicationName, username, modeName);
		if(application.size()!=1) return;
		ApplicationSchema schema = application.get(0); 
		ApplicationProfile profile = schema.getProfile().get(0);
		if(profile==null) return; 
		dao.removeApplicationMode(profile.getApplicationModeId());
		dao.removeApplication(schema.getApplicationId());
	}
	
	
	public ApplicationSchema getApplication(String applicationName, String username, String modeName) {
		List<ApplicationSchema> application = dao.getApplications(applicationName, username, modeName);
		if(application.size()!=1) return null;
		return application.get(0);
	}
}
