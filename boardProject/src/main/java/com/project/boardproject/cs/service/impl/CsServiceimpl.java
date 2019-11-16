package com.project.boardproject.cs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.boardproject.cs.service.CsService;

/*
 * 파일명 : CsServiceimpl.java
 * 용도    : 
 * 작성자 : mintchoco91
 * 변경일 : 2019/11/16
 */

@Service
public class CsServiceimpl implements CsService {
	
	@Autowired
	private CsDAO csDAO;	

/*	//내용 : 게시판 조회
	@Override
	public List<BoardVO> boardInq(BoardVO boardVO) {
		return cmDAO.boardInq_001(boardVO);
	}
*/
}
