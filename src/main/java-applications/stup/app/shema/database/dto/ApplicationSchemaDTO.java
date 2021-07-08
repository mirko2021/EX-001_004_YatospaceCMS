package stup.app.shema.database.dto;

import java.io.Serializable;

import stup.app.shema.database.model.ApplicationSchema;

/**
 * Транспорт о објекта података о једној апликацији. 
 * @author VM
 * @version 1.0
 */
public class ApplicationSchemaDTO implements Serializable{
	private static final long serialVersionUID = 5494881067222271586L;
	private ApplicationSchema schema;
	
	public ApplicationSchema getSchema() {
		return schema;
	}
	public void setSchema(ApplicationSchema schema) {
		this.schema = schema;
	} 
}
