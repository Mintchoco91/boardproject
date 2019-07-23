package com.project.boardproject.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 파일명 : adminController.java
 * 용도 : 관리자 관련 컨트롤러
 * 작성자 : mintchoco91
 * 변경일 : 2019/07/22
 */

@Controller
public class adminController {

	@RequestMapping("adminIndex")
	public String adminIndex() {
		return "admin/adminIndex";
		
	}
	
}
