package text2speech;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

//@Component //text2SpeechServiceImpl 변수로 객체 자동 생성함 DI(관계의존성주입)
@Component("service") //service 변수로 생성됨 
public class Text2SpeechServiceImpl extends TextToSpeech implements Text2SpeechService{

	public Text2SpeechServiceImpl() {
		setUsernameAndPassword("c307664a-97bb-4e0f-b8af-784116a2b676", "6Sokqkz82ep6");
	}

	public List<Voice> getVoiceList() throws Exception {
		List<Voice> voices = getVoices().execute();
		return voices;
	}


}
