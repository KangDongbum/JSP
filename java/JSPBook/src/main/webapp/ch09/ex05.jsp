<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="ko"/>
<fmt:setBundle basename="resourceBundle.message" var="bundle" />
name: <fmt:message bundle="${bundle}" key="name" /><br>
greeting: <fmt:message bundle="${bundle}" key="greeting"/>