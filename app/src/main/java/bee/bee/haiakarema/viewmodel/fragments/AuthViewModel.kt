package bee.bee.haiakarema.viewmodel.fragments

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bee.bee.haiakarema.model.Login
import bee.bee.haiakarema.model.LoginResponse
import bee.bee.haiakarema.utiles.Preferences.Companion.prefs
import bee.bee.haiakarema.network.ApiClient.Companion.api
import dmax.dialog.SpotsDialog
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AuthViewModel(val app: Application) : AndroidViewModel(app) {

    private val TAG = "AuthViewModel"

    lateinit var loginResponseLiveData: MutableLiveData<LoginResponse>

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////// hitting apis
    ////////////////////////////////////////////////////////////////////////////////////////////////
    fun login(loginRequest: Login, context: Context): LiveData<LoginResponse> {
        loginResponseLiveData = MutableLiveData<LoginResponse>()
        prepareLoginResponse(loginRequest, context)
        return loginResponseLiveData
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////// preparing for hitting apis
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun prepareLoginResponse(loginRequest: Login, context: Context) {
        val dialog = SpotsDialog.Builder().setContext(context)
            .setMessage("Loading....")
            .setCancelable(false).build().apply { show() }

        val loginResponseObservable = api.login(loginRequest)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())

        val loginResponseObserver = object : Observer<LoginResponse> {
            override fun onSubscribe(d: Disposable?) {}
            override fun onNext(authResponse: LoginResponse?) {
                Log.i(TAG, "onNext: ${authResponse?.toString()}")
                loginResponseLiveData.value = authResponse
                prefs.apply {
                    prefsToken = authResponse!!.message
                }
                Toast.makeText(app, authResponse?.message, Toast.LENGTH_LONG).show()
            }

            override fun onError(e: Throwable?) {
                dialog.dismiss()
                Toast.makeText(app, e?.message, Toast.LENGTH_LONG).show()
            }

            override fun onComplete() {
                dialog.dismiss()
            }
        }
        loginResponseObservable.subscribe(loginResponseObserver)
    }
}