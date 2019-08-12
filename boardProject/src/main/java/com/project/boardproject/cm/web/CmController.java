package com.project.boardproject.cm.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.boardproject.cm.service.BoardVO;
import com.project.boardproject.cm.service.CmService;

/*
 * 파일명 : customController.java
 * 용도    : 사용자 관련 컨트롤러
 * 작성자 : mintchoco91
 * 변경일 : 2019/07/22
 */

@Controller
public class CmController {
	
	@Autowired
	private CmService cmservice;
	
	@RequestMapping("index")
	public String index(Model model) {
		String sampleResult="";
		sampleResult = cmservice.sampleData();
		model.addAttribute("result",sampleResult);		
		
		return "index";
	}
	
	@RequestMapping(value="/board/boardList")
	public String board(Model model) throws Exception {
		return "board/boardList";
	}
	
	@RequestMapping(value="/board/boardRegister")
	public String boardRegister(Model model) throws Exception {
		return "board/boardRegister";
	}
	
	@RequestMapping("kwboardList")
	public String kwboardList(Model model) {		
		return "kwboard/kwboardList";
	}
	
	@RequestMapping("kwboardRegister")
	public String kwboardRegister(@ModelAttribute("boardVO") BoardVO boardVO, Model model) throws Exception {
		return "kwboard/kwboardRegister";
	}
	
	@RequestMapping("kwboardInq")
	public String kwboardInq(Model model,BoardVO boardVO) {
		//리스트로 구현
		List<BoardVO> boardVOArr = new ArrayList<BoardVO>();
		boardVOArr = cmservice.kwboardInq(boardVO);
		
		//출력값 확인해볼것
		//System.out.println("###"+boardVOArr().toString());
		
		return "kwboard/kwboardList";
	}
	
	@RequestMapping("kwboardWrite")
	public String kwboardWrite(Model model,BoardVO boardVO) throws Exception {
		cmservice.kwboardWrite(boardVO);
		return "kwboard/kwboardList";
	}
}