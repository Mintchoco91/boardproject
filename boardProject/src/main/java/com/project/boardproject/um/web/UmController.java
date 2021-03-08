package com.project.boardproject.um.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.boardproject.um.service.UmUsrService;
import com.project.boardproject.um.service.UsrAcntVO;

@Controller
public class UmController {

	@Autowired
	private UmUsrService umUsrService;

	@RequestMapping("pjsMemberRegister")
	public String memberRegister(@ModelAttribute UsrAcntVO UsrAcntVO, Model model) throws Exception {
		return "member/pjsMemberRegister";
	}
	
	@RequestMapping("/um/usrAcntRgt.do")
	public String usrAcntRgt(@ModelAttribute UsrAcntVO usrAcntVO, Model model, HttpServletRequest request) throws Exception {
		
		usrAcntVO = umUsrService.usrAcntRgt(usrAcntVO);
		String message= "Success";
		System.out.println(usrAcntVO.toString());
		model.addAttribute("message", message);
		
		return "member/pjsMember";
	}
	
	@RequestMapping("register")
	public String pjsboardRegister(UsrAcntVO usrAcntVO, Model model) {
	/*	int result = memberService.memberRegister(UsrAcntVO);
		if (result == 0) {
			System.out.println("홰원가입 성공");
			return "index";
		} else {
			System.out.println("회원가입 실패");
			model.addAttribute("resultMessage","이미 있는 아이디입니다.");
			return "member/pjsMemberRegister";
		}*/
		return "";
	}
	
	@RequestMapping("pjsMember")
	public String memberInfo(UsrAcntVO UsrAcntVO, Model model, HttpSession session) {
	/*	String userId=(String)session.getAttribute("userid");
		UsrAcntVO=memberService.getMember(userId);
		model.addAttribute("UsrAcntVO", UsrAcntVO);*/
		return "member/pjsMember";
	}
	
	@RequestMapping("memberModify")
	public String memberModify(UsrAcntVO UsrAcntVO, Model model, HttpSession session) {
//		UsrAcntVO.setUserId((String)session.getAttribute("userid"));
	/*	int result = memberService.memberModify(UsrAcntVO);
		if(result==0) {
			System.out.println("회원수정 성공");
			model.addAttribute("resultMessage", "정보가 수정되었습니다.");
			return "member/pjsMember";
		}
		else {
			model.addAttribute("resultMessage", "비밀번호가 일치하지 않습니다.");
			System.out.println("회원수정 실패");
			return "member/pjsMember";
		}*/
		return "";
	}
	
	@RequestMapping("memberDelete")
	public String memberDelete(Model model, HttpSession session) {
		String userId=(String)session.getAttribute("userid");
		session.invalidate();
		int result = umUsrService.memberDelete(userId);
		return "index";
	}
}
