# 命名支援ツール開発

## 流れ
1. 今回のプロジェクト名の入力
    1. 出力するjsonファイルにプロジェクト名を付ける
2. やりたいことを選ぶ
    1. 命名する
        1. 使用言語を選ぶ(最初はJava固定)
        2. 命名対象を選択する
        3. 処理内容の入力
        4. APIにプロンプトの送信
        5. 受け取った回答から表示する
            - 名前を選ぶ
                1. 選んだ名前をこのツールのjsonの形に入れる
                    - 先に既存の名前を抽出している場合、それに追加する
                    - 新規の場合新しくjsonを作る
            - プロンプトを再度送る
    2. プログラムから既存の名前を抽出する
        1. プログラムのパスを入力する
        2. 名前を抽出する
        3. このツールのjsonの形に入れる
            - 先に既存のjsonファイルがあるならそれに追加する
            - 無いなら新しくjsonを作る
        4. 作ったjsonをもとに既存の名前を一覧表示する
    3. このツールで作ったjsonファイルを読み込み、既存の名前を読み込む
        1. jsonファイルのパスを入力する
        2. jsonファイルを読み込む
            - 先に同じプログラムの既存のjsonがあるならそれに追加する
        3. jsonファイルをもとに既存の名前を一覧表示する
3. 最終的なjsonファイルを出力する
4. 継続するか確認する
    - 継続するなら1.に戻る
    - 終了

## Java 命名規則一覧 (Java Naming Conventions)

| 対象 (Target) | 命名規則 (Convention) | 例 (Example) |
| :--- | :--- | :--- |
| **Package (パッケージ)** | all lowercase (separated by dots) | `com.example.project` |
| **Module (モジュール)** | all lowercase (separated by dots) | `java.base`, `com.myapp.network` |
| **Class (クラス)** | **PascalCase** | `UserSession`, `PaymentManager` |
| **Interface (インターフェース)** | **PascalCase** | `Serializable`, `Renderable` |
| **Record (レコード)** | **PascalCase** | `UserRecord`, `Point` |
| **Enum (列挙型)** | **PascalCase** | `Color`, `HttpStatus` |
| **Enum Constant (列挙定数)** | **UPPER_SNAKE_CASE** | `RED`, `BLUE`, `NOT_FOUND` |
| **Constant (定数 / static final)** | **UPPER_SNAKE_CASE** | `MAX_RETRY_COUNT`, `TIMEOUT` |
| **Method (メソッド)** | **camelCase** | `getUserName`, `saveData` |
| **Variable / Field (変数 / フィールド)** | **camelCase** | `totalPrice`, `isAvailable` |
| **Parameter (引数)** | **camelCase** | `(int width, int height)` |
| **Type Parameter (型パラメータ)** | Single uppercase letter | `T`, `E`, `K`, `V` |

### 使用されている命名規則の説明

* **PascalCase**: 各単語の先頭を大文字にする (例: `MyClass`)。
* **camelCase**: 最初の単語を小文字、以降の単語の先頭を大文字にする (例: `myVariable`)。
* **UPPER_SNAKE_CASE**: 全て大文字で単語間を `_` で繋ぐ (例: `MAX_VALUE`)。