package stup.app.web.bean;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import stup.app.shema.database.controller.ApplicationInfoController;
import stup.app.shema.database.model.ApplicationProfile;
import stup.app.shema.database.model.ApplicationSchema;
import stup.faq.database.controller.UserRoleController;
import stup.faq.web.bean.UserSpaceBean;
import stup.web.center.ControllerListener;
import yatospace.session.bean.LoginBean;
import yatospace.session.generator.SessionBeansGenerator;

/**
 * Зрно које се односи на простор апликација. 
 * @author VM
 * @version 1.0
 */
public class ApplicationSpaceBean implements Serializable{
	private static final long serialVersionUID = 4053382309089911623L;
	private transient LoginBean loginBean;
	private transient UserRoleController controller; 
	private transient ApplicationInfoController applicationController; 
	private transient UserSpaceBean userSpaceBean; 
	
	public void initialize(HttpSession session) {
		boolean loginInit = false; 
		if(loginBean==null)  loginInit = true;
		if(loginBean==null)  loginBean = SessionBeansGenerator.loginBean(session);
		if(controller==null) controller = ControllerListener.getUserRoleController(session); 
		if(applicationController==null) applicationController = ControllerListener.getApplicationInfoController(session); 
		if(loginInit) loginBean.getEventTrigger().putLoginReaction(getClass().getName()+"#initialize^reset", ()->reset());
		userSpaceBean = (UserSpaceBean) session.getAttribute("userSpaceBean"); 
		if(userSpaceBean==null) {
			userSpaceBean = new UserSpaceBean(); 
			session.setAttribute("userSpaceBean", userSpaceBean);
		}
	}
	
	public void reset() {
		System.out.println("APPLICATION BEAN RESET");
	}
	
