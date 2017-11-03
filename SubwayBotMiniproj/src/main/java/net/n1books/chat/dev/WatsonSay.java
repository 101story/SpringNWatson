package net.n1books.chat.dev;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

@RestController
public class WatsonSay {
	private static Logger logger = LoggerFactory.getLogger(WatsonSay.class);
	@Value("${conversation.username}")
	private String username;
	@Value("${conversation.password}")
	private String password;
	@Value("${conversation.workspaceId}")
	private String workspaceId;

	@RequestMapping(value = "watsonsay")
	public MessageResponse watsonsay(String isay, Model model, HttpSession session,  HttpServletRequest request) {
		logger.info("user input : " + isay);
		MessageResponse response = null;

		Conversation service = new Conversation(Conversation.VERSION_DATE_2017_05_26);
		service.setUsernameAndPassword(username, password);

		Context context = (Context) session.getAttribute("context");
		logger.info("context: " + context);

		MessageOptions options = null;
		String msg = isay;

		options = new MessageOptions.Builder().workspaceId(workspaceId).input(new InputData.Builder(msg).build())
				.context(context).build();
		response = service.message(options).execute();
		logger.info(response.toString());
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = null;
		try {
			jsonObj = (JSONObject) parser.parse(response.getContext().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String menu  = (String) jsonObj.get("menu");
		
		if(menu != null && menu.length() > 0) {
			logger.info("len :"+ menu.length() );
			StringBuffer sourceKorSen = null;
			ArrayList<Source> sources = new ArrayList();
			String[] sourceKor = {"랜치", "마요네즈", "스위트 어니언", "허니머스타드", "스위트칠리", "비비큐",
					"핫칠리", "사우스 웨스트", "허니머스타드", "홀스래디쉬",
					"사우전 아일랜드", "이탈리아드래싱", "마리나라", "레드와인식초",
					"소금", "후추", "올리브오일"};

			switch (menu) {
			case "햄":
				sources.add(Source.Mayonnaise); 
				sources.add(Source.Chipotle);
				break;
			case "베지":
				sources.add(Source.OliveOil); 
				sources.add(Source.RedWine);
				sources.add(Source.RedWine);
				break;
			case "미트볼":
				sources.add(Source.Marinara);
				sources.add(Source.Mayonnaise);
				sources.add(Source.HotChilli);
				break;
			case "에그마요":
				sources.add(Source.HoneyMustard);
				sources.add(Source.SweetChilli);
				break;
			case "비엘티":
				sources.add(Source.Mayonnaise);
				sources.add(Source.SweetChilli);
				break;
			case "터키 베이컨":
				sources.add(Source.SweetOnion);
				sources.add(Source.Chipotle);
				break;
			case "치킨데리야끼":
				sources.add(Source.SweetOnion);
				sources.add(Source.BBQ);
				sources.add(Source.Ranch);
				break;
			case "이탈리아 비엠티":
				sources.add(Source.Horseradish);
				sources.add(Source.Chipotle);
				sources.add(Source.Mayonnaise);
				break;
			case "써브웨이 클럽":
				sources.add(Source.HoneyMustard);
				sources.add(Source.BBQ);
				sources.add(Source.Ranch);
				break;
			case "치킨베이컨랜치":
				sources.add(Source.Ranch);
				sources.add(Source.Horseradish);
				sources.add(Source.BlackPepper);
				break;
			case "터키베이컨 아보카도":
				sources.add(Source.BlackPepper);
				sources.add(Source.Salt);
				sources.add(Source.OliveOil);
				break;
			case "스테이크엔치즈":
				sources.add(Source.BBQ);
				sources.add(Source.YellowMustard);
				break;
			case "로스트비프":
				sources.add(Source.Salt);
				sources.add(Source.BlackPepper);
				sources.add(Source.RedWine);
				sources.add(Source.OliveOil);
				break;
			case "서브웨이멜트":
				sources.add(Source.HoneyMustard);
				sources.add(Source.SweetChilli);
				break;
			case "로스트치킨":
				sources.add(Source.Salt);
				sources.add(Source.BlackPepper);
				sources.add(Source.HoneyMustard);
				break;
			case "스파이시 이탈리안":
				sources.add(Source.HotChilli);
				sources.add(Source.Mayonnaise);
				sources.add(Source.Ranch);
				break;
			case "터키":
				sources.add(Source.HoneyMustard);
				sources.add(Source.Chipotle);
				sources.add(Source.Ranch);
				break;
			case "참치":
				sources.add(Source.HoneyMustard);
				sources.add(Source.BBQ);
				break;
			case "씨푸드":
				sources.add(Source.ThousandIsland);
				sources.add(Source.HotChilli);
				break;
			default:
				break;
			}
			
			sourceKorSen = new StringBuffer(); 
			sourceKorSen.append(" ");
			for (Source s: sources) {
				if(sourceKorSen.length()!=1)sourceKorSen.append(", ");
				sourceKorSen.append(sourceKor[s.ordinal()]);
			}
			sourceKorSen.append(" 소스를 곁들여 같이 드셔보세요.");
			
			logger.info("source : "+sourceKorSen.toString());
			response.put("source", sourceKorSen.toString());
			
			logger.info(response.toString());
		}
		
		logger.info(request.getServerName());
		context = response.getContext();
		session.setAttribute("context", context);
		
		return response;
	}

}
