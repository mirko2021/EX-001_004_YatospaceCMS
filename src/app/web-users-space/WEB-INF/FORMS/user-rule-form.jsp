<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean class='stup.faq.web.bean.UserSpaceBean' id='userSpaceBean' scope='session'></jsp:useBean>
<c:out value='${userSpaceBean.initialize(pageContext.session)}'></c:out>

<p>БАРАТАЊЕ УЛОГАМА</p>
<form name='page_zone_form' method='POST'>
	<input type='checkbox' name='usercheck' id='usercheck' ${userSpaceBean.isUser()?"checked":""}/>
	<label for='usercheck'>Корисник</label><br>
	<input type='checkbox' name='admincheck' id='admincheck' ${userSpaceBean.isAdministrator()?"checked":""}/>
	<label for='usercheck'>Администратор</label>
	<br><br><input type='submit' name='pgzf_submit' value='Потврда'/>
</form><br>