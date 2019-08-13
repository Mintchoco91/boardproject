package com.project.boardproject.cm.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.boardproject.cm.service.BoardVO;

import com.project.boardproject.cm.service.BoardVO;

/*
 * 파일명 : CmDAO.java
 * 용도    : 
 * 작성자 : mintchoco91
 * 변경일 : 2019/07/26
 */


@Mapper
public interface CmDAO  {
	public String tempsql_001();

	public void chboardInsert(BoardVO boardVO);

	public List<BoardVO> kwboardInq_001(BoardVO boardVO);
	
	public void kwboardWrite_001(BoardVO boardVO);	
}
