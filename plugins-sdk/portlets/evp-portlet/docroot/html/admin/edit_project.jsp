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

		<liferay-ui:error exception="<%= VolunteerAddressException.class %>" message="" />
		<liferay-ui:error exception="<%= VolunteerFirstNameException.class %>" message="" />
		<liferay-ui:error exception="<%= VolunteerLastNameException.class %>" message="" />

		<aui:input cssClass="required" name="name" />
		<aui:input name="description" />
		<aui:input name="location" />
		<aui:input name="coordX" />
		<aui:input name="coordY" />
		<aui:input name="coverImageId" />
		<aui:input name="requiredFunds" />
		<aui:input name="actualFunds" />

		<%
		Calendar startDateCalendar = CalendarFactoryUtil.getCalendar(themeDisplay.getTimeZone(), locale);

		if (project != null) {
			startDateCalendar.setTime(project.getStartDate());
		}

		int startDateDay = ParamUtil.getInteger(request, "startDateDay", startDateCalendar.get(Calendar.DATE));
		int startDateMonth = ParamUtil.getInteger(request, "startDateMonth", startDateCalendar.get(Calendar.MONTH));
		int startDateYear = ParamUtil.getInteger(request, "startDateYear", startDateCalendar.get(Calendar.YEAR));
		int startDateYearRangeEnd = startDateCalendar.get(Calendar.YEAR) + 25;
		int startDateYearRangeStart = startDateCalendar.get(Calendar.YEAR) - 25;
		%>

		<span class="aui-field-element">
			<liferay-ui:input-date cssClass="start-date"
				dayParam='<%= "startDateDay" %>'
				dayValue="<%= startDateDay %>"
				monthParam='<%= "startDateMonth" %>'
				monthValue="<%= startDateMonth %>"
				yearParam='<%= "startDateYear" %>'
				yearRangeEnd="<%= startDateYearRangeEnd %>"
				yearRangeStart="<%= startDateYearRangeStart %>"
				yearValue="<%= startDateYear %>"
			/>
		</span>

		<%
		Calendar endDate = CalendarFactoryUtil.getCalendar(themeDisplay.getTimeZone(), locale);

		if (project != null) {
			endDate.setTime(project.getStartDate());
		}

		int endDateDay = ParamUtil.getInteger(request, "endDateDay", endDate.get(Calendar.DATE));
		int endDateMonth = ParamUtil.getInteger(request, "endDateMonth", endDate.get(Calendar.MONTH));
		int endDateYear = ParamUtil.getInteger(request, "endDateYear", endDate.get(Calendar.YEAR));
		int endDateYearRangeEnd = endDate.get(Calendar.YEAR) + 25;
		int endDateYearRangeStart = endDate.get(Calendar.YEAR) - 25;
		%>

		<span class="aui-field-element">
			<liferay-ui:input-date cssClass="end-date"
				dayParam='<%= "endDateDay" %>'
				dayValue="<%= endDateDay %>"
				monthParam='<%= "endDateMonth" %>'
				monthValue="<%= endDateMonth %>"
				yearParam='<%= "endDateYear" %>'
				yearRangeEnd="<%= endDateYearRangeEnd %>"
				yearRangeStart="<%= endDateYearRangeStart %>"
				yearValue="<%= endDateYear %>"
			/>
		</span>

		<aui:button-row>
			<aui:button label="save" name="saveButton" type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>