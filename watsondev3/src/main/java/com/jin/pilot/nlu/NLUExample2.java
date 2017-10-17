package com.jin.pilot.nlu;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;

class NLUExample2 {
	private static String username = "bfd4c860-5d5a-4cf3-a7a6-e57f9b549401";
	private static String password = "AizNeJLkOMIm";
//	private static String URL = "http://edition.cnn.com/2017/10/16/asia/hong-kong-north-korea/index.html";
	private static String URL = "http://wizard2.sbs.co.kr/vobos/wizard2/resource/template/contents/07_review_list.jsp?vProgId=1000126&vVodId=V0000311936&vMenuId=1002036&rpage=4&cpage=1";
	
	public static void main(String[] args) {
		NaturalLanguageUnderstanding service = 
				new NaturalLanguageUnderstanding(NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27);
		service.setUsernameAndPassword(username, password);
		
		ConceptsOptions concepts = new ConceptsOptions.Builder().limit(20).build();
		Features features = new Features.Builder().concepts(concepts).build();
		AnalyzeOptions parameters = new AnalyzeOptions.Builder().url(URL).features(features).build();
		AnalysisResults response = service.analyze(parameters).execute();
		System.out.println(response);
	}

}
