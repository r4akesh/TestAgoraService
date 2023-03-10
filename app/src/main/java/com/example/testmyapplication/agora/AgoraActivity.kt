package com.example.testmyapplication.agora

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.testmyapplication.R
import com.example.testmyapplication.databinding.ActivityAgoraBinding
import io.agora.rtc.Constants
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas
import io.agora.rtc.video.VideoEncoderConfiguration


@Suppress("DEPRECATION")
class AgoraActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityAgoraBinding

    private val PERMISSION_REQUEST_ID = 7

    // Ask for Android device permissions at runtime.
    private val ALL_REQUESTED_PERMISSIONS = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CAMERA,
        Manifest.permission.READ_PHONE_STATE
    )

    private var mEndCall = false
    private var mMuted = false
    private var remoteView: SurfaceView? = null
    private var localView: SurfaceView? = null
    private lateinit var rtcEngine: RtcEngine
    var TAG= "AgoraActivityRtcEngine"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgoraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonCall.setOnClickListener(this)
        binding.buttonMute.setOnClickListener(this)
        binding.buttonSwitchCamera.setOnClickListener(this)

        // If all the permissions are granted, initialize the RtcEngine object and join a channel.
        if (checkSelfPermission(ALL_REQUESTED_PERMISSIONS[0], PERMISSION_REQUEST_ID) &&
            checkSelfPermission(ALL_REQUESTED_PERMISSIONS[1], PERMISSION_REQUEST_ID
            ) && checkSelfPermission(ALL_REQUESTED_PERMISSIONS[2], PERMISSION_REQUEST_ID)
        ) {
            initAndJoinChannel()
        }
    }
    private fun initAndJoinChannel() {
        // This is our usual steps for joining
        // a channel and starting a call.
        initRtcEngine()
        setupVideoConfig()
        setupLocalVideoView()
        joinChannel()
    }

    // Initialize the RtcEngine object.
    private fun initRtcEngine() {
        try {
            rtcEngine = RtcEngine.create(baseContext, getString(R.string.app_id), mRtcEventHandler)
        } catch (e: Exception) {
            Log.d(TAG, "initRtcEngine: $e")
        }
    }

    private val mRtcEventHandler = object : IRtcEngineEventHandler() {
        override fun onRemoteVideoStateChanged(uid: Int, state: Int, reason: Int, elapsed: Int) {
            Log.d(TAG, "onRemoteVideoStateChanged: $uid")
        }

        override fun onUserJoined(uid: Int, elapsed: Int) {
            Log.d(TAG, "onUserJoined: $uid")
            runOnUiThread {
               // setupRemoteVideoView(uid)
                Log.d(TAG, "onFirstRemoteVideoDecoded: $uid")
            }
        }



        override fun onError(err: Int) {
            Log.d(TAG, "onError $err")
        }

        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
            runOnUiThread {
                Toast.makeText(applicationContext, "Joined Channel Successfully", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onJoinChannelSuccess: $uid")

            }
        }

        /*
         * Listen for the onFirstRemoteVideoDecoded callback.
         * This callback occurs when the first video frame of a remote user is received and decoded after the remote user successfully joins the channel.
         * You can call the setupRemoteVideoView method in this callback to set up the remote video view.
         */
        override fun onFirstRemoteVideoDecoded(uid: Int, width: Int, height: Int, elapsed: Int) {
            runOnUiThread {
                setupRemoteVideoView(uid)
                Log.d(TAG, "onFirstRemoteVideoDecoded: $uid")
            }
        }

        /*
        * Listen for the onUserOffline callback.
        * This callback occurs when the remote user leaves the channel or drops offline.
        */
        override fun onUserOffline(uid: Int, reason: Int) {
            runOnUiThread {
                onRemoteUserLeft()

                Log.d(TAG, "onUserOffline: $uid")
            }
        }
    }

    private fun setupRemoteVideoView(uid: Int) {

        if (binding.remoteVideoView.childCount > 1) {
            return
        }
        remoteView = RtcEngine.CreateRendererView(baseContext)
        binding.remoteVideoView.addView(remoteView)

        rtcEngine.setupRemoteVideo(VideoCanvas(remoteView, VideoCanvas.RENDER_MODE_HIDDEN, uid))
    }

    private fun setupLocalVideoView() {

        localView = RtcEngine.CreateRendererView(baseContext)
        localView!!.setZOrderMediaOverlay(true)
        binding.localVideoView.addView(localView)

        // Set the local video view.
        rtcEngine.setupLocalVideo(VideoCanvas(localView, VideoCanvas.RENDER_MODE_FIT, 0))

    }

    private fun checkSelfPermission(permission: String, requestCode: Int): Boolean {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, ALL_REQUESTED_PERMISSIONS, requestCode)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_ID) {
            if (
                grantResults[0] != PackageManager.PERMISSION_GRANTED ||
                grantResults[1] != PackageManager.PERMISSION_GRANTED ||
                grantResults[2] != PackageManager.PERMISSION_GRANTED
            ) {

                Toast.makeText(applicationContext, "Permissions needed", Toast.LENGTH_LONG).show()
                finish()
                return
            }
            // Here we continue only if all permissions are granted.
            // The permissions can also be granted in the system settings manually.
            initAndJoinChannel()
        }
    }

    private fun onRemoteUserLeft() {
        removeRemoteVideo()
    }

    private fun removeRemoteVideo() {
        if (remoteView != null) {
            binding.remoteVideoView.removeView(remoteView)
        }
        remoteView = null
    }

    private fun setupVideoConfig() {

        rtcEngine.enableVideo()

        rtcEngine.setVideoEncoderConfiguration(
            VideoEncoderConfiguration(
                VideoEncoderConfiguration.VD_640x360,
                VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15,
                VideoEncoderConfiguration.STANDARD_BITRATE,
                VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT
            )
        )
    }
    private fun joinChannel() {
        val token = getString(R.string.agora_token)
        val chnalName = getString(R.string.channelName)
        // Join a channel with a token.

        //for audio call
       // rtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION);

        rtcEngine.joinChannel(token,chnalName, "Extra Optional Data", 0)
    }

    override fun onClick(v: View?) {
         when(v){
             binding.buttonSwitchCamera->{
                 try {
                     rtcEngine.switchCamera()
                 } catch (e: Exception) {
                     Log.d(TAG, "switchCamera>>$e")
                 }
             }

             binding.buttonMute->{
                 mMuted = !mMuted
                 rtcEngine.muteLocalAudioStream(mMuted)
                 val res: Int = if (mMuted) {
                     R.drawable.btn_mute
                 } else {
                     R.drawable.btn_unmute
                 }

                 binding.buttonMute.setImageResource(res)
             }
             binding.buttonCall->{
                 if (mEndCall) {
                     startCall()
                     mEndCall = false
                     binding.buttonCall.setImageResource(R.drawable.btn_endcall)
                     binding.buttonMute.visibility = VISIBLE
                     binding.buttonSwitchCamera.visibility = VISIBLE
                 } else {
                     endCall()
                     mEndCall = true
                     binding.buttonCall.setImageResource(R.drawable.btn_startcall)
                     binding.buttonMute.visibility = INVISIBLE
                     binding.buttonSwitchCamera.visibility = INVISIBLE
                 }
             }
         }
    }

    private fun startCall() {
       setupLocalVideoView()
        joinChannel()
    }

    private fun endCall() {
        removeLocalVideo()
        removeRemoteVideo()
        leaveChannel()
    }

    private fun removeLocalVideo() {
        if (localView != null) {
            binding.localVideoView.removeView(localView)
        }
        localView = null
    }

    private fun leaveChannel() {
        rtcEngine.leaveChannel()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!mEndCall) {
            leaveChannel()
        }
        RtcEngine.destroy()
    }
}