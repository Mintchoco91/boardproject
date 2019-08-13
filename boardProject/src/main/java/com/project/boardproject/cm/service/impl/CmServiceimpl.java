package com.project.boardproject.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.boardproject.cm.service.BoardVO;
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
		sampleResult = cmDAO.tempsql_001();
		return sampleResult;
	}

	@Override
	public void chboardInsert(BoardVO boardVO) {
		System.out.println(boardVO.toString());
			cmDAO.chboardInsert(boardVO);
			
	}
	@Override
	public List<BoardVO> kwboardInq(BoardVO boardVO) {
		return cmDAO.kwboardInq_001(boardVO);
	}

	@Override
	public void kwboardWrite(BoardVO boardVO) {
		cmDAO.kwboardWrite_001(boardVO);
	}
	
}
