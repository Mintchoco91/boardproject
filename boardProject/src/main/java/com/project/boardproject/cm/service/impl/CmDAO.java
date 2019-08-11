package com.project.boardproject.cm.service.impl;

import org.apache.ibatis.annotations.Mapper;

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

	public String kwboardInq_001();
	
	public void kwboardWrite_001(BoardVO boardVO);	
}
