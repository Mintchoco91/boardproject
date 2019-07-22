package com.project.boardproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class customController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}

}