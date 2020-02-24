package com.springstudy.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class DocFile implements Serializable {
	private static final long serialVersionUID = -381008200432720973L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fileid;

	@Column(name = "docid")
	private Long docid;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "stored_name")
	private String storedName;

	@Column(name = "file_size")
	private Long fileSize;

	@Column(name = "regdate")
	private Date regdate;

	public Long getFileid() {
		return fileid;
	}

	public void setFileid(Long fileid) {
		this.fileid = fileid;
	}

	public Long getDocid() {
		return docid;
	}

	public void setDocid(Long docid) {
		this.docid = docid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}
