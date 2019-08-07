package com.project.boardproject.cm.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.boardproject.cm.service.CmService;

/*
 * 파일명 : customController.java
 * 용도    :  사용자 관련 컨트롤러
 * 작성자 : mintchoco91
 * 변경일 : 2019/07/22
 */

@Controller
public class CmController {
	
	@Inject
	private CmService cmservice;
	
	@RequestMapping("index")
	public String index(Model model) {
		String sampleResult="";
		sampleResult = cmservice.sampleData();
		model.addAttribute("result",sampleResult);		
		
		return "index";
	}

	@RequestMapping("kwboardInq")
	public String kw_boardInq(Model model) {		
		return "kwboard/kwboardList";
	}
	
	@RequestMapping("kwboardRegister")
	public String kwboardRegister(Model model) throws Exception {
		
		return "kwboard/kwboardRegister";
	}	
	
	@RequestMapping(value="/board/boardList")
	public String board(Model model) throws Exception {
		return "board/boardList";
	}
	
	@RequestMapping(value="/board/boardRegister")
	public String boardRegister(Model model) throws Exception {
		
		return "board/boardRegister";
	}
}