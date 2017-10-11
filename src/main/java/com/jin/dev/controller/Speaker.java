package com.jin.dev.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

//@WebServlet("/Speaker")
public class Speaker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = 
			LoggerFactory.getLogger(Speaker.class);
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String statement = request.getParameter("statement");
		String voice = request.getParameter("voice");
		
		logger.info("statement : " + statement);
		logger.info("voice : " + voice);
		response.setContentType("application/octet-stream");
		response.setHeader(
				"Content-Disposition", "attachment;filename=" +
				URLEncoder.encode("voice.ogg","UTF-8"));

		TextToSpeech service = new TextToSpeech("c307664a-97bb-4e0f-b8af-784116a2b676", "6Sokqkz82ep6");
		InputStream is = service.synthesize(statement, new Voice(voice,null,null), AudioFormat.OGG).execute();
		OutputStream os = response.getOutputStream();
		
		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		os.close();
		is.close();
	}
}
