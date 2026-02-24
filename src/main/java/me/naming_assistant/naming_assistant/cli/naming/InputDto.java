package me.naming_assistant.naming_assistant.cli.naming;

import me.naming_assistant.naming_assistant.cli.NamingToolApp;
import me.naming_assistant.naming_assistant.cli.dto.management.History;

public class InputDto {
	public void inputDto(String targetLabel, History history) {
		switch(targetLabel) {
			case "インタフェース":
				NamingToolApp.nc.getHistories().getInterfaceList().add(history);
				break;
			case "クラス":
				NamingToolApp.nc.getHistories().getClassList().add(history);
				break;
			case "変数":
				NamingToolApp.nc.getHistories().getVariableList().add(history);
				break;
			case "定数":
				NamingToolApp.nc.getHistories().getConstantList().add(history);
				break;
			case "メソッド":
				NamingToolApp.nc.getHistories().getVariableList().add(history);
				break;
			default:
		}
	}
}
