package com.project.boardproject.cm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
	
	@Inject
	CmDAO cmDAO;
	
	@Override
	public String sampleData() {
		String sampleResult = "";
		List<String> sampleList = new ArrayList<>();
		sampleResult = "impl에서 가져온값임. DB에서 가져오도록 수정할것";
		//System.out.println("mintchoco91");
		//DB연결 후 주석제거할것
		sampleList = cmDAO.sampleData();
		return sampleResult;
	}
}
