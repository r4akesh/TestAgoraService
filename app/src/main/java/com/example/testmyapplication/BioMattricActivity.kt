package com.example.testmyapplication

import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.testmyapplication.extratask.UserInfo
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

class BioMattricActivity : AppCompatActivity() {
    private var cancellationSignal: CancellationSignal? = null

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkBiometricSupport()

        //getData
        var list = ArrayList<UserInfo>();

        list = intent.getSerializableExtra("DATASEND") as ArrayList<UserInfo>



        Log.d("HomeExtraActivity22222222", "onCreate: " + list.toString())


        findViewById<Button>(R.id.start_authentication).setOnClickListener {
            // This creates a dialog of biometric auth and
            // it requires title , subtitle ,
            // and description
            // In our case there is a cancel button by
            // clicking it, it will cancel the process of
            // fingerprint authentication
            val biometricPrompt = BiometricPrompt.Builder(this)
                .setTitle("Title of Prompt")
                .setSubtitle("Subtitle")
                .setDescription("Uses FP")
                .setNegativeButton(
                    "Cancel",
                    this.mainExecutor,
                    DialogInterface.OnClickListener { dialog, which ->
                        notifyUser("Authentication Cancelled")
                    }).build()

            // start the authenticationCallback in mainExecutor
            biometricPrompt.authenticate(
                getCancellationSignal(),
                mainExecutor,
                authenticationCallback
            )
        }

        //hashmap  store 0ne null key value
        var map = HashMap<String, Any>()
        map["1"] = 1
        map["2"] = 2
        map["3"] = 3
        map["4"] = 4

        Log.d("TAG_onCreate", "onCreate: $map")
        Log.d("TAG_onCreate", "onCreate2: $map")
        var a = 8
        var b = 10
        if (8 > 10) Log.d("TAG", "onCreate: ")
        else Log.d("TAG", "onCreate: ")

        runBlocking {
            supervisorScope {

            }
        }



}




    // it will be called when authentication is cancelled by the user
private fun getCancellationSignal(): CancellationSignal {
    cancellationSignal = CancellationSignal()
    cancellationSignal?.setOnCancelListener {
        notifyUser("Authentication was Cancelled by the user")
    }
    return cancellationSignal as CancellationSignal
}


// it checks whether the app the app has fingerprint permission
@RequiresApi(Build.VERSION_CODES.M)
private fun checkBiometricSupport(): Boolean {
    val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
    if (!keyguardManager.isDeviceSecure) {
        notifyUser("Fingerprint authentication has not been enabled in settings")
        return false
    }
    if (ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.USE_BIOMETRIC
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        notifyUser("Fingerprint Authentication Permission is not enabled")
        return false
    }
    return if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
        true
    } else true
}

private fun notifyUser(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

// create an authenticationCallback
private val authenticationCallback: BiometricPrompt.AuthenticationCallback
    get() = @RequiresApi(Build.VERSION_CODES.P)
    object : BiometricPrompt.AuthenticationCallback() {
        // here we need to implement two methods
        // onAuthenticationError and onAuthenticationSucceeded
        // If the fingerprint is not recognized by the app it will call
        // onAuthenticationError and show a toast
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
            super.onAuthenticationError(errorCode, errString)
            notifyUser("Authentication Error : $errString")
        }

        // If the fingerprint is recognized by the app then it will call
        // onAuthenticationSucceeded and show a toast that Authentication has Succeed
        // Here you can also start a new activity after that
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
            super.onAuthenticationSucceeded(result)
            notifyUser("Authentication Succeeded")

            // or start a new Activity

        }
    }

override fun onResume() {
    super.onResume()
    Log.d("BioMattricActivityTAG", "onResume: ")
}

override fun onStart() {
    super.onStart()
    Log.d("BioMattricActivityTAG", "onStart: ")
}

override fun onPause() {
    super.onPause()
    Log.d("BioMattricActivityTAG", "onPause: ")
}

override fun onStop() {
    super.onStop()
    Log.d("BioMattricActivityTAG", "onStop: ")
}

override fun onRestart() {
    super.onRestart()
    Log.d("BioMattricActivityTAG", "onRestart: ")
}

override fun onDestroy() {
    super.onDestroy()
    Log.d("BioMattricActivityTAG", "onDestroy: ")
}
}