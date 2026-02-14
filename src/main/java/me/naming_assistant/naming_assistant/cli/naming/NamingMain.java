package me.naming_assistant.naming_assistant.cli.naming;

import java.io.BufferedReader;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.naming_assistant.naming_assistant.cli.dto.management.NamingContext;

public class NamingMain {
	
	public void namingMain(NamingContext namingContext, BufferedReader br, ObjectMapper mapper) {
		
		NamingInput ni = new NamingInput();
		
		// gemini apiに命名してもらう
		ni.namingInput(namingContext, br, mapper);
		
		// 選択された名前をdtoに入れる
		
	}
}
