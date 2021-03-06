/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.docs.guestbook.service.impl;

import java.util.List;

import com.liferay.docs.guestbook.NoSuchGuestbookException;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
import com.liferay.docs.guestbook.service.base.GuestbookServiceBaseImpl;
import com.liferay.docs.guestbook.service.permission.GuestbookModelPermission;
import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
import com.liferay.docs.guestbook.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

/**
 * The implementation of the guestbook remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.docs.guestbook.service.GuestbookService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author Rich Sezov
 * @see com.liferay.docs.guestbook.service.base.GuestbookServiceBaseImpl
 * @see com.liferay.docs.guestbook.service.GuestbookServiceUtil
 */
public class GuestbookServiceImpl extends GuestbookServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.liferay.docs.guestbook.service.GuestbookServiceUtil} to access the
	 * guestbook remote service.
	 */

	public Guestbook addGuestbook(long userId, String name,
			ServiceContext serviceContext) throws SystemException,
			PortalException {

		GuestbookModelPermission.check(getPermissionChecker(),
				serviceContext.getScopeGroupId(), ActionKeys.ADD_GUESTBOOK);

		return GuestbookLocalServiceUtil.addGuestbook(userId, name,
				serviceContext);
	}

	public Guestbook deleteGuestbook(long guestbookId,
			ServiceContext serviceContext) throws PortalException,
			SystemException {

		GuestbookPermission.check(getPermissionChecker(), guestbookId,
				ActionKeys.DELETE);

		return GuestbookLocalServiceUtil.deleteGuestbook(guestbookId,
				serviceContext);
	}

	public List<Guestbook> getGuestbooks(long groupId) throws SystemException {
		return guestbookPersistence.filterFindByGroupId(groupId);
	}

	public List<Guestbook> getGuestbooks(long groupId, int start, int end)
			throws SystemException {

		return guestbookPersistence.filterFindByGroupId(groupId, start, end);
	}
	
	public Guestbook getGuestbookByG_N(long groupId, String name,
	OrderByComparator orderByComparator) throws SystemException,
	NoSuchGuestbookException {
		
		return GuestbookLocalServiceUtil.getGuestbookByG_N(groupId, name, orderByComparator);
		
	}

	public int getGuestbooksCount(long groupId) throws SystemException {
		return guestbookPersistence.filterCountByGroupId(groupId);
	}

	public Guestbook updateGuestbook(long userId, long guestbookId,
			String name, ServiceContext serviceContext) throws PortalException,
			SystemException {

		GuestbookPermission.check(getPermissionChecker(), guestbookId,
				ActionKeys.UPDATE);

		return GuestbookLocalServiceUtil.updateGuestbook(userId, guestbookId,
				name, serviceContext);
	}

}