# partner-java-sdk-client-demo

[partner-java-sdk](https://github.com/pokepay/partner-java-sdk)をgradle経由で使う簡単なクライアントです

# Usage

```
$ ./gradlew build
$ ./gradlew run
```

# partner-java-sdkの利用方法について

事前にpartner-java-sdkのjarを用意する

```
$ cd ~/pokepay/partner-java-sdk
$ ./gradlew jar
```

これでpartner-java-sdkのbuild/libsにpartner-java-sdk-[VERSION].jarが作られる

gradleを使ってクライアントを作る
```
$ mkdir partner-java-sdk-client-demo
$ cd partner-java-sdk-client-demo
$ gradle init --type java-application
```

partner-java-sdkのjarをそのクライアント内のディレクトリに配置する
```
$ mkdir lib
$ cp ~/pokepay/partner-java-sdk/build/libs/partner-java-sdk-[VERSION].jar lib/
```

app/build.gradleに依存するライブラリの情報を追加
build.gradleのdependenciesに下記の様にgsonとjarを追加する

gsonを追加するのはpartner-java-sdkで依存しているから。これがないと実行時にgsonが見つからずエラーになる

```diff
    // This dependency is used by the application.
    implementation 'com.google.guava:guava:31.1-jre'

+    implementation 'com.google.code.gson:gson:2.8.5'
+    implementation files('lib/partner-java-sdk-0.1.9.jar')

}

// Apply a specific Java toolchain to ease working on different environments.
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
