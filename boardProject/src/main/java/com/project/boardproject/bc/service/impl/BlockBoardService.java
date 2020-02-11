package com.project.boardproject.bc.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.boardproject.bc.blockBoard.BlockBoardVO;
import com.project.boardproject.bc.util.Configuration;

@Service
public class BlockBoardService {
	
	@Autowired
	BlockBoardDAO dao;
	
	public int insertBlockBoard(String userId) {
		int result=0;
		try {
			//dao.insertBlockBoard(vo);
			dao.insertBlockBoard(userId);
			result=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int updateUpVote(int idx) {
		int result=0;
		try {
			dao.updateUpVote(idx);
			Date nowTime=new Date();
			SimpleDateFormat form = new SimpleDateFormat("yyyyMMddHHmmss");
			String timestamp= form.format(nowTime);
			String voTimestamp= dao.getTimestamp(idx);
			float addCoinValue = 0;
			long timeInt = Long.parseLong(timestamp);
			long voTimeInt = Long.parseLong(voTimestamp);
			if(timeInt-voTimeInt < Configuration.BOARD_REWARD_TIME_DIF2) {
				addCoinValue = Configuration.BOARD_REWARD1;
			}else if(timeInt-voTimeInt < Configuration.BOARD_REWARD_TIME_DIF) {
				addCoinValue = Configuration.BOARD_REWARD2;
			}
			BlockBoardVO addVO = new BlockBoardVO(idx, addCoinValue);
			if(addCoinValue!= 0)dao.updateCoinValue(addVO);
			result=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int updateDownVote(int idx) {
		int result=0;
		try {
			dao.updateDownVote(idx);
			result=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<BlockBoardVO> getList(String start) {
		List<BlockBoardVO> list = new ArrayList<>();
		try{
			list = dao.getList(start);
		} catch(Exception e) {
			return new ArrayList<BlockBoardVO>();
		}
		return list;
	}
}
