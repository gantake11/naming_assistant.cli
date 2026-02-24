# Naming Assistant CLI

Gemini APIを活用して、Javaプロジェクトの変数名やクラス名の命名を支援するCLIツールです。
既存のコードから名前を抽出して文脈を学習したり、プロジェクトごとの命名履歴を管理したりすることができます。

## 主な機能

- **AIによる命名提案**: Gemini API (gemini-2.0-flash) を使用して、役割や文脈に応じた最適な名前を3つ提案します。
- **コード解析**: JavaParserを使用して既存のJavaファイルからクラス名、メソッド名、変数名などを抽出し、プロジェクトの文脈として取り込みます。
- **履歴管理**: 採用した名前をプロジェクトごとにJSON形式で保存し、次回以降の提案に活かします。
- **命名規則の遵守**: Java標準の命名規則（PascalCase, camelCase, UPPER_SNAKE_CASEなど）に基づいた提案を行います。

## 必要条件

- Java 21 以上
- Maven
- Google Gemini API キー

## セットアップ

### 1. APIキーの設定

環境変数 `GOOGLE_API_KEY` に Gemini API のキーを設定してください。

```bash
# Windows (Command Prompt)
set GOOGLE_API_KEY=your_api_key_here

# Windows (PowerShell)
$env:GOOGLE_API_KEY="your_api_key_here"

# macOS / Linux
export GOOGLE_API_KEY=your_api_key_here
```

### 2. ビルド

Mavenを使用してプロジェクトをビルドします。

```bash
mvn clean package
```

ビルドが成功すると、`target/naming-assistant-cli-1.0.0-SNAPSHOT.jar` が生成されます。

## 使い方

生成されたJARファイルを実行します。

```bash
java -jar target/naming-assistant-cli-1.0.0-SNAPSHOT.jar
```

実行後は対話型のメニューが表示されます。

1. **プロジェクト名の入力**: 管理用のプロジェクト名を入力します。
2. **メニュー選択**:
   - `1：APIを利用して命名する`: 命名対象（クラス、メソッド、変数等）と処理内容を入力し、AIから提案を受けます。
   - `2：プログラムを読み込み、名前を抽出する`: 既存のJavaファイルのパスを入力して名前を抽出します。
   - `3：このツールのjsonファイルを読み込む`: 以前保存したプロジェクトのデータを読み込みます。
   - `4：終了`: 履歴を保存して終了します。

## データの保存先

プロジェクトの履歴データは、ユーザーホームディレクトリ下の以下の場所に保存されます。
`~/.naming-assistant/[プロジェクト名].json`

## 技術スタック

- **Language**: Java 21
- **AI SDK**: Google GenAI SDK
- **JSON Library**: Jackson
- **Parser**: JavaParser

## ライセンス

[MIT License](LICENSE) (またはプロジェクトに適したライセンスを記載してください)
