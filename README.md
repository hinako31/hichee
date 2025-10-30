
# 🧀Hi Cheese Diary🧀

**チーズケーキの日記登録管理 JSP/Servlet アプリケーション**

## 🧀 概要

このアプリケーションは、**チーズケーキ巡りが好きで記録したい人**に作られた Web アプリです。  
**簡単なフォームになっているので**食べてすぐに記録ができて、登録した日記は **検索が可能**なので、
**思い出に浸れます。**

## 🧀 主な機能
- 新規登録/退会機能
- ログイン/ログアウト機能
- チーズケーキの記録・変更・削除機能
- 記録したチーズケーキの検索・閲覧機能

---

## 🧀 開発環境

| 項目 | 使用技術 |
|------|------------|
| 言語 | Java 21 |
| フレームワーク | JSP / Servlet |
| IDE | Eclipse |
| Web サーバ | Apache Tomcat |
| データベース | MySQL |
| フロントエンド | HTML / CSS / JavaScript |

---



DB接続情報は `DBManager.java` 内で設定します。

```java
private static final String URL = "jdbc:mysql://localhost:3306/cook_memo";
private static final String USER = "root";
private static final String PASSWORD = "（あなたのパスワード）";
```

---

## 🧀 起動方法

1. プロジェクトを Eclipse にインポート
   （File → Import → Existing Projects into Workspace）
2. Tomcat サーバにデプロイ
3. MySQL サーバを起動し、上記 SQL を実行
4. ブラウザで以下にアクセス：

```
http://localhost:8080/hichee/Main
```

---

## 🧀 画面構成

| 画面                                    | 説明                                                  |
| ------------------------               | --------------------                                  |
| **トップページ**                        | ログイン画面                                           |
| **会員登録ページ**                      | 会員登録                                               |
| **マイページ**                          | ログイン後のメニュー(日記登録/日記検索/アカウント管理) 表示|
| **アカウント管理ページ**                 | 登録情報変更/ログアウト/退会メニュー表示                  |
| **日記登録ページ(NewCheeseDiary)**       | 新規日記登録                                           |
| **日記検索ページ(MyCheeseDiary)**        | 登録日記の検索/詳細                                     |
| **日記変更ページ(ChangeCheeseDiary)**    | 登録日記の内容変更                                      |
| **日記削除ページ(ByeCheeseDiary)**       | 登録日記の削除                                          |

---

## 🧀 使用ライブラリ

* **jakarta.servlet.jsp.jstl-3.0.1.jar**
* **jakarta.servlet.jsp.jstl-api-3.0.0.jar**
* **mysql-connector-java-8.0.23.jar**
* **json-20250517.jar**

---

## 🧀 今後の改善アイデア

* 他ユーザーのレビュー閲覧機能
* My Cheese Diaryの閲覧時の画像表示
* 各場所での日記数の比較
* レスポンシブ対応

---

## 🧀 作者情報

**Author:** hinako31
**Language:** Japanese / 日本語


---

## 🧀 ライセンス

本プロジェクトは学習・個人利用目的で自由に利用できます。
商用利用の場合は作者への連絡を推奨します。


