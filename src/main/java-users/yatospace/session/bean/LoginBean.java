package yatospace.session.bean;

import java.io.Serializable;
import java.util.List;

import stup.event.listener.LogonEventModel;
import yatospace.gui.session.logic.AppControlCenter;
import yatospace.user.session.model.Session;
import yatospace.web.ajax.anotation.AjaxAccessable;
import yatospace.web.ajax.element.AjaxRequestContext;
import yatospace.web.ajax.model.AjaxExecutable;

/**
 * Зрно које се користи при логовању корисника. 
 * @author MV
 * @version 2.0 
 */
public class LoginBean implements Serializable, AjaxExecutable{
	private static final long serialVersionUID = 5001271650055526359L;
	private transient LogonEventModel eventTrigger = new LogonEventModel();
	private String username = "";
	private String sessionId = ""; 
	
	public LogonEventModel getEventTrigger() {
		return eventTrigger;
	}


	public String getUsername() {
		return username;
	}
	
	
	public void setUsername(String username) {
		if(username==null) username = ""; 
		this.username = username;
	}

	
	
	public String getSessionId() {
		return sessionId;
	}


	public void setSessionId(String sessionId) {
		if(sessionId==null) sessionId = ""; 
		this.sessionId = sessionId;
	}


	@Override
	public void importFrom(AjaxRequestContext request) {
		username = request.getRequest().get("username").getAsString(); 
		if(username==null) username = ""; 
	}

	@Override
	public void exportTo(AjaxRequestContext request) {
		request.getResponse().addProperty("username", username);
	}
	
	public void balanceLogged() {
		if(AppControlCenter.mainCenter.getController().getRegisterEngine().get(sessionId)==null) {
			username = ""; 
			sessionId = ""; 
		}
	}
	
	public boolean logged() { 
		balanceLogged(); 
		return username.trim().length()!=0 && AppControlCenter.mainCenter.getController().getRegisterEngine().get(sessionId)!=null; 
	}
	
	
	@AjaxAccessable("login")
	public void login(AjaxRequestContext request) {
		if(logged()) {
			request.getResponse().addProperty("success", false); 
			eventTrigger.executeLoginEventReactions();
			return; 
		}
		try {
			String username = request.getRequest().get("parameters").getAsJsonObject().get("username").getAsString(); 
			String password = request.getRequest().get("parameters").getAsJsonObject().get("password").getAsString();
			String sessionId = request.getRequest().get("parameters").getAsJsonObject().get("session_id").getAsString(); 
			
			if(AppControlCenter.mainCenter.getController().login(username, password, sessionId)) {
				this.username = username; 
				this.sessionId = sessionId;
				request.getResponse().addProperty("success", true);
			}
			else {
				this.username = "";
				this.sessionId = ""; 
				request.getResponse().addProperty("success", false);
			}
			eventTrigger.executeLoginEventReactions();
		}catch(Exception ex) {
			request.getResponse().addProperty("success", false);
		}
	}
	
	@AjaxAccessable("logout")
	public void logout(AjaxRequestContext request) {
		if(logged()) {
			AppControlCenter.mainCenter.getController().getRegisterEngine().logout(sessionId);
			eventTrigger.executeLogoutReaction(sessionId);
		}
		request.getResponse().addProperty("success", true);
		sessionId = ""; 
		username = ""; 
	}
	
	public boolean login(String username, String password, String sessionId) {
		if(username==null) return false; 
		if(password==null) return false; 
		if(sessionId==null) return false; 
		if(username.trim().length()==0) return false; 
		if(password.trim().length()==0) return false; 
		if(sessionId.trim().length()==0) return false; 
		if(logged()) return false;
		try {
			if(AppControlCenter.mainCenter.getController().getRegisterEngine().login(username, password, sessionId)) {
				this.username = username; 
				this.sessionId = sessionId;
				eventTrigger.executeLoginEventReactions();
				return true; 
			}
			return false; 
		}catch(Exception ex) {
			return false; 
		}
	}
	
	public void logout() {
		if(logged()) {
			AppControlCenter.mainCenter.getController().getRegisterEngine().logout(sessionId);
			this.username = ""; 
			this.sessionId = "";
			eventTrigger.executeLogoutReaction(sessionId); 
		}
	}
	
	public void logoutAll() {
		if(logged()) {
			AppControlCenter.mainCenter.getController().getRegisterEngine().logoutAll(username);
			this.username = ""; 
			this.sessionId = "";
			eventTrigger.executeLogoutAllReaction(sessionId); 
		}
	}
	
	public void logout(List<String> sessionIds) {
		if(logged()) {
			for(String sessionId: sessionIds) {
				if(!sessionId.contentEquals(this.sessionId)) {
					Session session = AppControlCenter.mainCenter.getController().getRegisterEngine().get(sessionId); 
					if(!session.getUserId().contentEquals(this.username)) continue;
					AppControlCenter.mainCenter.getController().getRegisterEngine().logout(sessionId); 
				}
				else { 
					logout(); 
				}
			}
		}
	}
}
