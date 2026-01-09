package me.naming_assistant.naming_assistant.cli;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

class GeminiClient {
	String geminiAPI(String target, String content) {
		String targetLanguage = "Java"; // ファイル拡張子から判定
		//  String userDescription = user[1];
		//  List<String> existingNames = Arrays.asList("email", "validate", "checkInput"); // ファイルから抽出したもの

		String geminiPrompt = String.format("""
		        あなたは「プログラミング命名専門の実行エージェント」です。
		        以下の情報に基づき、最適な名前を3つ選定して出力してください。
		        ※質問や前置きは一切不要です。案のみを出力してください。

		        【情報】
		        対象言語: %s
		        命名対象: %s
		        処理内容: %s

		        【制約】
		        ・Javaの標準的な規約（クラスならPascalCase、変数ならcamelCase）に従うこと。

		        【出力形式】
		        案1: [名前]
		        理由: [簡潔な理由]

		        案2: [名前]
		        理由: [簡潔な理由]

		        案3: [名前]
		        理由: [簡潔な理由]
		              """,
		        targetLanguage,
		        target,
		        content);
		try (// The client gets the API key from the environment variable `GEMINI_API_KEY`.
		        Client client = new Client()) {
			GenerateContentResponse response = client.models.generateContent(
			        "gemini-2.5-flash",
			        geminiPrompt,
			        null);

			return response.text();
		}
	}
}