package com.example.immadisairaj.quiz.api;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Request {

	@SerializedName("model")
	private String model;

	@SerializedName("messages")
	private List<Message> messages;

	public Request(String model, ArrayList<Message> messages) {
		this.model = model;
		this.messages = messages;
	}

	public void setModel(String model){
		this.model = model;
	}

	public String getModel(){
		return model;
	}

	public void setMessages(List<Message> messages){
		this.messages = messages;
	}

	public List<Message> getMessages(){
		return messages;
	}
}