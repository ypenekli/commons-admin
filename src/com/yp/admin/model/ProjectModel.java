package com.yp.admin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.yp.admin.Constants;
import com.yp.admin.data.GroupUsers;
import com.yp.admin.data.GroupUsersHistory;
import com.yp.admin.data.Groups;
import com.yp.admin.data.Projects;
import com.yp.admin.data.Users;
import com.yp.core.AModel;
import com.yp.core.BaseConstants;
import com.yp.core.FnParam;
import com.yp.core.db.DbCommand;
import com.yp.core.entity.IResult;
import com.yp.core.entity.Result;
import com.yp.core.log.MyLogger;
import com.yp.core.ref.IReference;
import com.yp.core.ref.RefContainer;
import com.yp.core.ref.Reference;
import com.yp.core.tools.StringTool;
import com.yp.core.user.IUser;

public class ProjectModel extends AModel<Projects> {
	public static final String Q_PRJKOD0 = "SRGPRJKOD0";
	public static final String Q_PRJKOD1 = "SRGPRJKOD1";
	public static final String Q_PRJKOD2 = "SRGPRJKOD2";
	public static final String Q_PROJECTS4 = "Q_PROJECTS4";
	public static final String Q_PROJECTFUNCS5 = "Q.PROJECTFUNCS5";
	public static final String Q_PRJKOD6 = "SRGPRJKOD6";
	public static final String Q_PRJKOD7 = "SRGPRJKOD7";
	public static final String Q_VERSION_NOTES = "Version.Notes";
	public static final String Q_VERSIONS = "Versions";

	public static final IReference<String> TARGET_WEB = new Reference<>("web", BaseConstants.getString("TARGET.WEB"));
	public static final IReference<String> TARGET_WIN = new Reference<>("win", BaseConstants.getString("TARGET.WIN"));
	public static final IReference<String> TARGET_MOBILE = new Reference<>("mobile", BaseConstants.getString("TARGET.MOBILE"));

	public static final RefContainer<String> TARGET = new RefContainer<>(TARGET_WEB, TARGET_WIN, TARGET_MOBILE);

	public ProjectModel() {
		super();
	}

	public ProjectModel(String pServer) {
		super(pServer);
	}

	public List<IReference<String>> getTargetList() {
		List<IReference<String>> targetList = new ArrayList<>(3);
		targetList.add(TARGET_WIN);
		targetList.add(TARGET_WEB);
		targetList.add(TARGET_MOBILE);
		return targetList;
	}

	public List<Projects> findAll() {
		DbCommand query = new DbCommand(Q_PRJKOD1, new FnParam[] {});
		query.setQuery(BaseConstants.getSgl(query.getName()));

		return findAny(query);
	}

	public List<Projects> findProjects(Integer pUserId) {
		return findSubitems("0", pUserId);
	}

	public List<Projects> findSubitems(String pUstkod, Integer pUserId) {
		DbCommand query = new DbCommand(Q_PRJKOD6, new FnParam("ustkod", pUstkod), new FnParam("kisikytnu", pUserId));
		query.setQuery(BaseConstants.getSgl(query.getName()));

		return findAny(query);
	}

	public List<Projects> findUserProjectTree(final Integer pUserId, final String pProjectId) {
		final DbCommand query = new DbCommand(Q_PROJECTFUNCS5, new FnParam("project_id", pProjectId),
				new FnParam("project_id", pProjectId), new FnParam("user_id", pUserId));
		query.setQuery(BaseConstants.getSgl(query.getName()));
		return this.findAny(query);
	}

//	public List<Projects> findGroupProjectTree(final Integer pGroupId, final String pProjectId) {
//		final DbCommand query = new DbCommand(Q_PROJECTS4, new FnParam("project_id", pProjectId),
//				new FnParam("groupid", pGroupId));
//		query.setQuery(Constants.getSgl(query.getName()));
//		return this.findAny(query);
//	}

	public List<Projects> findProjectVersionNotes(final String pProjectId, final String pVersion) {
		final DbCommand query = new DbCommand(Q_VERSION_NOTES, new FnParam("prjkod", pProjectId),
				new FnParam("version", pVersion));
		query.setQuery(BaseConstants.getSgl(query.getName()));
		return this.findAny(query);
	}

	public List<Projects> findProjectVersions(final String pProjectId) {
		final DbCommand query = new DbCommand(Q_VERSIONS, new FnParam("prjkod", pProjectId));
		query.setQuery(BaseConstants.getSgl(query.getName()));
		return this.findAny(query);
	}

	private IResult<Projects> validateFields(Projects pProject) {
		IResult<Projects> res = new Result<>(true, "");
		StringBuilder dSb = new StringBuilder();
		String mString = pProject.getId();
		if (StringTool.isNull(mString) || mString.length() < 3) {
			res.setSuccess(false);
			dSb.append(BaseConstants.getString("FrmProjectAUL.Warning.Id"));
			dSb.append(BaseConstants.EOL);
		}

		mString = pProject.getName();
		if (StringTool.isNull(mString) || mString.length() < 3) {
			res.setSuccess(false);
			dSb.append(BaseConstants.getString("FrmProjectAUL.Warning.Name"));
			dSb.append(BaseConstants.EOL);
		}
		res.setMessage(dSb.toString());
		return res;
	}

	public synchronized IResult<Projects> save(Projects pProject, IUser pUser) {
		IResult<Projects> result = validateFields(pProject);
		if (result.isSuccess()) {
			try {
				GroupModel groupModel = new GroupModel();
				setLastClientInfo(pProject, pUser);

				Groups group = null;
				GroupUsers groupUser = null;
				GroupUsersHistory history = null;

				if (pProject.isNew()) {
					// pProject.setPrjkod(pProject.getIslvkod());
					group = new Groups(groupModel.findGroupId());
					group.setProjectId(pProject.getId());
					group.setName(String.format("%s->%s", Constants.getString("FrmGroup.Admin"), pProject.getName()));
					group.setHierarchy(group.getId().toString());
					group.setGroupType(Groups.GROUP_TYPE_ADMIN);
					group.setLastClientInfo(pProject);

					groupUser = new GroupUsers(group.getId(), pUser.getId());
					groupUser.setLastClientInfo(group);

					history = new GroupUsersHistory(groupModel.findGroupUsersHistoryId(), groupUser);
					history.setUpdateUser((Users) pUser, GroupUsersHistory.UPDATE_MODE_ADD);
					history.setClientInfo(pUser);
				}

				final IResult<String> temp = saveAtomic(pProject, group, groupUser, history);
				if (temp != null) {
					if (temp.isSuccess())
						pProject.accept();
					result.setSuccess(temp.isSuccess());
					result.setMessage(temp.getMessage());
				} else
					result.setMessage(BaseConstants.MESSAGE_SAVE_ERROR);
				result.setData(pProject);

			} catch (Exception e) {
				result.setSuccess(false);
				result.setMessage(BaseConstants.MESSAGE_SAVE_ERROR);
				result.setData(pProject);
				Logger.getLogger(MyLogger.NAME).log(Level.SEVERE, e.getMessage(), e);
			}

		}
		return result;

	}
}
