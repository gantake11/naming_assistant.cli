package me.naming_assistant.naming_assistant.cli;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonDemo {
	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = JacksonConfig.createObjectMapper();
		// オブジェクトからJSONへの変換（シリアライズ）
		NamingContext nc = new NamingContext();
		String json = mapper.writeValueAsString(nc);
		System.out.println("シリアライズ結果:");
		System.out.println(json);

		mapper.writeValue(new File("naming-context.json"), nc);

	}
}