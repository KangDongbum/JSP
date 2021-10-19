<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="ko" />
<fmt:bundle basename="resourceBundle.message">
	name : <fmt:message key="name" /><br>
	greeting : <fmt:message key="greeting" var="greeting" />
				${greeting}
</fmt:bundle>