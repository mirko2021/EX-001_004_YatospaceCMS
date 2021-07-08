package stup.web.center;

import java.util.HashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import stup.app.shema.database.controller.ApplicationInfoController;
import stup.app.shema.database.dao.ApplicationSpaceDAO;
import stup.faq.database.YatospaceDBConnectionPool;
import stup.faq.database.controller.UserRoleController;
import stup.faq.database.dao.UserRoleDao;

/**
 * Ослушкивач који се однси на котролере, адаптере према базама 
 * података, конекцијама. 
 * @author VM
 * @version 1.0
 */
@WebListener
public class ControllerListener implements HttpSessionListener {
	private static YatospaceDBConnectionPool connectionPool; 
	
	private static HashMap<HttpSession, UserRoleDao> userRoleDAOMap = new HashMap<>();
	private static HashMap<HttpSession, ApplicationSpaceDAO> applicationSpaceDAOMap = new HashMap<>(); 
	
	private static HashMap<HttpSession, UserRoleController> userRoleControllerMap = new HashMap<>();
	private static HashMap<HttpSession, ApplicationInfoController> applicationInfoControllerMap  = new HashMap<>(); 
	
    public ControllerListener() {
         connectionPool = YatospaceDBConnectionPool.getConnectionPool();
    }

    public static YatospaceDBConnectionPool getConnectionPool() {
		return connectionPool;
	}

	public synchronized static HashMap<HttpSession, UserRoleDao> getUserRoleDAOMap() {
		return new HashMap<>(userRoleDAOMap);
	}

	public synchronized static HashMap<HttpSession, ApplicationSpaceDAO> getApplicationSpaceDAOMap() {
		return new HashMap<>(applicationSpaceDAOMap);
	}

	public synchronized static HashMap<HttpSession, UserRoleController> getUserRoleControllerMap() {
		return new HashMap<>(userRoleControllerMap);
	}

	public synchronized static HashMap<HttpSession, ApplicationInfoController> getApplicationInfoControllerMap() {
		return new HashMap<>(applicationInfoControllerMap);
	}

	public static void setApplicationInfoControllerMap(
			HashMap<HttpSession, ApplicationInfoController> applicationInfoControllerMap) {
		ControllerListener.applicationInfoControllerMap = applicationInfoControllerMap;
	}

	public void sessionCreated(HttpSessionEvent se)  { 
		UserRoleDao        userRoleDao        = new UserRoleDao(connectionPool);
		UserRoleController userRoleController = new UserRoleController(userRoleDao); 
		
		ApplicationSpaceDAO applicationSpaceDAO = new ApplicationSpaceDAO(connectionPool);
		ApplicationInfoController applicationInfoController = new ApplicationInfoController(applicationSpaceDAO);
		
		synchronized(ControllerListener.class) {
			userRoleDAOMap.put(se.getSession(), userRoleDao); 
			userRoleControllerMap.put(se.getSession(), userRoleController);
			applicationSpaceDAOMap.put(se.getSession(), applicationSpaceDAO);
			applicationInfoControllerMap.put(se.getSession(), applicationInfoController); 
		}
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	synchronized(ControllerListener.class) {
			userRoleDAOMap.remove(se.getSession());
			userRoleControllerMap.remove(se.getSession()); 
			applicationSpaceDAOMap.remove(se.getSession()); 
			applicationInfoControllerMap.remove(se.getSession());
		}
    }
    
    public synchronized static UserRoleDao getUserRoleDAO(HttpSession session) {
    	return userRoleDAOMap.get(session);
    }
    
    public synchronized static ApplicationSpaceDAO getUserApplicationSpaceDao(HttpSession session) {
    	return applicationSpaceDAOMap.get(session);
    }
    
    public synchronized static UserRoleController getUserRoleController(HttpSession session) {
    	return userRoleControllerMap.get(session);
    }
    
    public synchronized static ApplicationInfoController getApplicationInfoController(HttpSession session) {
    	return applicationInfoControllerMap.get(session);
    }
}
