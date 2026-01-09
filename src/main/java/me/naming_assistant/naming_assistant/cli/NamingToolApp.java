package me.naming_assistant.naming_assistant.cli;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NamingToolApp {

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			NamingInput ni = new NamingInput();

			System.out.println("=== 命名アシスタントツール ===");
			System.out.println("Javaのプロジェクトの命名を支援します。");

			System.out.println("今回のプロジェクト名を教えてください。");
			System.out.print("プロジェクト名：");
			String projectName = br.readLine();

			while(true) {
				System.out.println("やりたいことを選んでください");
				System.out.println("1：APIを利用して命名する");
				System.out.println("2：プログラムを読み込み、名前を抽出する");
				System.out.println("3：このツールのjsonファイルを読み込む");
				System.out.println("4：終了");
				System.out.print("やりたいこと：");
	
				int userChoice = Integer.parseInt(br.readLine());
	
				switch(userChoice) {
					case 1: 
						System.out.println("APIを利用して命名する");
						ni.namingInput(projectName);
						break;
					case 2:
						System.out.println("この処理は未実装です。");
						break;
					case 3:
						System.out.println("この処理は未実装です。");
						break;
					case 4:
						System.out.println("このツールを終了します。");
						System.exit(0);
					default:
						System.out.println("1か2か3を入力してください");
						break;
					
				}
			}

		} catch (Exception e) {
			System.err.println("予期せぬエラーが発生しました: " + e.getMessage());
			e.printStackTrace();
		}

	}

}
