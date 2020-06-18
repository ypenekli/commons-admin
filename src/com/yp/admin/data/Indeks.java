package com.yp.admin.data;


import com.yp.core.entity.DataEntity;
import java.lang.Integer;


public class Indeks extends DataEntity {

	private static String schemaName = "COMMON";
	private static String tableName = "INDEKS";


	public Indeks(){
		super();
		className = "Indeks";
		setPrimaryKeys(IDX);
	}

	public Indeks(Integer pidx){
		this();
		set(IDX, pidx);
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

	@Override
	public String getSchemaName() {
		return schemaName;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

}