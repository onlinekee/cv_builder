
<%
if (session.getAttribute("username") == null) {
	response.sendRedirect(request.getContextPath() + "/login");
} else {
	response.sendRedirect(request.getContextPath() + "/your_cvs");
}
%>