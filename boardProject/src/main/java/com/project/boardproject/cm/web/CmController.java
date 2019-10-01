
package com.project.boardproject.cm.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.boardproject.cm.service.BoardVO;
import com.project.boardproject.cm.service.CmService;
import com.project.boardproject.cm.web.Pagination;
import com.project.boardproject.mm.service.MemberVO;

/*
 * 파일명 : customController.java
 * 용도    : 사용자 관련 컨트롤러
 * 작성자 : mintchoco91
 * 변경일 : 2019/09/14
 */

@Controller
public class CmController {

	@Autowired
	private CmService cmservice;

	private static final Logger logger = LoggerFactory.getLogger(CmController.class);

	/*****공통 게시판 시작************************************************************/
	
	// 내용 : index조회
	@RequestMapping("index")
	public String index(Model model, MemberVO memberVO) {
		return "index";
	}

	// 내용 : 게시판 리스트 페이지 로드
	@RequestMapping(value = "boardListPage")
	public String boardListPage(Model model) throws Exception {
		return "board/boardListPage";
	}

	// 내용 : 게시판 조회
	@RequestMapping(value = "boardInq")
	public String boardInq(Model model, BoardVO boardVO, @RequestParam(defaultValue = "1") int curPage) {
		// 리스트로 구현
		List<BoardVO> boardVOArr = new ArrayList<BoardVO>();
		// 전체리스트 개수
		int listCnt = cmservice.boardInqCnt(boardVO);
		
		System.out.println("########key : "+boardVO.getSrchKeyword());
		
		Pagination pagination = new Pagination(listCnt, curPage);

		boardVO.setStartIndex(pagination.getStartIndex());
		boardVO.setPageSize(pagination.getPageSize());

		boardVOArr = cmservice.boardInq(boardVO);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date time = new Date();

		String dateNow = dateFormat.format(time);

		String fullRgtDtm = "";
		String todayRgtDtm = "";
		String convRgtDtm = "";

		for (int i = 0; i < boardVOArr.size(); i++) {
			fullRgtDtm = boardVOArr.get(i).getRgtDtm();
			todayRgtDtm = fullRgtDtm.substring(0, 8);

			if (todayRgtDtm.equals(dateNow)) {
				convRgtDtm = fullRgtDtm.substring(8, 10) + ":" + fullRgtDtm.substring(10, 12);
			} else {
				convRgtDtm = fullRgtDtm.substring(2, 4) + "." + fullRgtDtm.substring(4, 6) + "."
						+ fullRgtDtm.substring(6, 8);
			}

			boardVOArr.get(i).setRgtDtm(convRgtDtm);
		}

		model.addAttribute("boardVOArr", boardVOArr);
		model.addAttribute("pagination", pagination);

		return "board/boardList";
	}

	// 내용 : 게시판 글쓰기페이지 로드
	@RequestMapping(value = "boardWritePage")
	public String boardWritePage(@ModelAttribute("boardVO") BoardVO boardVO, Model model) throws Exception {
		return "board/boardRegister";
	}

	// 내용 : 게시판 글쓰기 등록
	@RequestMapping(value = "boardWrite")
	public String boardWrite(Model model, BoardVO boardVO) throws Exception {
		cmservice.boardWrite(boardVO);
		return "redirect:boardInq.do";
	}

	// Post AJAX
	@RequestMapping(value = "boardDelete", method = RequestMethod.POST)
	public @ResponseBody String boardDelete(HttpServletRequest request, String[] idxArray, Model model)
			throws Exception {

		String result = "error";
		
		result = cmservice.boardDelete(idxArray);
		return result;
	}

	// 내용 : 게시판 상세조회
	@RequestMapping(value = "boardDetail")
	public String boardDetail(Model model, BoardVO boardVO) throws Exception {

		BoardVO resultBoardVO = new BoardVO();
		resultBoardVO = cmservice.boardDetail(boardVO);
		
		String fullRgtDtm = resultBoardVO.getRgtDtm();
		String convRgtDtm = fullRgtDtm.substring(0, 4) + "." + fullRgtDtm.substring(4, 6) + "."
				+ fullRgtDtm.substring(6, 8) + ". " + fullRgtDtm.substring(8, 10) + ":" + fullRgtDtm.substring(10, 12);

		resultBoardVO.setRgtDtm(convRgtDtm);

		model.addAttribute("boardVO", resultBoardVO);

		return "board/boardDetail";
	}

	// 내용 : 게시판 수정페이지 로드
	@RequestMapping(value = "boardModifyPage")
	public String boardModifyPage(Model model, BoardVO boardVO) throws Exception {
		model.addAttribute("boardVO", boardVO);
		return "board/boardRegister";
	}

