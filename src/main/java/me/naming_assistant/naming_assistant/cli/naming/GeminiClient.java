package me.naming_assistant.naming_assistant.cli.naming;

import java.util.List;
import java.util.Map;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Schema;

public class GeminiClient {

    public String geminiAPI(String target, String content) {
        String targetLanguage = "Java"; // ファイル拡張子から判定（仮）

        // 1. プロンプトの作成
        // JSONの形式指示は削除し、命名のロジックのみを記述します
        String geminiPrompt = String.format("""
                あなたは「プログラミング命名専門の実行エージェント」です。
                以下の情報に基づき、最適な名前を3つ選定してください。
                理由は日本語で教えてください。
                
                【情報】
                対象言語: %s
                命名対象: %s
                処理内容: %s
                
                【制約】
                ・Javaの標準的な規約（クラスならPascalCase、変数ならcamelCase）に従うこと。
                ・出力は指定されたJSONスキーマに従うこと。
                """,
                targetLanguage,
                target,
                content);

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
                                        .build()
                        )
                )
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
                    config
            );

            return response.text();
        }
    }
}