package com.project.boardproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("move")
	public String move() {
		return "index";
	}
}