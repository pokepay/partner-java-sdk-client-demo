# partner-java-sdk-client-demo

partner-java-sdkをgradle経由で使う簡単なクライアントです

## 参考
https://confluence.coilinc.jp/pages/viewpage.action?pageId=29032824

# partner-java-sdkの利用方法について

事前にpartner-java-sdkのjarを用意する

```
$ cd ~/pokepay/partner-java-sdk
$ gradle jar
```

これでpartner-java-sdkのbuild/libsにpartner-java-sdk-[VERSION]-SNAPSHOT.jarが作られる

gradleを使ってクライアントを作る
```
$ mkdir partner-java-sdk-client-demo
$ cd partner-java-sdk-client-demo
$ gradle init --type java-application
```

partner-java-sdkのjarをそのクライアント内のディレクトリに配置する
```
$ mkdir lib
$ cp ~/pokepay/partner-java-sdk/build/libs/partner-java-sdk-1.0-SNAPSHOT.jar lib/
```

build.gradleに依存するライブラリの情報を追加
build.gradleのdependenciesに下記の用にgsonとjarを追加する

gsonを追加するのはpartner-java-sdkで依存しているから、これがないと実行時にgsonが見つからずエラーになる

```
diff --git a/build.gradle b/build.gradle
```

```diff
index 4686707..f771134 100644
 
--- a/build.gradle
 
+++ b/build.gradle
 
@@ -25,6 +25,9 @@ dependencies {
 
 
// Use JUnit test framework
 
testCompile 'junit:junit:4.12'
 
+
 
+ implementation 'com.google.code.gson:gson:2.8.5'
 
+ compile files('lib/partner-java-sdk-1.0-SNAPSHOT.jar')
 
}
 
 
// Define the main class for the application
```

# config.propertiesを用意する

ここでは~/.pokepay/config.propertiesに配置する

テンプレートはpartner-java-sdkのsample.propertiesにあるのでコピーして使う

```
$ cp ~/pokepay/partner-java-sdk/sample.properties ~/.pokepay/config.properties
```

config.propertiesに適切な値を設定する

```ini
CLIENT_ID  partner_clientのuuid
CLIENT_SECRET partner_clientのclient_secret
API_BASE_URL ベースURL
PKCS12_FILE pkcs12ファイルの場所を指定
PKCS12_PASSWORD pkcs12ファイルのパスワード
```

pkcs12ファイルはkey.pemとcert.pemから以下のようにopensslのコマンドを使って作る

```
$ openssl pkcs12 -export -in client.cert.pem -inkey client.key.pem -out client.p12
```

ここでパスワードを設定し、PKCS12_PASSWORDにも同じものを指定する
