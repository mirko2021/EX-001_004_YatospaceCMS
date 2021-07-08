<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean class='stup.faq.web.bean.UserSpaceBean' id='userSpaceBean' scope='session'></jsp:useBean>
<c:out value='${userSpaceBean.initialize(pageContext.session)}'></c:out>

<jsp:useBean class='stup.app.web.bean.ApplicationSpaceBean' id='applicationSpaceBean' scope='session'></jsp:useBean>
<c:out value='${applicationSpaceBean.initialize(pageContext.session)}'></c:out>

<c:if test="${userSpaceBean.isUser()}">
	<p>КОРИСНИЧКЕ АПЛИКАЦИЈЕ</p>
	<form name='user_application_form' method='POST'>
		<input type='checkbox' name='note_review' id='note_review' value='' ${applicationSpaceBean.application("NOTES", "NOTES#USER#REVIEW")?"checked": ""} />
		<label for='note_review'>Записи : прглед</label><br>
		<input type='checkbox' name='linx_review' id='linx_review' value='' ${applicationSpaceBean.application("LINX", "LINX#USER#REVIEW")?"checked": ""}/>
		<label for='linx_review'>Повезнице : преглед</label><br>
		<input type='checkbox' name='quiz_review' id='quiz_review' value='' ${applicationSpaceBean.application("QUIZ", "QUIZ#USER#REVIEW")?"checked": ""}/>
		<label for='quiz_review'>Квизови : преглед</label><br>
		<input type='checkbox' name='quiz_execute' id='quiz_execute' value='' ${applicationSpaceBean.application("QUIZ", "QUIZ#USER#EXECUTE")?"checked": ""}/>
		<label for='quiz_execute'>Квизови : апликација</label><br><br>
		<input type='submit' id='user_application_form_submit' name='user_application_form_submit' value='Потврда'/>
	</form>
</c:if>
<c:if test="${userSpaceBean.isAdministrator()}">
	<p>АДМИНИСТРАТОРСКЕ АПЛИКАЦИЈЕ</p>
	<form name='admin_application_form' method='POST'>
		<input type='checkbox' name='note_manervar' id='note_manervar' value='' ${applicationSpaceBean.application("NOTES", "NOTES#ADMINISTRATOR#MANERVAR")?"checked": ""}/>
		<label for='note_manervar'>Записи : уређивање</label><br>
		<input type='checkbox' name='linx_manervar' id='linx_manervar' value='' ${applicationSpaceBean.application("LINX", "LINX#ADMINISTRATOR#MANERVAR")?"checked": ""}/>
		<label for='linx_manervar'>Повезнице : уређивање</label><br>
		<input type='checkbox' name='quiz_manervar' id='quiz_manervar' value='' ${applicationSpaceBean.application("QUIZ", "QUIZ#ADMINISTRATOR#MANERVAR")?"checked": ""}/>
		<label for='quiz_manervar'>Квизови : уређивање</label><br><br>
		<input type='submit' id='admin_application_form_submit' name='admin_application_form_submit' value='Потврда'/>
	</form>
</c:if>
<br>