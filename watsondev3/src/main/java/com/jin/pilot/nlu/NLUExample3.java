package com.jin.pilot.nlu;

import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;

class NLUExample3 {
	private static String username = "bfd4c860-5d5a-4cf3-a7a6-e57f9b549401";
	private static String password = "AizNeJLkOMIm";
	private static String text = "Hundreds of thousands of Rohingya were already in Bangladesh before the "
			+ "recent exodus that began in late August. "
			+ "On August 25, Rohingya militants killed 12 security officers in coordinated attacks "
			+ "on border posts, according to Myanmar’s state media. The military responded with intensified "
			+ "clearance operations against terrorists, driving thousands of people from their homes. "
			+ "Satellite photos released by Human Rights Watch showed entire villages torched to the ground.";

	public static void main(String[] args) {
		NaturalLanguageUnderstanding service = 
				new NaturalLanguageUnderstanding(NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27);
		service.setUsernameAndPassword(username, password);
		
		List<String> targets = new ArrayList<String>();
		targets.add("Rohingya");
		targets.add("terrorists");
		EmotionOptions emotions = new EmotionOptions.Builder().targets(targets).build();
		
		Features features = new Features.Builder().emotion(emotions).build();
		AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(text).features(features).build();
		AnalysisResults response = service.analyze(parameters).execute();
		System.out.println(response);
	}

}
