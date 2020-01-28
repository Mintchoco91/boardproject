package com.project.boardproject.bc.blockBoard;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class BlockBoardVO {
	int idx;
	String registerId;
	int upVote;
	int downVote;
	float coinValue;
	String timestamp;
	
	public BlockBoardVO() {}
	public BlockBoardVO(int idx, float coinValue) {
		this.coinValue =coinValue;
		this.idx=idx;
	}
	public BlockBoardVO(String registerId, int upVote, int downVote, float coinValue, String timeStamp) {
		this.registerId = registerId;
		this.upVote = upVote;
		this.downVote = downVote;
		this.coinValue = coinValue;
		this.timestamp = timeStamp;
	}
	/*
	 * public static BlockBoardVO newBlockBoardVO(String registerId) { Date
	 * nowDate=new Date(); SimpleDateFormat format=new
	 * SimpleDateFormat("yyyyMMddHHmmss"); String nowTime = format.format(nowDate);
	 * BlockBoardVO newVO = new BlockBoardVO(registerId, 0, 0, 0, nowTime); return
	 * newVO; }
	 */
}
