package stup.faq.database.controller;


import stup.faq.database.dao.UserRoleDao;
import stup.faq.database.dto.UserRoleDto;

/**
 * Контролни механизми за баратање корисничким правилима.
 * @author VM
 * @version 1.0
 */
public class UserRoleController {
	private UserRoleDao userRoleDAO;
	
	public UserRoleController(UserRoleDao userRoleDAO) {
		if(userRoleDAO==null) throw new NullPointerException();
		this.userRoleDAO = userRoleDAO;
	}

	public UserRoleDao getUserRoleDAO() {
		return userRoleDAO;
	}
	
	public void setAsUser(String username) {
		if(username==null) return; 
		if(username.trim().length()==0) return;
		UserRoleDto dto = new UserRoleDto();
		dto.setApplication("yatospace_cms");
		dto.setKey("user");
		dto.setValue("true");
		dto.setUsername(username);
		userRoleDAO.putUserRole(dto);
	}
	
	public void setAsAdministrator(String username) {
		if(username==null) return;
		if(username.trim().length()==0) return; 
		UserRoleDto dto = new UserRoleDto();
		dto.setApplication("yatospace_cms");
		dto.setKey("administrator");
		dto.setValue("true");
		dto.setUsername(username);
		userRoleDAO.putAdministrationRole(dto);
	}
	
	public void resetAsUser(String username) {
		if(username==null) return; 
		if(username.trim().length()==0) return; 
		UserRoleDto dto = new UserRoleDto();
		dto.setApplication("yatospace_cms");
		dto.setKey("user");
		dto.setValue("false"); 
		dto.setUsername(username);
		userRoleDAO.putUserRole(dto);
	}
	
	public void resetAsAdministrator(String username) {
		UserRoleDto dto = new UserRoleDto();
		dto.setApplication("yatospace_cms");
		dto.setKey("administrator");
		dto.setValue("false");
		dto.setUsername(username);
		userRoleDAO.putAdministrationRole(dto);
	}
	
	public boolean isAdministrator(String username){
		try {
			return userRoleDAO.getAdministrationRole(username).getKey().contentEquals("administrator")
			    && userRoleDAO.getAdministrationRole(username).getValue().contentEquals("true");
		}catch(Exception ex) {
			return false;
		}
	}
	
	public boolean isUser(String username) {
		try {
			return userRoleDAO.getUserRole(username).getKey().contentEquals("user")
				&& userRoleDAO.getUserRole(username).getValue().contentEquals("true");
		}catch(Exception ex) {
			return false;
		}
	}
}
