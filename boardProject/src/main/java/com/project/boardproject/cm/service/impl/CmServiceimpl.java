package com.project.boardproject.cm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.boardproject.cm.service.CmService;

/*
 * 파일명 : CmServiceimpl.java
 * 용도    : 
 * 작성자 : mintchoco91
 * 변경일 : 2019/07/30
 */

@Service
public class CmServiceimpl implements CmService {
	
	@Autowired
	private CmDAO cmDAO;	
	
	@Override
	public String sampleData() {
		String sampleResult = "";
		//sampleResult = "impl에서 가져온값임. DB에서 가져오도록 수정할것";
		//DB연결 후 주석제거할것
		sampleResult = cmDAO.tempsql_001();
		return sampleResult;
	}
}
