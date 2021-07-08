<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="designMessageBean" class='yatospace.web.gui.bean.MessageBean' scope='session'></jsp:useBean>
<c:out value='${designMessageBean.error("Страница апликације не постоји.")}'></c:out>
<c:out value='${designMessageBean.setError()}'></c:out>

<c:redirect url="/home-logged.jsp"></c:redirect>