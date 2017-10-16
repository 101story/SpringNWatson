package com.jin.dev.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// spring version 4 이상 
// json, xml 형태로 출력 (주로 json만 사용) view 를 거치면 안됨 
@RestController
public class Text2SpeechRestController {

	private static Logger logger = 
			LoggerFactory.getLogger(Text2SpeechRestController.class);
	
	//객체 주입하기 
	@Autowired
	private Text2SpeechService service;
	
	/**
	 * list 뽑아와서 json 으로 뿌리기 
	 * @return List<Text2SpeechVO> 
	 * @throws Exception
	 */
	@RequestMapping("displayJSON")
	public List<Text2SpeechVO>  display_voice() throws Exception {
		List<Text2SpeechVO> list = service.getText2SpeechList();
		return list ;
		
	}
}
