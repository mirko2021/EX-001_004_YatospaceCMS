SELECT application_id, application_name, application_username, 
       application_description, application_mode_id, 
       application_mode_name,application_mode_description 
FROM yi_stup_applications NATURAL JOIN yi_stup_applications_profiles
WHERE application_name=? AND application_username=? AND application_mode_name=?;