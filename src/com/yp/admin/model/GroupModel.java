package com.yp.admin.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yp.admin.Constants;
import com.yp.admin.data.GroupProjectFuncs;
import com.yp.admin.data.GroupProjectFuncsHistory;
import com.yp.admin.data.GroupUsers;
import com.yp.admin.data.GroupUsersHistory;
import com.yp.admin.data.Groups;
import com.yp.admin.data.Users;
import com.yp.core.AModel;
import com.yp.core.BaseConstants;
import com.yp.core.FnParam;
import com.yp.core.db.DbCommand;
import com.yp.core.entity.IDataEntity;
import com.yp.core.entity.IResult;
import com.yp.core.entity.Result;
import com.yp.core.tools.StringTool;
import com.yp.core.user.IUser;

public class GroupModel extends AModel<Groups> {

	public static final String Q_GROUPS1 = "Q.GROUPS1";
	public static final String Q_GROUPS2 = "Q.GROUPS2";
	public static final String Q_GRPTNM3 = "SRGGRPTNM3";
	public static final String Q_GRPTNM4 = "SRGGRPTNM4";
	public static final String Q_GRPTNM41 = "SRGGRPTNM41";
	public static final String Q_GROUPS5 = "Q.GROUPS5";
	//public static final String Q_PRSTNM4 = "SRGPRSTNM4";
	//public static final String Q_GRPISLV1 = "SRGGRPISLV1";
	public static final String Q_GROUP_USERS1 = "Q.GROUP.USERS1";
	public static final String Q_GROUP_FUNCS1 = "Q.GROUP.FUNCS1";
	public static final String Q_GROUP_USERS_HISTORY1= "Q.GROUP.USERS.HISTORY1";
	public static final String Q_GROUP_PROJECT_FUNCS_HISTORY1= "Q.GROUP.PROJECT.FUNCS.HISTORY1";
	

	public synchronized Integer findGroupId() {
		final DbCommand query = new DbCommand(Q_GROUPS2, new FnParam[0]);
		query.setQuery(Constants.getSgl(query.getName()));
		final Groups de = findOne(query);
		Integer res = null;
		if (de != null) {
			res = de.getId();
		}
		if (res != null) {
			return res + 1;
		}
		return 0;
	}

	public List<Groups> findGroupList(final Integer pUserId) {
		final DbCommand query = new DbCommand(Q_GROUPS1, new FnParam("userid", pUserId));
		query.setQuery(Constants.getSgl(query.getName()));
		return this.findAny(query);
	}

	public List<Groups> findGroupList(final String pPrjkod) {
		final DbCommand query = new DbCommand(Q_GRPTNM4, new FnParam("prjkod", pPrjkod));
		query.setQuery(Constants.getSgl(query.getName()));
		return this.findAny(query);
	}

	public List<Groups> findGroupList(final String pEposta, final String pPrjkod) {
		final DbCommand query = new DbCommand(Q_GRPTNM41, new FnParam("prjkod", pPrjkod),
				new FnParam("eposta", pEposta), new FnParam("prjkod", pPrjkod));
		query.setQuery(Constants.getSgl(query.getName()));
		return this.findAny(query);
	}

	public List<Groups> findGroupList(final Integer pUserId, final String pProjectId) {
		final DbCommand query = new DbCommand(Q_GROUPS5, new FnParam("procet_id", pProjectId),
				new FnParam("user_id", pUserId));
		query.setQuery(Constants.getSgl(query.getName()));
		return this.findAny(query);
	}

	public List<IDataEntity> findUserPrinciples(final String pEposta, final String pPrjkod) {
		final DbCommand query = new DbCommand(Q_GRPTNM3, new FnParam("eposta", pEposta),
				new FnParam("prjkod", pPrjkod));
		query.setQuery(Constants.getSgl(query.getName()));
		return this.findAny(query, GroupUsers.class);
	}

	public Long findGroupUsersHistoryId() {
		final DbCommand query = new DbCommand(Q_GROUP_USERS_HISTORY1, new FnParam[0]);
		query.setQuery(Constants.getSgl(query.getName()));
		final GroupUsersHistory de = (GroupUsersHistory) this.findOne(query, GroupUsersHistory.class);
		Long idx = null;
		if (de != null) {
			idx = de.getIdx();
		}
		if (idx != null) {
			return idx + 1L;
		}
		return 0L;
	}

	public synchronized Long findGroupProjectFuncsHistoryId() {
		final DbCommand query = new DbCommand(Q_GROUP_PROJECT_FUNCS_HISTORY1, new FnParam[0]);
		query.setQuery(Constants.getSgl(query.getName()));
		final GroupProjectFuncsHistory de = (GroupProjectFuncsHistory) this.findOne(query,
				GroupProjectFuncsHistory.class);
		Long idx = null;
		if (de != null) {
			idx = de.getIdx();
		}
		if (idx != null) {
			return idx + 1L;
		}
		return 0L;
	}

