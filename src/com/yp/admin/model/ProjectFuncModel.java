package com.yp.admin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.yp.admin.Constants;
import com.yp.admin.data.GroupProjectFuncs;
import com.yp.admin.data.GroupProjectFuncsHistory;
import com.yp.admin.data.ProjectFuncs;
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

public class ProjectFuncModel extends AModel<ProjectFuncs> {
	public static final String Q_PRJKOD0 = "SRGPRJKOD0";
	public static final String Q_PRJKOD1 = "SRGPRJKOD1";
	public static final String Q_PRJKOD2 = "SRGPRJKOD2";
	public static final String Q_PROJECTS4 = "Q_PROJECTS4";
	public static final String Q_PRJKOD5 = "SRGPRJKOD5";
	public static final String Q_PRJKOD6 = "SRGPRJKOD6";
	public static final String Q_PRJKOD7 = "SRGPRJKOD7";
	public static final String Q_VERSION_NOTES = "Version.Notes";
	public static final String Q_VERSIONS = "Versions";

	public static final IReference<String> TARGET_UPDATE = new Reference<>(
			"targetAUDL",
			BaseConstants.getString("TARGET.UPDATE"));
	public static final IReference<String> TARGET_EDIT = new Reference<>("targetAUD",
			BaseConstants.getString("TARGET.EDIT"));
	public static final IReference<String> TARGET_LIST = new Reference<>("targetL",
			BaseConstants.getString("TARGET.LIST"));
	public static final IReference<String> TARGET_VIEW = new Reference<>("targetView",
			BaseConstants.getString("TARGET.VIEW"));

	public static final RefContainer<String> TARGET = new RefContainer<>(TARGET_UPDATE, TARGET_EDIT, TARGET_LIST,
			TARGET_VIEW);

	public ProjectFuncModel() {
		super();
	}

	public ProjectFuncModel(String pServer) {
		super(pServer);
	}

	public List<IReference<String>> getTargetList() {
		List<IReference<String>> targetList = new ArrayList<>(5);
		targetList.add(TARGET_UPDATE);
		targetList.add(TARGET_LIST);
		targetList.add(TARGET_EDIT);
		targetList.add(TARGET_UPDATE);
		targetList.add(TARGET_VIEW);
		return targetList;
	}

//	public List<Projects> findAll() {
//		DbCommand query = new DbCommand(Q_PRJKOD1, new FnParam[] {});
//		query.setQuery(BaseConstants.getSgl(query.getName()));
//
//		return findAny(query);
//	}
//
//	public List<Projects> findProjectFuncs(Integer pUserId) {
//		return findSubitems("0", pUserId);
//	}
//
//	public List<Projects> findSubitems(String pUstkod, Integer pUserId) {
//		DbCommand query = new DbCommand(Q_PRJKOD6, new FnParam("ustkod", pUstkod), new FnParam("kisikytnu", pUserId));
//		query.setQuery(BaseConstants.getSgl(query.getName()));
//
//		return findAny(query);
//	}
//
//	public List<Projects> findUserProjectTree(final Integer pUserId, final String pProjectId) {
//		final DbCommand query = new DbCommand(Q_PRJKOD5, new FnParam("prjkod", pProjectId),
//				new FnParam("prjkod", pProjectId), new FnParam("kisikytnu", pUserId));
//		query.setQuery(BaseConstants.getSgl(query.getName()));
//		return this.findAny(query);
//	}
//
	public List<ProjectFuncs> findGroupProjectFuncs(final Integer pGroupId, final String pProjectId) {
		final DbCommand query = new DbCommand(Q_PROJECTS4, new FnParam("project_id", pProjectId),
				new FnParam("groupid", pGroupId));
		query.setQuery(Constants.getSgl(query.getName()));
		return this.findAny(query);
	}
//
//	public List<Projects> findProjectVersionNotes(final String pProjectId, final String pVersion) {
//		final DbCommand query = new DbCommand(Q_VERSION_NOTES, new FnParam("prjkod", pProjectId),
//				new FnParam("version", pVersion));
//		query.setQuery(BaseConstants.getSgl(query.getName()));
//		return this.findAny(query);
//	}
//
//	public List<Projects> findProjectVersions(final String pProjectId) {
//		final DbCommand query = new DbCommand(Q_VERSIONS, new FnParam("prjkod", pProjectId));
//		query.setQuery(BaseConstants.getSgl(query.getName()));
//		return this.findAny(query);
//	}

	private IResult<ProjectFuncs> validateFields(ProjectFuncs pProject) {
		IResult<ProjectFuncs> res = new Result<>(true, "");
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

	public synchronized IResult<ProjectFuncs> save(ProjectFuncs pProject, Integer pGroupId, IUser pUser) {
		IResult<ProjectFuncs> result = validateFields(pProject);
		if (result.isSuccess()) {
			try {
				GroupModel groupModel = new GroupModel();
				setLastClientInfo(pProject, pUser);

				GroupProjectFuncs groupFuncs = null;
				GroupProjectFuncsHistory history = null;

				if (pProject.isNew()) {
					// pProject.setId(pProject.getIslvkod());
					groupFuncs = new GroupProjectFuncs(pGroupId, pProject.getId());
					groupFuncs.setLastClientInfo(pUser);

					history = new GroupProjectFuncsHistory(groupModel.findGroupProjectFuncsHistoryId(), groupFuncs);
					history.setUpdateUser((Users) pUser, GroupProjectFuncsHistory.UPDATE_MODE_ADD);
					history.setClientInfo(pProject);
				}

				final IResult<String> temp = saveAtomic(pProject, groupFuncs, history);
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
