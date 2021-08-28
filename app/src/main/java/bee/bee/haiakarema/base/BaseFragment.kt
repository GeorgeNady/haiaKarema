package bee.bee.haiakarema.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import bee.bee.haiakarema.R
import bee.bee.haiakarema.ui.auth.AuthActivity
import bee.bee.haiakarema.ui.auth.AuthActivity.Companion.authActivity
import bee.bee.haiakarema.ui.main.MainActivity
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.volokh.danylo.hashtaghelper.HashTagHelper


abstract class BaseFragment<T : ViewDataBinding?> : Fragment() {

    abstract val TAG: String

    private var contentId = 0
    var bundle: Bundle? = null
    var a: Activity? = null
    var binding: T? = null
    var progressDialog: ProgressDialog? = null
    private var hashTagHelper: HashTagHelper? = null
    private var mAlertDialog: AlertDialog? = null
    lateinit var mActivity: MainActivity
    val options = RequestOptions().centerCrop().priority(Priority.HIGH)

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (contentId == 0) {
            bundle = arguments
            contentId = ActivityFragmentAnnoationManager.check(this)
            a = activity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, contentId, container, false)
        }
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setListener()
    }

    val navOptions = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    protected abstract fun initViewModel() // TODO : add viewModel declaration
    protected abstract fun setListener() // TODO : Logic here


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////// {SNACK_BAR} ///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    fun showSnackBar(view: View, message: String) =
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()

    fun showSnackBar(context: Context, view: View, message: String) =
        Snackbar.make(context, view, message, Snackbar.LENGTH_LONG).show()

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////// {KEY_BOARD} ///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    fun Fragment.hideKeyboard() = view?.let { activity?.hideKeyboard(it) }
    fun Activity.hideKeyboard() = hideKeyboard(currentFocus ?: View(this))
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////// {NAVIGATION} //////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    fun View.navigateTo(fragment: Fragment, res: Int, args: Bundle? = null, hideNav:Boolean? = false) = this.setOnClickListener {
        fragment.navigateTo(res, args, navOptions)
        hideKeyboard()
    }
    fun View.popBack(fragment: Fragment) = this.setOnClickListener { fragment.goBack() }

    private fun Fragment.goBack() {
        hideKeyboard()
        findNavController().popBackStack()
    }
    private fun Fragment.navigateTo(res: Int, args: Bundle?, navOptions: NavOptions) {
        findNavController().navigate(res, args, navOptions)
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////// {DIALOG} ////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    fun dialog(context: Context, message: String?) {
        if (mAlertDialog == null) {
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.apply {
                setMessage(message)
                setCancelable(false)
                setTitle("Hint!")
                setPositiveButton("Ok") { _, _ ->

                }
                setNegativeButton("Sign In") { _, _ ->
                    val intent = Intent(context, AuthActivity::class.java)
                    context.startActivity(intent)
                }
            }

            mAlertDialog = alertDialogBuilder.create()
        }
        mAlertDialog!!.show()
    }

    fun goToMainActivity(millis:Long) {
        val intent = Intent(authActivity, MainActivity::class.java)
        authActivity.apply {
            Thread.sleep(millis)
            startActivity(intent)
            finish()
        }
    }



}