package bee.bee.hoshaapp.base

import android.app.Application
import android.app.Dialog
import android.app.ProgressDialog
import android.content.ContentValues
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import bee.bee.hoshaapp.R
import bee.bee.hoshaapp.databinding.DialogProgressBinding
import bee.bee.hoshaapp.utiles.Preferences
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration

class BaseApplication : Application() {

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        val config: ImageLoaderConfiguration = ImageLoaderConfiguration.Builder(this)
            .build()
        ImageLoader.getInstance().init(config)

        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id))
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
        tokenFireBase()
    }

    /*override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }*/
    private fun tokenFireBase() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task: Task<String> ->
                if (!task.isSuccessful) {
                    Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                    return@addOnCompleteListener
                }

                // Get new FCM registration token
                val fcmid = task.result
                Preferences.prefs.prefsFCMId = fcmid
                Log.d("TAG", "initViewModel: $fcmid")

            }
    }

}