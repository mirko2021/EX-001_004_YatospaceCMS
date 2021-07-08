package stup.event.listener;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Модел који се користи за ослушкивање 
 * пријава, одјава, одјава свих сесија 
 * и постављање и извршавање реактивности
 * у односу на ове операције. Очекују се 
 * независне реакције, па по том комбиновање 
 * засвисности је предмет других конролера. 
 * @author VM
 * @version 1.0
 */
public class LogonEventModel implements Serializable{
	private static final long serialVersionUID = 663981602588997285L;
	private HashMap<String, Runnable> loginReactor     = new HashMap<>(); 
	private HashMap<String, Runnable> logoutReactor    = new HashMap<>();
	private HashMap<String, Runnable> logoutAllReactor = new HashMap<>(); 
	
	public synchronized HashMap<String, Runnable> getLoginReactor() {
		return new HashMap<>(loginReactor);
	}
	public synchronized HashMap<String, Runnable> getLogoutReactor() {
		return new HashMap<>(logoutReactor);
	}
	public synchronized HashMap<String, Runnable> getLogoutAllReactor() {
		return new HashMap<>(logoutAllReactor);
	}
	
	public synchronized Runnable getLoginReaction(String id) {
		return loginReactor.get(id); 
	}
	
	public synchronized Runnable getLogoutReaction(String id) {
		return logoutReactor.get(id); 
	}
	
	public synchronized Runnable getLogoutAllReaction(String id) {
		return logoutAllReactor.get(id);
	}
	
	public boolean addLoginReaction(String id, Runnable action) {
		if(id==null) return false;
		if(action==null) return false;
		if(id.trim().length()==0) return false;
		if(loginReactor.get(id)!=null) return false; 
		loginReactor.put(id, action);
		return true; 
	}
	
	public boolean addLogoutReaction(String id, Runnable action) {
		if(id==null) return false;
		if(action==null) return false;
		if(id.trim().length()==0) return false;
		if(logoutReactor.get(id)!=null) return false; 
		logoutReactor.put(id, action); 
		return true; 
	}
	
	public boolean addLogoutAllReaction(String id, Runnable action) {
		if(id==null) return false;
		if(action==null) return false;
		if(id.trim().length()==0) return false;
		if(logoutAllReactor.get(id)!=null) return false; 
		logoutAllReactor.put(id, action); 
		return true;
	}
	
	public boolean addUniversalLogoutReaction(String id, Runnable action) {
		if(id==null) return false;
		if(action==null) return false;
		if(id.trim().length()==0) return false;
		if(logoutReactor.get(id)!=null) return false; 
		if(logoutAllReactor.get(id)!=null) return false; 
		logoutReactor.put(id, action);
		logoutAllReactor.put(id, action); 
		return true;
	}
	
	public void putLoginReaction(String id, Runnable action) {
		if(id==null)              return;
		if(action==null)          return;
		if(id.trim().length()==0) return;
		loginReactor.put(id, action); 
	}
	
	public void putLogoutReaction(String id, Runnable action) {
		if(id==null) return;
		if(action==null) return;
		if(id.trim().length()==0) return;
		logoutReactor.put(id, action); 
	}
	
	public void putLogoutAllReaction(String id, Runnable action) {
		if(id==null)              return;
		if(action==null)          return;
		if(id.trim().length()==0) return;
		logoutAllReactor.put(id, action);
	}
	
	public void putUniversalLogoutReaction(String id, Runnable action) {
		if(id==null) return;
		if(action==null) return;
		if(id.trim().length()==0) return;
		logoutReactor.put(id, action); 
		logoutAllReactor.put(id, action); 
	}
	
	public void removeLoginReaction(String id) {
		if(id==null) return; 
		loginReactor.remove(id); 
	}
	
	public void removeLogoutReaction(String id) {
		if(id==null) return; 
		logoutReactor.remove(id); 
	}
	
	public void removeLogoutAllReaction(String id) {
		if(id==null) return; 
		logoutAllReactor.remove(id); 
	}
	
	public void removeUniversalLogoutReaction(String id) {
		if(id==null) return; 
		logoutReactor.remove(id); 
		logoutAllReactor.remove(id); 
	}
	
	public boolean executeLoginReaction(String id) {
		if(id==null) return false; 
		Runnable action = getLoginReaction(id); 
		if(action==null) return false; 
		action.run();
		return true;
	}
	
	public boolean executeLogoutReaction(String id) {
		if(id==null) return false; 
		Runnable action = getLogoutReaction(id); 
		if(action==null) return false;
		action.run();
		return true; 
	}
	
	public boolean executeLogoutAllReaction(String id) {
		if(id==null) return false;
		Runnable action = getLogoutAllReaction(id); 
		if(action==null) return false; 
		action.run();
		return true;
	}
	
	public void executeLoginEventReactions() {
		for(Runnable runnable: loginReactor.values()) 
			runnable.run(); 
	}
	
	public void executeLogoutEventReactions() {
		for(Runnable runnable: logoutReactor.values())
			runnable.run(); 
	}
	
	public void executeLogoutAllEventReactions() {
		for(Runnable runnable: logoutAllReactor.values())
			runnable.run(); 
	}
}
