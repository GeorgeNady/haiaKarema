package bee.bee.haiakarema.ui.main

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import bee.bee.haiakarema.R
import bee.bee.haiakarema.base.BaseActivity
import bee.bee.haiakarema.databinding.ActivityMainBinding
import bee.bee.haiakarema.ui.auth.AuthActivity
import bee.bee.hoshaapp.viewmodel.MainViewModel
import com.google.gson.Gson


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    ActivityMainBinding::inflate
) {

    companion object {
        lateinit var mainActivity: MainActivity
    }

    override val TAG: String get() = this.javaClass.name
    var isBottomNavHidden = false

    override fun initialization() {
        mainActivity = this
        if (token.isEmpty()) {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun setListener() {

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.

        binding.apply {
            // setupNavigation(bottomNavigation, fragmentContainerView)
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
            val navController = navHostFragment.navController
            bottomNavigation.setupWithNavController(navController)
        }


    }

    override fun onPause() {
        super.onPause()
        pusher.disconnect()
    }

    fun hideBottomNav() = binding.bottomNavigation.animate().setDuration(250)
        .translationYBy(getActionBarH())
        .withEndAction { binding.bottomNavigation.visibility = View.GONE }
        .start()


    fun showBottomNav() = binding.bottomNavigation.animate().setDuration(250)
        .translationYBy(-getActionBarH())
        .withStartAction { binding.bottomNavigation.visibility = View.VISIBLE }
        .start()

    override fun onResumeFragments() {
        super.onResumeFragments()
        val bundle = intent.extras
        val gson = Gson()

        if (bundle != null) {
            val fromUser = bundle.getString("fromUser")
            val payload = bundle.getString("payload")
            val type = bundle.getString("type")
            val payloadNotification: Payload = gson.fromJson(
                payload,
                Payload::class.java
            )
            val fromUserNotification: From = gson.fromJson(
                fromUser,
                From::class.java
            )

            handlingNotificationBackground(
                type,
                fromUserNotification.id,
                payloadNotification.clashId
            )

        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handlingNotificationForGround(intent)
    }

    private fun navigateHandler(res: Int, bundle: Bundle? = null, hideBtmNav: Boolean? = null) {
        binding.mainNavHostFragment.findNavController()
            .navigate(res, bundle)

        if (hideBtmNav != null) if (hideBtmNav) hideBottomNav()

    }

    private fun getActionBarH(): Float {
        val tv = TypedValue()
        var actionBarHeight = 0
        if (theme.resolveAttribute(attr.actionBarSize, tv, true)) {
            actionBarHeight =
                TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
        }
        return actionBarHeight.toFloat()
    }

    private fun handlingNotificationForGround(intent: Intent?) {
        val bundle = intent!!.extras
        if (bundle != null) {
            val notificationResponse =
                bundle.getSerializable("notification") as FCMNotificationResponse
            val gson = Gson()

            val payload = gson.fromJson(notificationResponse.payload, Payload::class.java)
            val from = gson.fromJson(
                notificationResponse.from,
                From::class.java
            )
            val userIdBundle = Bundle().apply {
                putString("userId", from.id)
            }
            val clashIdBundle = Bundle().apply {
                putString("clashId", payload.clashId)

            }
            when (notificationResponse.type) {
                "follow" -> navigateHandler(R.id.usersProfileFragment, userIdBundle, true)

                "challenge" -> navigateHandler(R.id.clashFragment, clashIdBundle, true)

                "comment" -> navigateHandler(R.id.clashFragment, clashIdBundle, true)

                "replay" -> navigateHandler(R.id.clashFragment, clashIdBundle, true)

                "react-comment" -> navigateHandler(R.id.clashFragment, clashIdBundle, true)

                "react-reply" -> navigateHandler(R.id.clashFragment, clashIdBundle, true)

                "follow-request" -> navigateHandler(R.id.myProfileFragment, userIdBundle)

                "accept-follow-request" -> navigateHandler(
                    R.id.usersProfileFragment,
                    userIdBundle
                )

                "result-changed" -> navigateHandler(R.id.clashFragment, clashIdBundle, true)

                "hosha-ended" -> Log.d(TAG, "onNewIntent: ${notificationResponse.type}")

            }

        }
    }

    private fun handlingNotificationBackground(type: String?, fromId: String, clashId: String) {

        val userIdBundle = Bundle().apply {
            putString("userId", fromId)
        }
        val clashIdBundle = Bundle().apply {
            putString("clashId", clashId)

        }
        when (type) {
            "follow" -> navigateHandler(R.id.usersProfileFragment, userIdBundle, true)

            "challenge" -> navigateHandler(R.id.clashFragment, clashIdBundle, true)

            "comment" -> navigateHandler(R.id.clashFragment, clashIdBundle, true)

            "replay" -> navigateHandler(R.id.clashFragment, clashIdBundle, true)

            "react-comment" -> navigateHandler(R.id.clashFragment, clashIdBundle, true)

            "react-reply" -> navigateHandler(R.id.clashFragment, clashIdBundle, true)

            "follow-request" -> navigateHandler(R.id.myProfileFragment, userIdBundle)

            "accept-follow-request" -> navigateHandler(
                R.id.usersProfileFragment,
                userIdBundle
            )

            "result-changed" -> navigateHandler(R.id.clashFragment, clashIdBundle, true)

            "hosha-ended" -> Log.d(TAG, "onNewIntent: $type")

        }
    }
}