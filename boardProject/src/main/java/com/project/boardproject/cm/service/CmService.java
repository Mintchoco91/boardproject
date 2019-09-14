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
	
	//board 정리
	public List<BoardVO> boardInq(BoardVO boardVO);

	public void boardWrite(BoardVO boardVO);
	
	public String boardDelete(String[] idxArray);

	public BoardVO boardDetail(BoardVO boardVO);

	public Integer boardModify(BoardVO boardVO);

	public Integer boardInqCnt(BoardVO boardVO);
	
	//board 정리 끝
	
	public void chboardInsert(BoardVO boardVO);

	public List<BoardVO> chboardGetList(BoardVO boardVO);

	public int chboardgetBoardCnt(BoardVO boardVO);

	public void chboardDelete(BoardVO vo);

	public int chboardUpdateReadCnt(int idx);

	public BoardVO chboardDetail(BoardVO boardVO);



}
