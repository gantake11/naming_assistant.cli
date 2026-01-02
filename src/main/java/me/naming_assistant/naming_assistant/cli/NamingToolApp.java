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
			String programName = br.readLine();

			System.out.println("やりたいことを選んでください");
			System.out.println("1：APIを利用して命名する");
			System.out.println("2：プログラムを読み込み、名前を抽出する");
			System.out.println("3：このツールのjsonファイルを読み込む");

			int todo = Integer.parseInt(br.readLine());

			switch(todo) {
				case 1: 
					ni.namingInput(programName);
					break;
				case 2:
					System.out.println("この処理は未実装です。");
					break;
				case 3:
					System.out.println("この処理は未実装です。");
					break;
				default:
					System.out.println("1か2か3を入力してください");
					break;
				
			}

		} catch (Exception e) {
			System.err.println("予期せぬエラーが発生しました: " + e.getMessage());
			e.printStackTrace();
		}

	}

}
