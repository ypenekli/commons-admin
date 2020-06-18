package com.yp.admin.data;


import java.math.BigDecimal;
import java.util.Date;
import com.yp.core.entity.DataEntity;
import com.yp.core.tools.DateTime;
import java.lang.String;
import java.lang.Integer;


public class Users extends DataEntity {

	private static String schemaName = "COMMON";
	private static String tableName = "USERS";


	public Users(){
		super();
		className = "Users";
		setPrimaryKeys(ID);
	}

	public Users(Integer pid){
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

	protected static final String CITIZENSHIP_NO = "citizenship_no";

	public BigDecimal getCitizenshipNo() {
		return (BigDecimal) get(CITIZENSHIP_NO);
	}
	
	public void setCitizenshipNo(BigDecimal pCitizenshipNo){
		set(CITIZENSHIP_NO, pCitizenshipNo);
	}
	
	public boolean isCitizenshipNoNull(){
		return isNull(CITIZENSHIP_NO);
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

	protected static final String SURNAME = "surname";

	public String getSurname() {
		return (String) get(SURNAME);
	}
	
	public void setSurname(String pSurname){
		set(SURNAME, pSurname);
	}
	
	public boolean isSurnameNull(){
		return isNull(SURNAME);
	}

	protected static final String BIRTH_DATE = "birth_date";
		
	public BigDecimal getBirthDateDb() {
		return (BigDecimal) get(BIRTH_DATE);
	}
	
	public void setBirthDateDb(BigDecimal pBirthDate){
		mBirthDate = null;
		set(BIRTH_DATE, pBirthDate);
	}

	protected Date mBirthDate;

	public Date getBirthDate() {
		if (mBirthDate == null)
			mBirthDate = DateTime.asDate((BigDecimal) get(BIRTH_DATE));
		return mBirthDate;
	}
	
	public void setBirthDate(Date pBirthDate) {
		mBirthDate = pBirthDate;
		set(BIRTH_DATE, DateTime.asDbDateTime(pBirthDate));
	}
	
	public boolean isBirthDateNull(){
		return isNull(BIRTH_DATE);
	}

	protected static final String BIRTH_CITY = "birth_city";

	public Integer getBirthCity() {
		return (Integer) get(BIRTH_CITY);
	}
	
	public void setBirthCity(Integer pBirthCity){
		set(BIRTH_CITY, pBirthCity);
	}
	
	public boolean isBirthCityNull(){
		return isNull(BIRTH_CITY);
	}

	protected static final String GENDER = "gender";

	public String getGender() {
		return (String) get(GENDER);
	}
	
	public void setGender(String pGender){
		set(GENDER, pGender);
	}
	
	public boolean isGenderNull(){
		return isNull(GENDER);
	}

	protected static final String TITLE = "title";

	public Integer getTitle() {
		return (Integer) get(TITLE);
	}
	
	public void setTitle(Integer pTitle){
		set(TITLE, pTitle);
	}
	
	public boolean isTitleNull(){
		return isNull(TITLE);
	}

	protected static final String PROFESSION = "profession";

	public Integer getProfession() {
		return (Integer) get(PROFESSION);
	}
	
	public void setProfession(Integer pProfession){
		set(PROFESSION, pProfession);
	}
	
	public boolean isProfessionNull(){
		return isNull(PROFESSION);
	}

	protected static final String POSITION = "position";

	public Integer getPosition() {
		return (Integer) get(POSITION);
	}
	
	public void setPosition(Integer pPosition){
		set(POSITION, pPosition);
	}
	
	public boolean isPositionNull(){
		return isNull(POSITION);
	}

	protected static final String CHECKIN_DATE = "checkin_date";
		
	public BigDecimal getCheckinDateDb() {
		return (BigDecimal) get(CHECKIN_DATE);
	}
	
	public void setCheckinDateDb(BigDecimal pCheckinDate){
		mCheckinDate = null;
		set(CHECKIN_DATE, pCheckinDate);
	}

	protected Date mCheckinDate;

	public Date getCheckinDate() {
		if (mCheckinDate == null)
			mCheckinDate = DateTime.asDate((BigDecimal) get(CHECKIN_DATE));
		return mCheckinDate;
	}
	
	public void setCheckinDate(Date pCheckinDate) {
		mCheckinDate = pCheckinDate;
		set(CHECKIN_DATE, DateTime.asDbDateTime(pCheckinDate));
	}
	
	public boolean isCheckinDateNull(){
		return isNull(CHECKIN_DATE);
	}

	protected static final String CHECKOUT_DATE = "checkout_date";
		
	public BigDecimal getCheckoutDateDb() {
		return (BigDecimal) get(CHECKOUT_DATE);
	}
	
	public void setCheckoutDateDb(BigDecimal pCheckoutDate){
		mCheckoutDate = null;
		set(CHECKOUT_DATE, pCheckoutDate);
	}

	protected Date mCheckoutDate;

	public Date getCheckoutDate() {
		if (mCheckoutDate == null)
			mCheckoutDate = DateTime.asDate((BigDecimal) get(CHECKOUT_DATE));
		return mCheckoutDate;
	}
	
	public void setCheckoutDate(Date pCheckoutDate) {
		mCheckoutDate = pCheckoutDate;
		set(CHECKOUT_DATE, DateTime.asDbDateTime(pCheckoutDate));
	}
	
	public boolean isCheckoutDateNull(){
		return isNull(CHECKOUT_DATE);
	}

	protected static final String EMAIL = "email";

	public String getEmail() {
		return (String) get(EMAIL);
	}
	
	public void setEmail(String pEmail){
		set(EMAIL, pEmail);
	}
	
	public boolean isEmailNull(){
		return isNull(EMAIL);
	}

	protected static final String PHONENO1 = "phoneno1";

	public String getPhoneno1() {
		return (String) get(PHONENO1);
	}
	
	public void setPhoneno1(String pPhoneno1){
		set(PHONENO1, pPhoneno1);
	}
	
	public boolean isPhoneno1Null(){
		return isNull(PHONENO1);
	}

	protected static final String PHONENO2 = "phoneno2";

	public String getPhoneno2() {
		return (String) get(PHONENO2);
	}
	
	public void setPhoneno2(String pPhoneno2){
		set(PHONENO2, pPhoneno2);
	}
	
	public boolean isPhoneno2Null(){
		return isNull(PHONENO2);
	}

	protected static final String PHONENO3 = "phoneno3";

	public String getPhoneno3() {
		return (String) get(PHONENO3);
	}
	
	public void setPhoneno3(String pPhoneno3){
		set(PHONENO3, pPhoneno3);
	}
	
	public boolean isPhoneno3Null(){
		return isNull(PHONENO3);
	}

	protected static final String PASSWORD = "password";

	public String getPassword() {
		return (String) get(PASSWORD);
	}
	
	public void setPassword(String pPassword){
		set(PASSWORD, pPassword);
	}
	
	public boolean isPasswordNull(){
		return isNull(PASSWORD);
	}

	protected static final String LOGIN_ERROR_COUNT = "login_error_count";

	public Integer getLoginErrorCount() {
		return (Integer) get(LOGIN_ERROR_COUNT);
	}
	
	public void setLoginErrorCount(Integer pLoginErrorCount){
		set(LOGIN_ERROR_COUNT, pLoginErrorCount);
	}
	
	public boolean isLoginErrorCountNull(){
		return isNull(LOGIN_ERROR_COUNT);
	}

	protected static final String PWD_UPDATE_DATETIME = "pwd_update_datetime";
		
	public BigDecimal getPwdUpdateDatetimeDb() {
		return (BigDecimal) get(PWD_UPDATE_DATETIME);
	}
	
	public void setPwdUpdateDatetimeDb(BigDecimal pPwdUpdateDatetime){
		mPwdUpdateDatetime = null;
		set(PWD_UPDATE_DATETIME, pPwdUpdateDatetime);
	}

	protected Date mPwdUpdateDatetime;

	public Date getPwdUpdateDatetime() {
		if (mPwdUpdateDatetime == null)
			mPwdUpdateDatetime = DateTime.asDate((BigDecimal) get(PWD_UPDATE_DATETIME));
		return mPwdUpdateDatetime;
	}
	
	public void setPwdUpdateDatetime(Date pPwdUpdateDatetime) {
		mPwdUpdateDatetime = pPwdUpdateDatetime;
		set(PWD_UPDATE_DATETIME, DateTime.asDbDateTime(pPwdUpdateDatetime));
	}
	
	public boolean isPwdUpdateDatetimeNull(){
		return isNull(PWD_UPDATE_DATETIME);
	}

	protected static final String IBAN = "iban";

	public String getIban() {
		return (String) get(IBAN);
	}
	
	public void setIban(String pIban){
		set(IBAN, pIban);
	}
	
	public boolean isIbanNull(){
		return isNull(IBAN);
	}

	protected static final String PAYCARD_NO = "paycard_no";

	public String getPaycardNo() {
		return (String) get(PAYCARD_NO);
	}
	
	public void setPaycardNo(String pPaycardNo){
		set(PAYCARD_NO, pPaycardNo);
	}
	
	public boolean isPaycardNoNull(){
		return isNull(PAYCARD_NO);
	}

	protected static final String PAYCARD_TYPE = "paycard_type";

	public String getPaycardType() {
		return (String) get(PAYCARD_TYPE);
	}
	
	public void setPaycardType(String pPaycardType){
		set(PAYCARD_TYPE, pPaycardType);
	}
	
	public boolean isPaycardTypeNull(){
		return isNull(PAYCARD_TYPE);
	}

	protected static final String HOME_CITY = "home_city";

	public Integer getHomeCity() {
		return (Integer) get(HOME_CITY);
	}
	
	public void setHomeCity(Integer pHomeCity){
		set(HOME_CITY, pHomeCity);
	}
	
	public boolean isHomeCityNull(){
		return isNull(HOME_CITY);
	}

	protected static final String HOME_DISTRICT = "home_district";

	public Integer getHomeDistrict() {
		return (Integer) get(HOME_DISTRICT);
	}
	
	public void setHomeDistrict(Integer pHomeDistrict){
		set(HOME_DISTRICT, pHomeDistrict);
	}
	
	public boolean isHomeDistrictNull(){
		return isNull(HOME_DISTRICT);
	}

	protected static final String HOME_ADDRESS = "home_address";

	public String getHomeAddress() {
		return (String) get(HOME_ADDRESS);
	}
	
	public void setHomeAddress(String pHomeAddress){
		set(HOME_ADDRESS, pHomeAddress);
	}
	
	public boolean isHomeAddressNull(){
		return isNull(HOME_ADDRESS);
	}

	protected static final String INVOICE_CITY = "invoice_city";

	public Integer getInvoiceCity() {
		return (Integer) get(INVOICE_CITY);
	}
	
	public void setInvoiceCity(Integer pInvoiceCity){
		set(INVOICE_CITY, pInvoiceCity);
	}
	
	public boolean isInvoiceCityNull(){
		return isNull(INVOICE_CITY);
	}

	protected static final String INVOICE_DISTRICT = "invoice_district";

	public Integer getInvoiceDistrict() {
		return (Integer) get(INVOICE_DISTRICT);
	}
	
	public void setInvoiceDistrict(Integer pInvoiceDistrict){
		set(INVOICE_DISTRICT, pInvoiceDistrict);
	}
	
	public boolean isInvoiceDistrictNull(){
		return isNull(INVOICE_DISTRICT);
	}

	protected static final String INVOICE_ADDRESS = "invoice_address";

	public String getInvoiceAddress() {
		return (String) get(INVOICE_ADDRESS);
	}
	
	public void setInvoiceAddress(String pInvoiceAddress){
		set(INVOICE_ADDRESS, pInvoiceAddress);
	}
	
	public boolean isInvoiceAddressNull(){
		return isNull(INVOICE_ADDRESS);
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