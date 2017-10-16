package com.jin.dev.controller;

import java.io.InputStream;
import java.util.List;

import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

public interface Text2SpeechService {
	public List<Voice> getVoiceList() throws Exception;
	public InputStream getSpeech(String statement, String voice) throws Exception;
	public void insertText2Speech(Text2SpeechVO vo) throws Exception;
	public List<Text2SpeechVO> getText2SpeechList() throws Exception;
	public void deleteText2Speech(int no) throws Exception;
	
}
