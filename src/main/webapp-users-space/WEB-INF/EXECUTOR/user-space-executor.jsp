<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean class='stup.faq.web.bean.UserSpaceBean' id='userSpaceBean' scope='session'></jsp:useBean>
<c:out value='${userSpaceBean.initialize(pageContext.session)}'></c:out>

<c:if test='${param["pgzf_submit"] ne null}'>
	<c:out value="${userSpaceBean.put(pageContext.request)}"></c:out>
</c:if>