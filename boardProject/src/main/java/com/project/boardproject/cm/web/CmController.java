package com.project.boardproject.cm.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(value = "index")
	public String index(Model model) {
		String sampleResult = "";
		sampleResult = cmservice.sampleData();
		model.addAttribute("result", sampleResult);

		return "index";
	}

	@RequestMapping(value = "/board/boardList")
	public String board(Model model) throws Exception {
		System.out.println("List");
		return "board/boardList";
	}

	@RequestMapping(value = "/board/boardRegister")
	public String boardRegister(Model model) throws Exception {
		return "board/boardRegister";
	}

	@RequestMapping(value = "kwboardList")
	public String kwboardList(Model model) {
		return "kwboard/kwboardList";
	}

	@RequestMapping(value = "kwboardRegister")
	public String kwboardRegister(@ModelAttribute("boardVO") BoardVO boardVO, Model model) throws Exception {

		return "kwboard/kwboardRegister";
	}

	@RequestMapping(value = "kwboardInq")
	public String kwboardInq(Model model, BoardVO boardVO) {
		// 리스트로 구현
		List<BoardVO> boardVOArr = new ArrayList<BoardVO>();
		boardVOArr = cmservice.kwboardInq(boardVO);
		model.addAttribute("boardVOArr", boardVOArr);

		return "kwboard/kwboardList";
	}

	@RequestMapping(value = "kwboardWrite")
	public String kwboardWrite(Model model, BoardVO boardVO) throws Exception {
		cmservice.kwboardWrite(boardVO);
		return "redirect:kwboardInq.do";
	}


	  @RequestMapping(value="kwboardDelete", method = RequestMethod.POST)
	  public @ResponseBody String kwboardDelete(HttpServletRequest request
			  ,String[] idxArray, Model model) throws Exception {
		  
		  String result = "error";
		  result = cmservice.kwboardDelete(idxArray);
		  return result; 
	  }
	 
		@RequestMapping(value = "kwboardDetail")
		public String kwboardDetail(Model model, BoardVO boardVO) throws Exception {
			
			BoardVO resultBoardVO = new BoardVO();
			resultBoardVO = cmservice.kwboardDetail(boardVO);
			
			System.out.println("#######"+boardVO.toString());
			System.out.println("@@@@@@"+resultBoardVO.toString());
			return "kwboard/kwboardDetail";
		}
		
}