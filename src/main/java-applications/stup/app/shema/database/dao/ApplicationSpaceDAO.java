package stup.app.shema.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import stup.app.shema.database.model.ApplicationProfile;
import stup.app.shema.database.model.ApplicationSchema;
import stup.faq.database.YatospaceDBConnectionPool;

/**
 * Адаптер према бази података који се односи 
 * на баратање са повезаношћу и својствима апликација.
 * @author VM
 * @version 1.0
 */
public class ApplicationSpaceDAO {
	public static final String SQL_GET_APPLICATION_MODE_BY_APPLICATION_ID                = "/stup/app/shema/database/sql/get_application_mode_by_application_id.sql";
	public static final String SQL_GET_APPLICATION_MODE_BY_APPLICATION_NAME              = "/stup/app/shema/database/sql/get_application_mode_by_application_name.sql";
	public static final String SQL_GET_APPLICATION_MODE_BY_APPLICATION_NAME_AND_USERNAME = "/stup/app/shema/database/sql/get_application_mode_by_application_name_and_username.sql";
	public static final String SQL_GET_APPLICATION_MODE_AS_TARGET           = "/stup/app/shema/database/sql/get_application_mode_as_target.sql";
	public static final String SQL_GET_APPLICATION_MODE                     = "/stup/app/shema/database/sql/get_application_mode.sql";
	public static final String SQL_GET_APPLICATION                          = "/stup/app/shema/database/sql/get_application.sql";
	public static final String SQL_LIST_APPLICATION_MODES                   = "/stup/app/shema/database/sql/list_application_modes.sql"; 
	public static final String SQL_LIST_APPLICATIONS                        = "/stup/app/shema/database/sql/list_applications";
	public static final String SQL_OP_INSERT_APPLICATION_MODE               = "/stup/app/shema/database/sql/op_insert_application_mode.sql";
	public static final String SQL_OP_INSERT_APPLICATION                    = "/stup/app/shema/database/sql/op_insert_application.sql";
	public static final String SQL_OP_REMOVE_APPLICATION_MODE               = "/stup/app/shema/database/sql/op_remove_application_mode.sql";
	public static final String SQL_OP_REMOVE_APPLICTAION                    = "/stup/app/shema/database/sql/op_remove_application.sql";
	public static final String SQL_OP_UPDATE_APPLICATION_MODE               = "/stup/app/shema/database/sql/op_update_application_mode.sql";
	public static final String SQL_OP_UPDATE_APPLICATION                    = "/stup/app/shema/database/sql/op_update_application.sql"; 
	
	private YatospaceDBConnectionPool connectionPool; 
	public ApplicationSpaceDAO(YatospaceDBConnectionPool connectionPool) {
		if(connectionPool == null) throw new NullPointerException();
		this.connectionPool = connectionPool;
	}
	
	public YatospaceDBConnectionPool getConnectionPool() {
		return connectionPool;
	}
	
