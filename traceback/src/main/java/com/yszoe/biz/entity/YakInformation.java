package com.yszoe.biz.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 牦牛基本信息表
 * @author shenjiaxing
 * datetime 2015-5-08
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "YAK_INFORMATION")
public class YakInformation implements java.io.Serializable {

	// Fields

	private String wid;
	private String nh;
	private String electronicNumber;
	private String sex;
	private String breedCode;
	private Date brithday;
	private Date intoDate;
	private String appearance;
	private String origin;
	private String herdsmanCode;
	private String departCode;
	private String FNh;
	private String MNh;
	private String type;
	private String state;
	private Date outDate;
	private String virtualIdentifiers;
	private Date createDate;
	private String creater;
	private String photo;
	private String zdbm;//字典名称
	private String breedName;//品种名称
	private String herdsmanName;//牧户名称
	private long acount;//流产次数


    /** default constructor */
	public YakInformation() {
	}

	/** minimal constructor */
	public YakInformation(String wid) {
		this.wid = wid;
	}

	/** full constructor */
	public YakInformation(String wid, String nh, String electronicNumber,
			String sex, String breedCode, Date brithday, Date intoDate,
			String appearance, String origin, String herdsmanCode,
			String departCode, String FNh, String MNh, String type,
			String state, Date outDate, String virtualIdentifiers,
			Date createDate, String creater,
			String photo) {
		this.wid = wid;
		this.nh = nh;
		this.electronicNumber = electronicNumber;
		this.sex = sex;
		this.breedCode = breedCode;
		this.brithday = brithday;
		this.intoDate = intoDate;
		this.appearance = appearance;
		this.origin = origin;
		this.herdsmanCode = herdsmanCode;
		this.departCode = departCode;
		this.FNh = FNh;
		this.MNh = MNh;
		this.type = type;
		this.state = state;
		this.outDate = outDate;
		this.virtualIdentifiers = virtualIdentifiers;
		this.createDate = createDate;
		this.creater = creater;
		this.photo = photo;
	}
	/** ParameterNLGL、ParameterLXBCD constructor */
	public YakInformation(String nh, String herdsmanName,
			String origin, String sex,String breedName,
			Date brithday, Date intoDate, 
			String appearance, Date createDate) {
		super();
		this.nh = nh;
		this.sex = sex;
		this.breedName = breedName;
		this.brithday = brithday;
		this.intoDate = intoDate;
		this.appearance = appearance;
		this.origin = origin;
		this.herdsmanName = herdsmanName;
		this.createDate = createDate; 
	}
	/** ParameterLCN constructor */
	public YakInformation(String nh, String herdsmanName,
			String origin, String sex,String breedName,
			Date brithday, Date intoDate, 
			String appearance, Date createDate,long acount) {
		super();
		this.nh = nh;
		this.sex = sex;
		this.breedName = breedName;
		this.brithday = brithday;
		this.intoDate = intoDate;
		this.appearance = appearance;
		this.origin = origin;
		this.herdsmanName = herdsmanName;
		this.createDate = createDate; 
		this.acount = acount;
	}
	/** ParameterYCQ constructor */
	public YakInformation(String nh, String herdsmanName,
			String origin, String sex,String breedName,
			Date brithday,String appearance, Date createDate,
			String creater,String electronicNumber,long acount) {
		super();
		this.nh = nh;
		this.sex = sex;
		this.breedName = breedName;
		this.brithday = brithday;
		this.appearance = appearance;
		this.origin = origin;
		this.herdsmanName = herdsmanName;
		this.createDate = createDate; 
		this.creater = creater;
		this.electronicNumber = electronicNumber;
		this.acount = acount;
	}
	@Transient
	public long getAcount() {
		return acount;
	}

	public void setAcount(long acount) {
		this.acount = acount;
	}
	@Transient
	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}
	@Transient
	public String getHerdsmanName() {
		return herdsmanName;
	}

	public void setHerdsmanName(String herdsmanName) {
		this.herdsmanName = herdsmanName;
	}

	// Property accessors
	@Id
	@Column(name = "WID", unique = true, nullable = false, length = 50)
	public String getWid() {
		return this.wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	@Column(name = "NH", length = 50,unique=true)
	//@Transient
	public String getNh() {
		return this.nh;
	}

	public void setNh(String nh) {
		this.nh = nh;
	}

	@Column(name = "ELECTRONIC_NUMBER", length = 50)
	public String getElectronicNumber() {
		return this.electronicNumber;
	}

	public void setElectronicNumber(String electronicNumber) {
		this.electronicNumber = electronicNumber;
	}

	@Column(name = "SEX", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "BREED_CODE", length = 50)
	public String getBreedCode() {
		return this.breedCode;
	}

	public void setBreedCode(String breedCode) {
		this.breedCode = breedCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BRITHDAY", length = 7)
	public Date getBrithday() {
		return this.brithday;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INTO_DATE", length = 7)
	public Date getIntoDate() {
		return this.intoDate;
	}

	public void setIntoDate(Date intoDate) {
		this.intoDate = intoDate;
	}

	@Column(name = "APPEARANCE", length = 2)
	public String getAppearance() {
		return this.appearance;
	}

	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}

	@Column(name = "ORIGIN", length = 2)
	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Column(name = "HERDSMAN_CODE", length = 50)
	public String getHerdsmanCode() {
		return this.herdsmanCode;
	}

	public void setHerdsmanCode(String herdsmanCode) {
		this.herdsmanCode = herdsmanCode;
	}

	@Column(name = "DEPART_CODE", length = 50)
	public String getDepartCode() {
		return this.departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	@Column(name = "F_NH", length = 50)
	public String getFNh() {
		return this.FNh;
	}

	public void setFNh(String FNh) {
		this.FNh = FNh;
	}

	@Column(name = "M_NH", length = 50)
	public String getMNh() {
		return this.MNh;
	}

	public void setMNh(String MNh) {
		this.MNh = MNh;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OUT_DATE", length = 7)
	public Date getOutDate() {
		return this.outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	@Column(name = "VIRTUAL_IDENTIFIERS", length = 1)
	public String getVirtualIdentifiers() {
		return this.virtualIdentifiers;
	}

	public void setVirtualIdentifiers(String virtualIdentifiers) {
		this.virtualIdentifiers = virtualIdentifiers;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATER", length = 50)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	@Column(name = "PHOTO", length = 50)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Transient
	public String getZdbm() {
		return zdbm;
	}

	public void setZdbm(String zdbm) {
		this.zdbm = zdbm;
	}
	
	

}