package com.yp.admin.data;


import com.yp.core.entity.DataEntity;
import java.lang.Integer;


public class GroupUsers extends DataEntity {

	private static String schemaName = "COMMON";
	private static String tableName = "GROUP_USERS";


	public GroupUsers(){
		super();
		className = "GroupUsers";
		setPrimaryKeys(GROUP_ID, USER_ID);
	}

	public GroupUsers(Integer pgroup_id, Integer puser_id){
		this();
		set(GROUP_ID, pgroup_id);
		set(USER_ID, puser_id);
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

	protected static final String USER_ID = "user_id";

	public Integer getUserId() {
		return (Integer) get(USER_ID);
	}
	
	public void setUserId(Integer pUserId){
		set(USER_ID, pUserId);
	}
	
	public boolean isUserIdNull(){
		return isNull(USER_ID);
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