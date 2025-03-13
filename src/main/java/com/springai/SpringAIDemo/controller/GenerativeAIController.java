package com.springai.SpringAIDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springai.SpringAIDemo.service.ChatService;

@RestController
public class GenerativeAIController {
	
	private ChatService chatService;
	
	public GenerativeAIController(ChatService chatService) {
		this.chatService = chatService;
	}

	@GetMapping("/askAI")
	public String getResponse(@RequestParam("query") String userPrompt) {
		
		return chatService.getAIResponse(userPrompt);
	}
	
	@GetMapping("/promptAI")
	public String getResponseWithPrompts(@RequestParam("query") String userPrompt) {
		
		return chatService.getAIResponseWithSystemPrompts(userPrompt);
	}
	
	@GetMapping("/promptAIOptions")
	public String getResponseWithPromptsAndOptions(@RequestParam("query") String userPrompt) {
		
		return chatService.getAIResponseWithOptions(userPrompt);
	}
	
	@GetMapping("/promptAIOFiles")
	public String getResponseFromSmallFiles(@RequestParam("query") String userPrompt) {
		
		return chatService.getAIResponseUsingSmallFiles(userPrompt);
	}

}
