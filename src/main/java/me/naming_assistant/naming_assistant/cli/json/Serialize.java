package me.naming_assistant.naming_assistant.cli.json;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import me.naming_assistant.naming_assistant.cli.dto.response.Response;

public class Serialize {
	
	public List<Response> apiResponses = null;
	
	public List<Response> serialize(ObjectMapper mapper, String json) {
		
		try {
			TypeFactory typeFactory = mapper.getTypeFactory();
			CollectionType collectionType = typeFactory.constructCollectionType(List.class, Response.class);
			this.apiResponses = mapper.readValue(json, collectionType);
		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return this.apiResponses;
	}
}
