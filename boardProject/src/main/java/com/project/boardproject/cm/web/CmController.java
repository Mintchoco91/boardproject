package com.project.boardproject.cm.web;

import java.net.Proxy.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	@RequestMapping(value = "boardList")
	public String board(Model model) throws Exception {
		return "board/boardList";
	}

	@RequestMapping(value = "boardRegister")
	public String boardRegister(Model model) throws Exception {
		return "board/boardRegister";
	}

	@RequestMapping(value = "kwboardList")
	public String kwboardList(Model model) {
		return "kwboard/kwboardList";
	}

	@RequestMapping(value = "kwboardWritePage")
	public String kwboardWritePage(@ModelAttribute("boardVO") BoardVO boardVO, Model model) throws Exception {
		return "kwboard/kwboardRegister";
	}

	@RequestMapping(value = "kwboardInq")
	public String kwboardInq(Model model, BoardVO boardVO) {
		// 리스트로 구현
		List<BoardVO> boardVOArr = new ArrayList<BoardVO>();
		boardVOArr = cmservice.kwboardInq(boardVO);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyyMMdd");
		Date time = new Date();
		
		String dateNow = dateFormat.format(time);
		
		String fullRgtDtm = "";
		String todayRgtDtm = "";
		String convRgtDtm = "";
		
		for(int i=0;i<boardVOArr.size();i++) {
			fullRgtDtm = boardVOArr.get(i).getRgtDtm();
			todayRgtDtm = fullRgtDtm.substring(0,8);			
			
			//if(todayRgtDtm == dateNow)  - 틀림	
			//작성일이 오늘일경우
			if(todayRgtDtm.equals(dateNow)) {
				convRgtDtm = fullRgtDtm.substring(8,10) + ":" + fullRgtDtm.substring(10,12);
			}else {
				convRgtDtm = fullRgtDtm.substring(2,4) + "." + fullRgtDtm.substring(4,6) + "." + fullRgtDtm.substring(6,8);
			}			

			boardVOArr.get(i).setRgtDtm(convRgtDtm);
		}
		
		model.addAttribute("boardVOArr", boardVOArr);

		return "kwboard/kwboardList";
	}

	@RequestMapping(value = "kwboardWrite")
	public String kwboardWrite(Model model, BoardVO boardVO) throws Exception {
		cmservice.kwboardWrite(boardVO);
		return "redirect:kwboardInq.do";
	}


	  //Post AJAX
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
			String fullRgtDtm = resultBoardVO.getRgtDtm();
			String convRgtDtm = fullRgtDtm.substring(0,4) + "." + fullRgtDtm.substring(4,6) + "." + fullRgtDtm.substring(6,8) + ". " + fullRgtDtm.substring(8,10) + ":" + fullRgtDtm.substring(10,12);
			
			resultBoardVO.setRgtDtm(convRgtDtm);
			
			model.addAttribute("boardVO",resultBoardVO);
			
			return "kwboard/kwboardDetail";
		}
		
		 
		@RequestMapping(value = "kwboardModifyPage")
		public String kwboardModifyPage(Model model, BoardVO boardVO) throws Exception {			
			model.addAttribute("boardVO",boardVO);
			return "kwboard/kwboardRegister";
		}

		@RequestMapping(value = "kwboardModify")
		public String kwboardModify(Model model, BoardVO boardVO) throws Exception {
			cmservice.kwboardModify(boardVO);
			return "redirect:kwboardInq.do";
		}
}