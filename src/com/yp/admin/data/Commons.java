package com.yp.admin.data;


import com.yp.core.entity.DataEntity;


public class Commons extends DataEntity {

	private static final long serialVersionUID = 8655468033821499434L;
	private static String schemaName = "COMMON";
	private static String tableName = "COMMONS";


	public Commons(){
		super();
		className = "Commons";
		setPrimaryKeys(ID);
	}

	public Commons(Integer pId){
		this();
		set(ID, pId);
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

	protected static final String ABRV = "abrv";

	public String getAbrv() {
		return (String) get(ABRV);
	}
	
	public void setAbrv(String pAbrv){
		set(ABRV, pAbrv);
	}
	
	public boolean isAbrvNull(){
		return isNull(ABRV);
	}

	protected static final String DESCRIPTION = "description";

	public String getDescription() {
		return (String) get(DESCRIPTION);
	}
	
	public void setDescription(String pDescription){
		set(DESCRIPTION, pDescription);
	}
	
	public boolean isDescriptionNull(){
		return isNull(DESCRIPTION);
	}

	protected static final String GROUP_CODE = "group_code";

	public Integer getGroupCode() {
		return (Integer) get(GROUP_CODE);
	}
	
	public void setGroupCode(Integer pGroupCode){
		set(GROUP_CODE, pGroupCode);
	}
	
	public boolean isGroupCodeNull(){
		return isNull(GROUP_CODE);
	}

	protected static final String PARENT_ID = "parent_id";

	public Integer getParentId() {
		return (Integer) get(PARENT_ID);
	}
	
	public void setParentId(Integer pParentId){
		set(PARENT_ID, pParentId);
	}
	
	public boolean isParentIdNull(){
		return isNull(PARENT_ID);
	}

	protected static final String IDX = "idx";

	public Integer getIdx() {
		return (Integer) get(IDX);
	}
	
	public void setIdx(Integer pIdx){
		set(IDX, pIdx);
	}
	
	public boolean isIdxNull(){
		return isNull(IDX);
	}

	protected static final String LEVEL = "level";

	public Integer getLevel() {
		return (Integer) get(LEVEL);
	}
	
	public void setLevel(Integer pLevel){
		set(LEVEL, pLevel);
	}
	
	public boolean isLevelNull(){
		return isNull(LEVEL);
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

	protected static final String LEAF = "leaf";

	public String getLeaf() {
		return (String) get(LEAF);
	}
	
	public void setLeaf(String pLeaf){
		set(LEAF, pLeaf);
	}
	
	public boolean isLeafNull(){
		return isNull(LEAF);
	}

	protected static final String ICON_URL = "icon_url";

	public String getIconUrl() {
		return (String) get(ICON_URL);
	}
	
	public void setIconUrl(String pIconUrl){
		set(ICON_URL, pIconUrl);
	}
	
	public boolean isIconUrlNull(){
		return isNull(ICON_URL);
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
		checkInteger(GROUP_CODE);
		checkInteger(PARENT_ID);
		checkInteger(IDX);
		checkInteger(LEVEL);
	}

}