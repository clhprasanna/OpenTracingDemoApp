package com.mjc.opentracing.OpenTracingDemoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoController 
{
	@Autowired
    private RestTemplate restTemplate;


	@GetMapping("/test")
	public String test()
	{
		return "Testing Open Tracing";
	}
	
	@GetMapping("/testchaining")
	public String testChaining()
	{
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/test", String.class);
        return response.getBody() + " Chaining";
		
	}
	
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) 
	{
	   return builder.build();
	}

	
}
