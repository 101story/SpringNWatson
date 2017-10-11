package com.jin.dev.controller;

import java.io.InputStream;
import java.util.List;

import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

public interface Text2SpeechService {
	public List<Voice> getVoiceList() throws Exception;
	public InputStream getSpeech(String statement, String voice) throws Exception;
}