	public synchronized IResult<String> deleteUserFromGroup(final Integer pGroupId, final Integer[] pUserIds,
			final IUser pUser, final String pClientIP) {
		IResult<String> result = null;
		Long idx = findGroupUsersHistoryId();
		final Date datetime = new Date();
		final Integer self = pUser.getId();
		final String email = pUser.getEmail();
		final List<IDataEntity> deleteList = new ArrayList<>();
		final List<IDataEntity> addHistoryList = new ArrayList<>();
		for (int dI = 0; dI < pUserIds.length; ++dI) {
			if (!self.equals(pUserIds[dI])) {
				final GroupUsers delete = new GroupUsers(pGroupId, pUserIds[dI]);
				delete.setLastClientInfo(email, pClientIP, datetime);
				delete.accept();
				delete.delete();
				deleteList.add(delete);

				final GroupUsersHistory history = new GroupUsersHistory(idx, delete);
				history.setUpdateUser((Users) pUser, GroupUsersHistory.UPDATE_MODE_DELETE);
				history.setClientInfo(email, pClientIP, datetime);
				addHistoryList.add(history);
				++idx;
			}
		}
		if (!deleteList.isEmpty()) {
			return saveAtomic(deleteList, addHistoryList);
		}
		return result;
	}

	public synchronized IResult<String> deleteUserFromGroup(final String pAppId, final Integer pUserId,
			final IUser pUser, final String pClientIP) {
		IResult<String> result = null;
		final Date datetime = new Date();
		final Integer self = pUser.getId();
		final String email = pUser.getEmail();
		if (!self.equals(pUserId)) {
			final List<Groups> groupList = findGroupList(pUserId, pAppId);
			if (groupList != null && !groupList.isEmpty()) {
				Long idx = this.findGroupUsersHistoryId();
				final List<IDataEntity> deleteList = new ArrayList<>();
				final List<IDataEntity> addHistoryList = new ArrayList<>();
				for (final Groups v : groupList) {
					final GroupUsers delete = new GroupUsers(v.getId(), pUserId);
					delete.setLastClientInfo(email, pClientIP, datetime);
					delete.accept();
					delete.delete();
					deleteList.add(delete);

					final GroupUsersHistory history = new GroupUsersHistory(idx, delete);
					history.setUpdateUser((Users) pUser, GroupUsersHistory.UPDATE_MODE_DELETE);
					history.setClientInfo(email, pClientIP, datetime);
					addHistoryList.add(history);
					++idx;
				}
				if (!deleteList.isEmpty()) {
					return saveAtomic(deleteList, addHistoryList);
				}
			}
		}
		return result;
	}

	public IResult<String> addUserToGroup(final Integer pGroupId, final Integer[] pUserIds, final IUser pUser,
			final String pClientIP) {
		IResult<String> result = null;
		Long idx = findGroupUsersHistoryId();
		final Date datetime = new Date();
		final String email = pUser.getEmail();
		final List<IDataEntity> addList = new ArrayList<>();
		final List<IDataEntity> addHistoryList = new ArrayList<>();
		final List<IDataEntity> groupUsersList = findGroupUsers(pGroupId);
		for (int dI = 0; dI < pUserIds.length; ++dI) {
			if (!containsUserInGroup(groupUsersList, pUserIds[dI])) {
				final GroupUsers groupuser = new GroupUsers(pGroupId, pUserIds[dI]);
				groupuser.setLastClientInfo(email, pClientIP, datetime);
				addList.add(groupuser);

				final GroupUsersHistory history = new GroupUsersHistory(idx, groupuser);
				history.setUpdateUser((Users) pUser, GroupUsersHistory.UPDATE_MODE_ADD);
				history.setClientInfo(email, pClientIP, datetime);
				addHistoryList.add(history);
				++idx;
			}
		}
		if (!addList.isEmpty()) {
			return saveAtomic(addList, addHistoryList);
		}
		return result;
	}

	public synchronized IResult<Groups> save(final Groups pGroup, final IUser pUser) {
		final IResult<Groups> result = checkFields(pGroup);
		if (result.isSuccess()) {
			GroupUsers groupUser = null;
			GroupUsersHistory groupUserHistory = null;
			if (pGroup.isNew()) {
				final Integer groupId = findGroupId();
				pGroup.setId(groupId);
				pGroup.setHierarchy(groupId.toString());
				pGroup.setGroupType(Groups.GROUP_TYPE_USER);
				groupUser = new GroupUsers(groupId, pUser.getId());
				setLastClientInfo(groupUser, pUser);

				groupUserHistory = new GroupUsersHistory(findGroupUsersHistoryId(), groupUser);
				groupUserHistory.setUpdateUser((Users) pUser, GroupUsersHistory.UPDATE_MODE_ADD);
				groupUserHistory.setClientInfo(pUser);
			}
			setLastClientInfo(pGroup, pUser);
			final IResult<String> temp = saveAtomic(pGroup, groupUser, groupUserHistory);
			if (temp != null) {
				if (temp.isSuccess())
					pGroup.accept();
				result.setSuccess(temp.isSuccess());
				result.setMessage(temp.getMessage());
			} else
				result.setMessage(BaseConstants.MESSAGE_SAVE_ERROR);
			result.setData(pGroup);
		}
		return result;
	}

