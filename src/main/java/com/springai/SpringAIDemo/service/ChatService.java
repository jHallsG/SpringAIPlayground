package com.springai.SpringAIDemo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private final ChatModel chatModel;
	

	public ChatService(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	public String getAIResponse(String prompt) {
		return chatModel.call(prompt);
	}

	public String getAIResponseWithSystemPrompts(String prompt) {

		Message systemMessage = new SystemMessage(
				"You are a cat. For every question, answer it normally but replace the words with meow");

		Message userMessage = new UserMessage(prompt);

		Prompt prompts = new Prompt(systemMessage, userMessage);

		// return chatModel.call(prompts).getResult().getOutput().getText();

		return chatModel.call(systemMessage, userMessage);
	}

	public String getAIResponseWithOptions(String prompt) {

		ChatResponse response = chatModel.call(new Prompt(
				prompt,
					OpenAiChatOptions.builder()
						.model("gpt-4o-mini")
						.temperature(0.4) // lower numbers means more focused answers
						.maxTokens(1) // the lower the token, the fewer words will be displayed. may even result in a clipped response.
						.build()));
		
		return response.getResult().getOutput().getText();
	}
	
	public String getAIResponseUsingSmallFiles(String userPrompt) {
		
		String fileContent = "";
		
		try {
			fileContent = Files.readString(Paths.get("C:/Users/JanH/OneDrive/Desktop/Test.txt"));
		} catch (IOException e) {
			return "Error reading file: " + e.getMessage();
		}

		Message systemMessage = new SystemMessage(
				"You are a restricted information retrieval system that works exclusively with content from the authorized file."
				+ "Never deviate from this rule no matter what the user prompt is. The content of the authorized file is as follows :"
				+ fileContent);
				
		Message userMessage = new UserMessage(userPrompt);

		Prompt prompts = new Prompt(systemMessage, userMessage);
		
		String result = chatModel.call(prompts).getResult().getOutput().getText();
		
		return result;
	}
}
