package com.project.boardproject.bc.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.boardproject.bc.member.BlockMemberVO;

@Mapper
public interface BlockMemberDAO {
	public void insertBlockMember(BlockMemberVO vo) ;
	
	public List<String> getIP();
	
	public String getMemberPublicKey(String userId);
	
	public int loginCheck(BlockMemberVO vo);
}
