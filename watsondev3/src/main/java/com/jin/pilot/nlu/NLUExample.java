package com.jin.pilot.nlu;

import org.springframework.beans.factory.annotation.Value;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;

class NLUExample {
	@Value("${nlu.username}")
	private static String username;
	@Value("${nlu.username}")
	private static String password;

	private static String text = "전문가들은 최근 코스피가 많이 올랐기 때문에 쉬어가는 것이라고 해석했다. 또 3분기 실적에 대한 관망세, 업종별 온기 확산, 각종 외부 이벤트 등이 복합적으로 작용하며 상승폭이 크지 않고 있다고 분석했다.\r\n" + 
			"단기간 지수가 많이 올랐기 때문에 숨고르기하는 시간이 필요한 것”이라고 말했다." + 
			"그는 주식시장의 색깔 변화도 주목할 필요가 있다며 최근 IT 등 주도주보다 소비주, 사드 관련주가 강세를 나타내며 스타일의 변화가 나타나고 있는데 교체가 일어나는 상황에서 지수가 많이 오르기는 힘들다고 설명했다.";

	public static void main(String[] args) {
		NaturalLanguageUnderstanding service = 
				new NaturalLanguageUnderstanding(NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27);
		service.setUsernameAndPassword(username, password);
		
		ConceptsOptions concepts = new ConceptsOptions.Builder().limit(20).build();
		Features features = new Features.Builder().concepts(concepts).build();
		AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(text).features(features).build();
		AnalysisResults response = service.analyze(parameters).execute();
		System.out.println(response);
	}

}
