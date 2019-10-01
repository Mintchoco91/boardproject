package com.project.boardproject.mm.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	@RequestMapping(value="member/callback", method =RequestMethod.GET)
	public String Callback(HttpSession session) {
		return "member/callback";
	}
	
	@RequestMapping(value="loginSession.do")
	public String loginSession(HttpSession session, @RequestParam(value="email") String email ,@RequestParam(value="name") String name) throws Exception {
		String tmpEmail[] = email.split("@");
		String email1 = tmpEmail[0];
		String email2 =tmpEmail[1];
		MemberVO vo = new MemberVO();
		vo.setUserId(email1);
		vo.setEmail1(email1);
		vo.setEmail2(email2);
		vo.setName(name);
		memberService.naverRgtUsr(vo);
		
		session.setAttribute("userid", email1);
		return "index";
	}

	@RequestMapping(value="naverLogin")
	public String naverLogin() {
		return "member/naverLogin";
	}
	
	@RequestMapping(value="member/naverCallback", method=RequestMethod.GET)
	public String naverCallback(HttpSession session) {
		return "member/naverCallback";
	}
}
