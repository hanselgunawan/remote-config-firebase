package com.example.remoteconfigfirebase

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

object RemoteConfigUtils {

    private const val TAG = "RemoteConfigUtils"

    private const val HELLO_WORLD_TEXT = "hello_world_text"

    private val DEFAULTS: HashMap<String, Any> =
        hashMapOf(
            HELLO_WORLD_TEXT to "Hello World!"
        )

    @SuppressLint("StaticFieldLeak")
    private lateinit var remoteConfig: FirebaseRemoteConfig

    fun init() {
        remoteConfig = getFirebaseRemoteConfig()
    }

    private fun getFirebaseRemoteConfig(): FirebaseRemoteConfig {
        val remoteConfig = Firebase.remoteConfig

        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) {
                0 // kept 0 for a quick debug purpose
            } else {
                60 * 60 // based on requirement
            }
        }

        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(DEFAULTS)
            fetchAndActivate().addOnCanceledListener {
                Log.d(TAG, "Remote Config Fetch Complete")
            }
        }

        return remoteConfig
    }

    fun getHelloWorldText(): String = remoteConfig.getString(HELLO_WORLD_TEXT)
}