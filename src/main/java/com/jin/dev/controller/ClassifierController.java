package com.jin.dev.controller;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.watson.developer_cloud.natural_language_classifier.v1.NaturalLanguageClassifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classification;

@RestController
public class ClassifierController {
	private static Logger logger = 
			LoggerFactory.getLogger(ClassifierController.class);
	
	@RequestMapping("weather")
	public ModelAndView weather() {
		return new ModelAndView("weather");
	}

	@RequestMapping(value = "/classifier", method = RequestMethod.POST)
	// public String classify(@RequestParam("content") String content) {
	public String classify(@RequestBody String content) throws UnsupportedEncodingException {
		logger.info("classifier start");
		logger.info(content);

		NaturalLanguageClassifier service = new NaturalLanguageClassifier();
		service.setUsernameAndPassword("b6bfd215-81bc-46e9-8137-18d22553c1d4", "APVYuQL7Diwz");

		Classification classification = service.classify("ebd44cx231-nlc-24297", content).execute();
		logger.info("class "+classification);

		logger.info("classifier end");
		return classification.toString();
	}
}
