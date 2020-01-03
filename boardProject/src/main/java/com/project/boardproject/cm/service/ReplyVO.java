package com.project.boardproject.cm.service;

import java.io.Serializable;

public class ReplyVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3678447845347971171L;
	private int idx; // 댓글번호
	private int bno; // 게시글 번호
	private String contents; // 내용

	private String rgtId; // 등록자ID
	private String rgtDtm; // 등록일시
	private String updId; // 수정자ID
	private String updDtm;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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
		return "ReplyVO [idx=" + idx + ", bno=" + bno + ", contents=" + contents + ", rgtId=" + rgtId + ", rgtDtm="
				+ rgtDtm + ", updId=" + updId + ", updDtm=" + updDtm + "]";
	}

}
