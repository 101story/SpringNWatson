package com.jin.visual;

import org.springframework.beans.factory.annotation.Value;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

public class VisualRecognitionTest {
	@Value("${visual.apikey}")
	private static String apikey;
	
	public static void main(String[] args) {
		String SINGLE_IMAGE_FILE = "img/impoint_red.png";
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
		service.setApiKey(apikey);

		System.out.println("Classify an image");
		ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
				.url("http://i.huffpost.com/gen/4194488/thumbs/o-GRAUER-GORILLAS-570.jpg?7")
				.build();
//		System.out.println("Classify an image");
//		ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
//		    .images(new File(SINGLE_IMAGE_FILE))
//		    .build();
		VisualClassification result = service.classify(options).execute();
		System.out.println(result);
	}

}
