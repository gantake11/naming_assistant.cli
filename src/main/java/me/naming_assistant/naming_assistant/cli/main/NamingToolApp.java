package me.naming_assistant.naming_assistant.cli.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.naming_assistant.naming_assistant.cli.dto.management.NamingContext;
import me.naming_assistant.naming_assistant.cli.json.JacksonConfig;
import me.naming_assistant.naming_assistant.cli.json.JsonManager;
import me.naming_assistant.naming_assistant.cli.json.Serialize;
import me.naming_assistant.naming_assistant.cli.naming.NamingInput;

public class NamingToolApp {
	
	

	public static void main(String[] args) {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			NamingContext nc = new NamingContext();
			NamingInput ni = new NamingInput();
			JsonManager wr = new JsonManager();
			Serialize se = new Serialize();
			ObjectMapper mapper = JacksonConfig.createObjectMapper();
			String projectName;
			String userChoice;

			System.out.println("=== 命名アシスタントツール ===");
			System.out.println("Javaのプロジェクトの命名を支援します。\n");

			System.out.println("今回のプロジェクト名を教えてください。\n");
			System.out.print("プロジェクト名：");
			projectName = br.readLine();
			
			nc.setProjectName(projectName);

			while(true) {
				System.out.println("\nやりたいことを選んでください");
				System.out.println("1：APIを利用して命名する");
				System.out.println("2：プログラムを読み込み、名前を抽出する");
				System.out.println("3：このツールのjsonファイルを読み込む");
				System.out.println("4：終了\n");
				System.out.print("やりたいこと：");
	
				userChoice = br.readLine();
	
				switch(userChoice) {
					case "1": 
						System.out.println("\nAPIを利用して命名する\n");
						ni.namingInput(nc, br, mapper);
						break;
					case "2":
						System.out.println("\nこの処理は未実装です。");
						break;
					case "3":
						System.out.println("\nJsonファイルを受け取ってオブジェクトに変換する");
						se.serialize(br, nc, mapper, wr, se);
						break;
					case "4":
						System.out.println("Json結果");
						System.out.println(mapper.writeValueAsString(nc));
						System.out.println("\n最終的なJsonファイルを出力します");
						System.out.println("出力先はuser/home/.naming-assistantです");
						wr.saveToFile(nc, mapper);
						
						System.out.println("\nこのツールを終了します。");
						System.exit(0);
					default:
						System.out.println("\n1か2か3か4を入力してください");
						break;
					
				}
				
				System.out.println("Json結果");
				System.out.println(mapper.writeValueAsString(nc));
			}

		} catch (Exception e) {
			System.err.println("予期せぬエラーが発生しました: " + e.getMessage());
			e.printStackTrace();
		}

	}

}
