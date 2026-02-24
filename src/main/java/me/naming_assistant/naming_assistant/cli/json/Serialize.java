package me.naming_assistant.naming_assistant.cli.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.naming_assistant.naming_assistant.cli.NamingToolApp;
import me.naming_assistant.naming_assistant.cli.dto.management.History;
import me.naming_assistant.naming_assistant.cli.dto.management.NamingContext;

public class Serialize {
	public void serialize(BufferedReader br, ObjectMapper mapper, JsonManager wr, Serialize se) {
		
		System.out.println("入力するjsonファイルのパスを入力してください。");
		System.out.print("パス：");
		try {
			String path = br.readLine();

			NamingContext ncInput = mapper.readValue(new File(path), NamingContext.class);

			if (NamingToolApp.nc.getProjectName().equals(ncInput.getProjectName())) {
				// ncInputにある名前を追加する
				se.processNameArrays(NamingToolApp.nc, ncInput);
				System.out.println("\n入力されたJsonファイルをオブジェクトに変換しました");
			} else {
				while (true) {
					System.out.println("前回のプロジェクトのデータは保持できません");
					System.out.print("Jsonデータを上書きしますか(はい : いいえ)：");
					String overwriteAnswer = br.readLine();
					if (overwriteAnswer.equals("はい")) {
						System.out.println("\n前回のプロジェクトのJsonファイルを出力します");
						wr.saveToFile(mapper);

						NamingToolApp.nc = new NamingContext();
						NamingToolApp.nc.setProjectName(ncInput.getProjectName());
						NamingToolApp.nc = se.processNameArrays(NamingToolApp.nc, ncInput);
						System.out.println("\n入力されたJsonファイルをオブジェクトに変換しました");
					} else if (overwriteAnswer.equals("いいえ")) {
						System.out.println("やりたいことの選択に戻ります");
						return;
//						return nc;
					} else {
						System.out.println("\nはい か いいえ を入力してください\n");
					}
				}
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			System.out.println("IOException");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			System.out.println("Exception");
			e.printStackTrace();
		}
//		return nc;
	}

	public NamingContext processNameArrays(NamingContext nc, NamingContext ncInput) {
		for (History history : ncInput.getHistories().getInterfaceList()) {
			nc.getHistories().getInterfaceList().add(history);
		}
		for (History history : ncInput.getHistories().getClassList()) {
			nc.getHistories().getClassList().add(history);
		}
		for (History history : ncInput.getHistories().getVariableList()) {
			nc.getHistories().getVariableList().add(history);
		}
		for (History history : ncInput.getHistories().getConstantList()) {
			nc.getHistories().getConstantList().add(history);
		}
		for (History history : ncInput.getHistories().getMethodList()) {
			nc.getHistories().getMethodList().add(history);
		}
		return nc;
	}
}
