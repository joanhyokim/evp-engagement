package com.liferay.evp.admin;

import com.liferay.evp.admin.pojos.LiferayAuditPOJO;
import com.liferay.evp.admin.service.ProjectLocalServiceUtil;
import com.liferay.evp.admin.service.VolunteerLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * Portlet implementation class EVPAdmin
 */
public class EVPAdmin extends MVCPortlet {

    public void updateProject(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
            WebKeys.THEME_DISPLAY);

        long projectId = ParamUtil.getLong(actionRequest, "projectId");

        LiferayAuditPOJO liferayAudit =
            (LiferayAuditPOJO)actionRequest.getAttribute("liferayAudit");

        String name = ParamUtil.getString(actionRequest, "name");
        String description = ParamUtil.getString(
            actionRequest, "description");
        String location = ParamUtil.getString(actionRequest, "location");
        long coordX = ParamUtil.getLong(actionRequest, "coordX");
        long coordY = ParamUtil.getLong(actionRequest, "coordY");
        long coverImageId = ParamUtil.getLong(actionRequest, "coverImageId");
        double requiredFunds = ParamUtil.getDouble(
            actionRequest, "requiredFunds");
        double actualFunds = ParamUtil.getDouble(actionRequest, "actualFunds");

        int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
        int startDateMonth = ParamUtil.getInteger(
            actionRequest, "startDateMonth");
        int startDateYear = ParamUtil.getInteger(
            actionRequest, "startDateYear");

        Date startDate = PortalUtil.getDate(
            startDateMonth, startDateDay, startDateYear,
            themeDisplay.getTimeZone(), ProjectStartDateException.class);

        int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
        int endDateMonth = ParamUtil.getInteger(actionRequest, "endDateMonth");
        int endDateYear = ParamUtil.getInteger(actionRequest, "endDateYear");

        Date endDate = PortalUtil.getDate(
            endDateMonth, endDateDay, endDateYear, themeDisplay.getTimeZone(),
            ProjectEndDateException.class);

        String managerName = ParamUtil.getString(actionRequest, "managerName");

        if (projectId > 0) {
            ProjectLocalServiceUtil.updateProject(
                projectId, themeDisplay.getScopeGroupId(), liferayAudit, name,
                description, location, coordX, coordY, coverImageId,
                requiredFunds, actualFunds, startDate, endDate, null,
                managerName);
        }
        else {
            ProjectLocalServiceUtil.addProject(
                themeDisplay.getScopeGroupId(), liferayAudit, name, description,
                location, coordX, coordY, coverImageId, requiredFunds,
                actualFunds, startDate, endDate, null, managerName);
        }
    }

    public void updateVolunteer(
         ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
            WebKeys.THEME_DISPLAY);

        long volunteerId = ParamUtil.getLong(actionRequest, "volunteerId");

        LiferayAuditPOJO liferayAudit =
            (LiferayAuditPOJO)actionRequest.getAttribute("liferayAudit");

        String firstName = ParamUtil.getString(actionRequest, "firstName");
        String lastName = ParamUtil.getString(actionRequest, "lastName");
        String address = ParamUtil.getString(actionRequest, "address");
        long coordX = ParamUtil.getLong(actionRequest, "coordX");
        long coordY = ParamUtil.getLong(actionRequest, "coordY");

        if (volunteerId > 0) {
            VolunteerLocalServiceUtil.updateVolunteer(
                themeDisplay.getUserId(), volunteerId, firstName, lastName,
                address, coordX, coordY);
        }
        else {
            VolunteerLocalServiceUtil.addVolunteer(
                themeDisplay.getUserId(), liferayAudit, firstName, lastName,
                address, coordX, coordY);
        }
    }

    @Override
    protected boolean isSessionErrorException(Throwable cause) {
        if (_log.isDebugEnabled()) {
            _log.debug(cause, cause);
        }

        if (cause instanceof ManagerNameException ||
            cause instanceof ProjectNameException ||
            cause instanceof ProjectEndDateException ||
            cause instanceof ProjectStartDateException ||
            cause instanceof VolunteerAddressException ||
            cause instanceof VolunteerFirstNameException ||
            cause instanceof VolunteerLastNameException) {

            return true;
        }

        return false;
    }

	private static Log _log = LogFactoryUtil.getLog(EVPAdmin.class);

}