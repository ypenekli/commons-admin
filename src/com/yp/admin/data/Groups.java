package com.yp.admin.data;


import com.yp.core.entity.DataEntity;
import java.lang.String;
import java.lang.Integer;


public class Groups extends DataEntity {

	private static String schemaName = "COMMON";
	private static String tableName = "GROUPS";


	public Groups(){
		super();
		className = "Groups";
		setPrimaryKeys(ID);
	}

	public Groups(Integer pid){
		this();
		set(ID, pid);
	}

	protected static final String ID = "id";

	public Integer getId() {
		return (Integer) get(ID);
	}
	
	public void setId(Integer pId){
		set(ID, pId);
	}
	
	public boolean isIdNull(){
		return isNull(ID);
	}

	protected static final String NAME = "name";

	public String getName() {
		return (String) get(NAME);
	}
	
	public void setName(String pName){
		set(NAME, pName);
	}
	
	public boolean isNameNull(){
		return isNull(NAME);
	}

	protected static final String PROJECT_ID = "project_id";

	public String getProjectId() {
		return (String) get(PROJECT_ID);
	}
	
	public void setProjectId(String pProjectId){
		set(PROJECT_ID, pProjectId);
	}
	
	public boolean isProjectIdNull(){
		return isNull(PROJECT_ID);
	}

	protected static final String HIERARCHY = "hierarchy";

	public String getHierarchy() {
		return (String) get(HIERARCHY);
	}
	
	public void setHierarchy(String pHierarchy){
		set(HIERARCHY, pHierarchy);
	}
	
	public boolean isHierarchyNull(){
		return isNull(HIERARCHY);
	}

	protected static final String GROUP_TYPE = "group_type";

	public String getGroupType() {
		return (String) get(GROUP_TYPE);
	}
	
	public void setGroupType(String pGroupType){
		set(GROUP_TYPE, pGroupType);
	}
	
	public boolean isGroupTypeNull(){
		return isNull(GROUP_TYPE);
	}

	protected static final String STATUS = "status";

	public String getStatus() {
		return (String) get(STATUS);
	}
	
	public void setStatus(String pStatus){
		set(STATUS, pStatus);
	}
	
	public boolean isStatusNull(){
		return isNull(STATUS);
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