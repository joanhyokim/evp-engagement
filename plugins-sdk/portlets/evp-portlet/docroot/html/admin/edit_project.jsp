<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect", currentURL);

long projectId = ParamUtil.getLong(request, "projectId");

Project project = ProjectLocalServiceUtil.fetchProject(projectId);
%>

<div class="edit-script">
	<liferay-ui:header
		backURL="<%= redirect %>"
		title="edit-script"
	/>

	<liferay-portlet:actionURL name="updateProject" var="updateProjectURL" />

	<aui:form action="<%= updateProjectURL %>" method="post" name="fm">
		<aui:input name="mvcPath" type="hidden" value="/edit_project.jsp" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="projectId" type="hidden" value="<%= String.valueOf(projectId) %>" />

		<aui:model-context bean="<%= project %>" model="<%= Project.class %>" />

		<liferay-ui:error exception="<%= ManagerNameException.class %>" message="" />
		<liferay-ui:error exception="<%= ProjectNameException.class %>" message="" />
		<liferay-ui:error exception="<%= VolunteerAddressException.class %>" message="" />
		<liferay-ui:error exception="<%= VolunteerFirstNameException.class %>" message="" />
		<liferay-ui:error exception="<%= VolunteerLastNameException.class %>" message="" />

		<aui:input cssClass="required" name="firstName" />
		<aui:input cssClass="required" name="lastName" />
		<aui:input cssClass="required" name="address" />
		<aui:input name="coordX" />
		<aui:input name="coordY" />

		<aui:button-row>
			<aui:button label="save" name="saveButton" type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>