	private boolean containsUserInGroup(final List<IDataEntity> pGroupUsersList, final Integer pUserId) {
		if (!BaseConstants.isEmpty(pGroupUsersList)) {
			for (final IDataEntity groupUser : pGroupUsersList) {
				if (pUserId.equals(((GroupUsers) groupUser).getUserId())) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean containsFnInGroup(final List<IDataEntity> pGroupFnList, final String pFuncId) {
		if (!BaseConstants.isEmpty(pGroupFnList)) {
			for (final IDataEntity groupFunc : pGroupFnList) {
				if (pFuncId.equals(((GroupProjectFuncs) groupFunc).getProjectFuncsId())) {
					return true;
				}
			}
		}
		return false;
	}

	public List<IDataEntity> findGroupUsers(final Integer pGroupId) {
		final DbCommand query = new DbCommand(Q_GROUP_USERS1, new FnParam("groupid", pGroupId));
		query.setQuery(Constants.getSgl(query.getName()));
		return this.findAny(query, GroupUsers.class);
	}

	public List<IDataEntity> findGroupFuncs(final Integer pGroupId) {
		final DbCommand query = new DbCommand(Q_GROUP_FUNCS1, new FnParam("grpkod", pGroupId));
		query.setQuery(Constants.getSgl(query.getName()));
		return this.findAny(query, GroupProjectFuncs.class);
	}

	private IResult<Groups> checkFields(final Groups pGroup) {
		final IResult<Groups> res = new Result<>(true, "");
		final StringBuilder dSb = new StringBuilder();
		String mString = pGroup.getName();
		if (StringTool.isNull(mString) || mString.length() < 3) {
			res.setSuccess(false);
			dSb.append(BaseConstants.getString("FrmGroup.Warning.Name"));
			dSb.append(BaseConstants.EOL);
		}
		mString = pGroup.getProjectId();
		if (StringTool.isNull(mString) || mString.length() < 3) {
			res.setSuccess(false);
			dSb.append(BaseConstants.getString("FrmProjectAUL.Warning.Name"));
			dSb.append(BaseConstants.EOL);
		}
		res.setMessage(dSb.toString());
		return res;
	}

	public IResult<String> addFnToGroup(final Integer pGroupId, final String[] pFuncs, final IUser pUser,
			final String pClientIP) {
		IResult<String> result = null;
		Long idx = findGroupProjectFuncsHistoryId();
		final Date datetime = new Date();
		final String email = pUser.getEmail();
		final List<IDataEntity> addList = new ArrayList<>();
		final List<IDataEntity> addHistoryList = new ArrayList<>();
		final List<IDataEntity> groupFnList = findGroupFuncs(pGroupId);
		for (int dI = 0; dI < pFuncs.length; ++dI) {
			if (!containsFnInGroup(groupFnList, pFuncs[dI])) {
				final GroupProjectFuncs groupFuncs = new GroupProjectFuncs(pGroupId, pFuncs[dI]);
				groupFuncs.setLastClientInfo(email, pClientIP, datetime);
				addList.add(groupFuncs);
				final GroupProjectFuncsHistory history = new GroupProjectFuncsHistory(idx, groupFuncs);
				history.setUpdateUser((Users) pUser, GroupProjectFuncsHistory.UPDATE_MODE_ADD);
				history.setClientInfo(email, pClientIP, datetime);
				addHistoryList.add(history);
				++idx;
			}
		}
		if (!addList.isEmpty()) {
			return saveAtomic(addList, addHistoryList);
		}
		return result;
	}

	public synchronized IResult<String> deleteFnFromGroup(final Integer pGroupId, final String[] pFuncs,
			final IUser pUser, final String pClientIP) {
		IResult<String> dSnc = null;
		Long idx = findGroupProjectFuncsHistoryId();
		final Date datetime = new Date();
		final String email = pUser.getEmail();
		final List<IDataEntity> deleteList = new ArrayList<>();
		final List<IDataEntity> addHistoryList = new ArrayList<>();
		for (int dI = 0; dI < pFuncs.length; ++dI) {
			final GroupProjectFuncs delete = new GroupProjectFuncs(pGroupId, pFuncs[dI]);
			delete.setLastClientInfo(email, pClientIP, datetime);
			delete.accept();
			delete.delete();
			deleteList.add(delete);
			final GroupProjectFuncsHistory history = new GroupProjectFuncsHistory(idx, delete);
			history.setUpdateUser((Users) pUser, GroupProjectFuncsHistory.UPDATE_MODE_DELETE);
			history.setClientInfo(email, pClientIP, datetime);
			addHistoryList.add(history);
			++idx;
		}
		if (!deleteList.isEmpty()) {
			dSnc = saveAtomic(deleteList, addHistoryList);
		}
		return dSnc;
	}
}
