package bee.bee.haiakarema.viewmodel.fragments

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isGone
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bee.bee.haiakarema.model.GetProjects
import bee.bee.haiakarema.model.ItemProject
import bee.bee.haiakarema.network.ApiClient
import bee.bee.haiakarema.network.ApiClient.Companion.api
import bee.bee.haiakarema.utiles.Preferences.Companion.prefs
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ProjectsViewModel(val app: Application) : AndroidViewModel(app) {


    private val TAG = "ProjectsViewModel"
    private lateinit var projectsResponse: MutableLiveData<List<ItemProject>>
    private lateinit var projectResponse: MutableLiveData<ItemProject>

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////// hitting apis
    ////////////////////////////////////////////////////////////////////////////////////////////////

    fun getProjects(progressBar: ProgressBar): LiveData<List<ItemProject>> {
        projectsResponse = MutableLiveData()
        prepareGetProjects(progressBar)
        return projectsResponse
    }

    fun getProject(id: String, progressBar: ProgressBar): LiveData<ItemProject> {
        projectResponse = MutableLiveData()
        prepareGetProject(id, progressBar)
        return projectResponse
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////// preparing for hitting apis
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun prepareGetProjects(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
        val observable: Observable<List<ItemProject>> =
            ApiClient.api.getprojects(prefs.prefsToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        val observer: Observer<List<ItemProject>> =
            object : Observer<List<ItemProject>> {
                override fun onSubscribe(d: @NonNull Disposable?) {

                }

                override fun onNext(project: List<ItemProject>?) {
                    Log.d(TAG, "onNext: $project")
                    projectsResponse.postValue(project)
                    progressBar.visibility = View.GONE

                }

                override fun onError(e: @NonNull Throwable?) {
                    Log.d(TAG, "onError: " + e!!.message)
                    progressBar.visibility = View.GONE
                }

                override fun onComplete() {}
            }
        observable.subscribe(observer)
    }

    private fun prepareGetProject(id: String, progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
        val observable =  api.getproject(id, prefs.prefsToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        val observer = object : Observer<ItemProject> {
                override fun onSubscribe(d: Disposable?) {}

                override fun onNext(project: ItemProject?) {
                    Log.d(TAG, "onNext: $project")
                    projectResponse.postValue(project)
                    progressBar.visibility = View.GONE

                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, "onError: " + e!!.message)
                    progressBar.visibility = View.GONE
                }

                override fun onComplete() {}
            }
        observable.subscribe(observer)
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////// Helper Function
    ////////////////////////////////////////////////////////////////////////////////////////////////
}