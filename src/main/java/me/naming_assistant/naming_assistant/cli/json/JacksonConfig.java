package me.naming_assistant.naming_assistant.cli.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonConfig {
	public static ObjectMapper createObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();

		// 基本設定
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true); // 整形されたJSON出力
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false); // 空のオブジェクトを許可

		// Java 8 日付/時刻モジュールの登録
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		return mapper;
	}
}