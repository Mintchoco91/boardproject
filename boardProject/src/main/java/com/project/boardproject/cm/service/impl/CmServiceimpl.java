package com.project.boardproject.cm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.boardproject.cm.service.BoardVO;
import com.project.boardproject.cm.service.CmService;
import com.project.boardproject.cm.service.ReplyVO;

/*
 * 파일명 : CmServiceimpl.java
 * 용도    : 
 * 작성자 : mintchoco91
 * 변경일 : 2019/09/14
 */

@Service
public class CmServiceimpl implements CmService {
	
	@Autowired
	private CmDAO cmDAO;	


	@Override
	public void boardInsert(BoardVO boardVO) {
		System.out.println(boardVO.toString());
		if(cmDAO.volumeCheck()) {//디비용량 충분할 경우 insert문 실행
			cmDAO.boardInsert_001(boardVO);
		}else {
			System.out.println("디비용량 부족으로 데이터를 저장하지 못했습니다.");
		}
		
	}
	
	public List<BoardVO> boardGetList(BoardVO boardVO) {
		return cmDAO.boardGetList_001(boardVO);
	}
	
	@Override
	public int boardgetBoardCnt(BoardVO boardVO) {
		return cmDAO.boardgetBoardCnt_001(boardVO);
	}

	@Override
	public void boardDelete(BoardVO vo) {
		 cmDAO.boardDelete_001(vo);
	}

	@Override
	public int boardUpdateReadCnt(int idx) {
		return cmDAO.boardUpdateReadCnt_001(idx);
	}

	@Override
	public BoardVO boardDetail(BoardVO boardVO) {
		return cmDAO.boardDetail_001(boardVO);
	}

	@Override
	public BoardVO boardSchboard(Map<String, String> schMap) {
		return cmDAO.boardSchBoard_001(schMap);
	}

	@Override
	public void boardUpdBoard(BoardVO boardVO) {
		if(boardVO.getScrYn() == null || boardVO.getScrPw() == null || boardVO.getScrPw().trim().equals("")) {
			boardVO.setScrYn("N");
		}
		cmDAO.boardUpdBoard_001(boardVO);
	}

	@Override
	public int boardScrPwChkConfirm(BoardVO vo) {
		int result;
		result =cmDAO.boardScrPwChkConfirm_001(vo);
		return result;
	}

	/***************************댓글***************************/
	
	@Override
	public List<ReplyVO> replyGetList(int bno) {
		return cmDAO.replyGetList(bno);
	}

	@Override
	public void replyInsert(ReplyVO replyVO) {
		try {
		if(cmDAO.volumeCheck()) {//디비용량 충분할 경우 insert문 실행
			cmDAO.replyInsert(replyVO);
		}else {
			System.out.println("디비용량 부족으로 데이터를 저장하지 못했습니다.");
		}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		
	}

	@Override
	public void replyUpdate(ReplyVO replyVO) {
		if(cmDAO.volumeCheck()) {//디비용량 충분할 경우 update문 실행
			cmDAO.replyUpdate(replyVO);
		}else {
			System.out.println("디비용량 부족으로 데이터를 저장하지 못했습니다.");
		}
		
	}

	@Override
	public void replyDelete(ReplyVO replyVO) {
		cmDAO.replyDelete(replyVO);
	}

}
