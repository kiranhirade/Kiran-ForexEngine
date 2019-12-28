package com.example.forex;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.forex.pojo.Forex;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForexApplicationTests extends AbstractTest {

	/**
	 * For test getAllForexs method
	 * 
	 * @throws Exception
	 */
	@Test
	public void getAllForexsTest() throws Exception {
		String uri = "/forexs";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Forex[] forexs = super.mapFromJson(content, Forex[].class);
		assertTrue(forexs.length > 0);
	}

	/**
	 * Get by forex id 
	 * @throws Exception
	 */
	@Test
	public void getAllForexsByIdTest() throws Exception {
		String uri = "/forexs/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Forex forexs = super.mapFromJson(content, Forex.class);
		assertTrue(forexs !=null);
	}

	
	/**
	 * For create forex object.
	 * 
	 * @throws Exception
	 */
	@Test
	public void createForexTest() throws Exception {
		String uri = "/forexs";
		Forex forex = new Forex();
		forex.setCurrency("USD");
		forex.setCreatedAt(new Date());
		forex.setRate(71.65);

		String inputJson = super.mapToJson(forex);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}

	/**
	 * Update forex object
	 * 
	 * @throws Exception
	 */

	@Test
	public void updateForexTest() throws Exception {
		String uri = "/forexs/1";
		Forex forex = new Forex();
		forex.setCurrency("INR");

		String inputJson = super.mapToJson(forex);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Forex forexObj = super.mapFromJson(content, Forex.class);

		assertEquals(forexObj.getId(), 1);
	}

	/**
	 * Delete forex object
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteProduct() throws Exception {
		String uri = "/forexs/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		//String content = mvcResult.getResponse().getContentAsString();
	}
}
