<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import = "java.util.*, java.text.*" %>
<%
	//Locale locale = request.getLocale();
	Locale locale = Locale.US;
	NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
	currency.setGroupingUsed(false);
	out.print(currency.format(1000));
	out.print("<br><br>");
	NumberFormat percentage = NumberFormat.getPercentInstance(locale);
	out.print(percentage.format(0.25));
%>