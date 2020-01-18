package com.project.boardproject.cm.web;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.boardproject.cm.service.BoardVO;
import com.project.boardproject.cm.service.CmService;
import com.project.boardproject.cm.service.ReplyVO;
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

	/***** 공통 게시판 시작 ************************************************************/
	//index조회
	@RequestMapping("/")
	public String indexRoot(Model model, MemberVO memberVO) {
		return "index";
	}
	
	//index조회
	@RequestMapping("index")
	public String index(Model model, MemberVO memberVO) {
		return "index";
	}

	//게시판 리스트 페이지 로드
	@RequestMapping(value = "boardListPage")
	public String boardListPage(Model model) throws Exception {
		return "board/boardListPage";
	}
	
	//게시판 조회
	@RequestMapping(value = "boardList")
	public String boardList(@ModelAttribute("BoardVO") BoardVO boardVO, Model model,
			@RequestParam(defaultValue = "1") int curPage) throws Exception {
	
		int listCnt = cmservice.boardgetBoardCnt(boardVO);
		Pagination pagination = new Pagination(listCnt, curPage);

		boardVO.setStartIndex(pagination.getStartIndex());
		boardVO.setPageSize(pagination.getPageSize());

		List<BoardVO> boardList = new ArrayList<>();

		boardList = cmservice.boardGetList(boardVO);

		for (int i = 0; i < boardList.size(); i++) {
			String year = boardList.get(i).getRgtDtm().substring(0, 4);
			String month = boardList.get(i).getRgtDtm().substring(4, 6);
			String date = boardList.get(i).getRgtDtm().substring(6, 8);
			boardList.get(i).setRgtDtm(year + "년" + month + "월" + date + "일");
		}
		model.addAttribute("boardList", boardList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("srchKeyword", boardVO.getSrchKeyword());
		model.addAttribute("srchtrg", boardVO.getSrchtrg());
		return "board/boardList";
	}

	//게시글 등록페이지
	@RequestMapping(value = "boardRegister", method = RequestMethod.GET)
	public String boardRegister(Model model) throws Exception {
		String flag = "등록";
		model.addAttribute("flag", flag);
		return "board/boardRegister";
	}

	//게시글 수정페이지
	@RequestMapping(value = "boardUpdList", method = RequestMethod.POST)
	public String boardUpdList(@ModelAttribute("BoardVO") BoardVO boardVO, Model model) throws Exception {
		String flag = "수정";
		model.addAttribute("flag", flag);
		model.addAttribute("BoardVO", boardVO);
		return "board/boardRegister";
	}

	//게시글 등록
	@RequestMapping(value = "boardInsert", method = RequestMethod.POST)
	public String boardInsert(Model model, @ModelAttribute("BoardVO") BoardVO boardVO, HttpServletRequest request)
			throws Exception {
		cmservice.boardInsert(boardVO);
		model.addAttribute("BoardVO", boardVO);
		return "redirect:boardList.do";
	}

	//게시글 삭제
	@ResponseBody
	@RequestMapping(value = "boardDelete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public int boardDelete(@RequestParam(value = "chbox[]") List<String> chArr, HttpServletRequest request)
			throws Exception {
		int idx = 0;
		int result = 0;
		BoardVO vo = new BoardVO();
		for (String delete : chArr) {
			idx = Integer.parseInt(delete);
			vo.setIdx(idx);

			cmservice.boardDelete(vo);
			result = 1;
		}
		return result;
	}

	//게시글 조회수증가
	@ResponseBody
	@RequestMapping(value = "boardUpdateReadCnt", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public int boardUpdateReadCnt(@RequestParam(value = "idx") int idx, HttpServletRequest request) throws Exception {
		int result = cmservice.boardUpdateReadCnt(idx);
		return result;
	}

	//게시글 수정
	@RequestMapping(value = "boardUpdBoard")
	public String boardUpdBoard(@ModelAttribute(value = "BoardVO") BoardVO boardVO, Model model) throws Exception {
		cmservice.boardUpdBoard(boardVO);
		model.addAttribute("BoardVO", boardVO);
		model.addAttribute("idx", boardVO.getIdx());
		return "redirect:Detail.do?flag='T'";
	}

	//게시글 상세보기+댓글 추가
	@RequestMapping(value = "Detail")
	public String boardDetail(Model model, BoardVO boardVO, @RequestParam(value = "idx") int idx,
			@RequestParam(defaultValue = "F") String flag, HttpServletRequest request) throws Exception {
		
		
		String url = "";
		BoardVO vo = new BoardVO();
		boardVO.setIdx(idx);
		System.out.println(boardVO);
		vo = cmservice.boardDetail(boardVO);
		List<ReplyVO> replyList = cmservice.replyGetList(idx);
		
		if ("Y".equals(vo.getScrYn()) && "F".equals(flag)) {
			url = "board/boardScrPwChk";
		} else {
			url = "board/boardDetail";
		}
		model.addAttribute("replyList", replyList);
		model.addAttribute("vo", vo);
		return url;
	}

	//비밀글 체크?
	@RequestMapping(value = "boardScrPwChk")
	public String boardScrPwChk(BoardVO boardVO, Model model) throws Exception {
		model.addAttribute("vo", boardVO);
		return "board/boardScrPwChk";
	}

	//비밀글 확인
	@ResponseBody
	@RequestMapping(value = "boardScrPwChkConfirm")
	public int boardScrPwChkConfirm(@RequestParam(value = "idx") int idx, @RequestParam(value = "scrPw") String scrPw,
			Model model) throws Exception {
		BoardVO vo = new BoardVO();
		vo.setIdx(idx);
		vo.setScrPw(scrPw);
		int result = cmservice.boardScrPwChkConfirm(vo);
		return result;
	}
	
	
	
	/****************************댓글***********************************/
	
	
	//댓글 입력
	@RequestMapping(value = "replyInsert")
	public String replyInsert(RedirectAttributes redirect, ReplyVO vo, HttpSession session, Model model) {
//		String rgtId = (String) session.getAttribute("userid");
//		vo.setRgtId(rgtId); //현재 로그인 되어 있는 사람 아이디가 댓글 작성자
		
		cmservice.replyInsert(vo);
		redirect.addAttribute("idx", vo.getBno());//리다이렉트 시 게시글번호 리턴
		
		return "redirect:Detail.do";
	}
	
	//댓글 수정
	@RequestMapping(value = "replyUpdate")
	public String replyUpdate(RedirectAttributes redirect, ReplyVO vo) {
//		cmservice.replyUpdate(vo);
		
//		redirect.addAttribute("idx", vo.getBno());
		return "redirect:Detail.do";
	}
	
	
}