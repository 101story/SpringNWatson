package net.n1books.chat.dev;

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
		
		JsonObject jobjRes = new JsonObject();
		JsonObject jobjText = new JsonObject();
		jobjText.addProperty("text", watsonSay.toString());
		if (jsonObj != null) {
			JSONObject jsonNode = (JSONObject) arrStack.get(0);
			String node = (String) jsonNode.get("dialog_node");
			logger.info("Node : "+node);
			if(node.equals("node_9_1508721360692")) {
				JsonObject photo = new JsonObject();
				photo.addProperty("url", "http://cfile23.uf.tistory.com/image/2665D143575D40DD0D6EFA");
				photo.addProperty("width", 400);
				photo.addProperty("height", 350);
				jobjText.add("photo", photo);
				logger.info("imgName : "+img);
			}else if(node.equals("node_3_1508719554698")) {
				JsonObject photo = new JsonObject();
				photo.addProperty("url", "http://postfiles7.naver.net/MjAxNjExMjlfODgg/MDAxNDgwMzc1MTEyMDE3.nMA33n9L1T3Hn8hT4qpF6Ct1SvXgAXw5ZfAOIp9I7k4g.MhuS5KpbH9HVYnwZvSgFZ1vf-aniRumSW6YeSMWvsDwg.JPEG.alizee8/2016-11-29_08%3B17%3B14.jpg?type=w773");
				photo.addProperty("width", 400);
				photo.addProperty("height", 350);
				jobjText.add("photo", photo);
				logger.info("imgName : "+img);
			}
		}

		jobjRes.add("message", jobjText);
		logger.info(jobjRes.toString());

		return jobjRes.toString();
	}
}
