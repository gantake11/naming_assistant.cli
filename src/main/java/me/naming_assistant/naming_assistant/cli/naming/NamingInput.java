package me.naming_assistant.naming_assistant.cli.naming;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.naming_assistant.naming_assistant.cli.dto.management.NamingContext;
import me.naming_assistant.naming_assistant.cli.dto.response.Response;
import me.naming_assistant.naming_assistant.cli.json.Serialize;

public class NamingInput {
	
	public String targetLabel;
	public Response choiseName;
	
	public  void namingInput(NamingContext nc, BufferedReader br, ObjectMapper mapper) {
		// try-with-resources で BufferedReader を確実に閉じる
		try {

			GeminiClient gc = new GeminiClient();
			Serialize se = new Serialize();
			List<Response> responses;
			String targetInput;
			String content;
			String output;
			int userChoise = 0;

			while (true) {
				// 1. 命名対象の選択
				System.out.println("命名対象を選んでください。\n(0：インタフェース、1：クラス, 2：変数, 3：定数、4：メソッド)\n");
				System.out.print("選択：");
				targetInput = br.readLine();

			
				// 入力された数値を言葉に変換
				this.targetLabel = switch (targetInput) {
				case "0" -> "インタフェース";
				case "1" -> "クラス";
				case "2" -> "変数";
				case "3" -> "定数";
				case "4" -> "メソッド";
				default -> "識別子"; // 予期せぬ入力への備え
				};
				if (this.targetLabel != "識別子") {
					break;
				}
			}

			// 2. 処理内容の入力
			System.out.print("どんな処理をするものですか？（例：ユーザー登録をする）：");
			content = br.readLine();

			// 3. (発展) 本来ならここでファイルを読み込んで既存名を取得する
			// 今回は一旦空のリスト、または仮の文字列を渡す想定
			//            String existingNames = "特になし"; 
			
			while (true) {
				int index = 1;
				
				System.out.println("\nGeminiに問い合わせ中...");

				// GeminiClientのメソッドを呼び出し（引数は設計に合わせて調整してください）
				output = gc.geminiAPI(this.targetLabel, content);
				responses = se.serialize(mapper, output);
				
				System.out.println("\n--- Geminiの提案 ---");
				for (Response response : responses) {
					System.out.println("\n" + index + ":" + response.getName());
					System.out.println("理由" + response.getReason());
					index++;
				}
				System.out.println("-------------------\n");

				System.out.print("使いたい名前がありましたか(はい : いいえ)：");
				String userDecision = br.readLine();
				if (userDecision.trim().equals("はい")) {
					while (true) {
						System.out.println("どの提案を採用しますか");
						System.out.print("1～3を入力してください：");

						try {
							userChoise = Integer.parseInt(br.readLine());
							this.choiseName = responses.get(userChoise);
							
							if(userChoise > 0 && userChoise < 4) {
								break;
							} else {
								System.out.println("\n1～3を入力してください\n");
							}
						} catch (NumberFormatException e) {
							System.out.println("\n1～3を入力してください\n");
						}
					}

					break;
				} else if (userDecision.trim().equals("いいえ")) {
					continue;
				} else {
					System.out.println("\nはい か いいえ を入力してください\n");
				}
			}

		} catch (IOException e) {
			System.err.println("入力エラーが発生しました: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("予期せぬエラーが発生しました: " + e.getMessage());
			e.printStackTrace();
		}
	}
}