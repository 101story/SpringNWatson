package com.jin.dev.controller;

import java.io.Serializable;

public class Text2SpeechVO implements Serializable{
	private int no;
	private String statement;
	private String lang;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	@Override
	public String toString() {
		return "Text2SpeechVO [no=" + no + ", statement=" + statement + ", lang=" + lang + "]";
	}

	
}
