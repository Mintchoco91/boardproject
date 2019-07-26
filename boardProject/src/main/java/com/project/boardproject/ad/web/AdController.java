package com.project.boardproject.ad.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.boardproject.ad.service.AdService;

/*
 * 파일명 : adminController.java
 * 용도 : 관리자 관련 컨트롤러
 * 작성자 : mintchoco91
 * 변경일 : 2019/07/22
 */

@Controller
public class AdController {
 
	//private static final Logger logger = LoggerFactory.getLogger(AdController.class);
	
	@Autowired
	private AdService adService;
	
	@RequestMapping("/")
	public String getIndex() {
		return "/adminIndex";
	}
	@RequestMapping("/adminIndex")
	public String adminIndex() {
		return "admin/adminIndex";
	}
	
	
}