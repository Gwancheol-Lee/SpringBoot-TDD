package com.example.productorderservice;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTest {
	
	@Autowired
	private DatabaseCleanup databaseCleanup;
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	void setUp() {
		// ApiTest 실행 전 포트 설정 안되어 있으면 포트 설정 및 DataBase Cleanup 
		if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
			RestAssured.port = port;
			databaseCleanup.afterPropertiesSet();
		}
		databaseCleanup.execute();
	}
}
