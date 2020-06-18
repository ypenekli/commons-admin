package com.yp.admin.data;


import com.yp.core.entity.DataEntity;
import java.lang.String;
import java.lang.Integer;


public class GroupFuncs extends DataEntity {

	private static String schemaName = "COMMON";
	private static String tableName = "GROUP_FUNCS";


	public GroupFuncs(){
		super();
		className = "GroupFuncs";
		setPrimaryKeys(GROUP_ID, FUNC_ID);
	}

	public GroupFuncs(Integer pgroup_id, String pfunc_id){
		this();
		set(GROUP_ID, pgroup_id);
		set(FUNC_ID, pfunc_id);
	}

	protected static final String GROUP_ID = "group_id";

	public Integer getGroupId() {
		return (Integer) get(GROUP_ID);
	}
	
	public void setGroupId(Integer pGroupId){
		set(GROUP_ID, pGroupId);
	}
	
	public boolean isGroupIdNull(){
		return isNull(GROUP_ID);
	}

	protected static final String FUNC_ID = "func_id";

	public String getFuncId() {
		return (String) get(FUNC_ID);
	}
	
	public void setFuncId(String pFuncId){
		set(FUNC_ID, pFuncId);
	}
	
	public boolean isFuncIdNull(){
		return isNull(FUNC_ID);
	}

	@Override
	public String getSchemaName() {
		return schemaName;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

}