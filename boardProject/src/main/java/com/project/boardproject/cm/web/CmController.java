package com.project.boardproject.cm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.boardproject.cm.service.BoardList;
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
	
	private static final Logger logger = LoggerFactory.getLogger(CmController.class);
	
	@Autowired
	private CmService cmservice;
	
	@RequestMapping(value="/")
	public String startPage() {
		return "/chboard/chboardList";
	}
	
	@RequestMapping("index")
	public String index(Model model) {
		String sampleResult="";
		sampleResult = cmservice.sampleData();
		model.addAttribute("result",sampleResult);		
		
		return "index";
	}
	
	@RequestMapping(value="/board/boardList")
	public String board(Model model) throws Exception {
		System.out.println("List");
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
		
		/*
		for(int i=0;i<boardVOArr.size();i++) {
			System.out.println("######"+boardVOArr.get(i).toString());
		}*/
		
		model.addAttribute("boardVOArr",boardVOArr);
		
		return "kwboard/kwboardList";
	}
	
	@RequestMapping("kwboardWrite")
	public String kwboardWrite(Model model,BoardVO boardVO) throws Exception {
		cmservice.kwboardWrite(boardVO);
		return "redirect:kwboardInq.do";
	}
	
	@RequestMapping(value="chboard/chboardList.do", method = RequestMethod.GET)
	public String chboardList(@ModelAttribute("BoardVO") BoardVO boardVO, Model model,HttpServletRequest request) throws Exception {
		int totalCount = cmservice.chboardgetBoardCnt(boardVO);
		int pageSize = 10;
		int currentPage =1;
		
		try {
			currentPage =Integer.parseInt(request.getParameter("currentPage"));
		}catch(NumberFormatException e) {
		
		}
		BoardList list =new BoardList();
		list.initBoardList(pageSize, totalCount, currentPage);
		System.out.println(list.getCurrentPage() + "리스트속");
		
		Map<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo",0);
		hmap.put("endNo", 10);
		//List<BoardVO> boardList = new ArrayList<>();
		list.setBoardList(cmservice.chboardGetList(hmap));
		
		for(int i=0; i<list.getBoardList().size(); i++) {
			String year=  list.getBoardList().get(i).getRgtDtm().substring(0,4);
			String month=  list.getBoardList().get(i).getRgtDtm().substring(4,6);
			String date= list.getBoardList().get(i).getRgtDtm().substring(6,8);
			list.getBoardList().get(i).setRgtDtm(year+"년" +month + "월" + date +"일");
		}
		System.out.println(list.toString());
		//boardList =cmservice.chboardGetList(boardVO);
		model.addAttribute("boardList", list);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPage", currentPage);
		return "chboard/chboardList";
	}
	
	@RequestMapping(value="chboard/chboardRegister", method = RequestMethod.GET)
	public String chboardRegister(Model model) throws Exception {
		
		return "chboard/chboardRegister";
	}
	
	
	
	@RequestMapping(value="chboard/chboardInsert", method = RequestMethod.GET)
	public String chboardInsert(Model model, @ModelAttribute("BoardVO") BoardVO boardVO,HttpServletRequest request) throws Exception {
		
		int currentPage = 1;
		try {
			currentPage =Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {	}
		
//		System.out.println(boardVO.toString());
		cmservice.chboardInsert(boardVO);
		
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("currentPage", currentPage);
		return "redirect:chboardList.do";
	}

	@ResponseBody
	@RequestMapping(value = "chboard/chboardDelete",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
	public int chboardDelete( @RequestParam(value="chbox[]") List<String> chArr, HttpServletRequest request) throws Exception {
		int idx= 0;
		int result=0;
		BoardVO vo = new BoardVO();
	
		for(String delete : chArr) {
		idx = Integer.parseInt(delete);
		vo.setIdx(idx);
		
		 cmservice.chboardDelete(vo);
		 result= 1;
	}
		/*boardVO.setIdx(Integer.parseInt(check));*/
		//cmservice.chboardDelete(boardVO);
	
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="chboard/chboardUpdateReadCnt",   method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public int chboardUpdateReadCnt(@RequestParam(value="idx") int idx, HttpServletRequest request) throws Exception {
	int result =cmservice.chboardUpdateReadCnt(idx);
		return result;
	}
	
	@RequestMapping(value="chboard/Detail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String chboardDetail(Model model, @ModelAttribute("BoardVO") BoardVO boardVO,  HttpServletRequest request) throws Exception {
		logger.info("chboardDetail");
		System.out.println(boardVO.toString());
		BoardVO vo =new BoardVO();
		vo =cmservice.chboardDetail(boardVO);
		model.addAttribute("vo", vo);
		return "chboard/chboardDetail";
	}

	
}