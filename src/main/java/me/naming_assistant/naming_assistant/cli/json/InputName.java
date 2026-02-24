package me.naming_assistant.naming_assistant.cli.json;

import java.time.LocalDate;

import me.naming_assistant.naming_assistant.cli.dto.management.History;

public class InputName {
	
	public static String packageName;
	
	public static void inputPackageName(String name) {
		
		packageName = name;
		
	}
	
	public static History gettHistoryData(String name) {
		
		History history = new History();
		
		history.setName(name);
		history.setPackageName(packageName);
		LocalDate today = LocalDate.now();
		history.setTimestamp(today);
		
		return history;
	}
}
