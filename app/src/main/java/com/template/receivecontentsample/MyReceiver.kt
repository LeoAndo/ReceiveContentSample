package com.template.receivecontentsample

import android.net.Uri
import android.util.Log
import android.util.Pair
import android.view.View
import android.webkit.MimeTypeMap
import androidx.core.content.MimeTypeFilter
import androidx.core.view.ContentInfoCompat
import androidx.core.view.OnReceiveContentListener

class MyReceiver(val contentInfo: (uri: Uri, clipMimeType: ClipMimeType?) -> Unit) :
    OnReceiveContentListener {
    companion object {
        val MIME_TYPES: Array<String> = arrayOf("image/*", "video/*")
    }

    override fun onReceiveContent(view: View, contentInfo: ContentInfoCompat): ContentInfoCompat? {
        Log.d("MyReceiver", "view $view, contentInfo: $contentInfo")
        val split: Pair<ContentInfoCompat, ContentInfoCompat> =
            contentInfo.partition { it.uri != null }
        val uriContent = split.first
        val remaining = split.second
        Log.d("MyReceiver", "split: $split, uriContent: $uriContent, remaining: $remaining")
        uriContent?.run {
            for (i in 0 until clip.itemCount) {
                val uri = clip.getItemAt(i).uri
                // URIを処理するアプリ固有のロジックをここに記述する.
                Log.d("MyReceiver", "uri: $uri")

                // MIME_TYPEを取得
                val clipMimeType: ClipMimeType? = view.context.contentResolver.run {
                    val mimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(getType(uri))
                    Log.d("MyReceiver", "getType: ${getType(uri)}") // getType: image/png
                    Log.d("MyReceiver", "mimeType: $mimeType")// mimeType: png
                    ClipMimeType.find(MimeTypeFilter.matches(getType(uri), MIME_TYPES))
                }

                // insert using Paste from long-press menu: source=SOURCE_CLIPBOARD
                // D/MyReceiver: uri: content://com.android.chrome.FileProvider/images/screenshot/16212234876413094417636868582894.jpg

                // insert using drag and drop: source=SOURCE_DRAG_AND_DROP
                // D/MyReceiver: uri: content://com.android.providers.downloads.documents/document/msf%3A33

                // insert a keyboard image: source=SOURCE_INPUT_METHOD
                // D/MyReceiver: uri: content://com.google.android.inputmethod.latin.fileprovider/clipboard_image/1621225546274.png
                contentInfo(uri, clipMimeType)
            }
        }
        return remaining
    }

    enum class ClipMimeType(private val mimetype: String) {
        IMAGE("image/*"), VIDEO("video/*");

        companion object {
            fun find(mimetype: String?): ClipMimeType? {
                return values().firstOrNull { it.mimetype == mimetype }
            }
        }
    }
}