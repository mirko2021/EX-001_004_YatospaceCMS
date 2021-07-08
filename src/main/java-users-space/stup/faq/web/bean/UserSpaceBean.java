package stup.faq.web.bean;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import stup.faq.database.controller.UserRoleController;
import stup.web.center.ControllerListener;
import yatospace.session.bean.LoginBean;
import yatospace.session.generator.SessionBeansGenerator;

/**
 * Зрно корисничког простора. 
 * @author VM
 * @version 1.0
 */
public class UserSpaceBean implements Serializable{
	private static final long serialVersionUID = 1403373504868672717L;
	private transient LoginBean loginBean; 
	private transient UserRoleController controller; 
	
	public void initialize(HttpSession session) {
		boolean loginInit = false; 
		if(loginBean==null)  loginInit = true;
		if(loginBean==null)  loginBean = SessionBeansGenerator.loginBean(session);
		if(controller==null) controller = ControllerListener.getUserRoleController(session); 
		if(loginInit) loginBean.getEventTrigger().putLoginReaction(getClass().getName()+"#initialize^reset", ()->reset());
	}
	
	public void reset() {
		System.out.println("USER SPACE BEAN RESET.");
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}


	public UserRoleController getController() {
		return controller;
	}
	
	public void put(HttpServletRequest request) {
		boolean user  = request.getParameter("usercheck")!=null;
		boolean admin = request.getParameter("admincheck")!=null;
		if(user)  controller.setAsUser(loginBean.getUsername());
		else      controller.resetAsUser(loginBean.getUsername());
		if(admin) controller.setAsAdministrator(loginBean.getUsername());
		else 	  controller.resetAsAdministrator(loginBean.getUsername());
	}
	
	public boolean isUser() {
		try {
			String username = loginBean.getUsername();
			return controller.isUser(username);
		}catch(Exception ex) {
			return false; 
		}
	}
	
	public boolean isAdministrator() {
		try {
			String username = loginBean.getUsername(); 
			return controller.isAdministrator(username);
		}catch(Exception ex) {
			return false; 
		}
	}
}
