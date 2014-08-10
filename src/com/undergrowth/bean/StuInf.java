package com.undergrowth.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StuInf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STU_INF", schema = "UNDER")
public class StuInf implements java.io.Serializable {

	// Fields

	private String id;
	private Timestamp birthday;
	private String descriptPath;

	// Constructors

	/** default constructor */
	public StuInf() {
	}

	/** minimal constructor */
	public StuInf(String id) {
		this.id = id;
	}

	/** full constructor */
	public StuInf(String id, Timestamp birthday, String descriptPath) {
		this.id = id;
		this.birthday = birthday;
		this.descriptPath = descriptPath;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "BIRTHDAY", length = 7)
	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	@Column(name = "DESCRIPT_PATH", length = 50)
	public String getDescriptPath() {
		return this.descriptPath;
	}

	public void setDescriptPath(String descriptPath) {
		this.descriptPath = descriptPath;
	}

	@Override
	public String toString() {
		return "StuInf [id=" + id + ", birthday=" + birthday
				+ ", descriptPath=" + descriptPath + "]";
	}

	
	
}