	public void  putUserApplications(HttpServletRequest request) {
		boolean noteReview  = request.getParameter("note_review") != null; 
		boolean linksReview = request.getParameter("linx_review") != null;
		boolean quizReview  = request.getParameter("quiz_review") != null; 
		boolean quizExecute = request.getParameter("quiz_execute")!= null;
		
		ApplicationSchema noteReviewApplicationSchema = new ApplicationSchema(); 
		ApplicationSchema linksReviewApplicationSchema = new ApplicationSchema();
		ApplicationSchema quizReviewApplicationSchema = new ApplicationSchema();
		ApplicationSchema quizExcecuteApplicationSchema = new ApplicationSchema(); 
		
		ApplicationProfile noteReviewApplicationProfile = new ApplicationProfile(); 
		ApplicationProfile linksReviewApplicationProfile = new ApplicationProfile(); 
		ApplicationProfile quizReviewApplicationProfile = new ApplicationProfile(); 
		ApplicationProfile quizExcecuteApplicationProfile = new ApplicationProfile(); 
		
		noteReviewApplicationSchema.setApplicationName("NOTES");
		noteReviewApplicationSchema.setApplicationId("NOTES#USER#REVIEW#"+loginBean.getUsername());
		noteReviewApplicationSchema.setApplicationUsername(loginBean.getUsername());
		noteReviewApplicationSchema.addGeneralMode(noteReviewApplicationProfile);
		
		linksReviewApplicationSchema.setApplicationName("LINX");
		linksReviewApplicationSchema.setApplicationId("LINX#USER#REVIEW#"+loginBean.getUsername());
		linksReviewApplicationSchema.setApplicationUsername(loginBean.getUsername());
		linksReviewApplicationSchema.addGeneralMode(linksReviewApplicationProfile);
		
		quizReviewApplicationSchema.setApplicationName("QUIZ");
		quizReviewApplicationSchema.setApplicationId("QUIZ#USER#REVIEW#"+loginBean.getUsername());
		quizReviewApplicationSchema.setApplicationUsername(loginBean.getUsername());
		quizReviewApplicationSchema.addGeneralMode(quizReviewApplicationProfile);
		
		quizExcecuteApplicationSchema.setApplicationName("QUIZ");
		quizExcecuteApplicationSchema.setApplicationId("QUIZ#USER#EXECUTE#"+loginBean.getUsername());
		quizExcecuteApplicationSchema.setApplicationUsername(loginBean.getUsername());
		quizExcecuteApplicationSchema.addGeneralMode(quizReviewApplicationProfile);
		
		noteReviewApplicationProfile.setApplicationModeId("NOTES#USER#REVIEW#"+loginBean.getUsername());
		noteReviewApplicationProfile.setApplicationId("NOTES#USER#REVIEW#"+loginBean.getUsername());
		noteReviewApplicationProfile.setApplicationModeName("NOTES#USER#REVIEW#"+loginBean.getUsername());
		
		linksReviewApplicationProfile.setApplicationModeId("LINX#USER#REVIEW#"+loginBean.getUsername());
		linksReviewApplicationProfile.setApplicationId("LINX#USER#REVIEW#"+loginBean.getUsername());
		linksReviewApplicationProfile.setApplicationModeName("LINX#USER#REVIEW#"+loginBean.getUsername());
	
		quizReviewApplicationProfile.setApplicationModeId("QUIZ#USER#REVIEW#"+loginBean.getUsername());
		quizReviewApplicationProfile.setApplicationId("QUIZ#USER#REVIEW#"+loginBean.getUsername());
		quizReviewApplicationProfile.setApplicationModeName("QUIZ#USER#REVIEW#"+loginBean.getUsername());
		
		quizExcecuteApplicationProfile.setApplicationModeId("QUIZ#USER#EXECUTE#"+loginBean.getUsername());
		quizExcecuteApplicationProfile.setApplicationId("QUIZ#USER#EXECUTE#"+loginBean.getUsername());
		quizExcecuteApplicationProfile.setApplicationModeName("QUIZ#USER#EXECUTE#"+loginBean.getUsername());
		
		if(quizExecute) applicationController.putApplicationMode(quizExcecuteApplicationSchema, quizExcecuteApplicationProfile);
		if(quizReview)  applicationController.putApplicationMode(quizReviewApplicationSchema, quizReviewApplicationProfile);
		if(linksReview) applicationController.putApplicationMode(linksReviewApplicationSchema, linksReviewApplicationProfile);
		if(noteReview)  applicationController.putApplicationMode(noteReviewApplicationSchema, noteReviewApplicationProfile);
		
		if(!quizExecute) applicationController.removeApplication(quizExcecuteApplicationSchema.getApplicationName(), quizExcecuteApplicationSchema.getApplicationUsername(), quizExcecuteApplicationProfile.getApplicationModeName());
		if(!quizReview)  applicationController.removeApplication(quizReviewApplicationSchema.getApplicationName(), quizReviewApplicationSchema.getApplicationUsername(), quizReviewApplicationProfile.getApplicationModeName());
		if(!linksReview) applicationController.removeApplication(linksReviewApplicationSchema.getApplicationName(), linksReviewApplicationSchema.getApplicationUsername(), linksReviewApplicationProfile.getApplicationModeName());
		if(!noteReview)  applicationController.removeApplication(noteReviewApplicationSchema.getApplicationName(), noteReviewApplicationSchema.getApplicationUsername(), noteReviewApplicationProfile.getApplicationModeName());
	}
	
