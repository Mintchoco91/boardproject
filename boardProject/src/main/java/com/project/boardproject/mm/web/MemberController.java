package com.project.boardproject.mm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.boardproject.mm.service.MemberService;
import com.project.boardproject.mm.service.MemberVO;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("pjsMemberRegister")
	public String memberRegister(@ModelAttribute MemberVO memberVO, Model model) throws Exception {
		return "member/pjsMemberRegister";
	}
	
	@RequestMapping("register")
	public String pjsboardRegister(MemberVO memberVO, Model model) {
		int result = memberService.memberRegister(memberVO);
		if (result == 0) {
			System.out.println("홰원가입 성공");
			return "index";
		} else {
			System.out.println("회원가입 실패");
			model.addAttribute("resultMessage","이미 있는 아이디입니다.");
			return "member/pjsMemberRegister";
		}
	}
	
	@RequestMapping("pjsMember")
	public String memberInfo(MemberVO memberVO, Model model, HttpSession session) {
		String userId=(String)session.getAttribute("userid");
		memberVO=memberService.getMember(userId);
		model.addAttribute("memberVO", memberVO);
		return "member/pjsMember";
	}
	
	@RequestMapping("memberModify")
	public String memberModify(MemberVO memberVO, Model model, HttpSession session) {
		memberVO.setUserId((String)session.getAttribute("userid"));
		int result = memberService.memberModify(memberVO);
		if(result==0) {
			System.out.println("회원수정 성공");
			model.addAttribute("resultMessage", "정보가 수정되었습니다.");
			return "member/pjsMember";
		}
		else {
			model.addAttribute("resultMessage", "비밀번호가 일치하지 않습니다.");
			System.out.println("회원수정 실패");
			return "member/pjsMember";
		}
	}
	
	@RequestMapping("memberDelete")
	public String memberDelete(Model model, HttpSession session) {
		String userId=(String)session.getAttribute("userid");
		session.invalidate();
		int result = memberService.memberDelete(userId);
		return "index";
	}
}
