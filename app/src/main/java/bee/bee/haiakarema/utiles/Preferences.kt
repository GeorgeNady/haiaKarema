package bee.bee.haiakarema.utiles

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import bee.bee.haiakarema.base.BaseApplication.Companion.application

class Preferences(context: Context) {

    companion object {

        // SHARED PREFERENCES Name
        private const val PREFS_NAME = "haia_karima"

        // USER DATA
        const val USER_TOKEN_PREF = "token"
        const val USER_ID_PREF = "user_id"
        const val USER_FULL_NAME_PREF = "user_full_name"
        const val USER_EMAIL_PREF = "user_email"
        const val USER_PHONE_PREF = "user_phone"

        // we can use this Singleton object of Prefs and use from anywhere within the app.
        val prefs: Preferences by lazy {
            Preferences(application)
        }

    }

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var prefsToken: String
        get() = sharedPrefs.getString(USER_TOKEN_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_TOKEN_PREF, value) }

    var prefsUserId: String
        get() = sharedPrefs.getString(USER_ID_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_ID_PREF, value) }

    var prefsFullName: String
        get() = sharedPrefs.getString(USER_FULL_NAME_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_FULL_NAME_PREF, value) }

    var prefsUserEmail: String
        get() = sharedPrefs.getString(USER_EMAIL_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_EMAIL_PREF, value) }

    var prefsUserPhone: String
        get() = sharedPrefs.getString(USER_PHONE_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_PHONE_PREF, value) }


}