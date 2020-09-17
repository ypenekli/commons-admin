package com.yp.admin.data;


import com.yp.core.BaseConstants;
import com.yp.core.entity.DataEntity;
import com.yp.core.entity.IDataEntity;


public class Groups extends DataEntity {

	private static final long serialVersionUID = 2760324020608568006L;
	private static String schemaName = "COMMON";
	private static String tableName = "GROUPS";

	public static final String GROUP_TYPE_ADMIN = "A";
	public static final String GROUP_TYPE_USER = "U";


	public Groups(){
		super();
		className = "Groups";
		setPrimaryKeys(ID);	
		setProjectId("-1");
	}

	public Groups(Integer pId){
		this();
		set(ID, pId);
	}

	public Groups(IDataEntity pDe){
		this(Double.valueOf(pDe.get(ID).toString()).intValue());
		set(GROUP_TYPE, pDe.get(GROUP_TYPE));
		set(HIERARCHY, pDe.get(HIERARCHY));
		set(NAME, pDe.get(NAME));
		set(PROJECT_ID, pDe.get(PROJECT_ID));
		set(STATUS, pDe.get(STATUS));		
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

	@Override
	public void checkValues(){
		super.checkValues();
		checkInteger(ID);
	}
	
	//***********
	
	public boolean isStatusEnabled() {
		return BaseConstants.ENABLED.equals(get(STATUS));
	}



	public Boolean isAdmin() {
		return GROUP_TYPE_ADMIN.equals(getGroupType());
	}
	// for rootMenuList add query below fields

	protected static final String MENU_ID = "menu_id";

	public String getMenuId() {
		return (String) get(MENU_ID);
	}

	protected static final String MENU_LABEL = "menu_label";

	public String getMenuLabel() {
		return (String) get(MENU_LABEL);
	}

	protected static final String MENU_DETAIL = "menu_detail";

	public String getMenuDetail() {
		return (String) get(MENU_DETAIL);
	}

	protected static final String MENU_TOOLTIP = "menu_tooltip";

	public String getMenuTooltip() {
		return (String) get(MENU_TOOLTIP);
	}

	// for rootMenuList add query below fields


	protected static final String PROJECT_NAME = "project_name";

	public String getProjectName() {
		return (String) get(PROJECT_NAME);
	}
}