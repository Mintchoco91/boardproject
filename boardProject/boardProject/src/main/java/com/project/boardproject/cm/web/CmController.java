package com.project.boardproject.cm.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	
	@RequestMapping(value="/", method = RequestMethod.GET) 
	public String indexPage(Model model) {
		String sampleResult="";
		sampleResult = cmservice.sampleData();
		model.addAttribute("result",sampleResult);		
		return "redirect :index";
	}
	
	@RequestMapping(value="/index")
	public String index(Model model) {
		String sampleResult="";
		
		sampleResult = cmservice.sampleData();
		model.addAttribute("result",sampleResult);		
		return "index";
	}
	
	@RequestMapping(value="/test")
	public String test() throws Exception {
		return "test";
	}
}