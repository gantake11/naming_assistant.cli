package me.naming_assistant.naming_assistant.cli.naming;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Schema;

import me.naming_assistant.naming_assistant.cli.NamingToolApp;

public class GeminiClient {

	public String geminiAPI(String target, String packageName, String content, ObjectMapper mapper) {

		// 1. プロンプトの作成
		// JSONの形式指示は削除し、命名のロジックのみを記述します
		String geminiPrompt = null;
		try {
			geminiPrompt = String.format("""
					あなたは「プログラミング命名専門の実行エージェント」です。
					提供される【プロジェクトコンテキスト】（JSON形式）を深く理解し、
					その文脈と命名規則に則った上で、以下の【依頼内容】に対する最適な名前を3つ選定してください。

					理由は日本語で教えてください。

					【プロジェクトコンテキスト】
					%s

					【依頼内容】
					命名対象: %s
					処理内容: %s

					【制約】
					・コンテキスト内の "conventions" に定義された命名規則を厳守すること。
					・コンテキスト内の "histories" にある既存名称と重複しないこと。
					・出力は指定されたJSONスキーマに従うこと。
					""",
					mapper.writeValueAsString(NamingToolApp.nc), // ここにJSON文字列がドカンと入ります
					target, // 今回命名したい対象（例: "設定ファイルを読み込むクラス"）
					content // 処理内容の説明
			);
		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// 2. スキーマ（構造）の定義
		// リストの中身：名前(name)と理由(reason)
		Schema itemSchema = Schema.builder()
				.type("OBJECT") // SDKによっては "OBJECT" 文字列または Type.OBJECT
				.properties(
						Map.of(
								"name", Schema.builder()
										.type("STRING")
										.build(),
								"reason", Schema.builder()
										.type("STRING")
										.build()))
				.required(List.of("name", "reason"))
				.build();

		// 全体の構造：上記のオブジェクトの配列(Array)
		Schema rootSchema = Schema.builder()
				.type("ARRAY")
				.items(itemSchema)
				.build();

		// 3. 設定(Config)の作成
		GenerateContentConfig config = GenerateContentConfig.builder()
				.responseMimeType("application/json")
				.responseSchema(rootSchema)
				.build();

		// 4. APIの実行
		try (Client client = new Client()) {
			GenerateContentResponse response = client.models.generateContent(
					"gemini-2.0-flash", // 最新のモデルを指定
					geminiPrompt,
					config);

			return response.text();
		}
	}
}