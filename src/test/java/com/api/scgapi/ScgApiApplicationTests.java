package com.api.scgapi;

import com.api.scgapi.controllers.ApiController;
import com.api.scgapi.models.ABCModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ScgApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ApiController apiController;

	@Test
	void ABC() {
		ResponseEntity res = restTemplate.getForEntity("/DOSCG/doFindBC?param=21,23,-21", ABCModel.class);
		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	void XYZ(){
		ResponseEntity res = restTemplate.getForEntity("/DOSCG/doFindXYZ?param=X, Y, 5, 9, 15, 23, Z", String.class);
		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	void Map() throws IOException {
//		Gson gson = new Gson();
//		ResponseBody responseBody = (ResponseBody) apiController.map();
//		ResponseBody entity = gson.fromJson(responseBody.string(), ResponseBody.class);
//		System.out.println(entity);
	}

}
