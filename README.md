# ReceiveContentSample
sample for unified-content-api(Android 12 +)

# コンテンツ受信用のUnified APIについて

既存APIでは、アプリにコンテンツを挿入するために「クリップボード、キーボード、ドラッグ&ドロップ」などのアクションごとに呼び出すAPIが異なっていました。<br>
Android 12から [OnReceiveContentListener](https://developer.android.com/reference/android/view/OnReceiveContentListener) インターフェースが追加されたので、<br>
このインターフェースを使えば、さまざまなアクションでのコンテンツが挿入されたイベントをフックできます。<br>
[リンク１](https://developer.android.com/about/versions/12/features/unified-content-api#overview)

# 使用ライブラリ
[androidx.core:core-ktx:1.5.0-beta03](https://developer.android.com/jetpack/androidx/releases/core#1.5.0-beta03)<br>
[androidx.appcompat:appcompat:1.3.0-beta01](https://developer.android.com/jetpack/androidx/releases/appcompat#1.3.0-beta01)<br>

# 動作確認(キャプチャ)

## Pixcel 4 OS 12 preview

| Insert using Paste<br> from long-press menu | Insert using drag and drop (image) | Insert using drag and drop (video) | insert a keyboard image |
----|---- |----|----
|  | <img src="https://user-images.githubusercontent.com/16476224/118485167-0b9ded80-b753-11eb-8e80-9b22ce20f112.gif" width=320 /> |   | <img src="https://user-images.githubusercontent.com/16476224/118485561-91219d80-b753-11eb-852e-9ede2e512827.gif" width=320 /> |
