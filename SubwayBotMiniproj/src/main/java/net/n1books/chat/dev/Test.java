package net.n1books.chat.dev;

import com.google.gson.JsonObject;

public class Test {

	public static void main(String[] args) {
		JsonObject jobjText = new JsonObject();
		jobjText.addProperty("text", "Hello");

		JsonObject photo = new JsonObject();
		photo.addProperty("url", "http://cfile23.uf.tistory.com/image/2665D143575D40DD0D6EFA");
		photo.addProperty("width", 350);
		photo.addProperty("height", 300);
		jobjText.add("photo", photo);
		jobjText.addProperty("text", "Hello"+" "+"ddd");
		System.out.println(jobjText);
	}

}
