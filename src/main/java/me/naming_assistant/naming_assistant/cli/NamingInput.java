package me.naming_assistant.naming_assistant.cli;

import java.io.BufferedReader;
import java.io.IOException;

class NamingInput {
	void namingInput(String programName, BufferedReader br) {
		// try-with-resources で BufferedReader を確実に閉じる
		try {

			GeminiClient gc = new GeminiClient();

			// 1. 命名対象の選択
			System.out.println("命名対象を選んでください。\n(0：インタフェース、1：クラス, 2：変数, 3：定数、4：メソッド)\n");
			System.out.print("選択：");
			String targetInput = br.readLine();

			String targetLabel;
			while (true) {
				// 入力された数値を言葉に変換
				targetLabel = switch (targetInput) {
				case "0" -> "インタフェース";
				case "1" -> "クラス";
				case "2" -> "変数";
				case "3" -> "定数";
				case "4" -> "メソッド";
				default -> "識別子"; // 予期せぬ入力への備え
				};
				if (targetLabel != "識別子") {
					break;
				}
			}

			// 2. 処理内容の入力
			System.out.print("どんな処理をするものですか？（例：ユーザー登録をする）：");
			String content = br.readLine();

			// 3. (発展) 本来ならここでファイルを読み込んで既存名を取得する
			// 今回は一旦空のリスト、または仮の文字列を渡す想定
			//            String existingNames = "特になし"; 

			System.out.println("\nGeminiに問い合わせ中...");

			// GeminiClientのメソッドを呼び出し（引数は設計に合わせて調整してください）
			String output = gc.geminiAPI(targetLabel, content);

			System.out.println("\n--- Geminiの提案 ---");
			System.out.println(output);
			System.out.println("--------------------");

		} catch (IOException e) {
			System.err.println("入力エラーが発生しました: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("予期せぬエラーが発生しました: " + e.getMessage());
			e.printStackTrace();
		}
	}
}