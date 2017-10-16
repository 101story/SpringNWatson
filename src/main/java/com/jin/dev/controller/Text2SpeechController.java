package com.jin.dev.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

//webserver 에서 자동으로 bean 생성시킴 (action controller)
@Controller  
public class Text2SpeechController {
	private static Logger logger = 
			LoggerFactory.getLogger(Text2SpeechController.class);
	
	//객체 주입하기 
	@Autowired
	private Text2SpeechService service;
	
	@RequestMapping("hello")
	public ModelAndView hello() {
		return new ModelAndView("hello", "msg", "Hello MVC");
	}
	
	
	@RequestMapping(value="displayJSON2",
			headers="Accept=application/json;charset=UTF-8",
			produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	public List<Text2SpeechVO>  display_json() throws Exception {
		List<Text2SpeechVO> list = service.getText2SpeechList();
		return list ;
		
	}
	
	@RequestMapping("display")
	public ModelAndView display_voice() throws Exception {
		TextToSpeech service2 = new TextToSpeech();
		service2.setUsernameAndPassword("c307664a-97bb-4e0f-b8af-784116a2b676", "6Sokqkz82ep6");
		
		List<Text2SpeechVO> list = service.getText2SpeechList();
		List<Voice> voices = service2.getVoices().execute();
		ModelAndView mav = new ModelAndView("hello", "voices", voices);
		mav.addObject("list", list);		
		return mav ;
		
	}

//	@PostMapping("text2speech")
//	@GetMapping("text2speech")//3 이전 버전
	@RequestMapping(value="text2speech", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView text2speech(HttpServletRequest request, HttpServletResponse response, 
			HttpSession session) throws Exception {
		List<Voice> voices = (List<Voice>) service.getVoiceList();
		
		return new ModelAndView("text2speech", "voices", voices);
	}
	
	@GetMapping("speaker")
	public void speaker(Text2SpeechVO vo, HttpServletResponse response) throws Exception {
		logger.info("vo : "+vo);
		
		response.setContentType("application/octet-stream");
		response.setHeader(
				"Content-Disposition", "attachment;filename=" +
				URLEncoder.encode("voice.ogg","UTF-8"));
		
		InputStream is = service.getSpeech(vo.getStatement(), vo.getLang());
		OutputStream os = response.getOutputStream();
		
/*		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		os.close();
		is.close();*/
		
		// AOP에서 수행 
		//service.insertText2Speech(vo);
		
		FileCopyUtils.copy(is, os);	
	}
	
	@RequestMapping("delete/{no}")
	public ModelAndView delete(@PathVariable int no) {
		logger.info("no : "+no);
		ModelAndView mav = new ModelAndView("result");
		try {
			service.deleteText2Speech(no);
			mav.addObject("msg", no+"번 레코드 삭제 성공");
			mav.addObject("url", "../display");
		} catch (Exception e) {
			mav.addObject("msg", no+"번 레코드 삭제 실패");
		}
		return mav;
	}
	
}
