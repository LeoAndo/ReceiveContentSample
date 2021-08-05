# ReceiveContentSample
sample for unified-content-api(Android 12 +)

[日本語版README](https://github.com/LeoAndo/ReceiveContentSample/blob/main/readme/README_JP.md)

# about unified-content-api
With the existing API, the API to call for each action such as "clipboard, keyboard, drag and drop" to insert content into the app was different.<br>
Since Android 12 added the [OnReceiveContentListener](https://developer.android.com/reference/android/view/OnReceiveContentListener) interface, <br>
This interface allows you to hook events with content inserted in various actions.<br>
[link1](https://developer.android.com/about/versions/12/features/unified-content-api#overview)

# use libraries
[androidx.core:core-ktx:1.5.0-beta03](https://developer.android.com/jetpack/androidx/releases/core#1.5.0-beta03)<br>
[androidx.appcompat:appcompat:1.3.0-beta01](https://developer.android.com/jetpack/androidx/releases/appcompat#1.3.0-beta01)<br>

Currently, there are the following restrictions to use the backward compatible API of androidx.<br>
- Valid only for `AppCompatEditText`. We are considering enabling the content insertion function in other views in the future. <br>
[ref](https://youtu.be/D2cU_itNDAI?t=1602)

# capture

The following external application was used to check the operation.
- Google Chrome
- File Exploler

## Pixcel 4 OS 12 preview

| Insert using Paste<br> from long-press menu | Insert using drag and drop (image) | Insert using drag and drop (video) | insert a keyboard image |
----|---- |----|----
| <img src="https://user-images.githubusercontent.com/16476224/118488454-b663db00-b756-11eb-8774-5b4383a3872e.gif" width=320 /> | <img src="https://user-images.githubusercontent.com/16476224/118488449-b532ae00-b756-11eb-863b-fec4e3956f4a.gif" width=320 /> | <img src="https://user-images.githubusercontent.com/16476224/118488425-aea43680-b756-11eb-84cf-73fed8f544a4.gif" width=320 />  | <img src="https://user-images.githubusercontent.com/16476224/118485561-91219d80-b753-11eb-852e-9ede2e512827.gif" width=320 /> |
