package me.naming_assistant.naming_assistant.cli.json;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.naming_assistant.naming_assistant.cli.dto.management.NamingContext;

public class JacksonDemo {
	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = JacksonConfig.createObjectMapper();
		// オブジェクトからJSONへの変換（シリアライズ）
		NamingContext nc = new NamingContext();
		String json = mapper.writeValueAsString(nc);
		System.out.println("シリアライズ結果:");
		System.out.println(json);

		Path configDirPath = Paths.get(System.getProperty("user.home"), ".naming-assistant");
		Path filePath = configDirPath.resolve("naming-context.json");

		Files.createDirectories(configDirPath);

		mapper.writeValue(filePath.toFile(), nc);

	}
}