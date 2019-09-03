package com.project.boardproject.mm.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.boardproject.mm.service.MemberService;
import com.project.boardproject.mm.service.MemberVO;

@Controller
public class LoginController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("loginCheck")
	public String loginCheck(MemberVO memberVO, HttpSession session, Model model) {
		int pwCheck = memberService.loginCheck(memberVO);

		if (pwCheck == 0) {
			session.setAttribute("userid", memberVO.getUserId());
			System.out.println("로그인 성공");
			return "index";
		} else {
			model.addAttribute("resultMessage", "아이디/비밀번호를 확인해주세요");
			if (pwCheck == 1) {
				System.out.println("비밀번호 불일치");
				return "member/login";
			} else {
				System.out.println("존재하지 않는 아이디");
				return "member/login";
			}
		}
	}

	@RequestMapping("login")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
}
