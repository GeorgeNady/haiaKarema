package bee.bee.haiakarema.utiles

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import bee.bee.haiakarema.base.BaseApplication
import com.google.gson.Gson

class Preferences(context: Context) {

    companion object {

        // SHARED PREFERENCES Name
        private const val PREFS_NAME = "hosha"

        // USER DATA
        const val USER_TOKEN_PREF = "token"
        const val USER_ID_PREF = "user_id"
        const val USER_FULL_NAME_PREF = "user_full_name"
        const val USER_NAME_PREF = "user_name"
        const val USER_EMAIL_PREF = "user_email"
        const val USER_PHONE_PREF = "user_phone"
        const val USER_AGE_PREF = "user_age"
        const val USER_GENDER_PREF = "user_status"
        const val USER_IMAGE_PREF = "user_image_url"
        const val USER_BIO_PREF = "user_bio"

        // NETWORK DATA
        const val USERS_PREF = "users"

        // we can use this Singleton object of Prefs and use from anywhere within the app.
        val prefs: Preferences by lazy {
            Preferences(BaseApplication.instance)
        }

    }

    private val gson = Gson()

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var prefsToken: String
        get() = sharedPrefs.getString(USER_TOKEN_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_TOKEN_PREF, value) }

    var prefsUserId: String
        get() = sharedPrefs.getString(USER_ID_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_ID_PREF, value) }

    var prefsUserName: String
        get() = sharedPrefs.getString(USER_NAME_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_NAME_PREF, value) }

    var prefsFullName: String
        get() = sharedPrefs.getString(USER_FULL_NAME_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_FULL_NAME_PREF, value) }

    var prefsUserEmail: String
        get() = sharedPrefs.getString(USER_EMAIL_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_EMAIL_PREF, value) }

    var prefsUserPhone: String
        get() = sharedPrefs.getString(USER_PHONE_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_PHONE_PREF, value) }

    var prefsAge: String
        get() = sharedPrefs.getString(USER_AGE_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_AGE_PREF, value) }

    var prefsGender: String
        get() = sharedPrefs.getString(USER_GENDER_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_GENDER_PREF, value) }

    var prefsUserAvatar: String
        get() = sharedPrefs.getString(USER_IMAGE_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_IMAGE_PREF, value) }

    var prefsBio: String
        get() = sharedPrefs.getString(USER_BIO_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USER_BIO_PREF, value) }

    var prefsUsers: String
        get() = sharedPrefs.getString(USERS_PREF, "") ?: ""
        set(value) = sharedPrefs.edit { putString(USERS_PREF, value) }

    fun saveUsers(store: List<String>) {
        val str = StringBuilder()
        for (i in store.indices) {
            str.append(store[i]).append(",")
        }
        prefs.prefsUsers = str.toString()
    }


    // var myObject: MyObject?
    //     get() {
    //         val jsonString = sharedPrefs.getString(KEY_USER_DATA, null) ?: return null
    //         return gson.fromJson(jsonString, object : TypeToken<MyObject>() {}.type)
    //     }
    //     set(value) = sharedPrefs.edit { putString(KEY_MY_OBJECT, gson.toJson(value)) }


}