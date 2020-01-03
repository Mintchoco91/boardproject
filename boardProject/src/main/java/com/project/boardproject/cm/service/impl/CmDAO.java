package com.project.boardproject.cm.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.boardproject.cm.service.BoardVO;
import com.project.boardproject.cm.service.ReplyVO;

/*
 * 파일명 : CmDAO.java
 * 용도    : 
 * 작성자 : mintchoco91
 * 변경일 : 2019/07/26
 */


@Mapper
public interface CmDAO  {

	
	public void boardInsert_001(BoardVO boardVO);
	
	public List<BoardVO> boardGetList_001(BoardVO boardVO);

	public int boardgetBoardCnt_001(BoardVO boardVO);

	public void boardDelete_001(BoardVO boardVO);

	public int boardUpdateReadCnt_001(int idx);

	public BoardVO boardDetail_001(BoardVO vo);

	public BoardVO boardSchBoard_001(Map<String, String> schMap);

	public void boardUpdBoard_001(BoardVO boardVO);

	public int boardScrPwChkConfirm_001(BoardVO vo);	
	
	/********************************댓글****************/
	public List<ReplyVO> replyGetList(int bno); //특정 게시물에 달린 댓글 리스트 불러오기
	
	public void replyInsert(ReplyVO replyVO); //댓글 등록
	
}
