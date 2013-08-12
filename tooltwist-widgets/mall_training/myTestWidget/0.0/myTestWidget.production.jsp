<%   
MyTestWidgetProductionHelper myTestWidgetHelper = (MyTestWidgetProductionHelper) helper;
String snippetVar_name = myTestWidgetHelper.getName();
if (snippetVar_name.length()<=0) {
%>
%%INC(myTestWidget.html)%% 
<%
} else {
%>
%%INC(myTestWidgetWelcome.html)%% 	
<%
}
%>