package com.project.boardproject.mm.web;

import javax.servlet.http.HttpServletRequest;

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
	public String memberRegister() {
		return "member/pjsMemberRegister";
	}
	
	@RequestMapping("register")
	public String pjsboardRegister(MemberVO memberVO) {
		int result = memberService.memberRegister(memberVO);
		if (result == 0) {
			System.out.println("홰원가입 성공");
			return "index";
		} else
			System.out.println("회원가입 실패");
		return "member/pjsMemberRegister";
	}
	
	@RequestMapping("pjsMember")
	public String memberInfo(MemberVO memberVO, Model model, HttpServletRequest request) {
		String userId=request.getParameter("userId");
		model.addAttribute("userId", userId);
		return "member/pjsMember";
	}
	
	@RequestMapping("memberModify")
	public String memberModify(MemberVO memberVO) {
		int result = memberService.memberModify(memberVO);
		if(result==0) {
			System.out.println("회원수정 성공");
		}
		else {
			System.out.println("회원수정 실패");
		}
		return "index";
	}
}
