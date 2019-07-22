package com.project.boardproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController2 {

	@RequestMapping("move2")
	public String move2() {
		return "index";
	}

}