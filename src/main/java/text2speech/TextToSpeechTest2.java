package text2speech;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TextToSpeechTest2 {

	public static void main(String[] args) {
		// ApplicationContext context = new ClassPathXmlApplicationContext("text2speech/beaninit.xml");
		// path 가 긴 경우 처리하는 방법
		ApplicationContext context = new ClassPathXmlApplicationContext("beaninit.xml", TextToSpeechTest2.class);
		
		// Text2SpeechService service = new Text2SpeechServiceImpl();
		Text2SpeechService service = (Text2SpeechService) context.getBean("service");
		
		try {
			System.out.println(service.getVoiceList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
