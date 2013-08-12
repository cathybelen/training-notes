<%MyLoginWidgetProductionHelper myLoginWidgetProductionHelper = (MyLoginWidgetProductionHelper) helper;
/* String snippetVar_emailVar = myLoginWidgetCProductionHelper.getEmail();
String snippetVar_passwordVar = myLoginWidgetCProductionHelper.getPassword(); */
String snippetVar_statusVar = myLoginWidgetProductionHelper.getStatus();
String snippetVar_nameVar = myLoginWidgetProductionHelper.getName();

if (snippetVar_statusVar.equals("success")) {%>
%%INC(myLoginWidgetSuccess.html)%% 	
<%
} else {
%>
%%INC(myLoginWidget.html)%% 
<%
}
%>