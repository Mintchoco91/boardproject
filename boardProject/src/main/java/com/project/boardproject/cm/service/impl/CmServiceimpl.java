package com.project.boardproject.cm.service.impl;

import java.util.List;
import java.util.Map;

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

	//내용 : 게시판 조회
	@Override
	public List<BoardVO> boardInq(BoardVO boardVO) {
		return cmDAO.boardInq_001(boardVO);
	}

	//내용 : 게시판 글쓰기
	@Override
	public void boardWrite(BoardVO boardVO) {
		cmDAO.boardWrite_001(boardVO);
	}

	//내용 : 게시판 총 글수 count
	@Override
	public Integer boardInqCnt(BoardVO boardVO) {
		return cmDAO.boardInqCnt_001(boardVO);
	}

	//내용 : 게시판 글 삭제
	@Override
	public String boardDelete(String[] idxArray) {
		String[] convIdxArray = idxArray[0].split(",");
		Integer resultval = 0;
		String result = "error";
		
		BoardVO boardVO = new BoardVO();
		for(int i=0; i<convIdxArray.length ; i++) {
			boardVO.setIdx(Integer.parseInt(convIdxArray[i]));
			resultval = resultval + cmDAO.boardDelete_001(boardVO);
		}
		
		if(resultval == convIdxArray.length) {
			result = "success";
		}
		return result;
	}
	
	//내용 : 게시판 글 상세조회
	@Override
	public BoardVO boardDetail(BoardVO boardVO) {
		return cmDAO.boardDetail_001(boardVO);
	}

	//내용 : 게시판 글 수정
	@Override
	public Integer boardModify(BoardVO boardVO) {
		Integer resultval = 0;
		resultval = cmDAO.boardModify_001(boardVO);
		return resultval;
	}
	
	@Override
	public void chboardInsert(BoardVO boardVO) {
		System.out.println(boardVO.toString());
			cmDAO.chboardInsert_001(boardVO);
			
	}
	
	public List<BoardVO> chboardGetList(BoardVO boardVO) {
		return cmDAO.chboardGetList_001(boardVO);
	}
	
	@Override
	public int chboardgetBoardCnt(BoardVO boardVO) {
		return cmDAO.chboardgetBoardCnt_001(boardVO);
	}

	@Override
	public void chboardDelete(BoardVO vo) {
		 cmDAO.chboardDelete_001(vo);
	}

	@Override
	public int chboardUpdateReadCnt(int idx) {
		return cmDAO.chboardUpdateReadCnt_001(idx);
	}

	@Override
	public BoardVO chboardDetail(BoardVO boardVO) {
		return cmDAO.chboardDetail_001(boardVO);
	}
}
