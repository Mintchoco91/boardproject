package com.project.boardproject.cm.service.impl;

import org.springframework.stereotype.Service;

import com.project.boardproject.cm.service.CmService;

/*
 * 파일명 : CmServiceimpl.java
 * 용도    : 
 * 작성자 : mintchoco91
 * 변경일 : 2019/07/26
 */

@Service
public class CmServiceimpl implements CmService {
	
	public String sampleData() {
		String sampleResult = "";
		sampleResult = "DB에서 가져온값";
		
		//DB연결 후 주석제거할것
		//sampleResult = cmdao.sampleData();
		return sampleResult;
	}
}
