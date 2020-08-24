package com.yp.admin.data;


import java.math.BigDecimal;
import java.util.Date;

import com.yp.core.BaseConstants;
import com.yp.core.entity.DataEntity;
import com.yp.core.entity.IDataEntity;
import com.yp.core.tools.DateTime;
import com.yp.core.user.IUser;


public class Users extends DataEntity implements IUser{

	private static final long serialVersionUID = 6685001995998534606L;
	private static String schemaName = "COMMON";
	private static String tableName = "USERS";


	public Users(){
		super();
		className = "Users";
		setPrimaryKeys(ID);
	}

	public Users(Integer pId){
		this();
		set(ID, pId);
		set(CITIZENSHIP_NU, BigDecimal.ZERO);
		set(LOGIN_ERROR_COUNT, 0);
		setStatusActive(false);
	}

	public Users(IDataEntity pUser) {
		this(Double.valueOf(pUser.get(ID).toString()).intValue());
		set(CITIZENSHIP_NU, pUser.get(CITIZENSHIP_NU));
		set(NAME, pUser.get(NAME));
		set(SURNAME, pUser.get(SURNAME));
		set(BIRTH_DATE, pUser.get(BIRTH_DATE));
		set(BIRTH_CITY, pUser.get(BIRTH_CITY));
		set(TITLE, pUser.get(TITLE));
		set(PROFESSION, pUser.get(PROFESSION));
		set(POSITION, pUser.get(POSITION));
		set(CHECKIN_DATE, pUser.get(CHECKIN_DATE));
		set(CHECKOUT_DATE, pUser.get(CHECKOUT_DATE));
		set(INVOICE_ADDRESS, pUser.get(INVOICE_ADDRESS));
		set(INVOICE_CITY, pUser.get(INVOICE_CITY));
		set(INVOICE_DISTRICT, pUser.get(INVOICE_DISTRICT));
		setEmail((String) pUser.get(EMAIL));
		set(PHONENO1, pUser.get(PHONENO1));
		set(PHONENO2, pUser.get(PHONENO2));
		set(PHONENO3, pUser.get(PHONENO3));
		set(PASSWORD, pUser.get(PASSWORD));
		set(LOGIN_ERROR_COUNT, pUser.get(LOGIN_ERROR_COUNT));
		set(PWD_UPDATE_DATETIME, pUser.get(PWD_UPDATE_DATETIME));
		set(STATUS, pUser.get(STATUS));
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

	protected static final String CITIZENSHIP_NU = "citizenship_nu";

	public BigDecimal getCitizenshipNu() {
		return (BigDecimal) get(CITIZENSHIP_NU);
	}
	
	public void setCitizenshipNu(BigDecimal pCitizenshipNu){
		set(CITIZENSHIP_NU, pCitizenshipNu);
	}
	
	public boolean isCitizenshipNuNull(){
		return isNull(CITIZENSHIP_NU);
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

	@Override
	public void checkValues(){
		super.checkValues();
		checkInteger(ID);
		checkBigDecimal(CITIZENSHIP_NU);
		checkBigDecimal(BIRTH_DATE);
		checkInteger(BIRTH_CITY);
		checkInteger(TITLE);
		checkInteger(PROFESSION);
		checkInteger(POSITION);
		checkBigDecimal(CHECKIN_DATE);
		checkBigDecimal(CHECKOUT_DATE);
		checkInteger(LOGIN_ERROR_COUNT);
		checkBigDecimal(PWD_UPDATE_DATETIME);
		checkInteger(HOME_CITY);
		checkInteger(HOME_DISTRICT);
		checkInteger(INVOICE_CITY);
		checkInteger(INVOICE_DISTRICT);
	}

	/***********/

	protected static final String TITLE_NAME = "title_name";

	public String getTitleName() {
		return (String) get(TITLE_NAME);
	}

	protected static final String PROFESSION_NAME = "profession_name";

	public String getProfessionName() {
		return (String) get(PROFESSION_NAME);
	}

	protected static final String FULL_NAME = "full_name";

	public String getFullName() {
		if (isNull(FULL_NAME)) {
			String name = getName() + " " + getSurname();

			if (!isNull(PROFESSION_NAME))
				name = get(PROFESSION_NAME) + " " + name;
			setField(FULL_NAME, name, false);
		}
		return (String) get(FULL_NAME);
	}

	public void incrementLoginErrorCount() {
		Integer count = getLoginErrorCount();
		if (count == null)
			count = 0;
		count += 1;
		setLoginErrorCount(count);
	}

	protected static final String GROUP_ID = "group_id";

	public Integer getGroupId() {
		return (Integer) get(GROUP_ID);
	}

	public void setGroupId(Integer pGroupId) {
		setField(GROUP_ID, pGroupId, false);
	}

	public void setStatusActive(Boolean pStatus) {
		set(STATUS, pStatus ? BaseConstants.ENABLED.getKey() : BaseConstants.DISABLED.getKey());
	}

	public boolean isStatusActive() {
		return BaseConstants.ENABLED.equals(get(STATUS));
	}


	public String getMobilePhoneNu() {
		return (String) get(PHONENO2);
	}

}