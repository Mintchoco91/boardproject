package com.project.boardproject.bc.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.boardproject.bc.blockBoard.BlockBoardVO;

@Mapper
public interface BlockBoardDAO {
	//public void insertBlockBoard(BlockBoardVO vo) ;
	public void insertBlockBoard(String registerId);
	
	public void updateUpVote(int idx);
	
	public void updateDownVote(int idx);
	
	public String getTimestamp(int idx);
	
	public void updateCoinValue(BlockBoardVO vo);
	
	public List<BlockBoardVO> getList(String start);
	
}
