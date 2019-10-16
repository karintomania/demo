package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.controller.SampleController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class SampleTests {

	@Autowired
	SampleController sc;

	@Test
	void test_test() {
		String result = sc.test("2301 (株)学情");
		assertEquals("(株)学情",result,"result="+result);
		
	}

}

