package com.jin.dev.restful.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("board/{bno}/{pg}/{ano}")
public class ArticledetailController {
	
	@RequestMapping("/")
	public String list(@PathVariable int bno,@PathVariable int ano, Model model) {
		model.addAttribute("msg", bno+"번 게시판의 "+ano+"게시물 상세보기 입니다.");
		return "detail";
	}
	
	@RequestMapping("/update")
	public String update(@PathVariable int bno,@PathVariable int ano, Model model) {
		model.addAttribute("msg", bno+"번 게시판의 "+ano+"글의 수정화면 입니다.");
		return "insert";
	}
	
	@RequestMapping("/delete")
	public String delete(@PathVariable int bno,@PathVariable int ano, Model model) {
		model.addAttribute("msg", bno+"번 게시판의"+ano+"글의 삭제화면 입니다.");
		return "delete";
	}
}