	// 내용 : 게시판 내용 수정
	@RequestMapping(value = "boardModify")
	public String boardModify(Model model, BoardVO boardVO) throws Exception {
		cmservice.boardModify(boardVO);
		return "redirect:boardInq.do";
	}

	/*****공통 게시판 끝************************************************************/
	
	@RequestMapping(value="chboardList.do", method = RequestMethod.GET)
	public String chboardList(@ModelAttribute("BoardVO") BoardVO boardVO, Model model,@RequestParam(defaultValue="1") int curPage) throws Exception {

		int listCnt = cmservice.chboardgetBoardCnt(boardVO);
		Pagination pagination = new Pagination(listCnt, curPage);

		boardVO.setStartIndex(pagination.getStartIndex());
		boardVO.setPageSize(pagination.getPageSize());

		List<BoardVO> boardList = new ArrayList<>();

		boardList = cmservice.chboardGetList(boardVO);
		// List<BoardVO> boardList = new ArrayList<>();

		for (int i = 0; i < boardList.size(); i++) {
			String year = boardList.get(i).getRgtDtm().substring(0, 4);
			String month = boardList.get(i).getRgtDtm().substring(4, 6);
			String date = boardList.get(i).getRgtDtm().substring(6, 8);
			boardList.get(i).setRgtDtm(year + "년" + month + "월" + date + "일");
		}
		System.out.println(boardList.toString());
		// boardList =cmservice.chboardGetList(boardVO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pagination", pagination);
		return "chboard/chboardList";
	}
	
	@RequestMapping(value="chboardRegister", method = RequestMethod.GET)
	public String chboardRegister(Model model) throws Exception {
		String flag ="등록";
		model.addAttribute("flag",flag);
		return "chboard/chboardRegister";
	}
	
	@RequestMapping(value="chboardUpdList", method= RequestMethod.POST)
	public String chboardUpdList(@ModelAttribute("BoardVO") BoardVO boardVO, Model model) throws Exception {
		String flag ="수정";
		model.addAttribute("flag",flag);
		model.addAttribute("BoardVO", boardVO);
		return "chboard/chboardRegister";
	}
	
	@RequestMapping(value="chboardInsert", method = RequestMethod.POST)
	public String chboardInsert(Model model, @ModelAttribute("BoardVO") BoardVO boardVO,HttpServletRequest request) throws Exception {
		
//		System.out.println(boardVO.toString());
		cmservice.chboardInsert(boardVO);
		model.addAttribute("BoardVO", boardVO);
		return "redirect:chboardList.do";
	}

	@ResponseBody
	@RequestMapping(value = "chboardDelete",  method = RequestMethod.POST, produces = "application/json;charset=UTF-8" )
	public int chboardDelete( @RequestParam(value="chbox[]") List<String> chArr, HttpServletRequest request) throws Exception {
		int idx= 0;
		int result=0;
		BoardVO vo = new BoardVO();
		for(String delete : chArr) {
		idx = Integer.parseInt(delete);
		vo.setIdx(idx);
		
		 cmservice.chboardDelete(vo);
		 result= 1;
		 System.out.println(vo.getIdx());
	}
		return result;
	}

	@ResponseBody
	@RequestMapping(value="chboardUpdateReadCnt",   method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public int chboardUpdateReadCnt(@RequestParam(value="idx") int idx, HttpServletRequest request) throws Exception {
	int result =cmservice.chboardUpdateReadCnt(idx);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="chboardSchBoard" , method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String chboardSchBoard(@RequestParam(value="sel") String sel,@RequestParam(value="test") String text, Model model) throws Exception {
		System.out.println("일단 들어온당");
		System.out.println(sel + "," +text);
		
		Map<String, String> schMap = new HashMap<>();
		schMap.put("sel", sel);
		schMap.put("text", text);
		BoardVO boardVO=cmservice.chboardSchBoard(schMap);
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("schsel", sel);
		model.addAttribute("schtext", text);
		return "result";
	}
	
	@RequestMapping(value="chboardUpdBoard")
	public String chboardUpdBoard(@ModelAttribute(value="BoardVO") BoardVO boardVO, Model model) throws Exception {
		cmservice.chboardUpdBoard(boardVO);
		model.addAttribute("BoardVO", boardVO);
		model.addAttribute("idx", boardVO.getIdx());
		return "redirect:Detail.do";
	}
	
	@RequestMapping(value="Detail")
	public String chboardDetail(Model model, BoardVO boardVO,@RequestParam(value="idx") int idx,  HttpServletRequest request) throws Exception {
		logger.info("chboardDetail");
		BoardVO vo =new BoardVO();
		boardVO.setIdx(idx);
		vo =cmservice.chboardDetail(boardVO);
		model.addAttribute("vo", vo);
		return "chboard/chboardDetail";
	}
}