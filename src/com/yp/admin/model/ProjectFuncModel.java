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
	
	public static final String Q_PROJECTFUNCS1 = "Q.PROJECTFUNCS1";
	public static final String Q_PROJECTFUNCS4 = "Q.PROJECTFUNCS4";	
	public static final String Q_PROJECTFUNCS5 = "Q.PROJECTFUNCS5";
	public static final String Q_PROJECTFUNCS6 = "Q.PROJECTFUNCS6";
	
	
	public static final String Q_PRJKOD5 = "SRGPRJKOD5";
	public static final String Q_PRJKOD6 = "SRGPRJKOD6";
	public static final String Q_PRJKOD7 = "SRGPRJKOD7";
	public static final String Q_VERSION_NOTES = "Version.Notes";
	public static final String Q_VERSIONS = "Versions";

	public static final IReference<String> TARGET_UPDATE = new Reference<>("targetAUDL",
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


	public List<ProjectFuncs> findProjectFuncs(String pParentId) {
		DbCommand query = new DbCommand(Q_PROJECTFUNCS1, new FnParam("parent_id", pParentId));
		query.setQuery(Constants.getSgl(query.getName()));

		return findAny(query);
	}

	public List<ProjectFuncs> findUserProjectFuncs(final Integer pUserId, final String pProjectId) {
		final DbCommand query = new DbCommand(Q_PROJECTFUNCS5, new FnParam("project_id", pProjectId),
				new FnParam("project_id", pProjectId), new FnParam("user_id", pUserId));
		query.setQuery(Constants.getSgl(query.getName()));
		return this.findAny(query);
	}
	
	public List<ProjectFuncs> findGroupProjectFuncs(final Integer pGroupId, final String pProjectId) {
		final DbCommand query = new DbCommand(Q_PROJECTFUNCS4, new FnParam("project_id", pProjectId),
				new FnParam("groupid", pGroupId));
		query.setQuery(Constants.getSgl(query.getName()));
		return this.findAny(query);
	}


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

	public synchronized IResult<ProjectFuncs> save(ProjectFuncs pProjectFunc, Integer pGroupId, IUser pUser) {
		IResult<ProjectFuncs> result = validateFields(pProjectFunc);
		if (result.isSuccess()) {
			try {
				GroupModel groupModel = new GroupModel();
				setLastClientInfo(pProjectFunc, pUser);

				ProjectFuncs parent = null;
				GroupProjectFuncs groupFuncs = null;
				GroupProjectFuncsHistory history = null;

				if (pProjectFunc.isNew()) {						
					parent = new ProjectFuncs(pProjectFunc.getParentId());
					parent.accept();					
					parent.setLeaf(false);
					parent.setName(pProjectFunc.getParentName());					
					parent.setLastClientInfo(pUser);
					groupFuncs = new GroupProjectFuncs(pGroupId, pProjectFunc.getId());
					groupFuncs.setLastClientInfo(pUser);

					history = new GroupProjectFuncsHistory(groupModel.findGroupProjectFuncsHistoryId(), groupFuncs);
					history.setUpdateUser((Users) pUser, GroupProjectFuncsHistory.UPDATE_MODE_ADD);
					history.setClientInfo(pProjectFunc);
				}

				final IResult<String> temp = saveAtomic(pProjectFunc, groupFuncs, history, parent);
				if (temp != null) {
					if (temp.isSuccess())
						pProjectFunc.accept();
					result.setSuccess(temp.isSuccess());
					result.setMessage(temp.getMessage());
				} else
					result.setMessage(BaseConstants.MESSAGE_SAVE_ERROR);
				result.setData(pProjectFunc);

			} catch (Exception e) {
				result.setSuccess(false);
				result.setMessage(BaseConstants.MESSAGE_SAVE_ERROR);
				result.setData(pProjectFunc);
				Logger.getLogger(MyLogger.NAME).log(Level.SEVERE, e.getMessage(), e);
			}

		}
		return result;

	}

}
