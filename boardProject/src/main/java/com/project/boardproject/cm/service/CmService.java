package com.project.boardproject.cm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/*
 * 파일명 : CmService.java
 * 용도    : 
 * 작성자 : mintchoco91
 * 변경일 : 2019/09/14
 */



@Service
public interface CmService {
	
	
	public void boardInsert(BoardVO boardVO);

	public List<BoardVO> boardGetList(BoardVO boardVO);

	public int boardgetBoardCnt(BoardVO boardVO);

	public void boardDelete(BoardVO vo);

	public int boardUpdateReadCnt(int idx);

	public BoardVO boardDetail(BoardVO boardVO);

	public BoardVO boardSchboard(Map<String, String> schMap);

	public void boardUpdBoard(BoardVO boardVO);

	public int boardScrPwChkConfirm(BoardVO vo);


	/********************************댓글****************/
	public List<ReplyVO> replyGetList(int bno); //특정 게시물에 달린 댓글 리스트 불러오기
	
	public boolean replyInsert(ReplyVO replyVO); //댓글 등록 
	
	public boolean replyUpdate(ReplyVO replyVO); //댓글 수정
	
	public void replyDelete(ReplyVO replyVO); //댓글 삭제

	public void visitInfoInsert(VisitInfoVO visitInfoVO); //댓글 수정
}
