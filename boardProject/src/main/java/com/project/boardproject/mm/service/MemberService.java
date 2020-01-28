package com.project.boardproject.mm.service;

import org.springframework.stereotype.Service;

import com.project.boardproject.mm.service.MemberVO;

@Service
public interface MemberService {
	public int loginCheck(MemberVO memberVO);
	
	public int memberRegister(MemberVO memberVO);
	
	public int memberModify(MemberVO memberVO);
	
	public MemberVO getMember(String userId);
	
	public int memberDelete(String userId);

	public void naverRgtUsr(MemberVO vo);
}
