package com.yp.admin.data;

import com.yp.core.entity.DataEntity;
import com.yp.core.entity.IDataEntity;

public class GroupProjectFunc extends DataEntity {

	private static final long serialVersionUID = 7097405330140651594L;
	private static String schemaName = "COMMON";
	private static String tableName = "GROUP_PROJECT_FUNCS";

	public GroupProjectFunc() {
		super();
		className = "GroupProjectFunc";
		setPrimaryKeys(GROUP_ID, PROJECT_FUNCS_ID);
	}

	public GroupProjectFunc(Integer pGroupId, String pProjectFuncsId) {
		this();
		set(GROUP_ID, pGroupId);
		set(PROJECT_FUNCS_ID, pProjectFuncsId);
	}

	public GroupProjectFunc(IDataEntity pDe) {
		this(Double.valueOf(pDe.get(GROUP_ID).toString()).intValue(), (String) pDe.get(PROJECT_FUNCS_ID));
	}

	protected static final String GROUP_ID = "group_id";

	public Integer getGroupId() {
		return (Integer) get(GROUP_ID);
	}

	public void setGroupId(Integer pGroupId) {
		set(GROUP_ID, pGroupId);
	}

	public boolean isGroupIdNull() {
		return isNull(GROUP_ID);
	}

	protected static final String PROJECT_FUNCS_ID = "project_funcs_id";

	public String getProjectFuncsId() {
		return (String) get(PROJECT_FUNCS_ID);
	}

	public void setProjectFuncsId(String pProjectFuncsId) {
		set(PROJECT_FUNCS_ID, pProjectFuncsId);
	}

	public boolean isProjectFuncsIdNull() {
		return isNull(PROJECT_FUNCS_ID);
	}

	@Override
	public String getSchemaName() {
		return schemaName;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void checkValues() {
		super.checkValues();
		checkInteger(GROUP_ID);
	}

}