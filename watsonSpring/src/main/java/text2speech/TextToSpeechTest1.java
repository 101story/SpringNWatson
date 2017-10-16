package text2speech;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

public class TextToSpeechTest1 {

	public static void main(String[] args) {
		TextToSpeech service = new TextToSpeech();
		//"c307664a-97bb-4e0f-b8af-784116a2b676", "6Sokqkz82ep6" 생성자에도 가능 
		service.setUsernameAndPassword("c307664a-97bb-4e0f-b8af-784116a2b676", "6Sokqkz82ep6");

//		List<Voice> voices = service.getVoices().execute();
//		System.out.println(voices);		

//		Voice voice = service.getVoice("en-US_AllisonVoice").execute();
//		System.out.println(voice);		
	

		try {
		  String text = "Java is the world's most popular programming language. "
		  		+ "Java SE 9, our latest release, is the result of an industry-wide development effort involving open review, "
		  		+ "weekly builds, and extensive collaboration between Oracle engineers and members of the worldwide Java developer "
		  		+ "community via the OpenJDK Community and the JCP.";
		  InputStream stream = service.synthesize(text, Voice.EN_ALLISON, AudioFormat.WAV).execute();
		  InputStream in = WaveUtils.reWriteWaveHeader(stream);
		  OutputStream out = new FileOutputStream("hello_java.wav");
		  byte[] buffer = new byte[1024];
		  int length;
		  while ((length = in.read(buffer)) > 0) {
		    out.write(buffer, 0, length);
		  }
		  out.close();
		  in.close();
		  stream.close();
		}
		catch (Exception e) {
		  e.printStackTrace();
		}
	}

}