	public void  putAdministratorApplications(HttpServletRequest request) {
		boolean noteManervar  = request.getParameter("note_manervar") != null; 
		boolean linksManervar = request.getParameter("linx_manervar") != null;
		boolean quizManervar  = request.getParameter("quiz_manervar") != null;
		
		ApplicationSchema noteManervarApplicationSchema = new ApplicationSchema(); 
		ApplicationSchema linksManervarApplicationSchema = new ApplicationSchema();
		ApplicationSchema quizManervarApplicationSchema = new ApplicationSchema();
		
		ApplicationProfile noteManervarApplicationProfile = new ApplicationProfile(); 
		ApplicationProfile linksManervarApplicationProfile = new ApplicationProfile(); 
		ApplicationProfile quizManervarApplicationProfile = new ApplicationProfile();  
		
		noteManervarApplicationSchema.setApplicationName("NOTES");
		noteManervarApplicationSchema.setApplicationId("NOTES#ADMINISTRATOR#MANERVAR#"+loginBean.getUsername());
		noteManervarApplicationSchema.setApplicationUsername(loginBean.getUsername());
		noteManervarApplicationSchema.addGeneralMode(noteManervarApplicationProfile);
		
		linksManervarApplicationSchema.setApplicationName("LINX");
		linksManervarApplicationSchema.setApplicationId("LINX#ADMINISTRATOR#MANERVAR#"+loginBean.getUsername());
		linksManervarApplicationSchema.setApplicationUsername(loginBean.getUsername());
		linksManervarApplicationSchema.addGeneralMode(linksManervarApplicationProfile);
		
		quizManervarApplicationSchema.setApplicationName("QUIZ");
		quizManervarApplicationSchema.setApplicationId("QUIZ#ADMINISTRATOR#MANERVAR#"+loginBean.getUsername());
		quizManervarApplicationSchema.setApplicationUsername(loginBean.getUsername());
		quizManervarApplicationSchema.addGeneralMode(quizManervarApplicationProfile);
		
		noteManervarApplicationProfile.setApplicationModeId("NOTES#ADMINISTRATOR#MANERVAR#"+loginBean.getUsername());
		noteManervarApplicationProfile.setApplicationId("NOTES#ADMINISTRATOR#MANERVAR#"+loginBean.getUsername());
		noteManervarApplicationProfile.setApplicationModeName("NOTES#ADMINISTRATOR#MANERVAR#"+loginBean.getUsername());
		
		linksManervarApplicationProfile.setApplicationModeId("LINX#ADMINISTRATOR#MANERVAR#"+loginBean.getUsername());
		linksManervarApplicationProfile.setApplicationId("LINX#ADMINISTRATOR#MANERVAR#"+loginBean.getUsername());
		linksManervarApplicationProfile.setApplicationModeName("LINX#ADMINISTRATOR#MANERVAR#"+loginBean.getUsername());
	
		quizManervarApplicationProfile.setApplicationModeId("QUIZ#ADMINISTRATOR#MANERVAR#"+loginBean.getUsername());
		quizManervarApplicationProfile.setApplicationId("QUIZ#ADMINISTRATOR#MANERVAR#"+loginBean.getUsername());
		quizManervarApplicationProfile.setApplicationModeName("QUIZ#ADMINISTRATOR#MANERVAR#"+loginBean.getUsername());
		
		if(quizManervar)  applicationController.putApplicationMode(quizManervarApplicationSchema, quizManervarApplicationProfile);
		if(linksManervar) applicationController.putApplicationMode(linksManervarApplicationSchema, linksManervarApplicationProfile);
		if(noteManervar)  applicationController.putApplicationMode(noteManervarApplicationSchema, noteManervarApplicationProfile);
		
		if(!quizManervar)  applicationController.removeApplication(quizManervarApplicationSchema.getApplicationName(), quizManervarApplicationSchema.getApplicationUsername(), quizManervarApplicationProfile.getApplicationModeName());
		if(!linksManervar) applicationController.removeApplication(linksManervarApplicationSchema.getApplicationName(), linksManervarApplicationSchema.getApplicationUsername(), linksManervarApplicationProfile.getApplicationModeName());
		if(!noteManervar)  applicationController.removeApplication(noteManervarApplicationSchema.getApplicationName(), noteManervarApplicationSchema.getApplicationUsername(), noteManervarApplicationProfile.getApplicationModeName());
	}
	
	public boolean administrator() {
		if(loginBean.getUsername() == null) return false;
		if(loginBean.getUsername().trim().length() == 0) return false;
		if(userSpaceBean.isAdministrator() == false) return false; 
		return true;
	}
	
	public boolean user() {
		if(loginBean.getUsername() == null) return false;
		if(loginBean.getUsername().trim().length() == 0) return false;
		if(userSpaceBean.isUser() == false) return false; 
		return true;
	}
	
	public boolean application(String applicationName, String modeName) {
		try {
			if(applicationName==null) return false;
			if(applicationName.trim().length()==0) return false; 
			if(loginBean.getUsername() == null) return false;
			if(loginBean.getUsername().trim().length() == 0) return false;
			modeName+="#"+loginBean.getUsername(); 
			ApplicationSchema applicationSchema = applicationController.getApplication(applicationName, loginBean.getUsername(), modeName); 
			if(applicationSchema==null)  return false; 
			String applicationId = applicationSchema.getApplicationId(); 
			String applicationIdParts[] = applicationId.split("#"); 
			if(applicationIdParts.length<3) return false;
			if(applicationIdParts[1].contentEquals("ADMINISTRATOR") && administrator()) return true; 
			if(applicationIdParts[1].contentEquals("USER") && user()) return true;
			return false;
		}catch(Exception ex) {
			return false;
		}
	}
}
