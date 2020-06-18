package com.yp.admin.data;

import com.yp.core.BaseConstants;
import com.yp.core.entity.DataEntity;
import java.lang.String;
import java.lang.Integer;

public class ProjectSubfuncs extends DataEntity {

	private static String schemaName = "COMMON";
	private static String tableName = "PROJECT_SUBFUNCS";

	public ProjectSubfuncs() {
		super();
		className = "ProjectSubfuncs";
		setPrimaryKeys(ID);
	}

	public ProjectSubfuncs(String pid) {
		this();
		set(ID, pid);
	}

	protected static final String ID = "id";

	public String getId() {
		return (String) get(ID);
	}

	public void setId(String pId) {
		set(ID, pId);
	}

	public boolean isIdNull() {
		return isNull(ID);
	}

	protected static final String PROJECT_ID = "project_id";

	public String getProjectId() {
		return (String) get(PROJECT_ID);
	}

	public void setProjectId(String pProjectId) {
		set(PROJECT_ID, pProjectId);
	}

	public boolean isProjectIdNull() {
		return isNull(PROJECT_ID);
	}

	protected static final String NAME = "name";

	public String getName() {
		return (String) get(NAME);
	}

	public void setName(String pName) {
		set(NAME, pName);
	}

	public boolean isNameNull() {
		return isNull(NAME);
	}

	protected static final String DESCRIPTION = "description";

	public String getDescription() {
		return (String) get(DESCRIPTION);
	}

	public void setDescription(String pDescription) {
		set(DESCRIPTION, pDescription);
	}

	public boolean isDescriptionNull() {
		return isNull(DESCRIPTION);
	}

	protected static final String URL = "url";

	public String getUrl() {
		return (String) get(URL);
	}

	public void setUrl(String pUrl) {
		set(URL, pUrl);
	}

	public boolean isUrlNull() {
		return isNull(URL);
	}

	protected static final String TARGET = "target";

	public String getTarget() {
		return (String) get(TARGET);
	}

	public void setTarget(String pTarget) {
		set(TARGET, pTarget);
	}

	public boolean isTargetNull() {
		return isNull(TARGET);
	}

	protected static final String PARENT_ID = "parent_id";

	public String getParentId() {
		return (String) get(PARENT_ID);
	}

	public void setParentId(String pParentId) {
		set(PARENT_ID, pParentId);
	}

	public boolean isParentIdNull() {
		return isNull(PARENT_ID);
	}

	protected static final String IDX = "idx";

	public Integer getIdx() {
		return (Integer) get(IDX);
	}

	public void setIdx(Integer pIdx) {
		set(IDX, pIdx);
	}

	public boolean isIdxNull() {
		return isNull(IDX);
	}

	protected static final String LEVEL = "level";

	public Integer getLevel() {
		return (Integer) get(LEVEL);
	}

	public void setLevel(Integer pLevel) {
		set(LEVEL, pLevel);
	}

	public boolean isLevelNull() {
		return isNull(LEVEL);
	}

	protected static final String HIERARCHY = "hierarchy";

	public String getHierarchy() {
		return (String) get(HIERARCHY);
	}

	public void setHierarchy(String pHierarchy) {
		set(HIERARCHY, pHierarchy);
	}

	public boolean isHierarchyNull() {
		return isNull(HIERARCHY);
	}

	protected static final String LEAF = "leaf";

	public String getLeaf() {
		return (String) get(LEAF);
	}

	public void setLeaf(String pLeaf) {
		set(LEAF, pLeaf);
	}

	public boolean isLeafNull() {
		return isNull(LEAF);
	}

	protected static final String ICON_URL = "icon_url";

	public String getIconUrl() {
		return (String) get(ICON_URL);
	}

	public void setIconUrl(String pIconUrl) {
		set(ICON_URL, pIconUrl);
	}

	public boolean isIconUrlNull() {
		return isNull(ICON_URL);
	}

	protected static final String STATUS = "status";

	public String getStatus() {
		return (String) get(STATUS);
	}

	public void setStatus(String pStatus) {
		set(STATUS, pStatus);
	}

	public boolean isStatusNull() {
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

	public boolean isStatusActive() {
		return BaseConstants.ACTIVE.equals(get(STATUS));
	}

	public boolean isLeaf() {
		return BaseConstants.TRUE.equals(get(LEAF));
	}
}