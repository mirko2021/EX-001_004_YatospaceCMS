<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean class='stup.faq.web.bean.UserSpaceBean' id='userSpaceBean' scope='session'></jsp:useBean>
<c:out value='${userSpaceBean.initialize(pageContext.session)}'></c:out>

<jsp:useBean class='stup.app.web.bean.ApplicationSpaceBean' id='applicationSpaceBean' scope='session'></jsp:useBean>
<c:out value='${applicationSpaceBean.initialize(pageContext.session)}'></c:out>


<c:if test='${param["user_application_form_submit"] ne null}'>
	<c:out value='${applicationSpaceBean.putUserApplications(pageContext.request)}'></c:out>
</c:if>

<c:if test='${param["admin_application_form_submit"] ne null}'>
	<c:out value='${applicationSpaceBean.putAdministratorApplications(pageContext.request)}'></c:out>
</c:if>