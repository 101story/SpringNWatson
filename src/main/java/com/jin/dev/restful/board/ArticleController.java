package com.jin.dev.restful.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board/{bno}/{pg}")
public class ArticleController {
	
	@RequestMapping("/")
	public String list(@PathVariable int bno,@PathVariable int pg, Model model) {
		model.addAttribute("msg", bno+"번 게시판의 "+pg+"페이지 입니다.");
		return "board";
	}
	
	@RequestMapping("/insert")
	public String insert(@PathVariable int bno,@PathVariable int pg, Model model) {
		model.addAttribute("msg", bno+"번 게시판의 입력화면 입니다.");
		return "insert";
	}

}
