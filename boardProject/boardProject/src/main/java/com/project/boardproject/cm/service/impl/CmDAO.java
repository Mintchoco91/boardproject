package com.project.boardproject.cm.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * 파일명 : CmDAO.java
 * 용도    : 
 * 작성자 : mintchoco91
 * 변경일 : 2019/07/26
 */

//@Mapper
@Repository
public class CmDAO  {
	
	@Autowired
	SqlSession sqlSession;
	

	public String sampleData() {
		return sqlSession.selectOne("tempsql_001");
	}
}
