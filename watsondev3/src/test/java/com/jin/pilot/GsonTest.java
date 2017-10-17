package com.jin.pilot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

class BagOfPrimitives {
	private int value1 = 1;
	private String value2 = "abc";
	private transient int value3 = 3; // json 데이터로 포함하지 않음

	BagOfPrimitives() {
		// no-args constructor
	}

	@Override
	public String toString() {
		return "BagOfPrimitives [value1=" + value1 + ", value2=" + value2 + ", value3=" + value3 + "]";
	}
	
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "beaninit.xml")
public class GsonTest {

	@Autowired
	ApplicationContext context;

	@Test
	public void testGSON() {
		// Gson gson = new Gson();	
		// assertEquals("1", gson.toJson(1));
		// assertEquals("abcd", gson.toJson("abcd"));
		// assertEquals(10, gson.toJson(new Long(10)));
		// int[] value = {1};
		// assertEquals(new int[1], gson.toJson(value));

		Gson gson = new Gson();
		System.out.println(gson.toJson(1));
		gson.toJson("abcd");
		gson.toJson(new Long(10));
		int[] values = { 1 };
		gson.toJson(values);
		String s = gson.toJson(values);

	}

	@Test
	public void BagOfPrimitives() {
		BagOfPrimitives obj = new BagOfPrimitives();
		Gson gson = new Gson();
		
		//직렬화
		String json = gson.toJson(obj);
		System.out.println("BagOfPrimitives json " + json);

		// 역직렬화
		BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
		System.out.println("BagOfPrimitives class " + obj2.toString());
	}
}
