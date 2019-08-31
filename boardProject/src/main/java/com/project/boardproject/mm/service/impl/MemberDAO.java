package com.project.boardproject.mm.service.impl;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.boardproject.mm.service.MemberVO;


@Mapper
public interface MemberDAO {
//	@Autowired
//	SqlSession session;
	
	public void insertMember(MemberVO vo) ;
	
	public String CheckPw(String userId);
	
	public void ModifyMember(MemberVO vo) ;
}
