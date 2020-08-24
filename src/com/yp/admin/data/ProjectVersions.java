package com.yp.admin.data;


import java.math.BigDecimal;
import java.util.Date;
import com.yp.core.entity.DataEntity;
import com.yp.core.tools.DateTime;


public class ProjectVersions extends DataEntity {

	private static final long serialVersionUID = -5398951249303223864L;
	private static String schemaName = "COMMON";
	private static String tableName = "PROJECT_VERSIONS";


	public ProjectVersions(){
		super();
		className = "ProjectVersions";
		setPrimaryKeys(PROJECT_ID, VERSION, IDX);
	}

	public ProjectVersions(String pProjectId, Integer pVersion, Integer pIdx){
		this();
		set(PROJECT_ID, pProjectId);
		set(VERSION, pVersion);
		set(IDX, pIdx);
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

	protected static final String VERSION = "version";

	public Integer getVersion() {
		return (Integer) get(VERSION);
	}
	
	public void setVersion(Integer pVersion){
		set(VERSION, pVersion);
	}
	
	public boolean isVersionNull(){
		return isNull(VERSION);
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

	protected static final String LABEL = "label";

	public String getLabel() {
		return (String) get(LABEL);
	}
	
	public void setLabel(String pLabel){
		set(LABEL, pLabel);
	}
	
	public boolean isLabelNull(){
		return isNull(LABEL);
	}

	protected static final String PROJECT_FUNCS_ID = "project_funcs_id";

	public String getProjectFuncsId() {
		return (String) get(PROJECT_FUNCS_ID);
	}
	
	public void setProjectFuncsId(String pProjectFuncsId){
		set(PROJECT_FUNCS_ID, pProjectFuncsId);
	}
	
	public boolean isProjectFuncsIdNull(){
		return isNull(PROJECT_FUNCS_ID);
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

	protected static final String PUBLISH_DATE = "publish_date";
		
	public BigDecimal getPublishDateDb() {
		return (BigDecimal) get(PUBLISH_DATE);
	}
	
	public void setPublishDateDb(BigDecimal pPublishDate){
		mPublishDate = null;
		set(PUBLISH_DATE, pPublishDate);
	}

	protected Date mPublishDate;

	public Date getPublishDate() {
		if (mPublishDate == null)
			mPublishDate = DateTime.asDate((BigDecimal) get(PUBLISH_DATE));
		return mPublishDate;
	}
	
	public void setPublishDate(Date pPublishDate) {
		mPublishDate = pPublishDate;
		set(PUBLISH_DATE, DateTime.asDbDateTime(pPublishDate));
	}
	
	public boolean isPublishDateNull(){
		return isNull(PUBLISH_DATE);
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
		checkInteger(VERSION);
		checkInteger(IDX);
		checkBigDecimal(PUBLISH_DATE);
	}

}