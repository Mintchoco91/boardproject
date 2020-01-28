package com.project.boardproject.bc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.boardproject.bc.member.BlockMemberVO;

@Service
public class BlockMemberService {
	
	@Autowired
	BlockMemberDAO dao;
	
	public List<String> getIP(){
		List<String> listIP = new ArrayList();
		try{
			listIP=dao.getIP();
		}catch(Exception e) {
			//null일 경우
			return new ArrayList<String>();
		}
		return listIP;
	}
	
	public String getPublicKey(String userId) {
		return dao.getMemberPublicKey(userId);
	}
	
	public int insertBlockMember(BlockMemberVO vo) {
		int result = 0;
		try {
			if(dao.loginCheck(vo)==0) {
				dao.insertBlockMember(vo);
			}
			else result=1;
		} catch(Exception e) {
			System.out.println("실패위치: service-insertBlockMember");
			e.printStackTrace();
			result=1;
		}
		return result;
	}
	
	public int loginCheck(String userId, String pw) {
		int result = 0;
		BlockMemberVO vo = new BlockMemberVO(userId,pw);
		try {
			result = dao.loginCheck(vo);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
