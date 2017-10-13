package com.jin.dev.controller;

import java.util.List;

public interface Text2SpeechServiceDAO {

	void insertText2Speech(Text2SpeechVO vo) throws Exception;

	List<Text2SpeechVO> getText2SpeechList() throws Exception;

	int deleteText2Speech(int no) throws Exception;

}
