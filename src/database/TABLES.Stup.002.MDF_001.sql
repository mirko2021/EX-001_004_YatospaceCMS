USE yatospace_db; 

DROP TABLE IF EXISTS yi_stup_applications; 
CREATE TABLE yi_stup_applications(
	 id_application          INTEGER PRIMARY KEY AUTO_INCREMENT, 
     application_id          VARCHAR(100) NOT NULL, 
     application_name        VARCHAR(100),
     application_username    VARCHAR(100),
     application_profiles    INTEGER, 
     application_description TEXT, 
     CONSTRAINT UNIQUE(application_id, application_username)
);

DROP TABLE IF EXISTS yi_stup_applications_profiles; 
CREATE TABLE yi_stup_applications_profiles(
	id_application_mode          INTEGER PRIMARY KEY AUTO_INCREMENT, 
    application_mode_id          VARCHAR(100) NOT NULL UNIQUE, 
	application_mode_name        VARCHAR(100), 
    application_id               VARCHAR(100) NOT NULL, 
    application_mode_description TEXT
);