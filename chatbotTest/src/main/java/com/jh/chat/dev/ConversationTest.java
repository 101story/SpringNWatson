package com.jh.chat.dev;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class ConversationTest {
	@Value("${conver.username}")
	private String username;
	@Value("${conver.password}")
	private String password;
	@Value("${conver.workspace}")
	private String workspaceId;
	
	public void testConversation() {
		Scanner sc = new Scanner(System.in);
		Conversation service = new Conversation(Conversation.VERSION_DATE_2017_05_26);
		service.setUsernameAndPassword(username, password);
		
		MessageResponse response = null;
		Context context = null;
		MessageOptions options = null;
		String msg = "";
		StringBuffer watsonSay = null;
		
		while(true) {
			options = new MessageOptions.Builder()
				    .workspaceId(workspaceId)
				    .input(new InputData.Builder(msg).build())
				    .context(context) //대화처음엔 null 값으로 주면됨
				    .build();
			
			response = service.message(options).execute();
			System.out.println("watson : "+response);

			watsonSay = new StringBuffer();
			for(String outstr :  response.getOutput().getText()) {
				watsonSay.append(outstr);
				watsonSay.append(" ");
			}
			System.out.println(watsonSay);
			System.out.println("I : ");
			msg = sc.nextLine();
			context = response.getContext();
			if(msg.equals("exit")) {
				System.exit(0);
			}
		}
	}
	
}
