/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.evp.admin.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.evp.admin.model.Project;
import com.liferay.evp.admin.model.ProjectModel;
import com.liferay.evp.admin.model.ProjectSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Project service. Represents a row in the &quot;evp_Project&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.evp.admin.model.ProjectModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ProjectImpl}.
 * </p>
 *
 * @author Joan.Kim
 * @see ProjectImpl
 * @see com.liferay.evp.admin.model.Project
 * @see com.liferay.evp.admin.model.ProjectModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class ProjectModelImpl extends BaseModelImpl<Project>
	implements ProjectModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a project model instance should use the {@link com.liferay.evp.admin.model.Project} interface instead.
	 */
	public static final String TABLE_NAME = "evp_Project";
	public static final Object[][] TABLE_COLUMNS = {
			{ "projectId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "location", Types.VARCHAR },
			{ "coordX", Types.BIGINT },
			{ "coordY", Types.BIGINT },
			{ "coverImageId", Types.BIGINT },
			{ "requiredFunds", Types.DOUBLE },
			{ "actualFunds", Types.DOUBLE },
			{ "startDate", Types.TIMESTAMP },
			{ "endDate", Types.TIMESTAMP },
			{ "approvalDate", Types.TIMESTAMP },
			{ "managerName", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table evp_Project (projectId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,description VARCHAR(75) null,location VARCHAR(75) null,coordX LONG,coordY LONG,coverImageId LONG,requiredFunds DOUBLE,actualFunds DOUBLE,startDate DATE null,endDate DATE null,approvalDate DATE null,managerName VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table evp_Project";
	public static final String ORDER_BY_JPQL = " ORDER BY project.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY evp_Project.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.evp.admin.model.Project"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.evp.admin.model.Project"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.evp.admin.model.Project"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long MANAGERNAME_COLUMN_BITMASK = 2L;
	public static final long NAME_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Project toModel(ProjectSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Project model = new ProjectImpl();

		model.setProjectId(soapModel.getProjectId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setLocation(soapModel.getLocation());
		model.setCoordX(soapModel.getCoordX());
		model.setCoordY(soapModel.getCoordY());
		model.setCoverImageId(soapModel.getCoverImageId());
		model.setRequiredFunds(soapModel.getRequiredFunds());
		model.setActualFunds(soapModel.getActualFunds());
		model.setStartDate(soapModel.getStartDate());
		model.setEndDate(soapModel.getEndDate());
		model.setApprovalDate(soapModel.getApprovalDate());
		model.setManagerName(soapModel.getManagerName());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Project> toModels(ProjectSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Project> models = new ArrayList<Project>(soapModels.length);

		for (ProjectSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.evp.admin.model.Project"));

	public ProjectModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _projectId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setProjectId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _projectId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Project.class;
	}

	@Override
	public String getModelClassName() {
		return Project.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("projectId", getProjectId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("location", getLocation());
		attributes.put("coordX", getCoordX());
		attributes.put("coordY", getCoordY());
		attributes.put("coverImageId", getCoverImageId());
		attributes.put("requiredFunds", getRequiredFunds());
		attributes.put("actualFunds", getActualFunds());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("approvalDate", getApprovalDate());
		attributes.put("managerName", getManagerName());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String location = (String)attributes.get("location");

		if (location != null) {
			setLocation(location);
		}

		Long coordX = (Long)attributes.get("coordX");

		if (coordX != null) {
			setCoordX(coordX);
		}

		Long coordY = (Long)attributes.get("coordY");

		if (coordY != null) {
			setCoordY(coordY);
		}

		Long coverImageId = (Long)attributes.get("coverImageId");

		if (coverImageId != null) {
			setCoverImageId(coverImageId);
		}

		Double requiredFunds = (Double)attributes.get("requiredFunds");

		if (requiredFunds != null) {
			setRequiredFunds(requiredFunds);
		}

		Double actualFunds = (Double)attributes.get("actualFunds");

		if (actualFunds != null) {
			setActualFunds(actualFunds);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Date approvalDate = (Date)attributes.get("approvalDate");

		if (approvalDate != null) {
			setApprovalDate(approvalDate);
		}

		String managerName = (String)attributes.get("managerName");

		if (managerName != null) {
			setManagerName(managerName);
		}
	}

	@JSON
	@Override
	public long getProjectId() {
		return _projectId;
	}

	@Override
	public void setProjectId(long projectId) {
		_projectId = projectId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		_name = name;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return StringPool.BLANK;
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public String getLocation() {
		if (_location == null) {
			return StringPool.BLANK;
		}
		else {
			return _location;
		}
	}

	@Override
	public void setLocation(String location) {
		_location = location;
	}

	@JSON
	@Override
	public long getCoordX() {
		return _coordX;
	}

	@Override
	public void setCoordX(long coordX) {
		_coordX = coordX;
	}

	@JSON
	@Override
	public long getCoordY() {
		return _coordY;
	}

	@Override
	public void setCoordY(long coordY) {
		_coordY = coordY;
	}

	@JSON
	@Override
	public long getCoverImageId() {
		return _coverImageId;
	}

	@Override
	public void setCoverImageId(long coverImageId) {
		_coverImageId = coverImageId;
	}

	@JSON
	@Override
	public double getRequiredFunds() {
		return _requiredFunds;
	}

	@Override
	public void setRequiredFunds(double requiredFunds) {
		_requiredFunds = requiredFunds;
	}

	@JSON
	@Override
	public double getActualFunds() {
		return _actualFunds;
	}

	@Override
	public void setActualFunds(double actualFunds) {
		_actualFunds = actualFunds;
	}

	@JSON
	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	@JSON
	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	@JSON
	@Override
	public Date getApprovalDate() {
		return _approvalDate;
	}

	@Override
	public void setApprovalDate(Date approvalDate) {
		_approvalDate = approvalDate;
	}

	@JSON
	@Override
	public String getManagerName() {
		if (_managerName == null) {
			return StringPool.BLANK;
		}
		else {
			return _managerName;
		}
	}

	@Override
	public void setManagerName(String managerName) {
		_columnBitmask |= MANAGERNAME_COLUMN_BITMASK;

		if (_originalManagerName == null) {
			_originalManagerName = _managerName;
		}

		_managerName = managerName;
	}

	public String getOriginalManagerName() {
		return GetterUtil.getString(_originalManagerName);
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Project.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Project toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Project)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ProjectImpl projectImpl = new ProjectImpl();

		projectImpl.setProjectId(getProjectId());
		projectImpl.setGroupId(getGroupId());
		projectImpl.setCompanyId(getCompanyId());
		projectImpl.setUserId(getUserId());
		projectImpl.setUserName(getUserName());
		projectImpl.setCreateDate(getCreateDate());
		projectImpl.setModifiedDate(getModifiedDate());
		projectImpl.setName(getName());
		projectImpl.setDescription(getDescription());
		projectImpl.setLocation(getLocation());
		projectImpl.setCoordX(getCoordX());
		projectImpl.setCoordY(getCoordY());
		projectImpl.setCoverImageId(getCoverImageId());
		projectImpl.setRequiredFunds(getRequiredFunds());
		projectImpl.setActualFunds(getActualFunds());
		projectImpl.setStartDate(getStartDate());
		projectImpl.setEndDate(getEndDate());
		projectImpl.setApprovalDate(getApprovalDate());
		projectImpl.setManagerName(getManagerName());

		projectImpl.resetOriginalValues();

		return projectImpl;
	}

	@Override
	public int compareTo(Project project) {
		int value = 0;

		value = getName().compareTo(project.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Project)) {
			return false;
		}

		Project project = (Project)obj;

		long primaryKey = project.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		ProjectModelImpl projectModelImpl = this;

		projectModelImpl._originalCompanyId = projectModelImpl._companyId;

		projectModelImpl._setOriginalCompanyId = false;

		projectModelImpl._originalManagerName = projectModelImpl._managerName;

		projectModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Project> toCacheModel() {
		ProjectCacheModel projectCacheModel = new ProjectCacheModel();

		projectCacheModel.projectId = getProjectId();

		projectCacheModel.groupId = getGroupId();

		projectCacheModel.companyId = getCompanyId();

		projectCacheModel.userId = getUserId();

		projectCacheModel.userName = getUserName();

		String userName = projectCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			projectCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			projectCacheModel.createDate = createDate.getTime();
		}
		else {
			projectCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			projectCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			projectCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		projectCacheModel.name = getName();

		String name = projectCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			projectCacheModel.name = null;
		}

		projectCacheModel.description = getDescription();

		String description = projectCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			projectCacheModel.description = null;
		}

		projectCacheModel.location = getLocation();

		String location = projectCacheModel.location;

		if ((location != null) && (location.length() == 0)) {
			projectCacheModel.location = null;
		}

		projectCacheModel.coordX = getCoordX();

		projectCacheModel.coordY = getCoordY();

		projectCacheModel.coverImageId = getCoverImageId();

		projectCacheModel.requiredFunds = getRequiredFunds();

		projectCacheModel.actualFunds = getActualFunds();

		Date startDate = getStartDate();

		if (startDate != null) {
			projectCacheModel.startDate = startDate.getTime();
		}
		else {
			projectCacheModel.startDate = Long.MIN_VALUE;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			projectCacheModel.endDate = endDate.getTime();
		}
		else {
			projectCacheModel.endDate = Long.MIN_VALUE;
		}

		Date approvalDate = getApprovalDate();

		if (approvalDate != null) {
			projectCacheModel.approvalDate = approvalDate.getTime();
		}
		else {
			projectCacheModel.approvalDate = Long.MIN_VALUE;
		}

		projectCacheModel.managerName = getManagerName();

		String managerName = projectCacheModel.managerName;

		if ((managerName != null) && (managerName.length() == 0)) {
			projectCacheModel.managerName = null;
		}

		return projectCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(39);

		sb.append("{projectId=");
		sb.append(getProjectId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", location=");
		sb.append(getLocation());
		sb.append(", coordX=");
		sb.append(getCoordX());
		sb.append(", coordY=");
		sb.append(getCoordY());
		sb.append(", coverImageId=");
		sb.append(getCoverImageId());
		sb.append(", requiredFunds=");
		sb.append(getRequiredFunds());
		sb.append(", actualFunds=");
		sb.append(getActualFunds());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", endDate=");
		sb.append(getEndDate());
		sb.append(", approvalDate=");
		sb.append(getApprovalDate());
		sb.append(", managerName=");
		sb.append(getManagerName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(61);

		sb.append("<model><model-name>");
		sb.append("com.liferay.evp.admin.model.Project");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>projectId</column-name><column-value><![CDATA[");
		sb.append(getProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>location</column-name><column-value><![CDATA[");
		sb.append(getLocation());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>coordX</column-name><column-value><![CDATA[");
		sb.append(getCoordX());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>coordY</column-name><column-value><![CDATA[");
		sb.append(getCoordY());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>coverImageId</column-name><column-value><![CDATA[");
		sb.append(getCoverImageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>requiredFunds</column-name><column-value><![CDATA[");
		sb.append(getRequiredFunds());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>actualFunds</column-name><column-value><![CDATA[");
		sb.append(getActualFunds());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endDate</column-name><column-value><![CDATA[");
		sb.append(getEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>approvalDate</column-name><column-value><![CDATA[");
		sb.append(getApprovalDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>managerName</column-name><column-value><![CDATA[");
		sb.append(getManagerName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Project.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Project.class
		};
	private long _projectId;
	private long _groupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private String _location;
	private long _coordX;
	private long _coordY;
	private long _coverImageId;
	private double _requiredFunds;
	private double _actualFunds;
	private Date _startDate;
	private Date _endDate;
	private Date _approvalDate;
	private String _managerName;
	private String _originalManagerName;
	private long _columnBitmask;
	private Project _escapedModel;
}