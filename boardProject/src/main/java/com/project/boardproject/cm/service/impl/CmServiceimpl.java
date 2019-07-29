package com.project.boardproject.cm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	//주석풀면 안됨
	//@Autowired
	//private CmDAO cmDAO;
	
	public String sampleData() {
		String sampleResult = "";
		sampleResult = "impl에서 가져온값임. DB에서 가져오도록 수정할것";
		//System.out.println("mintchoco91");
		//DB연결 후 주석제거할것
		//sampleResult = cmDAO.sampleData();
		return sampleResult;
	}
}
