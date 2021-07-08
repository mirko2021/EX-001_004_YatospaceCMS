<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='baseBean' class='yatospace.session.bean.BaseBean'></jsp:useBean>
<c:out value='${baseBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<jsp:useBean id='designGeneralBean' class='yatospace.web.gui.bean.GeneralBean' scope='session'></jsp:useBean>
<c:out value='${designGeneralBean.initialize(pageContext.session).avoidSyntaxLexicalStream()}'></c:out>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:useBean class='stup.faq.web.bean.UserSpaceBean' id='userSpaceBean' scope='session'></jsp:useBean>
<c:out value='${userSpaceBean.initialize(pageContext.session)}'></c:out>

<jsp:useBean class='stup.app.web.bean.ApplicationSpaceBean' id='applicationSpaceBean' scope='session'></jsp:useBean>
<c:out value='${applicationSpaceBean.initialize(pageContext.session)}'></c:out>

<c:if test='${not designGeneralBean.serviceBean.isLoggedUser(pageContext.session) eq true}'>
	<dl class='index_dl'>
		<dt><b>ОПШТЕ</b></dt>
		<dd><br></dd>
		<dd><a href="${pageContext.request.contextPath}/home-users.jsp">Начелна</a></dd>
		<dd><a href="${pageContext.request.contextPath}/home-login.jsp">Пријава</a></dd>
		<dd><a href="${pageContext.request.contextPath}/home-register.jsp">Регистрација</a></dd>
	</dl>
</c:if>

<c:if test='${designGeneralBean.serviceBean.isLoggedUser(pageContext.session) eq true}'>
	<dl class='index_dl'>
		<dt><b>КОРИСНИК</b></dt>
		<dd><br></dd>
		<dd><font face='YI Courier New'><c:out value='${baseBean.loginBean.username}'></c:out></font></dd>
		<dd><br></dd>
		<dt><b>ОПШТЕ</b></dt>
		<dd><br></dd>
		<dd><a href="${pageContext.request.contextPath}/home-admin.jsp">Администрација</a></dd>
		<dd><a href="${pageContext.request.contextPath}/home-logged.jsp">Начелна</a></dd>
		<dd><a href="${pageContext.request.contextPath}/home-logout.jsp">Одјава</a></dd>
		<dd><br></dd>
		<dd><a href="${pageContext.request.contextPath}/home-user-space.jsp">Корисник</a></dd>
		<dd><a href="${pageContext.request.contextPath}/home-application-space.jsp">Апликације</a></dd>
	</dl>
</c:if>

<c:if test='${designGeneralBean.serviceBean.isLoggedUser(pageContext.session) eq true}'>
	<c:if test='${applicationSpaceBean.administrator()}'>
		<dl class='index_dl' id='administriranje_index'>
			<dt><b>АДМИНИСТРИРАЊЕ</b></dt>
			<dd><br></dd>
			<c:set var='n' value='0'></c:set>
			<c:if test='${applicationSpaceBean.application("NOTES", "NOTES#ADMINISTRATOR#MANERVAR")}'>
				<dd><a href='${pageContext.request.contextPath}/administartor-manervar-app-branch.jsp?app=NOTES%23ADMINISTRATOR%23MANERVAR'>Биљешке</a></dd>
				<c:set var='n' value='${n+1}'></c:set>
			</c:if>
			<c:if test='${applicationSpaceBean.application("LINX", "LINX#ADMINISTRATOR#MANERVAR")}'>
				<dd><a href='${pageContext.request.contextPath}/administartor-manervar-app-branch.jsp?app=LINX%23ADMINISTRATOR%23MANERVAR'>Повезнице</a></dd>
				<c:set var='n' value='${n+1}'></c:set>
			</c:if>
			<c:if test='${applicationSpaceBean.application("QUIZ", "QUIZ#ADMINISTRATOR#MANERVAR")}'>
				<dd><a href='${pageContext.request.contextPath}/administartor-manervar-app-branch.jsp?app=QUIZ%23ADMINISTRATOR%23MANERVAR'>Квизови</a></dd>
				<c:set var='n' value='${n+1}'></c:set>
			</c:if>
		</dl>
		<c:if test="${n eq 0}"><script>document.getElementById('administriranje_index').style.display='none'</script></c:if>
	</c:if>
	
	<c:if test='${applicationSpaceBean.user()}'>
		<dl class='index_dl' id='pregled_index'>
			<dt><b>ПРЕГЛЕД</b></dt>
			<dd><br></dd>
			<c:set var='n' value='0'></c:set>
			<c:if test='${applicationSpaceBean.application("NOTES", "NOTES#USER#REVIEW")}'>
				<dd><a href='${pageContext.request.contextPath}/user-review-app-branch.jsp?app=NOTES%23USER%23REVIEW'>Биљешке</a></dd>
				<c:set var='n' value='${n+1}'></c:set>
			</c:if>
			<c:if test='${applicationSpaceBean.application("LINX", "LINX#USER#REVIEW")}'>
				<dd><a href='${pageContext.request.contextPath}/user-review-app-branch.jsp?app=LINX%23USER%23REVIEW'>Повезнице</a></dd>
				<c:set var='n' value='${n+1}'></c:set>
			</c:if>
			<c:if test='${applicationSpaceBean.application("QUIZ", "QUIZ#USER#REVIEW")}'>
				<dd><a href='${pageContext.request.contextPath}/user-review-app-branch.jsp?app=QUIZ%23USER%23REVIEW'>Квизови</a></dd>
				<c:set var='n' value='${n+1}'></c:set>
			</c:if>
		</dl>
		<c:if test="${n eq 0}"><script>document.getElementById('pregled_index').style.display='none'</script></c:if>
	</c:if>
	
	<c:if test='${applicationSpaceBean.user()}'>
		<dl class='index_dl' id='izvrsavanje_index'>
			<dt><b>КОРИШТЕЊЕ</b></dt>
			<dd><br></dd>
			<c:set var='n' value='0'></c:set>
			<c:if test='${applicationSpaceBean.application("QUIZ", "QUIZ#USER#EXECUTE")}'>
				<dd><a href='${pageContext.request.contextPath}/user-execute-app-branch.jsp?app=QUIZ%23USER%23EXECUTE'>Квизови</a></dd>
				<c:set var='n' value='${n+1}'></c:set>
			</c:if>
		</dl>
		<c:if test="${n eq 0}"><script>document.getElementById('izvrsavanje_index').style.display='none'</script></c:if>
	</c:if>
</c:if>