package com.yp.admin.model;

import java.util.List;

import com.yp.admin.Constants;
import com.yp.admin.data.Commons;
import com.yp.core.AModel;
import com.yp.core.BaseConstants;
import com.yp.core.FnParam;
import com.yp.core.db.DbCommand;
import com.yp.core.entity.IResult;
import com.yp.core.entity.Result;
import com.yp.core.tools.StringTool;
import com.yp.core.user.IUser;

public class CommonModel extends AModel<Commons> {
	public static final String Q_COMMONS_PARENT_ID = "Q.COMMONS.PARENT.ID";
	public static final String Q_ORTKOD1 = "SRGORTKOD1";

	public List<Commons> findByParent(final Integer pParentId) {
		final DbCommand query = new DbCommand(Q_COMMONS_PARENT_ID, new FnParam("parent_id", pParentId));
		query.setQuery(Constants.getSgl(query.getName()));
		return this.findAny(query);
	}

	public IResult<Commons> save(final Commons pCommon, final IUser pUser) {
		final IResult<Commons> res = this.validateFields(pCommon);
		if (res.isSuccess()) {
			setLastClientInfo(pCommon, pUser);
			return super.save(pCommon);
		}
		return res;
	}

	private IResult<Commons> validateFields(final Commons pCommon) {
		final IResult<Commons> res = new Result<>(true, "");
		final StringBuilder dSb = new StringBuilder();
		String mString = pCommon.getName();
		if (StringTool.isNull(mString) || mString.length() < 3) {
			res.setSuccess(false);
			dSb.append(BaseConstants.getString("FrmProjectAUL.Warning.Id"));
			dSb.append(BaseConstants.EOL);
		}
		mString = pCommon.getAbrv();
		if (StringTool.isNull(mString) || mString.length() < 3) {
			res.setSuccess(false);
			dSb.append(BaseConstants.getString("FrmProjectAUL.Warning.Name"));			
			dSb.append(BaseConstants.EOL);
		}
		res.setMessage(dSb.toString());
		return res;
	}

	public Integer getCommonsId() {
		final DbCommand query = new DbCommand(Q_ORTKOD1, new FnParam[0]);
		query.setQuery(BaseConstants.getSgl(query.getName()));
		final Commons de = this.findOne(query);
		Integer res = null;
		if (de != null) {
			res = de.getId();
		}
		if (res != null) {
			return res + 1;
		}
		return 0;
	}

}
