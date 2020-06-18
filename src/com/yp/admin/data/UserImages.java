package com.yp.admin.data;


import com.yp.core.entity.DataEntity;
import java.sql.Blob;
import java.lang.Integer;


public class UserImages extends DataEntity {

	private static String schemaName = "COMMON";
	private static String tableName = "USER_IMAGES";


	public UserImages(){
		super();
		className = "UserImages";
		setPrimaryKeys(USER_ID, IDX);
	}

	public UserImages(Integer puser_id, Integer pidx){
		this();
		set(USER_ID, puser_id);
		set(IDX, pidx);
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

	protected static final String IMAGE = "image";

	public Blob getImage() {
		return (Blob) get(IMAGE);
	}
	
	public void setImage(Blob pImage){
		set(IMAGE, pImage);
	}
	
	public boolean isImageNull(){
		return isNull(IMAGE);
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