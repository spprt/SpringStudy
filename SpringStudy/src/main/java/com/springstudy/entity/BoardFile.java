package com.springstudy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "board_file")
public class BoardFile extends DocFile {

	private static final long serialVersionUID = -1720319848684784737L;

	public BoardFile() {
	}

	public BoardFile(Board doc) {
		super();
		this.doc = doc;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fileid;

	@ManyToOne
	@JoinColumn(name = "docid")
	private Board doc;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "stored_name")
	private String storedName;

	@Column(name = "file_size")
	private Long fileSize;

	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reg_date", insertable = false)
	private Date regdate;
	

	public Long getFileid() {
		return fileid;
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
}