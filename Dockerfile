# =========================================
# 1. Spring Bootをビルドする環境
# =========================================

FROM maven:3.9-eclipse-temurin-17 AS build

# Docker内の作業場所
WORKDIR /app

# 最初にpom.xmlだけコピー
COPY pom.xml .

# 依存ライブラリを先にダウンロード
# ソースコードだけ変更した場合にキャッシュを利用しやすくする
RUN mvn -B dependency:go-offline

# Javaソースをコピー
COPY src ./src

# Spring BootをJARファイルへ変換
# デプロイ用ビルドではテスト実行を省略
RUN mvn -B clean package -DskipTests


# =========================================
# 2. Spring Bootを実行する環境
# =========================================

FROM eclipse-temurin:17-jre

WORKDIR /app

# ビルド環境で作成したJARをコピー
COPY --from=build /app/target/portfolio-api-*.jar app.jar

# Renderの標準ポート
EXPOSE 10000

# Spring Bootを起動
ENTRYPOINT ["java", "-jar", "/app/app.jar"]