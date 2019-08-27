package com.project.boardproject.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

/*
 * 파일명 : CmService.java
 * 용도    : 
 * 작성자 : mintchoco91
 * 변경일 : 2019/07/26
 */

@Service
public interface CmService {
	public List<BoardVO> kwboardInq(BoardVO boardVO);

	public void kwboardWrite(BoardVO boardVO);
	
	public String kwboardDelete(String[] idxArray);

	public BoardVO kwboardDetail(BoardVO boardVO);

	public Integer kwboardModify(BoardVO boardVO);

	public Integer kwboardInqCnt(BoardVO boardVO);
	
	
	public String sampleData();
}
