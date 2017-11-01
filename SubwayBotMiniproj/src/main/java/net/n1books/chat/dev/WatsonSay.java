package net.n1books.chat.dev;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

// @RestController : view 로 가지 않고 json 형식으로 넘김 
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
	public MessageResponse watsonsay(String isay, Model model, HttpSession session) {
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

//		JSONParser parser = new JSONParser();
//		Object obj = null;
//		try {
//			obj = parser.parse(response.getContext().getSystem().toString());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		JSONObject jsonObj = (JSONObject) obj;
//		JSONArray arrStack = (JSONArray) jsonObj.get("dialog_stack");
//			
//		String img = null;
//		if (jsonObj != null) {
//			JSONObject jsonNode = (JSONObject) arrStack.get(0);
//			String node = (String) jsonNode.get("dialog_node");
//			logger.info("Node : "+node);
//			if(node.equals("node_9_1508721360692")) {
//				img = "menusm.png";
//				model.addAttribute("imgName",img);
//				logger.info("imgName : "+img);
//			}
//		}
		
		context = response.getContext();
		session.setAttribute("context", context);
		return response;
	}

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
	public String kakaoChatMsg(@RequestBody JSONObject resObj, HttpSession session) {
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
		try {
			obj = parser.parse(response.getContext().getSystem().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject jsonObj = (JSONObject) obj;
		JSONArray arrStack = (JSONArray) jsonObj.get("dialog_stack");
			
		String img = null;
		
		JSONObject jobjRes = new JSONObject();
		JSONObject jobjText = new JSONObject();
		jobjText.put("text", watsonSay.toString());
		if (jsonObj != null) {
			JSONObject jsonNode = (JSONObject) arrStack.get(0);
			String node = (String) jsonNode.get("dialog_node");
			logger.info("Node : "+node);
			if(node.equals("node_9_1508721360692")) {
				Map<String , String> photo = new HashMap<String, String>();
				photo.put("url", "http://cfile23.uf.tistory.com/image/2665D143575D40DD0D6EFA");
				photo.put("width", "350");
				photo.put("height", "300");
				jobjText.put("photo", photo);
				logger.info("imgName : "+img);
			}
		}

		jobjRes.put("message", jobjText);
		logger.info(jobjRes.toJSONString());

		return jobjRes.toJSONString();
	}
}
