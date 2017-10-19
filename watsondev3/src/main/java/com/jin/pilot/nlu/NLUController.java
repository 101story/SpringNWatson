package com.jin.pilot.nlu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;

@Controller
public class NLUController {
	private static Logger logger = LoggerFactory.getLogger(NLUController.class);
	
	@Autowired //@Inject
	// @Qualifier == @Resource(name="nLUServiceImpl")
	private NLUService nluService;
	
	@RequestMapping("nluForm")
	public void nluForm() {}
	
	@Value("${nlu.username}")
	private String username;
	@Value("${nlu.password}")
	private String password;
	
	@RequestMapping(value = "nluProcess",
		headers="Accept=application/json;charset=UTF-8",
		produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody()
	public String nluProcess(String statement) {
		logger.info("statement : " + statement);
		
		NaturalLanguageUnderstanding service =
				new NaturalLanguageUnderstanding(NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27);
		service.setUsernameAndPassword(username, password);

		EmotionOptions emotions = new EmotionOptions.Builder().build();
			
		Features features = new Features.Builder().emotion(emotions).build();
		AnalyzeOptions parameters = 
			new AnalyzeOptions.Builder().text(statement).features(features).build();
		AnalysisResults response = service.analyze(parameters).execute();
		logger.info("result : " + response);
		return response.toString();
	}
}
