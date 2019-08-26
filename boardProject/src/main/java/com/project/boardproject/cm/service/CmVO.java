package com.project.boardproject.cm.service;

import java.io.Serializable;

/*
 * 파일명 : CmVO.java
 * 용도    : 
 * 작성자 : mintchoco91
 * 변경일 : 2019/07/26
 */

public class CmVO implements Serializable{
	private static final long serialVersionUID = 1L; 
	
	public String name;
	public int a;
	
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
