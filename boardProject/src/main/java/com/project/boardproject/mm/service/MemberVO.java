package com.project.boardproject.mm.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberVO {
	private int idx;
	private String userId;
	private String pw;
	private String name;
	private String email1;
	private String email2;
	private String isAdminYn;
	private String delYn;
	private String rgtId;
	private String rgtDtm;
	private String updId;
	private String updDtm;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getIsAdminYn() {
		return isAdminYn;
	}
	public void setIsAdminYn(String isAdminYn) {
		this.isAdminYn = isAdminYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getRgtId() {
		return rgtId;
	}
	public void setRgtId(String rgtId) {
		this.rgtId = rgtId;
	}
	public String getRgtDtm() {
		return rgtDtm;
	}
	public void setRgtDtm(String rgtDtm) {
		this.rgtDtm = rgtDtm;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public String getUpdDtm() {
		return updDtm;
	}
	public void setUpdDtm(String updDtm) {
		this.updDtm = updDtm;
	}
	@Override
	public String toString() {
		return "MemberVO [idx=" + idx + ", userId=" + userId + ", pw=" + pw + ", name=" + name + ", email1=" + email1
				+ ", email2=" + email2 + ", isAdminYn=" + isAdminYn + ", delYn=" + delYn + ", rgtId=" + rgtId
				+ ", rgtDtm=" + rgtDtm + ", updId=" + updId + ", updDtm=" + updDtm + "]";
	}
	
	
}
