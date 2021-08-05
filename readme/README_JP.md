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

現在、androidxの下位互換APIを使うのに以下の制約がある
- `AppCompatEditText` にのみ有効。将来的には他のViewでもコンテンツ挿入機能を有効にすることを検討している<br>
[ref](https://youtu.be/D2cU_itNDAI?t=1602)

# 動作確認(キャプチャ)

動作確認には、以下の外部アプリを使用しました。
- Google Chrome (プリイン)
- File Exploler (プリイン)

## Pixcel 4 OS 12 preview

| Insert using Paste<br> from long-press menu | Insert using drag and drop (image) | Insert using drag and drop (video) | insert a keyboard image |
----|---- |----|----
| <img src="https://user-images.githubusercontent.com/16476224/118488454-b663db00-b756-11eb-8774-5b4383a3872e.gif" width=320 /> | <img src="https://user-images.githubusercontent.com/16476224/118488449-b532ae00-b756-11eb-863b-fec4e3956f4a.gif" width=320 /> | <img src="https://user-images.githubusercontent.com/16476224/118488425-aea43680-b756-11eb-84cf-73fed8f544a4.gif" width=320 />  | <img src="https://user-images.githubusercontent.com/16476224/118485561-91219d80-b753-11eb-852e-9ede2e512827.gif" width=320 /> |
