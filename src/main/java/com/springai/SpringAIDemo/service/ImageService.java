package com.springai.SpringAIDemo.service;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	private final OpenAiImageModel imageModel;

	public ImageService(OpenAiImageModel imageModel) {
		this.imageModel = imageModel;
	}

	public ImageResponse getAIImageResponse(String userPrompt) {

		Message userMessage = new UserMessage(userPrompt);

		OpenAiImageOptions imageOptions = new OpenAiImageOptions().builder().quality("hd") // image quality
				.N(4) // number of images to generate
				.height(1024).width(1024).build();

		ImagePrompt prompt = new ImagePrompt(userPrompt, imageOptions);

		ImageResponse response = imageModel.call(prompt);

		return response;
	}
}
