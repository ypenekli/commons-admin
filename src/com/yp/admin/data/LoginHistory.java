package com.yp.admin.data;


import java.math.BigDecimal;
import java.lang.Long;
import java.util.Date;
import com.yp.core.entity.DataEntity;
import com.yp.core.tools.DateTime;
import java.lang.Integer;


public class LoginHistory extends DataEntity {

	private static String schemaName = "COMMON";
	private static String tableName = "LOGIN_HISTORY";


	public LoginHistory(){
		super();
		className = "LoginHistory";
		setPrimaryKeys(IDX);
	}

	public LoginHistory(Long pidx){
		this();
		set(IDX, pidx);
	}

	protected static final String IDX = "idx";

	public Long getIdx() {
		return (Long) get(IDX);
	}
	
	public void setIdx(Long pIdx){
		set(IDX, pIdx);
	}
	
	public boolean isIdxNull(){
		return isNull(IDX);
	}

	protected static final String PROJECT_ID = "project_id";

	public Integer getProjectId() {
		return (Integer) get(PROJECT_ID);
	}
	
	public void setProjectId(Integer pProjectId){
		set(PROJECT_ID, pProjectId);
	}
	
	public boolean isProjectIdNull(){
		return isNull(PROJECT_ID);
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

	protected static final String LOGIN_DATETIME = "login_datetime";
		
	public BigDecimal getLoginDatetimeDb() {
		return (BigDecimal) get(LOGIN_DATETIME);
	}
	
	public void setLoginDatetimeDb(BigDecimal pLoginDatetime){
		mLoginDatetime = null;
		set(LOGIN_DATETIME, pLoginDatetime);
	}

	protected Date mLoginDatetime;

	public Date getLoginDatetime() {
		if (mLoginDatetime == null)
			mLoginDatetime = DateTime.asDate((BigDecimal) get(LOGIN_DATETIME));
		return mLoginDatetime;
	}
	
	public void setLoginDatetime(Date pLoginDatetime) {
		mLoginDatetime = pLoginDatetime;
		set(LOGIN_DATETIME, DateTime.asDbDateTime(pLoginDatetime));
	}
	
	public boolean isLoginDatetimeNull(){
		return isNull(LOGIN_DATETIME);
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