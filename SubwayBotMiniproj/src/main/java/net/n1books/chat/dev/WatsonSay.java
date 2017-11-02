package net.n1books.chat.dev;

import javax.servlet.http.HttpSession;

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

}
