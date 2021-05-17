package com.template.receivecontentsample

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat

class MainActivity : AppCompatActivity() {
    private var clipMimeType: MyReceiver.ClipMimeType? = null
    private var uri: Uri? = null
    private lateinit var videoView: VideoView
    private lateinit var imageView: ImageView
    private lateinit var editText: AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        videoView = findViewById(R.id.videoView)
        videoView.setOnPreparedListener { mediaPlayer ->
            setupVideoViewScaling(mediaPlayer)
        }
        videoView.setOnCompletionListener {
            showFullScreenVideoView(false)
            Toast.makeText(this@MainActivity, "video finish!!", Toast.LENGTH_SHORT).show()
        }
        imageView = findViewById(R.id.imageView)
        editText = findViewById(R.id.editText1)
        ViewCompat.setOnReceiveContentListener(
            editText,
            MyReceiver.MIME_TYPES,
            MyReceiver(contentInfo = { uri, clipMimeType ->
                handleClipContentInfo(uri, clipMimeType)
            })
        )
    }

    private fun handleClipContentInfo(uri: Uri, clipMimeType: MyReceiver.ClipMimeType?) {
        this.uri = uri
        this.clipMimeType = clipMimeType
        when (clipMimeType) {
            MyReceiver.ClipMimeType.IMAGE -> {
                // handling image uri
                kotlin.runCatching {
                    contentResolver.openInputStream(uri)?.use {
                        val bitmap = BitmapFactory.decodeStream(it)
                        imageView.setImageBitmap(bitmap)
                    }
                }.onFailure {
                    Log.e("MainActivity", "error: ${it}")
                }
            }
            MyReceiver.ClipMimeType.VIDEO -> {
                // handling video uri
                videoView.setVideoURI(uri)
                showFullScreenVideoView(true)
                videoView.start()
            }
        }
    }

    private fun setupVideoViewScaling(mediaPlayer: MediaPlayer) {
        val videoRatio = mediaPlayer.videoWidth / mediaPlayer.videoHeight.toFloat()
        val screenRatio = videoView.width / videoView.height.toFloat()
        val scaleX = videoRatio / screenRatio
        if (scaleX >= 1f) {
            videoView.scaleX = scaleX
        } else {
            videoView.scaleY = 1f / scaleX
        }
    }

    private fun showFullScreenVideoView(isFullScreen: Boolean) {
        videoView.visibility = if(isFullScreen) View.VISIBLE else View.GONE
        imageView.visibility = if(isFullScreen) View.GONE else View.VISIBLE
        editText.visibility = if(isFullScreen) View.GONE else View.VISIBLE
    }
}