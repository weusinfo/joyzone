package com.joyzone.platform.model.admin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShopControllerTest {
	
	 private MockMvc mvc;

	    @Autowired
	    private WebApplicationContext wac; // 注入WebApplicationContext

	    @Before
	    public void setUp() throws Exception {
	        //使用 WebApplicationContext 构建 MockMvc
	        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	    }
	    
	    @After
	    public void tearDown() throws Exception {
	    }
	    
	    @Test
	    public void addShop() throws Exception {
	    	MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/shop/add")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	                .param("userId", "1")
	                .param("name", "門店名")
	                .param("address", "address")
	                .param("description", "desc")
	                .param("phone", "12323123")
	                .characterEncoding("utf-8")
	                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE);
	    	ResultActions result = mvc.perform(requestBuilder);
	        MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
	        System.out.println("response------------------:"+mvcResult.getResponse().getContentAsString());
	    }

}
