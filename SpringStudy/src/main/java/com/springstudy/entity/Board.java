package com.springstudy.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity(name = "board")
@Table(name = "board")
public class Board implements Serializable {
	private static final long serialVersionUID = -5456226363968216061L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "subject")
	private String subject;

	@Column(name = "writer_id")
	private Long writerId;

	@Column(name = "writer_name")
	private String writerName;

	@Column(name = "content")
	private String content;

	@Column(name = "hit")
	private int hit;

	@Column(name = "ip")
	private String ip;

	@Generated(GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reg_date", insertable = false)
	private Date regDate;

	@Generated(GenerationTime.ALWAYS)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "mod_date", updatable = false, insertable = false)
	private Date modDate;

	// @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval =
	// true)
//	@JoinColumn(name = "docid", referencedColumnName = "id")
	@OneToMany(mappedBy = "doc", fetch = FetchType.EAGER)
	private Collection<BoardFile> files;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Long getWriterId() {
		return writerId;
	}

	public void setWriterId(Long writerId) {
		this.writerId = writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public Collection<BoardFile> getFiles() {
		return files;
	}

	public void setFiles(Collection<BoardFile> files) {
		this.files = files;
	}
}