	public List<ApplicationSchema> getApplications(String name){
		try {
			ArrayList<ApplicationSchema> applications = new ArrayList<ApplicationSchema>(); 
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream(SQL_GET_APPLICATION_MODE_BY_APPLICATION_NAME))){
				while(scanner.hasNextLine())
					sql += scanner.nextLine() + "\n";
			}
			Connection connection = connectionPool.checkOut();
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, name);
				try(ResultSet resultSet  = statement.executeQuery()){
					while(resultSet.next()) {
						String applicationId   = resultSet.getString("application_id"); 
						String applicationName = resultSet.getString("application_name"); 
						String applicationUsername = resultSet.getString("application_username");  
					    String applicationDescription = resultSet.getString("application_mode_id");  
					    String applicationModeId = resultSet.getString("application_mode_id");
					    String applicationModeName = resultSet.getString("application_mode_name"); 
					    String applicationModeDescription = resultSet.getString("application_mode_description"); 
					    ApplicationSchema schema = new ApplicationSchema(); 
					    ApplicationProfile profile = new ApplicationProfile(); 
					    schema.setApplicationId(applicationId);
					    schema.setApplicationDescription(applicationDescription); 
					    schema.setApplicationUsername(applicationUsername);
					    schema.setApplicationName(applicationName);
					    profile.setApplicationId(applicationId);
					    profile.setApplicationId(applicationModeId);
					    profile.setApplicationModeName(applicationModeName);
					    profile.setApplcationModeDescription(applicationModeDescription);
					    schema.addGeneralMode(profile);
					    applications.add(schema); 
					}
				}
			}finally {
				connectionPool.checkIn(connection);
			}
			return applications; 
		}catch(RuntimeException ex) {
			return new ArrayList<>(); 
		}catch(Exception ex) {
			return new ArrayList<>(); 
		}
	}
	
	
	public List<ApplicationSchema> getApplications(String name, String username){
		try {
			ArrayList<ApplicationSchema> applications = new ArrayList<ApplicationSchema>(); 
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream(SQL_GET_APPLICATION_MODE_BY_APPLICATION_NAME_AND_USERNAME))){
				while(scanner.hasNextLine())
					sql += scanner.nextLine() + "\n";
			}
			Connection connection = connectionPool.checkOut();
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, name);
				statement.setString(2, username);
				try(ResultSet resultSet  = statement.executeQuery()){
					while(resultSet.next()) {
						String applicationId   = resultSet.getString("application_id"); 
						String applicationName = resultSet.getString("application_name"); 
						String applicationUsername = resultSet.getString("application_username");  
					    String applicationDescription = resultSet.getString("application_mode_id");  
					    String applicationModeId = resultSet.getString("application_mode_id");
					    String applicationModeName = resultSet.getString("application_mode_name"); 
					    String applicationModeDescription = resultSet.getString("application_mode_description"); 
					    ApplicationSchema schema = new ApplicationSchema(); 
					    ApplicationProfile profile = new ApplicationProfile(); 
					    schema.setApplicationId(applicationId);
					    schema.setApplicationDescription(applicationDescription); 
					    schema.setApplicationUsername(applicationUsername);
					    schema.setApplicationName(applicationName);
					    profile.setApplicationId(applicationId);
					    profile.setApplicationId(applicationModeId);
					    profile.setApplicationModeName(applicationModeName);
					    profile.setApplcationModeDescription(applicationModeDescription);
					    schema.addGeneralMode(profile);
					    applications.add(schema); 
					}
				}
			}finally {
				connectionPool.checkIn(connection);
			}
			return applications; 
		}catch(RuntimeException ex) {
			return new ArrayList<>(); 
		}catch(Exception ex) {
			return new ArrayList<>(); 
		}
	}
	
	public List<ApplicationSchema> getApplications(String name, String username, String modeName){
		try {
			ArrayList<ApplicationSchema> applications = new ArrayList<ApplicationSchema>(); 
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream(SQL_GET_APPLICATION_MODE_AS_TARGET))){
				while(scanner.hasNextLine())
					sql += scanner.nextLine() + "\n";
			}
			Connection connection = connectionPool.checkOut();
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, name);
				statement.setString(2, username);
				statement.setString(3, modeName);
				try(ResultSet resultSet  = statement.executeQuery()){
					while(resultSet.next()) {
						String applicationId   = resultSet.getString("application_id"); 
						String applicationName = resultSet.getString("application_name"); 
						String applicationUsername = resultSet.getString("application_username");  
					    String applicationDescription = resultSet.getString("application_mode_id");  
					    String applicationModeId = resultSet.getString("application_mode_id");
					    String applicationModeName = resultSet.getString("application_mode_name"); 
					    String applicationModeDescription = resultSet.getString("application_mode_description"); 
					    ApplicationSchema schema = new ApplicationSchema(); 
					    ApplicationProfile profile = new ApplicationProfile(); 
					    schema.setApplicationId(applicationId);
					    schema.setApplicationDescription(applicationDescription); 
					    schema.setApplicationUsername(applicationUsername);
					    schema.setApplicationName(applicationName);
					    profile.setApplicationId(applicationId);
					    profile.setApplicationModeId(applicationModeId);
					    profile.setApplicationModeName(applicationModeName);
					    profile.setApplcationModeDescription(applicationModeDescription);
					    schema.addGeneralMode(profile);
					    applications.add(schema);
					}
				}
			}finally {
				connectionPool.checkIn(connection);
			}
			return applications; 
		}catch(RuntimeException ex) {
			return new ArrayList<>(); 
		}catch(Exception ex) {
			return new ArrayList<>(); 
		}
	}
	
	public void insertApplication(ApplicationSchema schema) {
		if(schema==null) return; 
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream(SQL_OP_INSERT_APPLICATION))){
				while(scanner.hasNextLine())
					sql += scanner.nextLine();
			}
			Connection connection = connectionPool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, schema.getApplicationId());
				statement.setString(2, schema.getApplicationName());
				statement.setString(3, schema.getApplicationUsername());
				statement.setString(4, schema.getApplicationDescription());
				statement.execute(); 
			}finally{
				connectionPool.checkIn(connection);
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}
		catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void upadteApplication(String applicationId, ApplicationSchema schema) {
		if(applicationId==null) return; 
		if(schema==null) return; 
		try {
			String sql = ""; 
			try (Scanner scanner = new Scanner(getClass().getResourceAsStream(SQL_OP_UPDATE_APPLICATION))){
				while(scanner.hasNextLine()) 
					sql += scanner.nextLine();
			}
			Connection connection = connectionPool.checkOut();
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, schema.getApplicationId());
				statement.setString(2, schema.getApplicationName()); 
				statement.setString(3, schema.getApplicationUsername());
				statement.setString(4, schema.getApplicationDescription());
				statement.setString(5, applicationId);
				statement.execute(); 
			}finally {
				connectionPool.checkIn(connection);
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
	}
	
	public void removeApplication(String applicationId) {
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream(SQL_OP_REMOVE_APPLICTAION))){
				while(scanner.hasNextLine())
					sql += scanner.nextLine(); 
			}
			Connection connection = connectionPool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, applicationId);
				statement.execute(); 
			}finally {
				connectionPool.checkIn(connection);
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void instertApplicationMode(ApplicationProfile profile) {
		if(profile==null) return; 
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream(SQL_OP_INSERT_APPLICATION_MODE))) {
				while(scanner.hasNextLine()) 
					sql += scanner.nextLine();
			}
			Connection connection = connectionPool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, profile.getApplicationModeId());
				statement.setString(2, profile.getApplicationModeName());
				statement.setString(3, profile.getApplicationId());
				statement.setString(4, profile.getApplcationModeDescription());
				statement.execute(); 
			}finally {
				connectionPool.checkIn(connection);
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void updateApplicationMode(String oldApplicationMode, ApplicationProfile profile) {
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream(SQL_OP_UPDATE_APPLICATION_MODE))){
				while(scanner.hasNextLine()) 
					sql  += scanner.nextLine(); 
			}
			Connection connection = connectionPool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, profile.getApplicationModeId());
				statement.setString(2, profile.getApplicationModeName());
				statement.setString(3, profile.getApplicationId());
				statement.setString(4, profile.getApplicationModeId());
				statement.setString(5, oldApplicationMode); 
				statement.execute();
			}catch(RuntimeException ex) {
				throw ex;
			}catch(Exception ex) {
				throw new RuntimeException(ex); 
			}finally {
				connectionPool.checkIn(connection);
			}
		}catch(RuntimeException ex){
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void removeApplicationMode(String modeId) {
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream(SQL_OP_REMOVE_APPLICATION_MODE))){
				while(scanner.hasNextLine())
					sql += scanner.nextLine();
			}
			Connection connection = connectionPool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, modeId);
				statement.execute();
			}catch(RuntimeException ex) {
				throw ex; 
			}catch(Exception ex){
				throw new RuntimeException(ex);
			}finally {
				connectionPool.checkIn(connection);
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
