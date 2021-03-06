package com.jin.dev.controller;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

//@Component //text2SpeechServiceImpl 변수로 객체 자동 생성함 DI(관계의존성주입)
@Component("service") //service 변수로 생성됨 
public class Text2SpeechServiceImpl extends TextToSpeech implements Text2SpeechService{

	@Autowired
	//@Qualifier("ibatis")
	@Qualifier("text2SpeechDAOMybatis")
	private Text2SpeechServiceDAO text2speechDAO;
	
	public Text2SpeechServiceImpl() {
		setUsernameAndPassword("c307664a-97bb-4e0f-b8af-784116a2b676", "6Sokqkz82ep6");
	}

	@Override
	public List<Voice> getVoiceList() throws Exception {
		List<Voice> voices = getVoices().execute();
		return voices;
	}

	@Override
	public InputStream getSpeech(String statement, String voice) throws Exception {
		return synthesize(statement, new Voice(voice,null,null), AudioFormat.OGG).execute();
	}

	@Override
	public void insertText2Speech(Text2SpeechVO vo) throws Exception {
		text2speechDAO.insertText2Speech(vo);
	}

	@Override
	public List<Text2SpeechVO> getText2SpeechList() throws Exception {
		// TODO Auto-generated method stub
		return text2speechDAO.getText2SpeechList();
	}

	@Override
	public void deleteText2Speech(int no) throws Exception {
		if(text2speechDAO.deleteText2Speech(no)==0){
			throw new RuntimeException("없는 번호 입니다.");
		}
	}
}
