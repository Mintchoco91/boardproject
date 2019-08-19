package com.project.boardproject.cm.service;

import java.util.List;
import java.util.Map;

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
	
	public String sampleData();

	public void chboardInsert(BoardVO boardVO);

	public List<BoardVO> chboardGetList(Map<String, Integer> hmap);

	public int chboardgetBoardCnt(BoardVO boardVO);
}
