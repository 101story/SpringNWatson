package NLClassifier;

import com.ibm.watson.developer_cloud.natural_language_classifier.v1.NaturalLanguageClassifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classification;

public class NLCExample {

	public static void main(String[] args) {
		NaturalLanguageClassifier service = new NaturalLanguageClassifier();
		service.setUsernameAndPassword("b6bfd215-81bc-46e9-8137-18d22553c1d4", "APVYuQL7Diwz");

		Classification classification = service.classify("ebd44cx231-nlc-24297", "hot today").execute();
		System.out.println(classification);
		
	}

}
