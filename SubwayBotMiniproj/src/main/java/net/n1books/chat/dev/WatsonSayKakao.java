package net.n1books.chat.dev;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

// @RestController : view 로 가지 않고 json 형식으로 넘김 
@RestController
public class WatsonSayKakao {
	private static Logger logger = LoggerFactory.getLogger(WatsonSayKakao.class);
	@Value("${conversation.username}")
	private String username;
	@Value("${conversation.password}")
	private String password;
	@Value("${conversation.workspaceId}")
	private String workspaceId;

	@RequestMapping(value = "/keyboard", method = RequestMethod.GET)
	public String kakaoChat() {
		logger.info("keyboard");

		JSONObject jobjBtn = new JSONObject();
		jobjBtn.put("type", "text");

		return jobjBtn.toJSONString();
	}

	@RequestMapping(value = "/message", method = RequestMethod.POST, headers = "Accept=application/json;charset=UTF-8", produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public String kakaoChatMsg(@RequestBody JSONObject resObj, HttpSession session, HttpServletRequest request) {
		logger.info("message");

		Conversation service = new Conversation(Conversation.VERSION_DATE_2017_05_26);
		service.setUsernameAndPassword(username, password);

		MessageResponse response = null;
		Context context = (Context) session.getAttribute("context");
		MessageOptions options = null;
		StringBuffer watsonSay = null;

		String content;
		content = (String) resObj.get("content");

		options = new MessageOptions.Builder().workspaceId(workspaceId).input(new InputData.Builder(content).build())
				.context(context).build();
		response = service.message(options).execute();

		watsonSay = new StringBuffer();
		for (String text : response.getOutput().getText()) {
			watsonSay.append(text);
			watsonSay.append(" ");
		}

		logger.info("Watson : " + watsonSay);

		context = response.getContext();
		session.setAttribute("context", context);

		JSONParser parser = new JSONParser();
		Object obj = null;
		JSONObject jsonContext = null;
		try {
			obj = parser.parse(response.getContext().getSystem().toString());
			jsonContext = (JSONObject) parser.parse(response.getContext().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//dialog 에 따라 이미지 mapping
		JSONObject jsonObj = (JSONObject) obj;
		JSONArray arrStack = (JSONArray) jsonObj.get("dialog_stack");

		String img = null;
		String imgPath = "https://"+request.getServerName() + "/resources/img/";
		
		JsonObject jobjRes = new JsonObject();
		JsonObject jobjText = new JsonObject();
		jobjText.addProperty("text", watsonSay.toString());
		
		if (jsonObj != null) {
			JSONObject jsonNode = (JSONObject) arrStack.get(0);
			String node = (String) jsonNode.get("dialog_node");
			logger.info("Node : " + node);
			if (node.equals("node_9_1508721360692")) {
				JsonObject photo = new JsonObject();
				photo.addProperty("url", imgPath+"menusm.png");
				photo.addProperty("width", 400);
				photo.addProperty("height", 350);
				jobjText.add("photo", photo);
				logger.info("imgName : " + img);
			} else if (node.equals("node_3_1508719554698")) {
				JsonObject photo = new JsonObject();
				photo.addProperty("url", imgPath+"bread.png");
				photo.addProperty("width", 450);
				photo.addProperty("height", 350);
				jobjText.add("photo", photo);
				logger.info("imgName : " + img);
			}
		}

		//Menu 소스 추천 
		String menu  = (String) jsonContext.get("menu");
		
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
			jobjText.addProperty("text", watsonSay.toString()+" "+sourceKorSen.toString());
			
			logger.info(response.toString());
			
		}
		
		jobjRes.add("message", jobjText);
		logger.info(jobjRes.toString());

		return jobjRes.toString();
	}
}
