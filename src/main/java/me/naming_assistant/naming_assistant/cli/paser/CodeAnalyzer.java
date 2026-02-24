package me.naming_assistant.naming_assistant.cli.paser;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.javaparser.ParserConfiguration; // ← 追加
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class CodeAnalyzer {
    public  void analyse(String path) {
        try {
            // 解析したいJavaファイルのパスを指定します
            Path filePath = Paths.get(path); 

            // ★ ここでJavaParserの言語レベルをJava 17（または必要なバージョン）に設定する
            StaticJavaParser.getParserConfiguration().setLanguageLevel(ParserConfiguration.LanguageLevel.JAVA_18);

            // ファイルをパースして AST を取得
            CompilationUnit cu = StaticJavaParser.parse(filePath);

            System.out.println("--- 解析結果 ---");
            // 作成したビジターに AST を渡して探索スタート
            cu.accept(new SimpleVisitor(), null);

        } catch (Exception e) {
        		System.out.println("正しいパスを入力してください");
        }
    }
}
