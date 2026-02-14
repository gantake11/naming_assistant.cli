package me.naming_assistant.naming_assistant.cli.naming;

import me.naming_assistant.naming_assistant.cli.dto.management.History;
import me.naming_assistant.naming_assistant.cli.dto.management.NamingContext;

public class InputDto {
	public void inputDto(NamingContext nc, String targetLabel, History history) {
		switch(targetLabel) {
			case "インタフェース":
				nc.getHistories().getInterfaceList().add(history);
				break;
			case "クラス":
				nc.getHistories().getClassList().add(history);
				break;
			case "変数":
				nc.getHistories().getVariableList().add(history);
				break;
			case "定数":
				nc.getHistories().getConstantList().add(history);
				break;
			case "メソッド":
				nc.getHistories().getVariableList().add(history);
				break;
			default:
		}
	}
}
