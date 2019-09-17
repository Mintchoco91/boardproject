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
	
	@Override
	public String sampleData() {
		String sampleResult = "";
		//sampleResult = cmDAO.tempsql_001();
		return sampleResult;
	}

	@Override
	public void chboardInsert(BoardVO boardVO) {
		System.out.println(boardVO.toString());
			cmDAO.chboardInsert_001(boardVO);
			
	}
	@Override
	public List<BoardVO> kwboardInq(BoardVO boardVO) {
		return cmDAO.kwboardInq_001(boardVO);
	}

	@Override
	public void kwboardWrite(BoardVO boardVO) {
		cmDAO.kwboardWrite_001(boardVO);
	}

	@Override
	public Integer kwboardInqCnt(BoardVO boardVO) {
		return cmDAO.kwboardInqCnt_001(boardVO);
	}

	@Override
	public String kwboardDelete(String[] idxArray) {
		String[] convIdxArray = idxArray[0].split(",");
		Integer resultval = 0;
		String result = "error";
		
		BoardVO boardVO = new BoardVO();
		for(int i=0; i<convIdxArray.length ; i++) {
			boardVO.setIdx(Integer.parseInt(convIdxArray[i]));
			resultval = resultval + cmDAO.kwboardDelete_001(boardVO);
		}
		
		if(resultval == convIdxArray.length) {
			result = "success";
		}
		return result;
	}
	
	@Override
	public BoardVO kwboardDetail(BoardVO boardVO) {
		return cmDAO.kwboardDetail_001(boardVO);
	}

	@Override
	public Integer kwboardModify(BoardVO boardVO) {
		Integer resultval = 0;
		resultval = cmDAO.kwboardModify_001(boardVO);
		return resultval;
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

	@Override
	public BoardVO chboardSchBoard(Map<String, String> schMap) {
		return cmDAO.chboardSchBoard_001(schMap);
	}

	@Override
	public void chboardUpdBoard(BoardVO boardVO) {
		cmDAO.chbaordUpdBoard_001(boardVO);
		
	}
}
