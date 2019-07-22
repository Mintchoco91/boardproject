package com.project.boardproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class adminController {

	@RequestMapping("adminIndex")
	public String adminIndex() {
		return "admin/adminIndex";
	}
	
}
