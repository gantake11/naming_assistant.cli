package me.naming_assistant.naming_assistant.cli.paser;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import me.naming_assistant.naming_assistant.cli.NamingToolApp;
import me.naming_assistant.naming_assistant.cli.json.InputName;

// 自分専用のビジター
public class SimpleVisitor extends VoidVisitorAdapter<Void> {

	// ① パッケージ名の抽出
	@Override
	public void visit(PackageDeclaration n, Void arg) {
		System.out.println("パッケージ名: " + n.getNameAsString());
		InputName.inputPackageName(n.getNameAsString());
		super.visit(n, arg);
	}

	// ② クラス/インタフェース名の抽出と区別
	@Override
	public void visit(ClassOrInterfaceDeclaration n, Void arg) {

		// isInterface() が true ならインタフェース、false ならクラス
		if (n.isInterface()) {
			System.out.println("インタフェース名: " + n.getNameAsString());
			NamingToolApp.nc.getHistories().getInterfaceList().add(InputName.gettHistoryData(n.getNameAsString()));
		} else {
			System.out.println("クラス名: " + n.getNameAsString());
			NamingToolApp.nc.getHistories().getClassList().add(InputName.gettHistoryData(n.getNameAsString()));
		}

		super.visit(n, arg);
	}

	// ③ メソッド名の抽出
	@Override
	public void visit(MethodDeclaration n, Void arg) {
		System.out.println("メソッド名: " + n.getNameAsString());
		NamingToolApp.nc.getHistories().getMethodList().add(InputName.gettHistoryData(n.getNameAsString()));
		super.visit(n, arg);
	}

	// ④ 変数名・定数名の抽出
	@Override
	public void visit(VariableDeclarator n, Void arg) {

		// 親ノードを取得（念のため存在チェック）
		if (n.getParentNode().isPresent()) {
			Object parent = n.getParentNode().get();

			// パターンA：クラスのメンバ変数（フィールド）の場合
			if (parent instanceof FieldDeclaration) {
				FieldDeclaration field = (FieldDeclaration) parent;
				if (field.isStatic() && field.isFinal()) {
					System.out.println("定数（メンバ）: " + n.getNameAsString());
					NamingToolApp.nc.getHistories().getConstantList().add(InputName.gettHistoryData(n.getNameAsString()));
				} else {
					System.out.println("変数（メンバ）: " + n.getNameAsString());
					NamingToolApp.nc.getHistories().getVariableList().add(InputName.gettHistoryData(n.getNameAsString()));
				}
			}
			// パターンB：メソッド内のローカル変数の場合
			else if (parent instanceof VariableDeclarationExpr) {
				VariableDeclarationExpr varExpr = (VariableDeclarationExpr) parent;

				// final がついていれば「ローカル定数」
				if (varExpr.isFinal()) {
					System.out.println("定数（ローカル）: " + n.getNameAsString());
					NamingToolApp.nc.getHistories().getConstantList().add(InputName.gettHistoryData(n.getNameAsString()));
				} else {
					System.out.println("変数（ローカル）: " + n.getNameAsString());
					NamingToolApp.nc.getHistories().getVariableList().add(InputName.gettHistoryData(n.getNameAsString()));
				}
			}
		}

		super.visit(n, arg);
	}
}