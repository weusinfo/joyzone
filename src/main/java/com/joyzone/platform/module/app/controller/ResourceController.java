package com.joyzone.platform.module.app.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apple-app-site-association")
public class ResourceController {
	
	@RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getAppJson() {
		String json = "{\r\n" + 
    			"    \"applinks\": {\r\n" + 
    			"        \"apps\": [],\r\n" + 
    			"        \"details\": [\r\n" + 
    			"            {\r\n" + 
    			"                \"appID\": \"4WRFSUX5RH.chaifuwei.Quwan\",\r\n" + 
    			"                \"paths\": [\"/*\"]\r\n" + 
    			"            }\r\n" + 
    			"        ]\r\n" + 
    			"    }\r\n" + 
    			"}";
		return json;
	}

}
