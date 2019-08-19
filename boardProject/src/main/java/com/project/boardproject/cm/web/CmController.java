package com.project.boardproject.cm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Literal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping(value="chboard/chboardList.do")
	public String chboardList(@ModelAttribute("BoardVO") BoardVO boardVO, Model model,HttpServletRequest request) throws Exception {
		int currentPage = 1;
		try {
			currentPage =Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {	}
	      int totalCount = cmservice.chboardgetBoardCnt(boardVO);
	      int pageSize = 10;
	      BoardList list =new BoardList();
	      list.initBoardList(pageSize, totalCount, currentPage);
	      Map<String, Integer> hmap = new HashMap<String, Integer>();
	      hmap.put("startNo",0);
	      hmap.put("endNo", 10);
	      list.setBoardList(cmservice.chboardGetList(hmap));
	      for(int i=0; i<list.getBoardList().size(); i++) {
	    	String year=  list.getBoardList().get(i).getRgtDtm().substring(0,4);
	    	String month=  list.getBoardList().get(i).getRgtDtm().substring(4,6);
	    	 String date= list.getBoardList().get(i).getRgtDtm().substring(6,8);
	    	 list.getBoardList().get(i).setRgtDtm(year+"년" +month + "월" + date +"일");
	      }
		//List<BoardVO> boardList = new ArrayList<>();
		
		
		System.out.println(list.toString());
		//boardList =cmservice.chboardGetList(boardVO);
		model.addAttribute("boardList", list);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPage", currentPage);
		return "chboard/chboardList";
	}
	
	@RequestMapping(value="chboard/chboardRegister")
	public String chboardRegister(Model model) throws Exception {
		
		return "chboard/chboardRegister";
	}
	
	
	
	@RequestMapping(value="chboard/chboardInsert")
	public String chboardInsert(Model model, @ModelAttribute("BoardVO") BoardVO boardVO,HttpServletRequest request) throws Exception {
		
		int currentPage = 1;
		try {
			currentPage =Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {	}
		
//		System.out.println(boardVO.toString());
		cmservice.chboardInsert(boardVO);
		
		model.addAttribute("boardVO", boardVO);
		return "redirect:chboardList.do";
	}

	
}