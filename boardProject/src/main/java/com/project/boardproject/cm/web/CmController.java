package com.project.boardproject.cm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 파일명 : customController.java
 * 용도    :  사용자 관련 컨트롤러
 * 작성자 : mintchoco91
 * 변경일 : 2019/07/22
 */

@Controller
public class CmController {
	@RequestMapping("index")
	public String index() {
		return "index";
	}

}