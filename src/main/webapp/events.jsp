<%@page import="dao.EventsManagerPropio"%>
<%@ page contentType="application/json" %>
<%= getEvents(request) %>
<%@ page import="com.dhtmlx.planner.*,dao.*,modelo.*" %>
<%!String getEvents(HttpServletRequest request) throws Exception {
    EventsManagerPropio evs = new EventsManagerPropio(request);
    return evs.run();
  }%>