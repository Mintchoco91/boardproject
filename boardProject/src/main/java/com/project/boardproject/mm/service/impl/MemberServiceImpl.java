package com.project.boardproject.mm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.boardproject.mm.service.impl.MemberDAO;
import com.project.boardproject.mm.service.MemberService;
import com.project.boardproject.mm.service.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public int loginCheck(MemberVO memberVO) {
		int result=1;//0: 성공, 1: 비밀번호 불일치, 2: 아이디 불일치
		String pw=memberDAO.CheckPw(memberVO.getUserId());
		if(pw==null) result=2;//id가 존재하지 않음
		else if(pw.equals(memberVO.getPw())) {
			result=0;
		}
		
		return result;
	}
	
	@Override
	public int memberRegister(MemberVO memberVO) {
		int result=1;//1:실패, 0:성공
		if(memberDAO.CheckPw(memberVO.getUserId())==null) {
			memberDAO.insertMember(memberVO);
			result=0;
		}
		return result;
	}
	
	@Override
	public int memberModify(MemberVO memberVO) {
		int result =1;//0:성공, 1: 비밀번호 불일치
		String pw=memberDAO.CheckPw(memberVO.getUserId());
		if(pw.equals(memberVO.getPw())) {
			memberDAO.ModifyMember(memberVO);
			result=0;
		}
		return result;
	}
	
	@Override
	public MemberVO getMember(String userId) {
		MemberVO memberVO= memberDAO.getMember(userId);
		return memberVO;
	}
}
