package com.yp.admin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.yp.admin.Constants;
import com.yp.admin.data.GroupUser;
import com.yp.admin.data.GroupUsersHistory;
import com.yp.admin.data.Group;
import com.yp.admin.data.Project;
import com.yp.admin.data.User;
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

public class ProjectModel extends AModel<Project> {
	public static final String Q_PROJECT1 = "SRGPRJKOD1";
	public static final String Q_PROJECTS6 = "Q.PROJECTS6";
	public static final String Q_VERSION_NOTES = "Version.Notes";
	public static final String Q_VERSIONS = "Versions";

	public static final IReference<String> TARGET_WEB = new Reference<>("targetWEB", BaseConstants.getString("TARGET.WEB"));
	public static final IReference<String> TARGET_WIN = new Reference<>("targetWIN", BaseConstants.getString("TARGET.WIN"));
	public static final IReference<String> TARGET_MOBILE = new Reference<>("targetMOBILE", BaseConstants.getString("TARGET.MOBILE"));

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

	public List<Project> findAll() {
		DbCommand query = new DbCommand(Q_PROJECT1, new FnParam[] {});
		query.setQuery(Constants.getSgl(query.getName()));

		return findAny(query);
	}

	public List<Project> findProjects(Integer pUserId) {
		DbCommand query = new DbCommand(Q_PROJECTS6,  new FnParam("userid", pUserId));
		query.setQuery(Constants.getSgl(query.getName()));

		return findAny(query);
	}

	public List<Project> findProjectVersionNotes(final String pProjectId, final String pVersion) {
		final DbCommand query = new DbCommand(Q_VERSION_NOTES, new FnParam("projectid", pProjectId),
				new FnParam("version", pVersion));
		query.setQuery(Constants.getSgl(query.getName()));
		return this.findAny(query);
	}

	public List<Project> findProjectVersions(final String pProjectId) {
		final DbCommand query = new DbCommand(Q_VERSIONS, new FnParam("projectid", pProjectId));
		query.setQuery(Constants.getSgl(query.getName()));
		return this.findAny(query);
	}

	private IResult<Project> validateFields(Project pProject) {
		IResult<Project> res = new Result<>(true, "");
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

	public synchronized IResult<Project> save(Project pProject, IUser pUser) {
		IResult<Project> result = validateFields(pProject);
		if (result.isSuccess()) {
			try {
				GroupModel groupModel = new GroupModel();
				setLastClientInfo(pProject, pUser);

				Group group = null;
				GroupUser groupUser = null;
				GroupUsersHistory history = null;

				if (pProject.isNew()) {					
					group = new Group(groupModel.findGroupId());
					group.setProjectId(pProject.getId());
					group.setName(String.format("%s->%s", Constants.getString("FrmGroup.Admin"), pProject.getName()));
					group.setHierarchy(group.getId().toString());
					group.setGroupType(Group.GROUP_TYPE_ADMIN);
					group.setLastClientInfo(pProject);

					groupUser = new GroupUser(group.getId(), pUser.getId());
					groupUser.setLastClientInfo(group);

					history = new GroupUsersHistory(groupModel.findGroupUsersHistoryId(), groupUser);
					history.setUpdateUser((User) pUser, GroupUsersHistory.UPDATE_MODE_ADD);
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
