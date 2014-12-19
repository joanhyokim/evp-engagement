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

package com.liferay.evp.admin.service.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.evp.admin.service.model.Volunteer;
import com.liferay.evp.admin.service.service.VolunteerLocalService;
import com.liferay.evp.admin.service.service.persistence.FundPersistence;
import com.liferay.evp.admin.service.service.persistence.ProjectPersistence;
import com.liferay.evp.admin.service.service.persistence.VolunteerPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the volunteer local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.evp.admin.service.service.impl.VolunteerLocalServiceImpl}.
 * </p>
 *
 * @author Joan.Kim
 * @see com.liferay.evp.admin.service.service.impl.VolunteerLocalServiceImpl
 * @see com.liferay.evp.admin.service.service.VolunteerLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class VolunteerLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements VolunteerLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.evp.admin.service.service.VolunteerLocalServiceUtil} to access the volunteer local service.
	 */

	/**
	 * Adds the volunteer to the database. Also notifies the appropriate model listeners.
	 *
	 * @param volunteer the volunteer
	 * @return the volunteer that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Volunteer addVolunteer(Volunteer volunteer) {
		volunteer.setNew(true);

		return volunteerPersistence.update(volunteer);
	}

	/**
	 * Creates a new volunteer with the primary key. Does not add the volunteer to the database.
	 *
	 * @param volunteerId the primary key for the new volunteer
	 * @return the new volunteer
	 */
	@Override
	public Volunteer createVolunteer(long volunteerId) {
		return volunteerPersistence.create(volunteerId);
	}

	/**
	 * Deletes the volunteer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param volunteerId the primary key of the volunteer
	 * @return the volunteer that was removed
	 * @throws PortalException if a volunteer with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Volunteer deleteVolunteer(long volunteerId)
		throws PortalException {
		return volunteerPersistence.remove(volunteerId);
	}

	/**
	 * Deletes the volunteer from the database. Also notifies the appropriate model listeners.
	 *
	 * @param volunteer the volunteer
	 * @return the volunteer that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Volunteer deleteVolunteer(Volunteer volunteer) {
		return volunteerPersistence.remove(volunteer);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Volunteer.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return volunteerPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.evp.admin.service.model.impl.VolunteerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return volunteerPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.evp.admin.service.model.impl.VolunteerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return volunteerPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return volunteerPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return volunteerPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public Volunteer fetchVolunteer(long volunteerId) {
		return volunteerPersistence.fetchByPrimaryKey(volunteerId);
	}

	/**
	 * Returns the volunteer with the primary key.
	 *
	 * @param volunteerId the primary key of the volunteer
	 * @return the volunteer
	 * @throws PortalException if a volunteer with the primary key could not be found
	 */
	@Override
	public Volunteer getVolunteer(long volunteerId) throws PortalException {
		return volunteerPersistence.findByPrimaryKey(volunteerId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.evp.admin.service.service.VolunteerLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(Volunteer.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("volunteerId");

		return actionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.evp.admin.service.service.VolunteerLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(Volunteer.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("volunteerId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return volunteerLocalService.deleteVolunteer((Volunteer)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return volunteerPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the volunteers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.evp.admin.service.model.impl.VolunteerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of volunteers
	 * @param end the upper bound of the range of volunteers (not inclusive)
	 * @return the range of volunteers
	 */
	@Override
	public List<Volunteer> getVolunteers(int start, int end) {
		return volunteerPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of volunteers.
	 *
	 * @return the number of volunteers
	 */
	@Override
	public int getVolunteersCount() {
		return volunteerPersistence.countAll();
	}

	/**
	 * Updates the volunteer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param volunteer the volunteer
	 * @return the volunteer that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Volunteer updateVolunteer(Volunteer volunteer) {
		return volunteerPersistence.update(volunteer);
	}

	/**
	 * Returns the fund local service.
	 *
	 * @return the fund local service
	 */
	public com.liferay.evp.admin.service.service.FundLocalService getFundLocalService() {
		return fundLocalService;
	}

	/**
	 * Sets the fund local service.
	 *
	 * @param fundLocalService the fund local service
	 */
	public void setFundLocalService(
		com.liferay.evp.admin.service.service.FundLocalService fundLocalService) {
		this.fundLocalService = fundLocalService;
	}

	/**
	 * Returns the fund remote service.
	 *
	 * @return the fund remote service
	 */
	public com.liferay.evp.admin.service.service.FundService getFundService() {
		return fundService;
	}

	/**
	 * Sets the fund remote service.
	 *
	 * @param fundService the fund remote service
	 */
	public void setFundService(
		com.liferay.evp.admin.service.service.FundService fundService) {
		this.fundService = fundService;
	}

	/**
	 * Returns the fund persistence.
	 *
	 * @return the fund persistence
	 */
	public FundPersistence getFundPersistence() {
		return fundPersistence;
	}

	/**
	 * Sets the fund persistence.
	 *
	 * @param fundPersistence the fund persistence
	 */
	public void setFundPersistence(FundPersistence fundPersistence) {
		this.fundPersistence = fundPersistence;
	}

	/**
	 * Returns the project local service.
	 *
	 * @return the project local service
	 */
	public com.liferay.evp.admin.service.service.ProjectLocalService getProjectLocalService() {
		return projectLocalService;
	}

	/**
	 * Sets the project local service.
	 *
	 * @param projectLocalService the project local service
	 */
	public void setProjectLocalService(
		com.liferay.evp.admin.service.service.ProjectLocalService projectLocalService) {
		this.projectLocalService = projectLocalService;
	}

	/**
	 * Returns the project remote service.
	 *
	 * @return the project remote service
	 */
	public com.liferay.evp.admin.service.service.ProjectService getProjectService() {
		return projectService;
	}

	/**
	 * Sets the project remote service.
	 *
	 * @param projectService the project remote service
	 */
	public void setProjectService(
		com.liferay.evp.admin.service.service.ProjectService projectService) {
		this.projectService = projectService;
	}

	/**
	 * Returns the project persistence.
	 *
	 * @return the project persistence
	 */
	public ProjectPersistence getProjectPersistence() {
		return projectPersistence;
	}

	/**
	 * Sets the project persistence.
	 *
	 * @param projectPersistence the project persistence
	 */
	public void setProjectPersistence(ProjectPersistence projectPersistence) {
		this.projectPersistence = projectPersistence;
	}

	/**
	 * Returns the volunteer local service.
	 *
	 * @return the volunteer local service
	 */
	public com.liferay.evp.admin.service.service.VolunteerLocalService getVolunteerLocalService() {
		return volunteerLocalService;
	}

	/**
	 * Sets the volunteer local service.
	 *
	 * @param volunteerLocalService the volunteer local service
	 */
	public void setVolunteerLocalService(
		com.liferay.evp.admin.service.service.VolunteerLocalService volunteerLocalService) {
		this.volunteerLocalService = volunteerLocalService;
	}

	/**
	 * Returns the volunteer remote service.
	 *
	 * @return the volunteer remote service
	 */
	public com.liferay.evp.admin.service.service.VolunteerService getVolunteerService() {
		return volunteerService;
	}

	/**
	 * Sets the volunteer remote service.
	 *
	 * @param volunteerService the volunteer remote service
	 */
	public void setVolunteerService(
		com.liferay.evp.admin.service.service.VolunteerService volunteerService) {
		this.volunteerService = volunteerService;
	}

	/**
	 * Returns the volunteer persistence.
	 *
	 * @return the volunteer persistence
	 */
	public VolunteerPersistence getVolunteerPersistence() {
		return volunteerPersistence;
	}

	/**
	 * Sets the volunteer persistence.
	 *
	 * @param volunteerPersistence the volunteer persistence
	 */
	public void setVolunteerPersistence(
		VolunteerPersistence volunteerPersistence) {
		this.volunteerPersistence = volunteerPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.service.ClassNameService classNameService) {
		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.evp.admin.service.model.Volunteer",
			volunteerLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.evp.admin.service.model.Volunteer");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return Volunteer.class;
	}

	protected String getModelClassName() {
		return Volunteer.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = volunteerPersistence.getDataSource();

			DB db = DBFactoryUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.evp.admin.service.service.FundLocalService.class)
	protected com.liferay.evp.admin.service.service.FundLocalService fundLocalService;
	@BeanReference(type = com.liferay.evp.admin.service.service.FundService.class)
	protected com.liferay.evp.admin.service.service.FundService fundService;
	@BeanReference(type = FundPersistence.class)
	protected FundPersistence fundPersistence;
	@BeanReference(type = com.liferay.evp.admin.service.service.ProjectLocalService.class)
	protected com.liferay.evp.admin.service.service.ProjectLocalService projectLocalService;
	@BeanReference(type = com.liferay.evp.admin.service.service.ProjectService.class)
	protected com.liferay.evp.admin.service.service.ProjectService projectService;
	@BeanReference(type = ProjectPersistence.class)
	protected ProjectPersistence projectPersistence;
	@BeanReference(type = com.liferay.evp.admin.service.service.VolunteerLocalService.class)
	protected com.liferay.evp.admin.service.service.VolunteerLocalService volunteerLocalService;
	@BeanReference(type = com.liferay.evp.admin.service.service.VolunteerService.class)
	protected com.liferay.evp.admin.service.service.VolunteerService volunteerService;
	@BeanReference(type = VolunteerPersistence.class)
	protected VolunteerPersistence volunteerPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ClassNameLocalService.class)
	protected com.liferay.portal.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = com.liferay.portal.service.ClassNameService.class)
	protected com.liferay.portal.service.ClassNameService classNameService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private VolunteerLocalServiceClpInvoker _clpInvoker = new VolunteerLocalServiceClpInvoker();
}