package com.project.boardproject.cs.service;

import java.io.Serializable;

public class CsSendMailVO implements Serializable {

	//첨부파일번호, 글번호, 첨부파일명
	private static final long serialVersionUID = 1L;
	private String email;
	private String subject;
	private String contents;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	@Override
	public String toString() {
		return "CsSendMailVO [email=" + email + ", subject=" + subject + ", contents=" + contents + "]";
	}
	
	
	
	
}
